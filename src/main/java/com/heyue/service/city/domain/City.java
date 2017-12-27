package com.heyue.service.city.domain;

import com.heyue.service.framework.DomainEntity;

import javax.persistence.*;

/**
 * Created by jessepi on 4/16/16.
 */
@Entity
@Table(name = "t_city")
public class City extends DomainEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "province")
    private String province;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
