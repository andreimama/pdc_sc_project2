
package com.mycompany.pdc_sc_project2;

import java.awt.List;
import java.util.ArrayList;

/**
 *
 * @author mamar
 */
public class Model {
    public boolean lang; //true = afrikaans, false = tagalog
    public Data data = new Data();
  //  private List<PanelListener> listeners = new ArrayList<>();
    
    private PanelListener listener;
    
    //makes a listener
    public void setListener(PanelListener listener) {
        this.listener = listener;
    }
    
    public void updateLanguageFlag(boolean lang) {
        this.lang = lang;
        data.lang = lang;
        System.out.println("yeah notifylistenr called");
        notifyListener();
    }
    
    public String getCurrentLanguage() {
        return lang ? "Afrikaans" : "Tagalog";
    }
    
    //notifys listener when data is updated
    private void notifyListener() {
        
       // for (PanelListener listener : listeners) {
         //   listener.onUpdated(this.data);
        if (listener != null) {
         listener.onUpdated(this.data);
        }
        
    }
    
    //once everything works put this in.
    /*public void quitGame() {
        
    }*/
    
}
