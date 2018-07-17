package com.vsc.business.gerd.web.work;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ParkingCamera;
import com.vsc.business.gerd.entity.work.ParkingCameraLog;
import com.vsc.business.gerd.entity.work.ParkingGarage;
import com.vsc.business.gerd.service.work.ParkingCameraLogService;
import com.vsc.business.gerd.service.work.ParkingCameraService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;

/**
 * 全视频相机视图
 * @author XiangXiaoLin
 *
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingCameraController.PATH)
public class ParkingCameraController extends BaseController {

	@Autowired
	private ParkingCameraService parkingCameraService;
	
	@Autowired
	private ParkingCameraLogService parkingCameraLogService;
	
	@Autowired
	private ParkingLotService parkingLotService;
	
	public static final String PATH = "work/parkingCamera";
	public static final String PATH_LIST = PATH + Constants.SPT + "list";
	public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
	public static final String PATH_VIEW = PATH + Constants.SPT + "view";

	@RequestMapping(value = "")
	public String list(Model model, HttpServletRequest request) throws Exception {

		PageRequest pageRequest = this.getPageRequest();
		Map<String, Object> searchParams = this.getSearchRequest();
		model.addAttribute("searchCodeParkingCamera", searchParams.get("IN_parkingGarage.parkingLotCode"));
		Page<ParkingCamera> page = parkingCameraService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		model.addAttribute("parkingLotTree",this.parkingLotService.findTree());
		return PATH_LIST;
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new ParkingCamera());
		model.addAttribute("action", BaseController.CREATE);
		return PATH_EDIT;
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid ParkingCamera entity,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId) throws Exception {
		ParkingGarage pg=new ParkingGarage();
		pg.setId(parkingGarageId);
		entity.setParkingGarage(pg);
		parkingCameraService.save(entity);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", parkingCameraService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return PATH_EDIT;
	}

	@RequestMapping(value="picture/{id}", method = RequestMethod.GET)
	public void picture(@PathVariable("id") Long id,HttpServletResponse response) throws IOException {
		ParkingCamera parkingCamera=parkingCameraService.getObjectById(id);
		ParkingCameraLog parkingCameraLog=this.parkingCameraLogService.getMaxBy(parkingCamera.getCode());
		if(parkingCameraLog==null){
			return;
		}
    	String path=parkingCameraLog.getPicturePath();
    	if(path==null){
			return;
		}
        File file=new File(path);   
        if(file.exists()){  
            String fileName = file.getName();
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

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") ParkingCamera entity,
			@RequestParam(value = "parkingGarageGroup.id", required = false) Long parkingGarageId) throws Exception {
		ParkingGarage pg=new ParkingGarage();
		pg.setId(parkingGarageId);
		entity.setParkingGarage(pg);
		parkingCameraService.save(entity);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
		parkingCameraService.deleteUpdateById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) throws Exception {
		parkingCameraService.deleteUpdateByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	@ModelAttribute("preloadModel")
	public ParkingCamera getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return parkingCameraService.getObjectById(id);
		}
		return null;
	}

}
