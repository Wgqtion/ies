package com.vsc.business.core.web.security;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.common.collect.Lists;
import com.vsc.business.core.entity.security.Resource;
import com.vsc.business.core.service.security.ResourceService;
import com.vsc.business.core.web.BaseController;
import com.vsc.modules.shiro.DBDefinitionsService;

@Controller
@RequestMapping(value = "/sys/resource")
public class ResourceController extends BaseController {
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private DBDefinitionsService dbDefinitionsService;
	@Autowired
	private ShiroFilterFactoryBean shiroFilterFactoryBean;

	@RequestMapping(value = "reloadchain")
	public ModelAndView reloadchain() {

		String definitions = dbDefinitionsService.getDefinitions();

		if (StringUtils.isNotEmpty(definitions)) {
			try {
				this.refreshFilterChain(definitions);
				return this.ajaxDoneSuccess("重新加载安全过滤成功");
			} catch (Exception e) {
				return this.ajaxDoneSuccess("重新加载安全过滤失败");
			}
		}
		return this.ajaxDoneSuccess("无法重新加载安全过滤成功");
	}

	private void refreshFilterChain(String definitions) throws Exception {
		AbstractShiroFilter shiroFilter = (AbstractShiroFilter) this.shiroFilterFactoryBean.getObject();
		PathMatchingFilterChainResolver resolver = (PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver();
		if (resolver != null) {
			DefaultFilterChainManager manager = (DefaultFilterChainManager) resolver.getFilterChainManager();
			synchronized (manager) {
				manager.getFilterChains().clear();
				this.shiroFilterFactoryBean.setFilterChainDefinitions(definitions);
				Map<String, String> chains = shiroFilterFactoryBean.getFilterChainDefinitionMap();
				if (!CollectionUtils.isEmpty(chains)) {
					for (Map.Entry<String, String> entry : chains.entrySet()) {
						String url = entry.getKey();
						String chainDefinition = entry.getValue();
						manager.createChain(url, chainDefinition);
					}
				}
			}
		}
	}

	@RequestMapping(value = "reset")
	public ModelAndView reset(ServletRequest request) {
		Map<String, Object> map = RequestContextUtils.getWebApplicationContext(request).getBeansWithAnnotation(
				Controller.class);
		Set<String> mapping = com.google.common.collect.Sets.newLinkedHashSet();
		Lists.newArrayList();
		Collection<Object> list = map.values();
		for (Object controller : list) {
			Class clazz = AnnotationUtils.findAnnotationDeclaringClass(Controller.class, controller.getClass());
			RequestMapping rm = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
			if (rm != null) {
				String[] classRootUrl = rm.value();
				Method[] methods = clazz.getMethods();
				for (int k = 0; k < classRootUrl.length; k++) {
					if (!StringUtils.startsWith(classRootUrl[k], "/")) {
						classRootUrl[k] = "/" + classRootUrl[k];
					}
					if (!StringUtils.endsWith(classRootUrl[k], "/")) {
						classRootUrl[k] = classRootUrl[k] + "/";
					}

					for (int i = 0; i < methods.length; i++) {

						rm = methods[i].getAnnotation(RequestMapping.class);
						if (rm != null) {
							String[] methodUrl = rm.value();
							for (int j = 0; j < methodUrl.length; j++) {
								if (StringUtils.startsWith(methodUrl[j], "/")) {
									methodUrl[j] = methodUrl[j].substring(1);
								}
								methodUrl[j] = classRootUrl[k] + methodUrl[j];
								if (StringUtils.endsWith(methodUrl[j], "/")) {
									methodUrl[j] = methodUrl[j].substring(0, methodUrl[j].length() - 1);
								}
								methodUrl[j] = methodUrl[j].replaceAll("\\{\\w*\\}", "*");
								mapping.add(methodUrl[j]);
							}
						}
					}
				}
			}
		}
		resourceService.resetRequestMappingResource(mapping);

		return this.ajaxDoneSuccess("重置成功");
	}

	@RequestMapping(value = "")
	public String list(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("position", "asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<Resource> page = resourceService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return "sys/resource/list";
	}

	@RequestMapping(value = "select")
	public String select(Model model, ServletRequest request) {
		PageRequest pageRequest = this.getPageRequest("position", "asc");
		Map<String, Object> searchParams = this.getSearchRequest();
		Page<Resource> page = resourceService.findPage(searchParams, pageRequest);
		model.addAttribute("page", page);
		return "sys/resource/select";
	}

	@RequestMapping(value = BaseController.VIEW + "/{id}", method = RequestMethod.GET)
	public String view(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", resourceService.getObjectById(id));
		return "sys/resource/view";
	}

	@RequestMapping(value = BaseController.NEW, method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("vm", new Resource());
		model.addAttribute("action", BaseController.CREATE);
		return "sys/resource/edit";
	}

	@RequestMapping(value = BaseController.CREATE, method = RequestMethod.POST)
	public ModelAndView create(@Valid Resource resource, RedirectAttributes redirectAttributes) {
		resourceService.save(resource);
		return this.ajaxDoneSuccess("创建成功");
	}

	@RequestMapping(value = BaseController.UPDATE + "/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("vm", resourceService.getObjectById(id));
		model.addAttribute("action", BaseController.UPDATE);
		return "sys/resource/edit";
	}

	@RequestMapping(value = BaseController.UPDATE, method = RequestMethod.POST)
	public ModelAndView update(@Valid @ModelAttribute("preloadModel") Resource resource,
			RedirectAttributes redirectAttributes) {
		resourceService.save(resource);
		return this.ajaxDoneSuccess("修改成功");
	}

	@RequestMapping(value = BaseController.DELETE + "/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		resourceService.deleteById(id);
		return this.ajaxDoneSuccess("删除成功");
	}

	@RequestMapping(value = BaseController.DELETE, method = RequestMethod.POST)
	public ModelAndView deleteBatch(@RequestParam Long[] ids) {
		resourceService.deleteByIds(ids);
		return this.ajaxDoneSuccess("删除成功");
	}

	/**
	 *  高级查询界面
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	/*@RequestMapping(value = "search")
	public String search(ServletRequest request) {
		return "sys/resource/search";
	}*/

	@ModelAttribute("preloadModel")
	public Resource getModel(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			return resourceService.getObjectById(id);
		}
		return null;
	}
}
