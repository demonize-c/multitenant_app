package org.example.tenantapp.utils;

import java.util.Random;

public class Helper {


    static Random random = new Random ();
    static String[] domains = new String[]{
            "gmail.com", "yahoo.com", "outlook.com", "hotmail.com", "aol.com", "icloud.com",
            "protonmail.com", "zoho.com", "yandex.com", "mail.com", "gmx.com", "fastmail.com",
            "tutanota.com", "mail.ru", "sbcglobal.net", "comcast.net", "att.net", "verizon.net"
    };

    public static String name() {
        return generateRandomChars(6) + " " + generateRandomChars(4);
    }

    public static String email(){
        return generateRandomChars(10) + "." + random.nextInt(100,999)
                + "@" + randomDomain();
    }
    public static String generateRandomChars (int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder ();
        for (int i = 0; i < length; i ++) {
            sb.append (chars.charAt (random.nextInt (chars
                    .length ())));
        }

        return sb.toString ();
    }

    public  static String randomDomain(){
        return domains[random.nextInt(domains.length)];
    }

    public static void print(String s){
        System.out.println(s);
    }
}
