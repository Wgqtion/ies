package com.vsc.modules.shiro;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.config.Ini;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.vsc.business.core.entity.security.Authority;
import com.vsc.business.core.service.security.AuthorityService;

@Repository
public class ShiroPermissionFactory extends ShiroFilterFactoryBean {  
    
    /**配置中的过滤链*/  
    public static String definitions;  
      
    /**权限service*/  
    @Autowired
	private AuthorityService authorityService;
  
    /** 
     * 从数据库动态读取权限 
     */  
    @Override  
    public void setFilterChainDefinitions(String definitions) {  
        ShiroPermissionFactory.definitions = definitions;  
          
        //数据库动态权限  
        Map<String,Object> filterParams=new HashMap<String, Object>();
        filterParams.put("EQ_resourceType", 3);
        List<Authority> permissions = authorityService.findList(filterParams);
        for(Authority po : permissions){  
            //字符串拼接权限  
        	String url=po.getValue();
        	if(url!=null&&url.indexOf("?")>-1){
        		url=url.substring(0, url.indexOf("?"));
        	}
            definitions = definitions+url+ " = "+"perms["+po.getCode()+"]\n";  
        }  
          
        //从配置文件加载权限配置  
        Ini ini = new Ini();  
        ini.load(definitions);  
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);  
        if (CollectionUtils.isEmpty(section)) {  
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
        }  
          
        //加入权限集合  
        setFilterChainDefinitionMap(section);  
    }  
}