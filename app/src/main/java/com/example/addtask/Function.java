package com.example.addtask;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Function {

    private static String EMPT = "Input requires";
    private static String NUMB = "Allows numbers only";

    public static String checkName(String string) {
        String res = null;
        if (string.isEmpty()) {
            res = EMPT;
        } else if (string.matches("[0-9\\s]")) {
            res = "No space & numbers allowed";
        } else if (!string.matches("^[a-zA-Z]{3,15}$")) {
            res = "Name is too long or short";
        }
        return res;
    }

    public static String checkMobile(String string) {
        String res = null;
        if (string.isEmpty()) {
            res = EMPT;
        } else if (string.length() != 10) {
            res = "Length must 10";
        } else if (!string.matches("^[0-9]{10}$")) {
            res = NUMB;
        }
        return res;
    }
}