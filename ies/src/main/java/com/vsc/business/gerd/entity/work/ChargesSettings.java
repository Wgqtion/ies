package com.vsc.business.gerd.entity.work;


import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

@Entity
@Table(name = Constants.TABLE_PREFIX + "charges_settings")
public class ChargesSettings extends BasicEntity {

  private Integer chargeStandard;
  private Double unitPrice;
  private Time priceTime;
  private Double privilegeFee;
  private Double maxFee;


  /**
   * 收费标准：1按次收费；2按时收费跨段取左；3跨段收费取右；4跨段收费取两段；5跨段收费取最大
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
   */
  @Column(name = "PRICE_TIME")
  public Time getPriceTime() {
    return priceTime;
  }

  public void setPriceTime(Time priceTime) {
    this.priceTime = priceTime;
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



}
