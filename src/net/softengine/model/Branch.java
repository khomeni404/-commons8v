package net.softengine.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.  (www.soft-engine.net)
 * <p>
 * Original author: Khomeni
 * Date: 23/12/2017 10:50 AM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 23/12/2017: 23/12/2017 10:50 AM
 * Current revision: 1.0.0: 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

@Entity
public class Branch extends WorkStation {

    public Branch(){}
    public Branch(String brCode){super.setCode(brCode);}

    @Column(name = "zone", length = 2)
    private  String zoneCode;

    @Column(name = "bb_code", length = 6)
    private  String bbCode;

    @Column(name = "ad_br_code", length = 4)
    private  String adBrCode;

    @Column(name = "is_urban")
    private Integer urban;

    @Override
    public String info() {
        return super.info();
    }

    @Override
    public String toString() {
        return super.getName();
    }


    public String getBrCode() {
        return super.getCode();
    }

    public String getZoneCode() {
        return zoneCode;
    }

    public void setZoneCode(String zoneCode) {
        this.zoneCode = zoneCode;
    }


    public String getBbCode() {
        return bbCode;
    }

    public void setBbCode(String bbCode) {
        this.bbCode = bbCode;
    }

    public String getAdBrCode() {
        return adBrCode;
    }

    public void setAdBrCode(String adBrCode) {
        this.adBrCode = adBrCode;
    }

    public Integer getUrban() {
        return urban;
    }

    public void setUrban(Integer urban) {
        this.urban = urban;
    }
}
