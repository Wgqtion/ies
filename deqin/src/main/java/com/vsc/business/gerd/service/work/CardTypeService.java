package com.vsc.business.gerd.service.work;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.Campus;
import com.vsc.business.gerd.entity.work.CardType;
import com.vsc.business.gerd.repository.work.CampusDao;
import com.vsc.business.gerd.repository.work.CardTypeDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author jerry
 * 
 */
@Service
@Transactional
public class CardTypeService extends BaseService<CardType> {
	private static Logger logger = LoggerFactory
			.getLogger(CardTypeService.class);

	@Autowired
	private CardTypeDao cardTypeDao;
	@Autowired
	private CampusDao campusDao;

	@Override
	public PagingAndSortingRepository<CardType, Long> getPagingAndSortingRepositoryDao() {
		return this.cardTypeDao;
	}

	@Override
	public JpaSpecificationExecutor<CardType> getJpaSpecificationExecutorDao() {
		return this.cardTypeDao;
	}

	@SuppressWarnings("unchecked")
	public void synchronizationCardType() {
		List<Campus> vl = this.campusDao.findAll();
		Map<String, Campus> campusIdMap = new HashMap<String, Campus>();
		for (Campus campus : vl) {
			campusIdMap.put(String.valueOf(campus.getCampusId()), campus);
		}

		List<CardType> newvl = this.ibatisQueryDao.getSqlMapClientTemplate()
				.queryForList("find.t_card_type.new");
		Date now = CoreUtils.nowtime();
		for (CardType vm : newvl) {
			
			if (null != vm.getLasttime()) {
				vm.setLasttime(vm.getLasttime() * 1000);
			}

			if (null != vm.getXmIds()) {
				String[] xmIds = vm.getXmIds().split(",");
				for (String xmId : xmIds) {
					Campus campus = campusIdMap.get(xmId);
					if (campus != null) {
						vm.getCampusList().add(campus);
					}
				}
			}
			
			this.cardTypeDao.save(vm);
		}
		List<CardType> updatevl = this.ibatisQueryDao.getSqlMapClientTemplate()
				.queryForList("find.t_card_type.update");
		for (CardType vm : updatevl) {

			vm.setLasttime(now.getTime());

			String[] xmIds = vm.getXmIds().split(",");
			for (String xmId : xmIds) {
				Campus campus = campusIdMap.get(xmId);
				if (campus != null) {
					vm.getCampusList().add(campus);
				}
			}
			this.cardTypeDao.save(vm);
		}
	}

}
