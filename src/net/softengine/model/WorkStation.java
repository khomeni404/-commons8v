package net.softengine.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 16-Jan-18 at 11:36 AM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 16-Jan-18
 * Version : 1.0
 */

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "AD_WORK_STATION_MASTER", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "name", "mnemonic"}))
public class WorkStation extends GenericModel {

    @Id
    @Column(length = 4)
    private String code;

    @Column(length = 100)
    private String name;

    @Column(length = 10)
    private String mnemonic;

    @Column(length = 200)
    private String address;

    private String institute;

    /*@OneToMany(fetch = FetchType.LAZY, mappedBy = "station")
    @Fetch(FetchMode.SELECT)
    private List<Contact> contactList = new ArrayList<Contact>();
*/
    @Override
    public String info() {
        return this.code + ": " + this.mnemonic +" | "+ this.name + " "+getDiscriminatorValue();
    }

    @Transient
    public String getDiscriminatorValue() {
        DiscriminatorValue val = this.getClass().getAnnotation(DiscriminatorValue.class);
        return val == null ? this.getClass().getSimpleName() : val.value();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }


}
