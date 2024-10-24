

package com.mycompany.pdc_sc_project2;

/**
 *
 * @author mamar
 */

//basically u have all your methods and logic in Model class, Controller class is just there to listen to events through actionPerformed.
//this data is just info that the model needs to communicate to all the view panels, you can try connect funFacts and phrases thrugh here.
public class Data {
    boolean lang = true;//True = Afrikaans, false = Tagalog
    int currentScore = 0;
    String funFact;
    
}
