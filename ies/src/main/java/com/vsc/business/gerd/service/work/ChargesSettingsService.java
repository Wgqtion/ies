package com.vsc.business.gerd.service.work;

import com.vsc.business.gerd.entity.work.ChargesSettings;
import com.vsc.business.gerd.repository.work.ChargesSettingsDao;
import com.vsc.modules.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Athor: 吴广庆
 * @Date: 2018-06-22
 */
@Service
@Transactional
public class ChargesSettingsService extends BaseService<ChargesSettings> {

    @Autowired
    private ChargesSettingsDao chargesSettingsDao;

    @Override
    public PagingAndSortingRepository<ChargesSettings, Long> getPagingAndSortingRepositoryDao() {
        return this.chargesSettingsDao;
    }

    @Override
    public JpaSpecificationExecutor<ChargesSettings> getJpaSpecificationExecutorDao() {
        return this.chargesSettingsDao;
    }

//    public List<ChargesSection> getChargesSetion(){
//        chargesSectionDao.f
//        return ;
//    }
}
