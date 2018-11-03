package net.softengine.util;

import org.apache.commons.collections.MapUtils;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.
 * <p>
 * Original author: Khomeni
 * Date: 21/11/2017 1:56 PM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 21/11/2017: 21/11/2017 1:56 PM
 * Current revision: 1.0.0: 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

public class ActionResult {
    private boolean success;

    private String msg;

    private Object dataObject;

    private Exception exception;

    private Map<String, String> errorMap = new HashMap<String, String>();

    private Map<String, Object> map = new HashMap<String, Object>();

    public Map<String, Object> getModelMap() {
        this.map.put("message", this.msg);
        this.map.put("success", this.success);
        return this.map;
    }

    public boolean hasMapValue() {
        return map != null && map.size() > 0;
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return this.map.get(key);
    }

    public void putError(String fieldName, String message) {
        this.errorMap.put(fieldName, message);
    }

    public String getError(String fieldName) {
        return this.errorMap.get(fieldName);
    }

    public boolean hasError() {
        return !MapUtils.isEmpty(this.errorMap);
    }

    public void copyError(BindingResult result) {
        for (String key : this.errorMap.keySet()) {
            GValidator.rejectValue(result, key, this.errorMap.get(key));
        }
        // this.errorMap.forEach((k, v) -> GValidator.rejectValue(result, k, v));
    }

    public ActionResult returnError(String fieldName, String message) {
        this.errorMap.put(fieldName, message);
        this.success = false;
        return this;
    }

    public ActionResult returnError(ActionResult result) {
        ActionResult actionResult = new ActionResult(false);
        actionResult.setErrorMap(result.getErrorMap());
        return actionResult;
    }


    public Object getDataObject() {
        return this.dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
        map.put("message", msg);
    }

    public ActionResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public ActionResult(Boolean bool) {
        this.success = bool;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
}
