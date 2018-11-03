package net.softengine.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.softengine.bean.SessionStaffBean;
import net.softengine.bean.SessionUserBean;

import java.util.Map;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 29-Jan-18 at 4:52 PM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 29-Jan-18
 * Version : 1.0
 */

public class Test {
    public static void main(String[] args) throws Exception {
        ActionResult result = SecurityUtil.getStaticSesaaResponse();
        Map<String, Object> resultMap = result.getMap();
        Gson gson = new Gson();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> sessionUserMap = (Map<String, Object>) result.get(SecurityConstants.SESSION_USER);
            JsonElement jsonElement = gson.toJsonTree(sessionUserMap);
            Class<?> clazz = Class.forName((SessionUserBean.class.getPackage() + ".Session" + result.getDataObject()).replace("package ", ""));
            SessionUserBean sessionUserBean = (SessionUserBean) gson.fromJson(jsonElement, clazz);

            sessionUserBean.setEmployeeId(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_EID)));
            sessionUserBean.setNickName(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_N_NAME)));
//            sessionUserBean.setBrCode(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_WSC)));
//            sessionUserBean.setBrName(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_WSN)));

            sessionUserBean.setAvatarPath(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_AVATAR_PATH)));
            sessionUserBean.setSignaturePath(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_SIGNATURE_PATH)));


            System.out.println(sessionUserBean instanceof SessionStaffBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
