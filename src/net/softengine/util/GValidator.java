package net.softengine.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.validator.GenericValidator;
import org.owasp.esapi.ESAPI;
import org.springframework.validation.BindingResult;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Copyright &copy; 2017-2018 Soft Engine Inc.
 * <p>
 *
 * @author Khomeni
 *         Date: 16/08/2017 10:25 AM
 *         Last modification by: Khomeni: Khomeni
 *         Last modification on 16/08/2017: 16/08/2017 10:25 AM
 *         Current revision: 1.0.0: 1.1 $
 *         <p>
 *         Revision History:
 *         ------------------
 */

public class GValidator extends org.springframework.validation.ValidationUtils {
    private static final List<String> HOME_NO_LIST_OF_BD_CELLS = Arrays.asList("011", "015", "016", "017", "018", "019");


    /**
     * Validating email address
     *
     * @param emailAddress typed email address
     * @param fieldName    input field name
     * @param mandatory    is to avoid wrong email-address in non mandatory address field.
     * @param result       void
     */
    public static void validateEmailAddress(BindingResult result, String emailAddress, String fieldName, boolean mandatory) {
        if (!isBlankOrNull(emailAddress)) {
            boolean valid = ESAPI.validator().isValidInput("", emailAddress, "Email", 100, true);
            if (!valid) {
                rejectValue(result, fieldName, "Type valid email" + (mandatory ? "." : ", or keep it blank"));
            }
        } else if (mandatory) {
            rejectValue(result, fieldName, "Email address required");
        }
    }

    public static void validateBrCode(BindingResult result, String brCode) {
        if (isDigits(brCode)) {
            if (brCode.length() != GConstants.BR_CODE_LENGTH) {
                rejectValue(result, "brCode", "Required " + GConstants.BR_CODE_LENGTH + " digit Branch Code.");
            }
        } else {
            rejectValue(result, "brCode", "Required a numeric Branch Code.");
        }
    }

    public static void rejectEmptyString(BindingResult result, String fieldValue, String fieldName, String message) {
        if (GenericValidator.isBlankOrNull(fieldValue)) {
            rejectValue(result, fieldName, message);
        }
    }

    public static void validateDigits(BindingResult result, String fieldValue, String fieldName, String message) {
        if (!isDigits(fieldValue)) {
            rejectValue(result, fieldName, message);
        }
    }

    public static boolean isSafeString(String string) {
        return ESAPI.validator().isValidInput("", string, "SafeString", 200, true);
    }

    public static void rejectValue(BindingResult result, String fieldName, String message) {
        result.rejectValue(fieldName, "", new Object[]{fieldName}, message);
    }

    public static void validateCellNo(String cell, String fieldName, BindingResult result) {
        String message = validateCellNo(cell);
        if (!BooleanUtils.toBoolean(message)) {
            result.rejectValue(fieldName, "", new Object[]{fieldName}, message);
        }
    }

    public static String validateCellNo(String cell) {
        if (isBlankOrNull(cell)) return "Cell phone no. is required.";
        if (!NumberUtils.isDigits(cell)) return "No character is allowed.";
        if (cell.length() != 11) {
            return "Must be 11 digit";
        }
        String homeNo = cell.substring(0, 3);
        if (!HOME_NO_LIST_OF_BD_CELLS.contains(homeNo)) {
            return "Not used in BD";
        } else {
            return BooleanUtils.toStringTrueFalse(true);
        }
    }

    /**
     * Validating NID without validate with the year of DOB
     *
     * @param nid       National ID No
     * @param fieldName Bean property name
     * @param result    Spring Binding result
     */
    public static void validateNID(BindingResult result, String nid, String fieldName) {
        String message = validateNID(null, false, nid);
        if (!BooleanUtils.toBoolean(message)) {
            rejectValue(result, fieldName, message);
        }
    }

    /**
     * Validating NID with validate with the year of DOB
     *
     * @param dob       Date of Birth
     * @param nid       National ID No
     * @param fieldName Bean property name
     * @param result    Spring Binding result
     */
    public static void validateNID(BindingResult result, Date dob, String nid, String fieldName) {
        String message = validateNID(dob, true, nid);
        if (!BooleanUtils.toBoolean(message)) {
            rejectValue(result, fieldName, message);
        }
    }

