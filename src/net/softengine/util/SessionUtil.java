package net.softengine.util;

import net.softengine.bean.SessionUserBean;
import net.softengine.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

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

public class SessionUtil {

    public static HttpSession getSession() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return attr.getRequest().getSession(true);
    }

    public static void setAttribute(String key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static Object getAttribute(String key) {
        return getSession().getAttribute(key);
    }

    public static void removeAttribute(String key) {
        getSession().removeAttribute(key);
    }

    public static void invalidate() {
        HttpSession userSession = getSession();
        userSession.removeAttribute(SecurityConstants.SESSION_USER);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_ID);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_EID);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_N_NAME);

        userSession.removeAttribute(SecurityConstants.SESSION_USER_WSC);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_WSA);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_WSN);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_AVATAR_PATH);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_SIGNATURE_PATH);

        userSession.removeAttribute(SecurityConstants.SESSION_USER_ASSIGNED_GROUPS);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_GRANTED_ACTION);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_GRANTED_ACTION_NAMES);
        userSession.removeAttribute(SecurityConstants.SESSION_USER_GRANTED_MAPPINGS);
        userSession.invalidate();
    }

    public static SessionUserBean getSessionUserBean() {
        Object user = getSession().getAttribute(SecurityConstants.SESSION_USER);
        if (user instanceof SessionUserBean) {
            return (SessionUserBean) user;
        }
        return null;
    }

    public static String getSessionUserIdentity() {
        Object user = getSession().getAttribute(SecurityConstants.SESSION_USER);
        if (user instanceof SessionUserBean) {
            SessionUserBean sessionUserBean = (SessionUserBean) user;
            return sessionUserBean.getEmployeeId() + " | " + sessionUserBean.getNickName();
        }
        return null;
    }

    public static User getSessionUser() {
        Long userId = getSessionUserId();
        if (userId != null) {
            User user = new User();
            user.setId(userId);
            return user;
        }
        return null;
    }

    public static String getSessionWSC() {
        try {
            return getSessionUserBean().getWorkStationCode();
        } catch (Exception e) {
            return null;
        }
    }

    public static String getSessionWSN() {
        try {
            return getSessionUserBean().getWorkStationName();
        } catch (Exception e) {
            return null;
        }
    }

    public static Long getSessionUserId() {
        Object id = getSession().getAttribute(SecurityConstants.SESSION_USER_ID);
        if (id == null) {
            try {
                id = getSessionUserBean().getId();
            } catch (Exception e) {
                System.out.println("No session User Found !");
            }
        }
        if (id instanceof Long) {
            return (Long) id;
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    public static List<String> getSessionUserActionNames() {
        return (List<String>) getSession().getAttribute(SecurityConstants.SESSION_USER_GRANTED_ACTION_NAMES);
    }

    @SuppressWarnings("unchecked")
    public static List<Long> getSessionUserGroupIds() {
        return (List<Long>) getSession().getAttribute(SecurityConstants.SESSION_USER_ASSIGNED_GROUPS);
    }

    @SuppressWarnings("unchecked")
    public static Set<String> getSessionUsersMappings() {
        return (Set<String>) getSession().getAttribute(SecurityConstants.SESSION_USER_GRANTED_MAPPINGS);
    }

    private void callConfirmation() {
        getSession();
        invalidate();
        getSessionUserBean();
        getSessionUserIdentity();
        getSessionUser();
        getSessionUserId();
        getSessionUserActionNames();
        getSessionUserGroupIds();
        getSessionUsersMappings();
    }

}
