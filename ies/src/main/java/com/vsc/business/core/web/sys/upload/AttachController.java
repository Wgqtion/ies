package com.vsc.business.core.web.sys.upload;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.vsc.util.CoreUtils;

/**
 * 用户管理的Controller 
 * @author 付翀
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
			attachService.saveAttach(entity, file.getInputStream(),
					CoreUtils.getStoragePath(request, entity.getDownloadPath()));
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

	@RequestMapping(value = "download/{id}", method = RequestMethod.GET)
	public String download(@PathVariable("id") Long id, Model model) {
		//实现下载
		//model.addAttribute("vm", userService.getObjectById(id));
		return "";
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
