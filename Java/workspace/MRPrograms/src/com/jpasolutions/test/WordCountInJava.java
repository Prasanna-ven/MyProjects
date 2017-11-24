package com.jpasolutions.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sivakumar Annamalai on 27/8/15.
 */
public class WordCountInJava {
    public static void main(String args[]){
        String lines[]= {"abc xyz abc","xyz mnp pqr","abc xyz mnp"};
        Map<String,Integer> wordsMap = new HashMap<String,Integer>();
        for(String line:lines){
            String oneLineWords[] = line.split(" ");
            for(String word:oneLineWords){
                if(!wordsMap.containsKey(word)){
                    wordsMap.put(word,new Integer(1));
                }else{
                    wordsMap.put(word, new Integer(wordsMap.get(word).intValue()+ 1));
                }
            }
        }

        System.out.println("abc count: "+wordsMap.get("abc"));
        System.out.println("mnp count: "+wordsMap.get("mnp"));
        System.out.println("xyz count: "+wordsMap.get("xyz"));
        System.out.println("pqr count: "+wordsMap.get("pqr"));
    }
}
