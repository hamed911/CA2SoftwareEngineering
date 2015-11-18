/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.gorjiara.banktransactions.convertor;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Hamed Ara
 */
public class StringConvertor {
    public static String convertToString(BigInteger integer){
        String pattern = "###,###";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
//        DecimalFormat formatter =(DecimalFormat) NumberFormat.getInstance(Locale.US);
//        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
//        symbols.setGroupingSeparator(',');
//        formatter.setDecimalFormatSymbols(symbols);
        return decimalFormat.format(integer.longValue());
    }
}
