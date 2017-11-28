package com.vsc.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * JSON处理包装类
 * 对fashjson进行包装，针对性的简化使用<br/>
 * ============Object to JSON=============<br/>
 * 1、基于 get方法来序列化<br/>
 * 2、注解的使用<br/>
 * @JSONField(//get方法上的注解
 * 	&nbsp;&nbsp;&nbsp;&nbsp; serialize=false 是否需要序列化，默认是true<br/>
 * 	&nbsp;&nbsp;&nbsp;&nbsp; name="aaa" 属性重命名，默认根据javabean的get方法规则<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp; format="yyyy-MM-dd HH:mm:ss" 设定日期显示格式<br/>
 * )<br/>
 * @JSONType(//类上的注解
 * &nbsp;&nbsp;&nbsp;&nbsp; orders={"b","a"} 设置各属性排序<br/>
 * &nbsp;&nbsp;&nbsp;&nbsp; ignores={"c"}) 设置忽略属性<br/>
 * @author Administrator
 *
 */
public class JSONUtil {
	/**
	 * 漂亮的格式显示，会换行缩进等格式化
	 */
	public static final String _Feature_PrettyFormat= "PrettyFormat";
	/**
	 * 值为NULL时也进行序列化，序列化为"null"，默认值为NULL时不进行序列化
	 */
	public static final String _Feature_WriteMapNullValue = "WriteMapNullValue";
	/**
	 * 当List对象为NULL时，序列化为[]。要先设置 _Feature_WriteMapNullValue
	 */
	public static final String _Feature_WriteNullListAsEmpty = "WriteNullListAsEmpty";
	/**
	 * 当String对象为NULL时，序列化为""。要先设置 _Feature_WriteMapNullValue
	 */
	public static final String _Feature_WriteNullStringAsEmpty = "WriteNullStringAsEmpty";
	/**
	 * 当Number对象为NULL时，序列化为0。要先设置 _Feature_WriteMapNullValue
	 */
	public static final String _Feature_WriteNullNumberAsZero = "WriteNullNumberAsZero";
	/**
	 * 当Boolean对象为NULL时，序列化为false。要先设置 _Feature_WriteMapNullValue
	 */
	public static final String _Feature_WriteNullBooleanAsFalse = "WriteNullBooleanAsFalse";
	/**
	 * DisableCircularReferenceDetect来禁止循环引用检测：
	 */
	public static final String _Feature_DisableCircularReferenceDetect="DisableCircularReferenceDetect";
	
