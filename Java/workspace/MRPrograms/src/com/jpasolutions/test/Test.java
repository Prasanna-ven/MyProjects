package com.jpasolutions.test;

/**
 * Created by Sivakumar Annamalai on 1/8/15.
 */
public class Test {
    public static void main(String args[]){
        String str = new String("xyz abc pqr xyz mnp");
        splitString(str);
    }

    public static void splitString(String str){
        String a[]= str.split(" ");

        for(int i=0;i<a.length;i++){
            System.out.println(a[i]);
        }

        for(String s:a){
            System.out.println(s);
        }
    }
}
