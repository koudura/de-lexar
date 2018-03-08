/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexar;

import com.jfoenix.controls.JFXTextArea;
import java.util.ArrayList;

/**
 *
 * @author habuto
 */
public class LexLogic implements ITables {

    private static JFXTextArea sourceArea;
    private static JFXTextArea tokenArea;
    int t;
    private static ArrayList<String> tokenizers;

    public LexLogic(JFXTextArea _source, JFXTextArea _token) {
        sourceArea = _source;
        tokenArea = _token;
        tokenizers = new ArrayList();
    }

    public LexLogic() {

    }

    public void runAnalysis() {
        LexDataControl control = new LexDataControl(sourceArea, tokenArea);
        control.readSourceCode();

        tokenizers = new ArrayList();
        for (int s = 0; s < SOURCECODE.size(); s++) {
            char[] line = SOURCECODE.get(s).toCharArray();
            String nnhold = "";
            String literal = "";
            String idhold = "";
            ArrayList<String> foundList = new ArrayList();

            for (int i = 0; i < line.length; i++) {
                String val = Character.toString(line[i]);

                if (!Character.isWhitespace(line[i])) {
                    if ((line.length >= 1)  && (line[0] == '/' && line[1] == '/')) {
                        continue;
                    } else if ((i < line.length - 1) && (line[i] == '/' && line[i + 1] == '/')) {
                        break;
                    }

                    if (isOperator(val)) {
                        t = i;
                        foundList.add(scanOperator(val, line, t));
                        i = t;

                    } else if (Character.isDigit(line[i])) {
                        int c = i;
                        while (c < line.length && (Character.isDigit(line[c]) || line[c] == '.')) {
                            if (nnhold.contains(Character.toString('.')) && line[c] == '.') {
                                break;
                            }
                            nnhold += line[c];
                            i = c;
                            c++;
                        }
                        foundList.add(nnhold);
                        nnhold = "";

                    } else if (Character.isLetter(line[i]) || val.equals("_")) {
                        int c = i;

                        while ((c < line.length) && (Character.isLetterOrDigit(line[c]) || Character.toString(line[c]).equals("_"))) {
                            idhold += line[c];
                            i = c;
                            c++;
                        }
                        foundList.add(idhold);
                        idhold = "";
                    } else if (isDelimiter(val)) {

                        if (!isLiteral(val)) {
                            foundList.add(val);
                        } else if (ischarLit(val)) {

                            int k = i;
                            literal += val;
                            while ((i + 2) < line.length && k < (i + 2) && line[i + 2] == '\'') {
                                literal += line[k];
                                k++;
                            }
                            i = k;
                            foundList.add(literal);
                            literal = "";

                        } else if (isStrLit(val)) {
                            int c = i + 1;
                            literal += val;
                            while ((c < line.length - 1) && !("\"".equals(Character.toString(line[c])))) {
                                literal += line[c];
                                i = c;
                                c++;
                            }
                            c--;
                            literal += ("\"".equals(Character.toString(line[c]))) ? line[c] : "";
                            foundList.add(literal);

                            literal = "";
                        }
                    }
                }
            }
            tokenizers.add(setLineTokens(foundList, s + 1));
        }

        DisplayTokens(tokenizers);

    }

    private boolean isOperator(String ch) {
        return OPERATORS.contains(ch);
    }

    private boolean isDelimiter(String ch) {
        return DELIMITERS.contains(ch);
    }

    private boolean isLiteral(String ch) {
        return (ch.equals("\"") || ch.equals("'"));
    }

    private boolean ischarLit(String ch) {
        return ch.equals("'");
    }

    private boolean isStrLit(String ch) {
        return ch.equals("\"");
    }

    private String scanOperator(String val, char[] line, int i) {
        String ophold = val;
        if ((i < line.length - 1) && isMergeableOP(i, line)) {
            ophold += getNextChar(i, line);
            ++t;
        }
        return ophold;
    }

    private boolean isMergeableOP(int i, char[] _line) {
        String op1 = Character.toString(_line[i]);
        String op2 = Character.toString(_line[i + 1]);

        return OPERATORS.contains(op1 + op2);
    }

    private String setLineTokens(ArrayList<String> tokens, int lineNo) {
        StringBuilder strbuild = new StringBuilder();

        for (int i = 0; i < tokens.size(); i++) {
            String tokensDisp;
            if (KEYSMAP.containsKey(tokens.get(i))) {
                tokensDisp = "[<" + lineNo + ">" + " : \"" + tokens.get(i) + "\" : " + KEYSMAP.get(tokens.get(i)) + "]\n";
            } else if (isKeyWord(tokens.get(i))) {
                tokensDisp = "[<" + lineNo + ">" + " : \"" + tokens.get(i) + "\" : {KEYWORD}]\n";
            } else if (isNumber(tokens.get(i))) {
                tokensDisp = "[<" + lineNo + ">" + " : \"" + tokens.get(i) + "\" : {NUMBER}]\n";
            } else if (isTrueLiteral(tokens.get(i))) {
                tokensDisp = "[<" + lineNo + ">" + " : \"" + tokens.get(i) + "\" : {LITERAL}]\n";
            } else {
                tokensDisp = "[<" + lineNo + ">" + " : \"" + tokens.get(i) + "\" : {ID}]\n";
            }
            strbuild.append("-");
            strbuild.append(tokensDisp);
        }
        return strbuild.toString();
    }

    private char getNextChar(int i, char[] _line) {
        return _line[i + 1];
    }

    private boolean isTrueLiteral(String str) {
        return (str.charAt(0) == '"' || str.charAt(0) == '\'');

    }

    private boolean isKeyWord(String ch) {
        return (JAVAKEYWORDS.contains(ch) || CKEYWORDS.contains(ch) || CSKEYWORDS.contains(ch));
    }

    private boolean isNumber(String s) {
        boolean isnumber;
        try {
            Double.parseDouble(s);
            isnumber = true;
        } catch (NumberFormatException e) {
            isnumber = false;
        }
        return isnumber;
    }

    private void DisplayTokens(ArrayList<String> tokensList) {
        for (int i = 0; i < tokensList.size(); i++) {
            tokenArea.appendText(tokensList.get(i) + "\n");
        }
    }

    public ArrayList<String> getTokenizers() {
        return tokenizers;
    }
}