	private static Map<String,SerializerFeature> _Feature_Map = new HashMap<String,SerializerFeature>();
	static{
		_Feature_Map.put(_Feature_PrettyFormat, SerializerFeature.PrettyFormat);
		_Feature_Map.put(_Feature_WriteMapNullValue, SerializerFeature.WriteMapNullValue);
		_Feature_Map.put(_Feature_WriteNullListAsEmpty, SerializerFeature.WriteNullListAsEmpty);
		_Feature_Map.put(_Feature_WriteNullStringAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
		_Feature_Map.put(_Feature_WriteNullNumberAsZero, SerializerFeature.WriteNullNumberAsZero);
		_Feature_Map.put(_Feature_WriteNullBooleanAsFalse, SerializerFeature.WriteNullBooleanAsFalse);
		_Feature_Map.put(_Feature_DisableCircularReferenceDetect, SerializerFeature.DisableCircularReferenceDetect);
	}
	/**
	 * 将对象转换为json字符串 <br/>
	 * 过滤及格式等都由object类上的注解来定义
	 * @param object 要转换的对象
	 * @return
	 */
	public static String toJSONString(Object object){
		return JSONUtil.toJSONString(object, null,true, null);
	}
	public static String toJSONStringWithDateFormat(Object object,String dateFormat){
		return JSONUtil.toJSONStringWithDateFormat(object, null,true, null,dateFormat);
	}
	/**
	 * 将对象转换为json字符串 <br/>
	 * 可以自定义过滤属性(在注解基础上的过滤)
	 * @param object 要转换的对象
	 * @param filterNames 要过滤或转换的属性名
	 * @param isIgnore 是过滤还是转换
	 * @return
	 */
	public static String toJSONString(Object object,String[] filterNames,boolean isIgnore){
		return JSONUtil.toJSONString(object, filterNames,isIgnore, null);
	}
	public static String toJSONStringWithDateFormat(Object object,String[] filterNames,boolean isIgnore,String dateFormat){
		return JSONUtil.toJSONStringWithDateFormat(object, filterNames,isIgnore, null,dateFormat);
	}
	/**
	 * 将对象转换为json字符串 <br/>
	 * 可以自定义转换特征
	 * @param object 要转换的对象
	 * @param featureNames 转换特征名，只支持：<br>
	 *  JSONUtil._Feature_PrettyFormat <br>
	 *  JSONUtil._Feature_WriteMapNullValue <br>
	 *  JSONUtil._Feature_WriteNullListAsEmpty <br>
	 *  JSONUtil._Feature_WriteNullStringAsEmpty <br>
	 *  JSONUtil._Feature_WriteNullNumberAsZero <br>
	 *  JSONUtil._Feature_WriteNullBooleanAsFalse <br>
	 * @return
	 */
	public static String toJSONString(Object object,String[] featureNames){
		return JSONUtil.toJSONString(object, null,true, featureNames);
	}
	public static String toJSONStringWithDateFormat(Object object,String[] featureNames,String dateFormat){
		return JSONUtil.toJSONStringWithDateFormat(object, null,true, featureNames,dateFormat);
	}
	/**
	 * 将对象转换为json字符串 <br/>
	 *  可以自定义过滤属性(在注解基础上的过滤)
	 *  可以自定义转换特征
	 * @param object 要转换的对象
	 * @param filterNames 要过滤或转换的属性名
	 * @param isIgnore 是过滤还是转换
	 * @param featureNames 转换特征名，只支持：<br>
	 *  JSONUtil._Feature_PrettyFormat <br>
	 *  JSONUtil._Feature_WriteMapNullValue <br>
	 *  JSONUtil._Feature_WriteNullListAsEmpty <br>
	 *  JSONUtil._Feature_WriteNullStringAsEmpty <br>
	 *  JSONUtil._Feature_WriteNullNumberAsZero <br>
	 *  JSONUtil._Feature_WriteNullBooleanAsFalse <br>
	 * @return
	 */
	public static String toJSONString(Object object,String[] filterNames,boolean isIgnore,String[] featureNames){
		return JSONUtil.toJSONStringWithDateFormat(object, filterNames,isIgnore, featureNames,null);
	}
	public static String toJSONStringWithDateFormat(Object object,String[] filterNames,boolean isIgnore,String[] featureNames,String dateFormat){
		SerializeFilter[] filters = createSerializeFilters(filterNames,isIgnore);
		SerializerFeature[] features = dealSerializerFeatures(featureNames);
		return JSONUtil.toJSONString(object, filters, features,dateFormat);
	}
	
	private static String toJSONString(Object object, SerializeFilter[] filters, SerializerFeature[] features,String dateFormat){
		 SerializeWriter out = new SerializeWriter();
        try {
            JSONSerializer serializer = new JSONSerializer(out);
            for(com.alibaba.fastjson.serializer.SerializeFilter filter : filters){
            	if (filter instanceof PropertyPreFilter) {
            		serializer.getPropertyPreFilters().add((PropertyPreFilter) filter);
            	}
	
            	if (filter instanceof NameFilter) {
            		serializer.getNameFilters().add((NameFilter) filter);
            	}
	
            	if (filter instanceof ValueFilter) {
            		serializer.getValueFilters().add((ValueFilter) filter);
            	}
	
            	if (filter instanceof PropertyFilter) {
            		serializer.getPropertyFilters().add((PropertyFilter) filter);
            	}
            }
            
            for (com.alibaba.fastjson.serializer.SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            if (dateFormat != null) {
                serializer.setDateFormat(dateFormat);
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
	}
	
	private static SerializeFilter[] createSerializeFilters(String[] filterNames,boolean isIgnore){
		if(filterNames != null && filterNames.length > 0){
			SerializeFilter[] filters = new SerializeFilter[1];
			filters[0] = new MyPropertyFilter(filterNames,isIgnore);
			return filters;
		}else{
			return new SerializeFilter[0];
		}
	}
	private static SerializerFeature[] dealSerializerFeatures(String[] featureNames){
		if(featureNames != null){
			List<SerializerFeature> templist = new ArrayList<SerializerFeature>();
			SerializerFeature feature = null;
			for(String featureName : featureNames){
				if(featureName != null){
					feature = _Feature_Map.get(featureName);
					if(feature != null){
						templist.add(feature);
					}
				}
			}
			return templist.toArray(new SerializerFeature[0]);
		}else{
			return new SerializerFeature[0];
		}
	}
}

class MyPropertyFilter implements PropertyFilter{
	private boolean isIgnore;
	private List<String> filterNameList = new ArrayList<String>();
	
	public MyPropertyFilter(String[] filterNames,boolean isIgnore){
		this.isIgnore = isIgnore;
		if(filterNames != null){
			for(String filterName : filterNames){
				if(filterName != null && filterName.length() > 0){
					filterNameList.add(filterName);
				}
			}
		}
	}
	
	@Override
	public boolean apply(Object source, String name, Object value) {
		if(isIgnore){
			return !filterNameList.contains(name);
		}else{
			return filterNameList.contains(name);
		}
	}
	
}
