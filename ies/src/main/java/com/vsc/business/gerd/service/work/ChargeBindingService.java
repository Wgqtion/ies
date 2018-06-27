package com.vsc.business.gerd.service.work;

import com.vsc.business.gerd.entity.work.ChargeBinding;
import com.vsc.business.gerd.repository.work.ChargeBindingDao;
import com.vsc.modules.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Athor: 吴广庆
 * @Date: 2018-06-14
 */
@Service
@Transactional
public class ChargeBindingService extends BaseService<ChargeBinding> {

    @Autowired
    private ChargeBindingDao chargeBindingDao;
    @Override
    public PagingAndSortingRepository<ChargeBinding, Long> getPagingAndSortingRepositoryDao() {
        return this.chargeBindingDao;
    }

    @Override
    public JpaSpecificationExecutor<ChargeBinding> getJpaSpecificationExecutorDao() {
        return this.chargeBindingDao;
    }
}
