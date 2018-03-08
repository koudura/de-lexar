/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexar;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author habuto
 */
public class LexTableLoad implements ITables, ILoader {

    public LexTableLoad() {
        initTable(PATHTOCKEYS, CKEYWORDS);
        initTable(PATHTOCSKEYS, CSKEYWORDS);
        initTable(PATHTOJAVAKEYS, JAVAKEYWORDS);
        initTable(PATHTODELIMITERS, DELIMITERS);
        initTable(PATHTOOPERATORS, OPERATORS);

        initMap(PATHTOTABLE, KEYSMAP);
    }

    @Override
    public void initTable(String path, ArrayList<String> table) {
        try {
            FileReader reader = new FileReader(path);
            BufferedReader bread = new BufferedReader(reader);

            String line;
            while ((line = bread.readLine()) != null) {
                table.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LexTableLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LexTableLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initMap(String path, Map<String, String> map) {
        try {
            FileReader reader = new FileReader(path);
            BufferedReader breadMap = new BufferedReader(reader);

            String line;
            while ((line = breadMap.readLine()) != null) {
                int mark = line.lastIndexOf(",");
                map.put(getKey(mark, line), getValue(mark, line));
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LexTableLoad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LexTableLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getKey(int mark, String line) {
        return line.substring(0, mark);
    }

    private String getValue(int mark, String line) {
        return line.substring(mark + 1, line.length());
    }
    
}
