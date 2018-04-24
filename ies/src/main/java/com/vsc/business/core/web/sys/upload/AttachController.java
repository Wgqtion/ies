package com.vsc.business.core.web.sys.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.entity.sys.upload.Attach;
import com.vsc.business.core.service.sys.upload.AttachService;
import com.vsc.business.core.web.BaseController;
import com.vsc.constants.Constants;

/**
 * 附件类
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + AttachController.PATH)
public class AttachController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(AttachController.class);
	@Autowired
	private AttachService attachService;

	private static final String[] NO_SAFE_SUFFIX = { "exe", "jsp", "bat" };

	public static final String PATH = "attach"; 

	@RequestMapping(value = "up", method = RequestMethod.POST)
	public ModelAndView create(@RequestParam("vfile") MultipartFile file, HttpServletRequest request)
			throws IOException {
		ModelAndView mav = new ModelAndView("upload/uploadDone");
		if (!file.isEmpty()) {
			Attach entity = new Attach();
			entity.setCreateTime(Calendar.getInstance().getTime());
			entity.setFileKey(this.getFileKey());
			entity.setFileSize(file.getSize());
			entity.setName(file.getOriginalFilename());
			entity.setFileType(StringUtils.lowerCase(StringUtils.substringAfterLast(file.getOriginalFilename(), ".")));

			for (int i = 0; i < NO_SAFE_SUFFIX.length; i++) {
				if (StringUtils.indexOf(entity.getFileType(), NO_SAFE_SUFFIX[i]) != -1) {
					mav.addObject("statusCode", 300);
					mav.addObject("message", "文件上传失败");
					return mav;
				}
			}

			entity.setUploadSessionId(request.getSession().getId());
			entity.setUrlPath(Constants.UPLOAD_ROOT_FOLDER + this.getStoragePath());
			entity.setUser(this.getCurrentUser());

			logger.info("上传文件开始：" + file.getOriginalFilename());
			attachService.saveAttach(entity, file.getInputStream(),entity.getDownloadPath());
			logger.info("上传文件结束：" + file.getOriginalFilename());
			mav.addObject("statusCode", 200);
			mav.addObject("message", "文件上传成功");
			mav.addObject("vm", entity);
			return mav;
		}
		mav.addObject("statusCode", 300);
		mav.addObject("message", "文件上传失败");
		return mav;
	}

	//文件下载  
    @RequestMapping("/download/{id}")  
    public void download(@PathVariable("id") Long id,HttpServletRequest request,HttpServletResponse response) throws IOException  
    {  
    	Attach attach=this.attachService.getObjectById(id);
    	String fileName=attach.getName();
    	String path=attach.getDownloadPath();
        File file=new File(path);  
        if(file.exists()){  
            //设置MIME类型  
            response.setContentType("application/octet-stream");              
            //或者为response.setContentType("application/x-msdownload");  
              
            //设置头信息,设置文件下载时的默认文件名，同时解决中文名乱码问题  
            response.addHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes(), "ISO-8859-1"));  
              
            InputStream inputStream=new FileInputStream(file);  
            ServletOutputStream outputStream=response.getOutputStream();  
            byte[] bs=new byte[1024];  
            while((inputStream.read(bs)>0)){  
                outputStream.write(bs);  
            }  
            outputStream.close();  
            inputStream.close();              
        }  
    }

	/**
	 * 获得文件目录
	 * 
	 * 8位随机数
	 * 
	 * @return
	 */
	private String getFileKey() {
		return RandomStringUtils.randomAlphanumeric(8);
	}

	/**
	 * 按当前日期生产路径：/2008_2/5_20/，/年_季/月_日/
	 * 
	 * @return 相对路径
	 */
	private String getStoragePath() {
		StringBuilder sb = new StringBuilder();
		Calendar cal = Calendar.getInstance();
		sb.append(Constants.SPT).append(cal.get(Calendar.YEAR)).append('_').append(cal.get((Calendar.MONTH)) / 3 + 1)
				.append(Constants.SPT).append(cal.get(Calendar.MONTH) + 1).append('_')
				.append(cal.get(Calendar.DAY_OF_MONTH)).append(Constants.SPT);
		return sb.toString();
	}

}
