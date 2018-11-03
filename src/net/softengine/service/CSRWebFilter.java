package net.softengine.service;

import org.owasp.esapi.filters.SecurityWrapperResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.  (www.soft-engine.net)
 * <p>
 * Original author: Khomeni
 * Date: 20/12/2017 1:45 PM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 20/12/2017: 20/12/2017 1:45 PM
 * Current revision: : 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

public class CSRWebFilter extends OncePerRequestFilter {
    private String encoding;
    private boolean forceEncoding = false;

    public CSRWebFilter() {
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void setForceEncoding(boolean forceEncoding) {
        this.forceEncoding = forceEncoding;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(this.encoding != null && (this.forceEncoding || request.getCharacterEncoding() == null)) {
            request.setCharacterEncoding(this.encoding);
            if(this.forceEncoding) {
                SecurityWrapperResponse ESAPIWrapperResponse = new SecurityWrapperResponse(response);
                ESAPIWrapperResponse.setCharacterEncoding(this.encoding);
            }
        }
        filterChain.doFilter(request, response);
    }
}
