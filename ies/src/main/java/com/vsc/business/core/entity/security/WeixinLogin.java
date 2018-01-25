///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.vsc.business.core.entity.security;
//
//import com.vsc.modules.entity.IdEntity;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//
///**
// *
// * @author wgqki
// */
//
//@Entity
//@Table(name = "weixinLogin")
//public class WeixinLogin extends IdEntity{
//    
//    private Long weixinId;
//    private User user;
//    private Long cardTypeId;
//    private Long carNumber;
//    private Long tel;
//
//    @Column(name = "CARD_TYPE_ID")
//    public Long getCardTypeId() {
//        return cardTypeId;
//    }
//
//    public void setCardTypeId(Long cardTypeId) {
//        this.cardTypeId = cardTypeId;
//    }
//    @Column(name = "CAR_NUMBER")
//    public Long getCarNumber() {
//        return carNumber;
//    }
//
//    public void setCarNumber(Long carNumber) {
//        this.carNumber = carNumber;
//    }
//    @Column(name = "TEL")
//    public Long getTel() {
//        return tel;
//    }
//
//    public void setTel(Long tel) {
//        this.tel = tel;
//    }
//    
//    @Id
//    @Column(name = "WEIXIN_ID")
//    public Long getWeixinId() {
//        return weixinId;
//    }
//
//    public void setWeixinId(Long weixinId) {
//        this.weixinId = weixinId;
//    }
//
//    @ManyToOne
//    @JoinColumn(name = "USER_ID")
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//}
