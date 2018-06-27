package com.vsc.business.gerd.service.work;

import com.vsc.business.gerd.entity.work.ChargesSection;
import com.vsc.business.gerd.repository.work.ChargesSectionDao;
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
public class ChargesSectionService extends BaseService<ChargesSection> {

    @Autowired
    private ChargesSectionDao chargesSectionDao;

    @Override
    public PagingAndSortingRepository<ChargesSection, Long> getPagingAndSortingRepositoryDao() {
        return this.chargesSectionDao;
    }

    @Override
    public JpaSpecificationExecutor<ChargesSection> getJpaSpecificationExecutorDao() {
        return this.chargesSectionDao;
    }

//    public List<ChargesSection> getChargesSetion(){
//        chargesSectionDao.f
//        return ;
//    }
}
