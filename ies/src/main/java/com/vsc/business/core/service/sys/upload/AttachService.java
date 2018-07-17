package com.vsc.business.core.service.sys.upload;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.core.entity.sys.upload.Attach;
import com.vsc.business.core.repository.sys.upload.AttachDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

@Service
@Transactional
public class AttachService extends BaseService<Attach> {
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
	 * @throws Exception 
	 */
	public  Attach saveAttach(Attach entity,InputStream srcfile, String savePath) throws Exception{
		CoreUtils.saveFile(srcfile,savePath);
		return this.save(entity);
	}

}
