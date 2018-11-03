package net.softengine.bean;

import java.util.Date;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 08-Feb-18 at 9:56 AM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 08-Feb-18
 * Version : 1.0
 *
 * Note: Please check SecurityUtil call for usage of the class name
 * @see net.softengine.util.SecurityUtil#setAuthenticationAndAuthorizationToken(net.softengine.util.ActionResult)
 */

public class SessionUserBean {
    private Long id;

    private String employeeId;

    private Boolean active;

    private String name;

    private String nickName;

    private String workStationCode;

    private String workStationAlias;

    private String workStationName;

    private String avatarPath;

    private String signaturePath;

    private Date loggedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getWorkStationCode() {
        return workStationCode;
    }

    public void setWorkStationCode(String workStationCode) {
        this.workStationCode = workStationCode;
    }

    public String getWorkStationAlias() {
        return workStationAlias;
    }

    public void setWorkStationAlias(String workStationAlias) {
        this.workStationAlias = workStationAlias;
    }

    public String getWorkStationName() {
        return workStationName;
    }

    public void setWorkStationName(String workStationName) {
        this.workStationName = workStationName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getSignaturePath() {
        return signaturePath;
    }

    public void setSignaturePath(String signaturePath) {
        this.signaturePath = signaturePath;
    }

    public Date getLoggedAt() {
        return loggedAt;
    }

    public void setLoggedAt(Date loggedAt) {
        this.loggedAt = loggedAt;
    }
}
