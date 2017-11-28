package com.vsc.business.gerd.service.work;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vsc.business.gerd.entity.work.Campus;
import com.vsc.business.gerd.entity.work.CardInfo;
import com.vsc.business.gerd.repository.work.CampusDao;
import com.vsc.business.gerd.repository.work.CardInfoDao;
import com.vsc.business.gerd.repository.work.CardTypeDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.CoreUtils;

/**
 * 
 * @author Administrator
 * 
 */
@Service
@Transactional
public class CardInfoService extends BaseService<CardInfo> {
	private static Logger logger = LoggerFactory
			.getLogger(CardInfoService.class);

	@Autowired
	private CardInfoDao cardInfoDao;
	@Autowired
	private CampusDao campusDao;

	@Override
	public PagingAndSortingRepository<CardInfo, Long> getPagingAndSortingRepositoryDao() {
		return this.cardInfoDao;
	}

	@Override
	public JpaSpecificationExecutor<CardInfo> getJpaSpecificationExecutorDao() {
		return this.cardInfoDao;
	}

	@SuppressWarnings("unchecked")
	public void synchronizationCardInfo() throws ParseException {
		Date now = CoreUtils.nowtime();
		List<Campus> vl = this.campusDao.findAll();
		Map<String, Campus> campusIdMap = new HashMap<String, Campus>();
		for (Campus campus : vl) {
			campusIdMap.put(String.valueOf(campus.getCampusId()), campus);
		}

		List<CardInfo> newvl = this.ibatisQueryDao.getSqlMapClientTemplate()
				.queryForList("find.t_card_infoByOldInfo.new");
		for (CardInfo vm : newvl) {
			
			vm.setLasttime(vm.getLasttime()*1000);
			
			if (StringUtils.isNotBlank(vm.getXmIds())) {
				String[] xmIds = vm.getXmIds().split(",");
				for (String xmId : xmIds) {
					Campus campus = campusIdMap.get(xmId);
					if (campus != null) {
						vm.getCampusList().add(campus);
					}
				}
			}
			this.cardInfoDao.save(vm);
		}

		List<CardInfo> updatevl = this.ibatisQueryDao.getSqlMapClientTemplate()
				.queryForList("find.t_card_infoByOldInfo.update");
		for (CardInfo vm : updatevl) {
			
			vm.setLasttime(now.getTime());
			
			String[] xmIds = vm.getXmIds().split(",");
			for (String xmId : xmIds) {
				Campus campus = campusIdMap.get(xmId);
				if (campus != null) {
					vm.getCampusList().add(campus);
				}
			}
			this.cardInfoDao.save(vm);
		}

		List<CardInfo> newvl2 = this.ibatisQueryDao.getSqlMapClientTemplate()
				.queryForList("find.t_card_infoByOldTemp.new");
		for (CardInfo vm : newvl2) {
			
			vm.setLasttime(now.getTime());
			
			Campus campus = campusIdMap.get(vm.getXmIds());
			if (campus != null) {
				vm.getCampusList().add(campus);
			}
			this.cardInfoDao.save(vm);
		}

		List<CardInfo> updatevl2 = this.ibatisQueryDao
				.getSqlMapClientTemplate().queryForList(
						"find.t_card_infoByOldTemp.update");
		for (CardInfo vm : updatevl2) {
			
			vm.setLasttime(now.getTime());
			
			Campus campus = campusIdMap.get(vm.getXmIds());
			if (campus != null) {
				vm.getCampusList().add(campus);
			}
			this.cardInfoDao.save(vm);
		}

	}
}
