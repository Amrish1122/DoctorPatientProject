package com.gaur.Assignment.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExtractor {
    public static String extractString(String input,String startWord,String endWord){
        String patternString =startWord+"(.*?)"+endWord;
        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(input);
        if (matcher.find()){
            return matcher.group(1).trim();
        }else return null;
    }
}
