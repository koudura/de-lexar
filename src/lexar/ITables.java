/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lexar;

import java.util.*;

/**
 *
 * @author habuto
 */

public interface ITables {

    public static  ArrayList<String> JAVAKEYWORDS = new ArrayList<>();
    public static  ArrayList<String> CKEYWORDS = new ArrayList<>();
    public static  ArrayList<String> CSKEYWORDS = new ArrayList<>();
    public static  ArrayList<String> OPERATORS = new ArrayList<>();
    public static  ArrayList<String> DELIMITERS = new ArrayList<>();
    public static ArrayList<String> SOURCECODE = new ArrayList<>();
    
    static String[] EXTSTRINGS = {"*.c","*.txt","*.java","*.cs","*.TXT"};
    
    public static Map<String,String> KEYSMAP = new HashMap<>();
    public static List<String> EXTENSIONS = new ArrayList<>(Arrays.asList(EXTSTRINGS));
    
}
