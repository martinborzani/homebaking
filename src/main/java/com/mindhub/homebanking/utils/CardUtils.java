
package com.mindhub.homebanking.utils;


public final class CardUtils {


    private CardUtils() {

    }


    public static String getCardNumber() {
        String cardNumber = (int) (Math.random() * (10000 - 1000) + 1000)
                + "-" + (int) (Math.random() * (10000 - 1000) + 1000)
                + "-" + (int) (Math.random() * (10000 - 1000) + 1000)
                + "-" + (int) (Math.random() * (10000 - 1000) + 1000);

        return  cardNumber;
    }

    public static int getCVV() {
        int numberAccount1 = (int)(Math.random() * 1000 - 100) + 100;
        return numberAccount1;
    }


}
