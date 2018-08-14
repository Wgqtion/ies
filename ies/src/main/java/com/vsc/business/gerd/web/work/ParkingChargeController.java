package com.vsc.business.gerd.web.work;

import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.ChargeBinding;
import com.vsc.business.gerd.entity.work.ChargesSettings;
import com.vsc.business.gerd.entity.work.ParkingLot;
import com.vsc.business.gerd.service.work.ChargeBindingService;
import com.vsc.business.gerd.service.work.ChargesSettingsService;
import com.vsc.business.gerd.service.work.ParkingLotService;
import com.vsc.constants.Constants;
import com.vsc.modules.shiro.ShiroUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2018-07-11
 */
@Controller
@RequestMapping(value = Constants.SPT + ParkingChargeController.PATH)
public class ParkingChargeController extends BaseController {
    public static final String PATH = "/work/parkingcharge";
    public static final String PATH_LIST = PATH + Constants.SPT + "list";
    public static final String PATH_EDIT = PATH + Constants.SPT + "edit";

    @Autowired
    private ChargeBindingService chargeBindingService;

    @Autowired
    private ParkingLotService parkingLotService;

    @Autowired
    private ChargesSettingsService chargesSettingsService;


    /**
     * 初始化/排序
     *
     * @param model
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "")
    public String tree(Model model, HttpServletRequest request) throws Exception {
        PageRequest pageRequest = this.getPageRequest();
        Map<String, Object> searchParams = this.getSearchRequest();
        Page<ParkingLot> page = this.parkingLotService.findPage(searchParams, pageRequest);
        model.addAttribute("page", page);
        return PATH_LIST;
    }

    /**
     * 跳转到新增区段页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
    public String createForm(Model model) {
        ChargeBinding chargeBinding = new ChargeBinding();
        model.addAttribute("vm", chargeBinding);
        model.addAttribute("action", BaseController.CREATE);
        return PATH_EDIT;
    }

    /**
     * 创建区段
     *
     * @param chargeBinding
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
    public ModelAndView create(@Valid ChargeBinding chargeBinding,
                               @RequestParam(value = "parkingLot.id", required = false) Long parkingLotId,
                               @RequestParam(value = "chargesSettings.id", required = false) Long chargesSettingsId) throws Exception {
        if (parkingLotId == null) {
            chargeBinding.setParkingLot(null);
        } else {
            ParkingLot parkingLot = parkingLotService.getObjectById(parkingLotId);
            Map<String, Object> filterParams = new HashMap<>();
            filterParams.put("EQ_parkingLot.id", parkingLot.getCode());
            chargeBinding.setId(chargeBindingService.find(filterParams).getId());
            chargeBinding.setParkingLot(parkingLot);
        }
        if (chargesSettingsId != null){
            ChargesSettings chargesSettings = chargesSettingsService.getObjectById(chargesSettingsId);
            chargeBinding.setChargesSettings(chargesSettings);
        }
        // 设置创建时间
        chargeBinding.setCreateDate(new Date());
        // 获取登录用户
        User user = ShiroUserUtils.GetCurrentUser();
        chargeBinding.setCreateUser(user);
        chargeBindingService.save(chargeBinding);
        return this.ajaxDoneSuccess("创建成功");
    }*/

    /**
     * 跳转到修改界面
     *
     * @param id
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model model) throws Exception {
        ParkingLot parkingLot = parkingLotService.getObjectById(id);
        ChargeBinding chargeBinding = chargeBindingService.findUniqueBy("parkingLot.code",parkingLot.getCode());
        if(chargeBinding==null){
            chargeBinding = new ChargeBinding();
            chargeBinding.setParkingLot(parkingLot);
        }
        List<ChargesSettings> chargesSettingsList = chargesSettingsService.getAllList();
        model.addAttribute("vm", chargeBinding);
        model.addAttribute("chargesSettingsList",chargesSettingsList);
        model.addAttribute("action", BaseController.UPDATE);
        return PATH_EDIT;
    }


    /**
     * 确定修改操作
     *
     * @param chargeBinding
     * @return
     * @throws Exception
     */
    @RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
    public ModelAndView update(@Valid ChargeBinding chargeBinding,
                               @RequestParam(value = "parkingLotId", required = false) Long parkingLotId,
                               @RequestParam(value = "chargesSettingsId", required = false) Long chargesSettingsId) throws Exception {
        if (parkingLotId == null) {
            chargeBinding.setParkingLot(null);
        } else {
            ParkingLot parkingLot = parkingLotService.getObjectById(parkingLotId);
            Map<String, Object> filterParams = new HashMap<>();
            filterParams.put("EQ_parkingLot.code", parkingLot.getCode());
            chargeBinding.setId(chargeBindingService.find(filterParams).getId());
            chargeBinding.setParkingLot(parkingLot);
        }
        if (chargesSettingsId != null){
            ChargesSettings chargesSettings = chargesSettingsService.getObjectById(chargesSettingsId);
            chargeBinding.setChargesSettings(chargesSettings);
        }
        // 设置创建时间
        chargeBinding.setCreateDate(new Date());
        chargeBinding.setUpdateDate(new Date());
        // 获取登录用户
        User user = ShiroUserUtils.GetCurrentUser();
        chargeBinding.setCreateUser(user);
        chargeBinding.setUpdateUser(user);
        chargeBindingService.save(chargeBinding);
        return this.ajaxDoneSuccess("修改成功");
    }

    /**
     * 删除区段
     *
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = BaseController.DELETE + "/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) throws Exception {
        chargeBindingService.deleteById(id);
        return this.ajaxDoneSuccess("删除成功");
    }

    /**
     * 批量删除所选区段
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
    public ModelAndView deleteBatch(@RequestParam Long[] ids) throws Exception {
        chargeBindingService.deleteByIds(ids);
        return this.ajaxDoneSuccess("删除成功");
    }
}
