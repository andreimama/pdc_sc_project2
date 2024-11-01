/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package languagelearner;

import java.lang.reflect.Field;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author mamar
 */


//This class is so that EasyPanelTest can access private variables in EasyPanel class without
//Using this instead of getter methods maintains encapsulation and reduces code smell.
//Chatgpt was used to help setup just the first couple methodsmethod.
public class EasyPanelTestUtils {

    public static String getPhrase() {
        try {
            // Get the `phrase` field from EasyPanel
            Field phraseField = EasyPanel.class.getDeclaredField("phrase");
            phraseField.setAccessible(true); //Make the field accessible
            return (String) phraseField.get(null); //Use `null` because `phrase` is static
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null; // Return null if there's an error accessing the field
        }
    }
    
    public static void setPhrase(String newPhrase) {
        try {
            Field phraseField = EasyPanel.class.getDeclaredField("phrase");
            phraseField.setAccessible(true);
            phraseField.set(null, newPhrase); //null for static fields
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
   public static void setLang(boolean newLang) {
        try {
            Field langField = EasyPanel.class.getDeclaredField("lang");
            langField.setAccessible(true);
            langField.set(null, newLang); //null for static fields
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static JLabel getNorthLabel() {
        try {
            Field northLabelField = EasyPanel.class.getDeclaredField("northLabel");
            northLabelField.setAccessible(true);
            return (JLabel) northLabelField.get(null); //Use null for static fields
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JButton getButton1() {
        try {
            Field button1Field = EasyPanel.class.getDeclaredField("button1");
            button1Field.setAccessible(true);
            return (JButton) button1Field.get(null); //Use null for static fields
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static JLabel getCounterLabel() {
        try {
            Field counterLabelField = EasyPanel.class.getDeclaredField("counterLabel");
            counterLabelField.setAccessible(true);
            return (JLabel) counterLabelField.get(null); //Use null for static fields
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
