package com.vsc.business.gerd.entity.work;

import com.vsc.constants.Constants;
import com.vsc.modules.entity.BasicEntity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 收费规则与收费时段关联实体类
 *
 * @Athor: 吴广庆
 * @Date: 2018-06-21
 */
@Entity
@Table(name = Constants.TABLE_PREFIX + "charges_section")
public class ChargesSection extends BasicEntity {
    private TimeSection timeSection;
    private ChargeBinding chargeBinding;
    private String description;

    /**
     * 分段计费规则
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "TIME_SECTION_ID",referencedColumnName="ID")
    public TimeSection getTimeSection() {
        return timeSection;
    }

    public void setTimeSection(TimeSection timeSection) {
        this.timeSection = timeSection;
    }

    /**
     * 收费规则
     * @return
     */
    @ManyToOne
    @JoinColumn(name = "CHARGE_BIND_ID",referencedColumnName="ID")
    public ChargeBinding getChargeBinding() {
        return chargeBinding;
    }

    public void setChargeBinding(ChargeBinding chargeBinding) {
        this.chargeBinding = chargeBinding;
    }

    @Basic
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChargesSection that = (ChargesSection) o;
        return id == that.id &&
                chargeBinding == that.chargeBinding &&
                Objects.equals(timeSection, that.timeSection) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, timeSection, chargeBinding, description);
    }
}
