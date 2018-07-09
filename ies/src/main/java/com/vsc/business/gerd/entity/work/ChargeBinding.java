package com.vsc.business.gerd.entity.work;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

import javax.persistence.*;

/**
 * 收费规则关联类
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "charge_binding")
public class ChargeBinding extends BasicEntity {

    private ParkingLot parkingLot;
    private AppointmentSettings appointmentSettings;
    private ChargesSettings chargesSettings;
    private String description;

    /**
     * 关联停车片区
     *
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "PARKING_LOT_CODE",referencedColumnName="CODE")
    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public void setParkingLot(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    /**
     * 停车收费设置
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "CHARGES_SETTINGS_ID",referencedColumnName="ID")
    public ChargesSettings getChargesSettings() {
        return chargesSettings;
    }

    public void setChargesSettings(ChargesSettings chargesSettings) {
        this.chargesSettings = chargesSettings;
    }

    /**
     * 预约设置
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "APPOINTMENT_SETTINGS_ID",referencedColumnName="ID")
    public AppointmentSettings getAppointmentSettings() {
        return appointmentSettings;
    }

    public void setAppointmentSettings(AppointmentSettings appointmentSettings) {
        this.appointmentSettings = appointmentSettings;
    }
    /**
     * 描述
     * @return
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