    public static String validateNID(Date birthDate, boolean birthYearValidation, String nid) {
        String message = BooleanUtils.toStringTrueFalse(true);
        if (isBlankOrNull(nid)) {
            message = "NID is required.";
        } else if (!isDigits(nid)) {
            message = "No character is allowed in NID.";
        } else {
            int len = nid.length();
            if (len != 13 && len != 17) {
                message = "Must be 13/17 digit NID required";
            } else if (len == 17 && birthYearValidation) {
                if (birthDate == null) {
                    message = "Please select birth date first.";
                } else {
                    String birthYear = new SimpleDateFormat("yyyy").format(birthDate);
                    if (!nid.startsWith(birthYear)) {
                        message = "NID birth year and Birth Date birth year not match";
                    }
                }
            }
        }
        return message;
    }

    /**
     * This method can be used in Bean Validation.
     * Using this we can check all of the String field value is Safe String
     *
     * @param result BindingResult
     * @param object The bean Object
     */
    public static void validateSafeString(BindingResult result, Object object) {
        validateSafeString(result, object, new ArrayList<String>());
    }

    /**
     * This method can be used in Bean Validation.
     * Using this we can check all of the String field value is Safe String
     *
     * @param result BindingResult
     * @param object The bean Object
     * @param escapeFieldList The field list that exclude from this validation
     */
    public static void validateSafeString(BindingResult result, Object object, List<String> escapeFieldList) {
        boolean hasEscapeField = !CollectionUtils.isEmpty(escapeFieldList);
        for (Field f : object.getClass().getDeclaredFields()) {
            int mod = f.getModifiers();
            if (Modifier.isPrivate(mod)) {
                if (Modifier.isPrivate(mod)) {
                    try {
                        if (f.getType().equals(String.class)) {
                            String fieldName = f.getName();
                            if (hasEscapeField && escapeFieldList.contains(fieldName)) continue;
                            String methodName = "get" + WordUtils.capitalize(fieldName);
                            Method method = object.getClass().getMethod(methodName);
                            Object fieldValue = method.invoke(object);

                            if (!isSafeString(String.valueOf(fieldValue))) {
                                rejectValue(result, fieldName, "Found Suspicious Character. <a class=\"unsafe-string-help-gritter\" href=\"#\">Help</a>");
                                //System.out.println(name + " = " + value);
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    /**
     * This method can be used in Bean Validation.
     * Using this we can check all of the String field value is Safe String
     *
     * @param object The bean Object
     */
    public static void toSafeHTML(Object object) {
        for (Field f : object.getClass().getDeclaredFields()) {
            int mod = f.getModifiers();
            if (Modifier.isPrivate(mod)) {
                if (Modifier.isPrivate(mod)) {
                    try {
                        if (f.getType().equals(String.class)) {
                            String fieldName = f.getName();
                            String methodName = "get" + WordUtils.capitalize(fieldName);
                            Method method = object.getClass().getMethod(methodName);
                            Object fieldValue = method.invoke(object);

                            if (!isSafeString(String.valueOf(fieldValue))) {
                                fieldValue = "";
                            } else {
                                fieldValue = ESAPI.encoder().encodeForHTML(fieldValue.toString());
                            }

                            String methodNameSet = "set" + WordUtils.capitalize(fieldName);
                            Method methodSetter = object.getClass().getMethod(methodNameSet);
                            methodSetter.invoke(object, fieldValue);
                            // todo set this value to Object Property
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private boolean isValid() {
        boolean valid = false;

        /*try {
            firstName = ESAPI.validator().getValidInput("CustomerFirstName", firstName, "SafeString", 30, false, true);
            lastName = ESAPI.validator().getValidInput("CustomerLastName", lastName, "SafeString", 30, false, true);
            email = ESAPI.validator().getValidInput("CustomerEmail", email, "Email", 254, false, true);
            valid = true;
        } catch (IntrusionException | ValidationException e) {
            System.out.println(e.getMessage());
        }*/

        return valid;
    }

    public static boolean isBlankOrNull(String value) {
        return GenericValidator.isBlankOrNull(value);
    }

    public static boolean isDigits(String str) {
        return NumberUtils.isDigits(str);
    }
}
