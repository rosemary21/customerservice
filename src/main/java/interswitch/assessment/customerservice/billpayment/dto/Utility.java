package interswitch.assessment.customerservice.billpayment.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Utility {

    public static String generateAlphaNumbericReference(String numeric,int length){
        String AlphaNumericString="abcdefghijklmnopqrstuvwxyz"+numeric;

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }


    public static String generateReference(int length){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        long random_number = generateRandom(length);
        return dateFormat.format(new Date()) + (random_number);
    }


    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }


    public static String generateNumbericReference(String numeric,int length){
        String AlphaNumericString=numeric;

        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }
}
