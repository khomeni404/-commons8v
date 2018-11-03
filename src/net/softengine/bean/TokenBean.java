package net.softengine.bean;

import net.softengine.model.User;
import net.softengine.util.GValidator;
import org.apache.commons.validator.GenericValidator;
import org.springframework.validation.BindingResult;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.
 * <p>
 * Original author: Khomeni
 * Date: 07/12/2017 2:22 PM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 04/12/2017: 07/12/2017 2:22 PM
 * Current revision: 1.0.0: 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

public class TokenBean implements Serializable {
    private Long id;

    @Size(min = 3, max = 20, message = "Username can be minimum 3 character & max 20")
    private String username;

    @Size(min = 3, max = 50, message = "Password can be minimum 3 character & max 50")
    private String password;

    private boolean encrypted;

    private String brCode;

    private User user;

    private Long projectId;

    private String secretKey;

    private String errors;

    public Object toPO() {
        return null;
    }


    public void validateToken(BindingResult result) {

        if (GenericValidator.isBlankOrNull(this.username)) {
            GValidator.rejectValue(result, "username", "Please type your username");
        } else if (this.username.length() < 3 || this.username.length() > 50) {
            GValidator.rejectValue(result, "username", "Only 3-50 Character Username is accepted.");
        }else if(!this.username.matches("^[a-z]([a-z0-9]+)*$")){
            GValidator.rejectValue(result, "username", "Use leading a-z can mix with 0-9 and dot(.) only");
        }

        if (GenericValidator.isBlankOrNull(this.password)) {
            GValidator.rejectValue(result, "password", "Please type your password");
        } else if (this.password.length() < 3 || this.password.length() > 50) {
            GValidator.rejectValue(result, "password", "Only 3-50 Character Password is accepted.");
        }


        /*if(GValidator.isDigits(brCode)) {
            if(brCode.length() != GConstants.BR_CODE_LENGTH) {
                GValidator.rejectValue(result, "brCode", "Required " + GConstants.BR_CODE_LENGTH + " digit Branch Code.");
            }
        } else {
            GValidator.rejectValue(result, "brCode", "Required a numeric Branch Code.");
        }*/

    }

    public void validateStudentToken(BindingResult result) {
        // ValidationUtils.rejectIfEmptyOrWhitespace(result, "username", "username.required", "Please type your username");
        // ValidationUtils.rejectIfEmptyOrWhitespace(result, "password", "password.required", "Please type your password.");

    }


    public String info() {
        return null;
    }


    // =============================


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBrCode() {
        return brCode;
    }

    public void setBrCode(String brCode) {
        this.brCode = brCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
