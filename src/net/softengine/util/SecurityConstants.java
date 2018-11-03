package net.softengine.util;

import org.apache.commons.lang.BooleanUtils;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.
 * <p>
 * Original author: Khomeni
 * Date: 17/06/2017 1:56 PM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 17/06/2017: 17/06/2017 1:56 PM
 * Current revision: 1.0.0: 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

public interface SecurityConstants {

    boolean ACTIVE_ASPECTS = BooleanUtils.toBoolean(PropertyUtil.getInstance("config").getPropertyValue("ACTIVE_ASPECTS"));

    String SecuritySessionBean_ser_path =  "D:\\cloud\\doc\\taims\\SecuritySessionBean.ser";
    //String SecuritySessionBean_ser_path =  "C:\\Users\\core-khomeni\\Downloads\\taims\\src\\com\\ibbl\\security\\resources\\SecuritySessionBean.ser";

    String SESSION_USER = "LU";
    String SESSION_USER_ID = "LU_id";
    String SESSION_USER_EID = "LU_eid";
    String SESSION_USER_N_NAME = "LU_n_name";

    String SESSION_USER_WSC = "LU_work_station_code";
    String SESSION_USER_WSA = "LU_work_station_alias";
    String SESSION_USER_WSN = "LU_work_station_name";
    String SESSION_USER_AVATAR_PATH = "LU_avatar_path";
    String SESSION_USER_SIGNATURE_PATH = "LU_signature_path";

    String SESSION_USER_ASSIGNED_GROUPS = "LU_groups";
    String SESSION_USER_GRANTED_ACTION = "LU_actions";
    String SESSION_USER_GRANTED_ACTION_NAMES = "LU_action_names";
    String SESSION_USER_GRANTED_MAPPINGS = "LU_actions_mappings";
    
    String REQUEST_MAPPING = "RequestMapping";

    String PROJECT_ID_PASS_PHRASE = "&jkX%_k%4#GGjhyg";
    String AUTHENTICATION_TOKEN_PASS_PHRASE = "_uy%#%^nnh$Gyg";
    String AUTHORIZATION_TOKEN_PASS_PHRASE = "&#jk#X%_k%4GG#jhy#g";

}
