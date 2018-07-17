package com.vsc.business.gerd.entity.work;


import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

/**
 * @Athor: 吴广庆
 * @Date: 2018-05-25
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "reserve_settings")
public class ReserveSettings extends BasicEntity {

  private Integer chargeStandard;
  private Double unitPrice;
  private Long priceTime;
  private Long freeTime;
  private Long reserveTime;
  private Double privilegeFee;
  private Double maxFee;
  private long cancelNum;


  /**
   * 收费标准：1按次收费；2按时间计费
   * @return
   */
  @Column(name = "CHARGE_STANDARD")
  public Integer getChargeStandard() {
    return chargeStandard;
  }

  public void setChargeStandard(Integer chargeStandard) {
    this.chargeStandard = chargeStandard;
  }


  /**
   * 单价
   * @return
   */
  @Column(name = "UNIT_PRICE")
  public Double getUnitPrice() {
    return unitPrice;
  }

  public void setUnitPrice(Double unitPrice) {
    this.unitPrice = unitPrice;
  }

  /**
   * 按时收费时间单位
   * @return
   */
  @Column(name = "PRICE_TIME")
  public Long getPriceTime() {
    return priceTime;
  }

  public void setPriceTime(Long priceTime) {
    this.priceTime = priceTime;
  }


  /**
   * 免费取消时间（在预约开始此时间段内取消免费）
   * @return
   */
  @Column(name = "FREE_TIME")
  public Long getFreeTime() {
    return freeTime;
  }

  public void setFreeTime(Long freeTime) {
    this.freeTime = freeTime;
  }


  /**
   * 保留时间
   * @return
   */
  @Column(name = "RESERVE_TIME")
  public Long getReserveTime() {
    return reserveTime;
  }

  public void setReserveTime(Long reserveTime) {
    this.reserveTime = reserveTime;
  }


  /**
   * 优惠减免费用
   * @return
   */
  @Column(name = "PRIVILEGE_FEE")
  public Double getPrivilegeFee() {
    return privilegeFee;
  }

  public void setPrivilegeFee(Double privilegeFee) {
    this.privilegeFee = privilegeFee;
  }


  /**
   * 最高费用
   * @return
   */
  @Column(name = "MAX_FEE")
  public Double getMaxFee() {
    return maxFee;
  }

  public void setMaxFee(Double maxFee) {
    this.maxFee = maxFee;
  }


  /**
   * 预约取消次数
   * @return
   */
  @Column(name = "CANCEL_NUM")
  public long getCancelNum() {
    return cancelNum;
  }

  public void setCancelNum(long cancelNum) {
    this.cancelNum = cancelNum;
  }


}
