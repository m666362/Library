package com.rich_it.library.Utils;

public class TypeConverter {
    public static int getInt(String s){
        return Integer.parseInt(s.replaceAll("[\\D]", ""));
    }
    public static String getString(int num){
        return String.valueOf(num);
    }
}
