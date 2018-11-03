package net.softengine.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.softengine.bean.SessionStaffBean;
import net.softengine.bean.SessionUserBean;
import net.softengine.model.User;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Copyright @ Soft Engine [www.soft-engine.net].
 * Created on 07-Jan-18 at 3:56 PM
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 07-Jan-18
 * Version : 1.0
 */

public class SecurityUtil {
    /**
     * Initialized on boot
     * <p/>
     * //* @see com.ibbl.home.BootstrapEngine#loadGroupActions()
     */
    @SuppressWarnings("unchecked")
    public static Map<Long, List<String>> GROUP_ACTION_LIST_MAP = new HashMap(0);

    @SuppressWarnings("unchecked")
    public static List<String> getActionValueList(List<Long> groupIds) {
        Set<String> data = new HashSet<String>();
        for (Long key : groupIds) {
            List<String> list = SecurityUtil.GROUP_ACTION_LIST_MAP.get(key);
            if (!CollectionUtils.isEmpty(list))
                data.addAll(list);
        }

        return new ArrayList<String>(data);
        /*List<?> actionList = groupIds.stream()
                .map(SecurityUtil.GROUP_ACTION_LIST_MAP::get)
                .map(ArrayList::new)
                .reduce((left, right) -> {
                    left.addAll(right);
                    return left;
                }).orElse(new ArrayList<>());
        return (List<String>) actionList;*/
    }

    /**
     * Setting up the Session User credentials to the HttpSession
     * This Property can be get from SessionUtil Class from any layer of this application.
     *
     * @param result The CAAMP Response ActionResult
     * @see net.softengine.util.SessionUtil#getSessionUser(),
     * @see net.softengine.util.SessionUtil#getSession()  etc.
     */
    @SuppressWarnings("unchecked")
    public static void setAuthenticationAndAuthorizationToken(ActionResult result) {
        HttpSession session = SessionUtil.getSession();
        Map<String, Object> resultMap = result.getMap();
        // session.setAttribute(SecurityConstants.SESSION_USER_ID, resultMap.get(SecurityConstants.SESSION_USER_ID));
        List<Double> groupIdList = (List<Double>) resultMap.get(SecurityConstants.SESSION_USER_ASSIGNED_GROUPS);
        List<Long> groupIdList_Long = new ArrayList<Long>();
        for (Double id : groupIdList) {
            groupIdList_Long.add(id.longValue());
        }
        session.setAttribute(SecurityConstants.SESSION_USER_ASSIGNED_GROUPS, groupIdList_Long);


        Gson gson = new Gson();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> sessionUserMap = (Map<String, Object>) result.get(SecurityConstants.SESSION_USER);
            JsonElement jsonElement = gson.toJsonTree(sessionUserMap);
            Class<?> clazz = Class.forName((SessionUserBean.class.getPackage() + ".Session" + result.getDataObject()+"Bean").replace("package ", ""));
            SessionUserBean sessionUserBean = (SessionUserBean) gson.fromJson(jsonElement, clazz);

            sessionUserBean.setEmployeeId(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_EID)));
            sessionUserBean.setNickName(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_N_NAME)));
            sessionUserBean.setWorkStationCode(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_WSC)));
            sessionUserBean.setWorkStationAlias(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_WSA)));
            sessionUserBean.setWorkStationName(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_WSN)));

            sessionUserBean.setAvatarPath(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_AVATAR_PATH)));
            sessionUserBean.setSignaturePath(String.valueOf(resultMap.get(SecurityConstants.SESSION_USER_SIGNATURE_PATH)));

            session.setAttribute(SecurityConstants.SESSION_USER, sessionUserBean);

            System.out.println("User's HttpSession initialized successfully.");
        } catch (Exception e) {
            System.out.println("User's HttpSession initialization error :: " + e.getMessage());
        }


        // map.forEach(session::setAttribute);

    }

    /**
     * Has the User exists in the session and check is this(Action User) the session user ?
     *
     * @param principal Action User
     * @return Result: thus the Action user is the authentic session user
     */
    public static ActionResult checkAuthentication(Object principal) {
        ActionResult actionResult = new ActionResult(false);
        if (principal instanceof User || principal instanceof SessionStaffBean) {
            actionResult.setSuccess(true);
            return actionResult;
        } else {
            // TODO... check and return for User
        }
        return actionResult;
    }

    /**
     * This is light weighted resource checking <code>List<String></code>
     *
     * @param mapping String action method request mapping.
     *                e.g. in '/ctx/hrm/saveSomething.ibbl' hence, 'saveSomething' is this
     * @return Is the session user authorized or having privilege the action.
     */
    public static boolean checkAuthorizationUsingMapping(String mapping) {
        Set<String> mappings = SessionUtil.getSessionUsersMappings();
        return CollectionUtils.isEmpty(mappings) || mappings.contains(mapping);
    }


    public static boolean checkAuthorizationUsingActionName(String name) {
        /*List<String> actions = SessionUtil.getSessionUserActionNames();
        if (CollectionUtils.isEmpty(actions)) {
            return false;
        } else {
            return actions.contains(name);
        }*/

        List<Long> groupIds = SessionUtil.getSessionUserGroupIds();
        if (CollectionUtils.isEmpty(groupIds)) {
            return false;
        } else {
            return SecurityUtil.getActionValueList(groupIds).contains(name);
        }
    }

    public static String responseFromGET(String url) throws Exception {
        String USER_AGENT = "Mozilla/5.0";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        // System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public static ActionResult getStaticSesaaResponse() {
        String data = "{\"success\":true, \"dataObject\":\"Staff\",\"map\":{\"session_user_id\":1,\"session_user_eid\":\"20170103001\",\"session_user_n_name\":\"Joti\",\"session_user_br_code\":\"0101\",\"session_user_br_name\":\"Khulna Branch\",\"session_user_groups\":[1,3,4],\"session_user\":{\"id\":1,\"active\":true,\"name\":\"Masura Akter Joti\"}}}";
        Gson gson = new Gson();
        return gson.fromJson(data, ActionResult.class);
    }

    private void callConfirmation() throws Exception {
        setAuthenticationAndAuthorizationToken(null);
        responseFromGET(null);
        checkAuthorizationUsingActionName(null);
        checkAuthorizationUsingMapping(null);
    }
}
