/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexar;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;

/**
 *
 * @author habuto
 */
public class LexDataControl implements ITables {

    private File sourceFile;
    private File tokenFile;
    private static JFXTextArea sourceArea;
    private static JFXTextArea tokenArea;
    private static JFXComboBox<?> box;

    public LexDataControl() {

    }

    public LexDataControl(JFXTextArea _sourceArea, JFXTextArea _tokenArea) {
        sourceArea = _sourceArea;
        tokenArea = _tokenArea;
        box = LexarController.extendsBox;
    }

    private File loadSourceFile() {
        FileChooser window = new FileChooser();
        window.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("C | CSharp | Java | Text Files only ", EXTENSIONS));

        File file = window.showOpenDialog(Lexar.stagee);
        if (file != null) {
            return file;
        }
        return null;
    }

    public File getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile() {
        sourceFile = loadSourceFile();
    }

    public File getTokenFile() {
        return tokenFile;
    }

    public void setTokenFile() {
        tokenFile = loadTokenFile();
    }

    public void loadSourceFile(File dafile) {
        if (LexarController.loadedfile) {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(dafile));
                String lineText;
                sourceArea.setText("");
                int LineNo = 0;
                while ((lineText = reader.readLine()) != null) {
                    LineNo++;
                    sourceArea.appendText(lineText + "\n");
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(LexDataControl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(LexDataControl.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                        LexarController.loadedfile = false;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(LexDataControl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void readSourceCode() {

        File file = new File("src/temp.lex");
        try {
            FileWriter fwrite;
            fwrite = new FileWriter(file);
            fwrite.write(sourceArea.getText());
            fwrite.close();

            readSourceCode(file);
        } catch (IOException ex) {
            Logger.getLogger(LexDataControl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void readSourceCode(File fl) throws FileNotFoundException, IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(fl))) {
            String lineText;
            SOURCECODE.clear();
            while ((lineText = reader.readLine()) != null) {
                SOURCECODE.add(lineText);
            }
        }
    }

    private File loadTokenFile() {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text File (*.txt)","*.txt"));
        File token_file = fileChooser.showSaveDialog(Lexar.stagee);

        if (token_file != null) {
            return token_file;
        }
        return null;
    }

    public void saveTokenFile(File dafile) {
        try {
            FileWriter filewriter;

            filewriter = new FileWriter(dafile);
            filewriter.write(tokenArea.getText());
            filewriter.close();

        } catch (IOException ex) {
            Logger.getLogger(LexDataControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getFileExtension() {
        String format = box.getAccessibleText();
        String bend = "*." + format.toLowerCase();
        return bend;
    }

    private String getFileType() {
        String format = box.getAccessibleText();
        return format;
    }

}
