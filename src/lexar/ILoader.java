/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexar;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author habuto
 */
public interface ILoader {

    public static final String PATHTOJAVAKEYS = "src/lexar/res/keywords_java.txt";
    public static final String PATHTOCKEYS = "src/lexar/res/keywords_c.txt";
    public static final String PATHTOCSKEYS = "src/lexar/res/keywords_cs.txt";
    public static final String PATHTOTABLE = "src/lexar/res/Table of Operators.txt";
    public static final String PATHTOOPERATORS = "src/lexar/res/operators.txt";
    public static final String PATHTODELIMITERS = "src/lexar/res/delimiters.txt";
    
    public void initTable(String path, ArrayList<String> table);
    public void initMap(String path, Map<String, String> map);
}
