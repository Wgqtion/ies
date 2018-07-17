package com.vsc.business.gerd.web.work;

import com.google.common.collect.Maps;
import com.vsc.business.core.entity.security.User;
import com.vsc.business.core.web.BaseController;
import com.vsc.business.gerd.entity.work.*;
import com.vsc.business.gerd.service.work.ChargesSettingsService;
import com.vsc.business.gerd.service.work.TimeSectionService;
import com.vsc.constants.Constants;
import com.vsc.modules.shiro.ShiroUserUtils;
import org.apache.commons.lang3.StringUtils;
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
import java.util.List;
import java.util.Map;

/**
 * @Athor: 吴广庆
 * @Date: 2018-07-11
 */
@Controller
@RequestMapping(value = Constants.SPT + ChargeController.PATH)
public class ChargeController extends BaseController {
    public static final String PATH = "/work/charge";
    public static final String PATH_LIST = PATH + Constants.SPT + "list";
    public static final String PATH_EDIT = PATH + Constants.SPT + "edit";
    public static final String PATH_TREE = PATH + Constants.SPT + "tree";
    public static final String PATH_SET = PATH + Constants.SPT + "settings";

    @Autowired
    private ChargesSettingsService chargesSettingsService;

    @Autowired
    private TimeSectionService timeSectionService;


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
        // PageRequest pageRequest = this.getPageRequest();
        Map<String, Object> searchParams = Maps.newHashMap();//树永远固定不接受查询条件
        List<ChargesSettings> vl = this.chargesSettingsService.findList(searchParams);
        model.addAttribute("vl", vl);
        this.list(model, request, vl == null ? null : vl.get(0).getId());
        return PATH_TREE;
    }

    /**
     * 跳转到收费设置界面
     *
     * @param model
     * @param request
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "settings" + "/{id}")
    public String settings(Model model, HttpServletRequest request, @PathVariable("id") Long id) throws Exception {
        // PageRequest pageRequest = this.getPageRequest();
        Map<String, Object> searchParams = Maps.newHashMap();//树永远固定不接受查询条件
        ChargesSettings vl = this.chargesSettingsService.getObjectById(id);
        model.addAttribute("vm", vl);
        model.addAttribute("action", BaseController.UPDATE);
        return PATH_SET;
    }

    /**
     * 修改收费规则参数
     *
     * @param model
     * @param request
     * @param chargesSettings
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "settings/update")
    public ModelAndView settingsUpdate(Model model, HttpServletRequest request, ChargesSettings chargesSettings) throws Exception {
        // 设置更新时间
        chargesSettings.setUpdateDate(new Date());
        // 获取更新用户
        User user = ShiroUserUtils.GetCurrentUser();
        chargesSettings.setUpdateUser(user);
        chargesSettingsService.save(chargesSettings);
        return this.ajaxDoneSuccess("修改成功");
    }


    /**
     * list界面参数设置
     *
     * @param model
     * @param request
     * @param chargesSettingsId
     * @return list界面
     * @throws Exception
     */
    @RequestMapping(value = "list")
    public String list(Model model, HttpServletRequest request, Long chargesSettingsId) throws Exception {

        PageRequest pageRequest = this.getPageRequest();
        Map<String, Object> searchParams = this.getSearchRequest();
        if (chargesSettingsId != null) {
            searchParams.put("EQ_chargesSettings.id", chargesSettingsId);
        } else {
            return PATH_LIST;
        }
        ChargesSettings chargesSettings = chargesSettingsService.getObjectById(chargesSettingsId);
        Page<TimeSection> page = timeSectionService.findPage(searchParams, pageRequest);
        model.addAttribute("page", page);
        model.addAttribute("chargesSettings", chargesSettings);
        return PATH_LIST;
    }

    /**
     * 跳转到新增区段页面
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(value = BaseController.NEW + "/{id}", method = RequestMethod.GET)
    public String createForm(Model model, @PathVariable("id") Long id) {
        TimeSection timeSection = new TimeSection();
        timeSection.setChargesSettings(chargesSettingsService.getObjectById(id));
        model.addAttribute("vm", timeSection);
        model.addAttribute("action", BaseController.CREATE);
        return PATH_EDIT;
    }

    /**
     * 创建区段
     *
     * @param timeSection
     * @return
     * @throws Exception
     */
    @RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
    public ModelAndView create(@Valid TimeSection timeSection) throws Exception {
        // 设置创建时间
        timeSection.setCreateDate(new Date());
        // 获取登录用户
        User user = ShiroUserUtils.GetCurrentUser();
        timeSection.setCreateUser(user);
        timeSectionService.save(timeSection);
        return this.ajaxDoneSuccess("创建成功");
    }

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
        TimeSection timeSection = timeSectionService.getObjectById(id);
        model.addAttribute("vm", timeSection);
        model.addAttribute("action", BaseController.UPDATE);
        return PATH_EDIT;
    }


    /**
     * 确定修改操作
     *
     * @param timeSection
     * @return
     * @throws Exception
     */
    @RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
    public ModelAndView update(@Valid @ModelAttribute("preloadModel") TimeSection timeSection) throws Exception {
        timeSectionService.save(timeSection);
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
        timeSectionService.deleteById(id);
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
        timeSectionService.deleteByIds(ids);
        return this.ajaxDoneSuccess("删除成功");
    }
}
