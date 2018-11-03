package net.softengine.aop;

import net.softengine.util.ActionResult;
import net.softengine.util.SecurityConstants;
import net.softengine.util.SecurityUtil;
import net.softengine.util.SessionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * AuthenticationAspect will ensure..
 * 1. Is the session user null
 * 2. Is this the right user (Authenticate)
 * 3. Is this instanceof defined User
 *
 * @author Khomeni
 *         Created on : 18-May-17 at 12:04 AM
 */


@Aspect
public class GAuthenticationAspect {

    @Pointcut("within(@org.springframework.stereotype.Controller *) " +
            "&& !within(@org.springframework.stereotype.Controller com.ibbl.home.HomeController) " +
            "&& !within(@org.springframework.stereotype.Controller com.ibbl.security.AuthenticationController) ")
    public void allControllers() {
    }

    @Pointcut("execution(* *(..))")
    public void methodPointcut() {
    }

    @Around("allControllers() && methodPointcut()")
    public Object proceedToAction(ProceedingJoinPoint joinPoint) throws Throwable {
        Object sessionUserInstance = SessionUtil.getSession().getAttribute(SecurityConstants.SESSION_USER);
        if (sessionUserInstance != null) {
            ActionResult result = SecurityUtil.checkAuthentication(sessionUserInstance);
            if (result.isSuccess()) {
                return joinPoint.proceed();
            }
        }
//        return new ModelAndView("redirect:/");
         return joinPoint.proceed();


    }
}
