package net.softengine.service;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.  (www.soft-engine.net)
 * <p>
 * Original author: Khomeni
 * Date: 20/12/2017 1:33 PM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 20/12/2017: 20/12/2017 1:33 PM
 * Current revision: 1.0.0: 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

public class XSSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);

    }
}
