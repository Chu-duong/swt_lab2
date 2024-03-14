package com.example.happyprogramingbackend.common;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class Ultil {

    public static String removeAccent(String s) { String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        temp = pattern.matcher(temp).replaceAll("");
        return temp.replaceAll("đ", "d"); }

   public static String generateRandomPassword(){
       char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
       return RandomStringUtils.random( 8, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
   }

}
