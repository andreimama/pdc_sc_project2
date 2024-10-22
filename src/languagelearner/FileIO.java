/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author franc
 */
public class FileIO {

    public List<String[]> readPhrases(String path) {
        List<String[]> phrases = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String phrase;

            while ((phrase = reader.readLine()) != null) {
                String engTranslation = reader.readLine();

                if (engTranslation != null) {
                    phrases.add(new String[]{phrase, engTranslation});
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return phrases;
    }

    public ArrayList<String> readLine(String path) {
        ArrayList<String> lines = new ArrayList<>();

        try ( BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return lines;
    }

    public boolean addToFile(String path, String newLine) {
        ArrayList<String> lines = readLine(path);

        if (!lines.contains(newLine)) {
            try ( BufferedWriter writer = new BufferedWriter(new FileWriter(path, true))) {

                writer.write(newLine);
                writer.newLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                return true;
            }
        }
        return false;
    }
}
