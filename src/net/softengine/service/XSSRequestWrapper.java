package net.softengine.service;

import org.owasp.esapi.ESAPI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.  (www.soft-engine.net)
 * <p>
 * Original author: Khomeni
 * Date: 20/12/2017 1:34 PM
 * Last modification by: Khomeni: Khomeni
 * Last modification on 20/12/2017: 20/12/2017 1:34 PM
 * Current revision: 1.0.0: 1.1 $
 * <p>
 * Revision History:
 * ------------------
 */

public class XSSRequestWrapper extends HttpServletRequestWrapper {

    public XSSRequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        String[] values = super.getParameterValues(parameter);
        if (values == null) {
            return null;
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++) {
            encodedValues[i] = stripXSS(parameter, values[i]);
        }
        return encodedValues;
    }

    @Override
    public String getParameter(String parameter) {
        String value = super.getParameter(parameter);
        return stripXSS(parameter, value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        return stripXSS(name, value);
    }

    private String stripXSS(String name, String value) {
        String safeOutput = "";
        if (value != null) {
            // NOTE: It’s highly recommended to use the ESAPI library and
            // avoid encoded attacks.
            value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            value = value.replaceAll("", "");

            // Remove all sections that match a pattern
            /*for (Pattern scriptPattern : patterns) {
                value = scriptPattern.matcher(value).replaceAll("");
            }*/

            /*try {
                 value = ESAPI.validator().getValidInput(name, value, "SafeString", 255, false);
                value = ESAPI.encoder().encodeForHTML(value);
                System.out.println(name + ": " + value + "");
            } catch (ValidationException e) {
                e.printStackTrace();
            }*/

        }
        return value;
    }
}
