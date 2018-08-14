package com.vsc.business.gerd.service.work;

import com.vsc.business.gerd.entity.work.ChargeBinding;
import com.vsc.business.gerd.entity.work.ChargesSettings;
import com.vsc.business.gerd.entity.work.WxCore;
import com.vsc.business.gerd.repository.work.ChargeBindingDao;
import com.vsc.modules.service.BaseService;
import com.vsc.util.ChargeHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 收费计算
     *
     * @param startTime
     * @param endTime
     * @param parkingCode
     * @return
     */
    public BigDecimal feeCharge(Date startTime,Date endTime,String parkingCode){
        boolean isFree = false;
        BigDecimal fee = BigDecimal.ZERO;
        try {
            ChargesSettings chargesSettings = selectCountSetting(parkingCode);
            if (chargesSettings != null) {
                fee = BigDecimal.valueOf(ChargeHandle.charge(startTime, endTime, chargesSettings));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return fee;
    }

    public ChargesSettings selectCountSetting(String parkingCode){
        try {
        Map<String, Object> filterParms = new HashMap<>();
        filterParms.put("EQ_parkingLot.code", parkingCode);
        List<ChargeBinding> chargeBindingServiceList = this.findList(filterParms);
            // TODO 缺少收费规则有效性判断
            if (chargeBindingServiceList != null) {
                return chargeBindingServiceList.get(0).getChargesSettings();
            }
        } catch (Exception e) {
            // 缺少异常处理
            e.printStackTrace();
        }
        return null;
    }
}
