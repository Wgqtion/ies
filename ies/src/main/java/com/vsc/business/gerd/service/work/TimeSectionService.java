package com.vsc.business.gerd.service.work;

import com.vsc.business.gerd.entity.work.TimeSection;
import com.vsc.business.gerd.repository.work.TimeSectionDao;
import com.vsc.modules.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Athor: 吴广庆
 * @Date: 2018-07-11
 */
@Service
@Transactional
public class TimeSectionService extends BaseService<TimeSection> {

    @Autowired
    private TimeSectionDao timeSectionDao;
    @Override
    public PagingAndSortingRepository<TimeSection, Long> getPagingAndSortingRepositoryDao() {
        return this.timeSectionDao;
    }

    @Override
    public JpaSpecificationExecutor<TimeSection> getJpaSpecificationExecutorDao() {
        return this.timeSectionDao;
    }
}
