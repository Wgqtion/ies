package com.vsc.business.core.service.sys.upload;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.sys.upload.Attach;
import com.vsc.business.core.repository.sys.upload.AttachDao;
import com.vsc.modules.repository.BaseDao;
import com.vsc.modules.service.BaseService;
import com.vsc.modules.service.ServiceException;

@Service
@Transactional
public class AttachService extends BaseService<Attach> {
	private static Logger logger = LoggerFactory.getLogger(AttachService.class);
	@Autowired
	private AttachDao attachDao;
	
	public List<Attach> findIds(Object[] idsObj) {
		return this.findIds(idsObj, attachDao);
	}
	
	@Override
	public PagingAndSortingRepository<Attach, Long> getPagingAndSortingRepositoryDao() {
		return this.attachDao;
	}

	@Override
	public JpaSpecificationExecutor<Attach> getJpaSpecificationExecutorDao() {
		return this.attachDao;
	}
	/** 
	 * 附件保存，并将附件文件同时保存
	 * @param entity       附件表数据
	 * @param srcfile       原始附件文件
	 * @param savePath  保存的系统文件绝对地址
	 * @return
	 */
	public  Attach saveAttach(Attach entity,InputStream srcfile, String savePath){
		this.saveFile(srcfile,savePath);
		return this.save(entity);
	}
	
	/**
	  * 保存文件
	  * @param srcfile
	  * @param savePath
	  * @return
	  * @throws ServiceException
	  */
		private void saveFile(InputStream srcfile, String savePath) throws ServiceException { 
			try {
			 
				File toSave = new File(savePath);
				FileUtils.copyInputStreamToFile(srcfile, toSave);
			} catch (Exception e) {
				throw new ServiceException("保存上传文件错误", e);
			} 
		}

}
