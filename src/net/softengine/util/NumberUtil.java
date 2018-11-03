package net.softengine.util;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Copyright @ Soft Engine
 * Created on 14/12/15
 * Created By : Khomeni
 * Edited By : Khomeni &
 * Last Edited on : 14/12/15
 * Version : 1.0
 * Release Note:
 */

public class NumberUtil {

    public static void main(String[] args) {
        System.out.println(toCommaFormattedTaka(1.2653));
    }
    public static String toCommaFormattedTaka(Object takaPaisaObj) {
        if(takaPaisaObj == null ) return "";
        if (takaPaisaObj instanceof Double || takaPaisaObj instanceof Integer  || NumberUtils.isNumber(takaPaisaObj.toString())) {
            DecimalFormat df = new DecimalFormat("#0.00");
            String takaPaisa = df.format(takaPaisaObj);
            String taka = takaPaisa.split("\\.")[0];
            String paisa = takaPaisa.split("\\.")[1];

            int len = taka.length();
            if (len <= 3) {
                return taka + "." + paisa;
            } else {
                String sotok = taka.substring(len - 3);
                String result = "";
                String rest = taka.substring(0, len - 3);
                for (int i = 0; i <= rest.length(); i++) {
                    if (rest.length() % 2 == 0) {
                        result += rest.substring(0, 2);
                        rest = rest.substring(2);
                    } else {
                        result += rest.substring(0, 1);
                        rest = rest.substring(1);
                    }
                    result += ",";
                }
                //System.out.println(result);
                return result + sotok + "." + paisa;
            }
        } else {
            return "";
        }
    }

    public static String toJasperAmt(Double d) {
        return BigDecimal.valueOf(d.intValue()).toPlainString()+"";
    }

    private static final String[] tensNames = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] numNames = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private static String convertLessThanOneThousand(int number) {
        String soFar;

        if (number % 100 < 20){
            soFar = numNames[number % 100];
            number /= 100;
        }
        else {
            soFar = numNames[number % 10];
            number /= 10;

            soFar = tensNames[number % 10] + soFar;
            number /= 10;
        }
        if (number == 0) return soFar;
        return numNames[number] + " hundred" + soFar;
    }

    public static void main3(String[] args) {
        long number = 877745844;
        String snumber = Long.toString(number);

        // pad with "0"
        String mask = "000000000000";
        DecimalFormat df = new DecimalFormat(mask);
        snumber = df.format(number);

        int billions = Integer.parseInt(snumber.substring(0,5));
        // nnnXXXnnnnnn
        int crore  = Integer.parseInt(snumber.substring(5,7));
        // nnnnnnXXXnnn
        int lakh = Integer.parseInt(snumber.substring(7,9));
        // nnnnnnnnnXXX
        int thousands = Integer.parseInt(snumber.substring(9,12));
        System.out.println("crore = " + billions);
        System.out.println("lakh = " + crore);
        System.out.println("Thousands / lakh= " + lakh);
        System.out.println("hundreds = " + thousands);
        System.out.println("snumber = " + snumber);
    }

    /**
     * Convert a number to word // 0 to 999 999 999 999
     * @param number - input number to be translated
     * @return String - English word of given number
     */
    public static String inWord(long number) {
        try {            
            if (number == 0) { return "zero"; }
            String sourceNumber;
            sourceNumber = Long.toString(number);
            System.out.println(sourceNumber);
            String mask = "000000000000";
            DecimalFormat df = new DecimalFormat(mask);
            sourceNumber = df.format(number);
            int crore = Integer.parseInt(sourceNumber.substring(0, 5));
            int lakh  = Integer.parseInt(sourceNumber.substring(5,7));
            int thousands = Integer.parseInt(sourceNumber.substring(7, 9));
            int hundreds = Integer.parseInt(sourceNumber.substring(9,12));

            String tradBillions;
            switch (crore) {
                case 0:
                    tradBillions = "";
                    break;
                case 1 :
                    tradBillions = convertLessThanOneThousand(crore)
                            + " one crore ";
                    break;
                default :
                    tradBillions = convertLessThanOneThousand(crore)
                            + " crore ";
            }
            String result =  tradBillions;

            String tradMillions;
            switch (lakh) {
                case 0:
                    tradMillions = "";
                    break;
                case 1 :
                    tradMillions = convertLessThanOneThousand(lakh)
                            + " one lakh ";
                    break;
                default :
                    tradMillions = convertLessThanOneThousand(lakh)
                            + " lakh ";
            }
            result =  result + tradMillions;

            String tradHundredThousands;
            switch (thousands) {
                case 0:
                    tradHundredThousands = "";
                    break;
                case 1 :
                    tradHundredThousands = "one thousand ";
                    break;
                default :
                    tradHundredThousands = convertLessThanOneThousand(thousands)
                            + " thousand ";
            }
            result =  result + tradHundredThousands;

            String tradThousand;
            tradThousand = convertLessThanOneThousand(hundreds);
            result =  result + tradThousand;

            // removing extra spaces!
            return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
        }   catch (Exception ex){
            return "";
        }

    }

    /**
     * testing
     * @param args
     */
    public static void main4(String[] args) {
      /*  System.out.println("*** " + EnglishNumberToWords.convert(0));
        System.out.println("*** " + EnglishNumberToWords.convert(1));
        System.out.println("*** " + EnglishNumberToWords.convert(16));
        System.out.println("*** " + EnglishNumberToWords.convert(100));
        System.out.println("*** " + EnglishNumberToWords.convert(118));
        System.out.println("*** 203" + EnglishNumberToWords.convert(203));
        System.out.println("*** 219" + EnglishNumberToWords.convert(219));
        System.out.println("*** 800" + EnglishNumberToWords.convert(800));
        System.out.println("*** 801" + EnglishNumberToWords.convert(801));
        System.out.println("***1316 " + EnglishNumberToWords.convert(1316));
        System.out.println("***1000000 " + EnglishNumberToWords.convert(1000000));
        System.out.println("*** 2000000" + EnglishNumberToWords.convert(2000000));
        System.out.println("*** 3000200" + EnglishNumberToWords.convert(3000200));
        System.out.println("*** 700000" + EnglishNumberToWords.convert(700000));*/
        System.out.println("*** 9035031 = " + NumberUtil.inWord(39335231));
       /* System.out.println("*** " + EnglishNumberToWords.convert(9001000));
        System.out.println("*** " + EnglishNumberToWords.convert(123456789));
        System.out.println("*** 2147483647" + EnglishNumberToWords.convert(2147483647));
        System.out.println("*** " + EnglishNumberToWords.convert(3000000010L));*/

    /*
     *** zero
     *** one
     *** sixteen
     *** one hundred
     *** one hundred eighteen
     *** two hundred
     *** two hundred nineteen
     *** eight hundred
     *** eight hundred one
     *** one thousand three hundred sixteen
     *** one million
     *** two millions
     *** three millions two hundred
     *** seven hundred thousand
     *** nine millions
     *** nine millions one thousand
     *** one hundred twenty three millions four hundred
     **      fifty six thousand seven hundred eighty nine
     *** two billion one hundred forty seven millions
     **      four hundred eighty three thousand six hundred forty seven
     *** three billion ten
     **/
    }

}
