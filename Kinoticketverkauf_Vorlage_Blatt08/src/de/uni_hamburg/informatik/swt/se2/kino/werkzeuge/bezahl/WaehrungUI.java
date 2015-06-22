package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahl;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Die GUI für die Änderung der Währung
 * 
 * @author VakuumSchwadron
 * @version SoSe15
 *
 */
public class WaehrungUI
{

    JDialog _fenster;
    JButton _ok;
    JButton _abbrechen;
    JComboBox<String> _liste;
    
    /**
     * Initialisiert die Währungs-GUI
     */
    public WaehrungUI()
    {
       _fenster = new JDialog();
       _fenster.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
       _fenster.setUndecorated(true);
       _fenster.setLayout(new BorderLayout());
       _fenster.setBounds(500,300,300,165);
       _ok = new JButton("OK");
       _abbrechen = new JButton("Abbrechen");
       
       String[] auswahl = {"Eurocent", "Dollarcent", "mBitcoin", "Bonbons","Hamster",  "Blowjob", "Steine", "Kunde ist Chuck Norris"};
       _liste = new JComboBox<String>(auswahl);
       
       JPanel sueden = initialisiereButtons();
       JPanel center = initialisiereListe();
       
       _fenster.add(sueden, BorderLayout.SOUTH);
       _fenster.add(center, BorderLayout.CENTER);
    }
    
    /**
     * Bindet die Buttons in ein Panel ein
     * 
     * @return panel Panel, in das der Abbrechen- und der ok-Button eingebunden sind
     */
    private JPanel initialisiereButtons()
    {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel.add(_ok);
        panel.add(_abbrechen);
        
        return panel;
    }

    /**
     * 
     * @return panel
     */
    private JPanel initialisiereListe()
    {
        JPanel panel = new JPanel();
        JPanel auswahl = new JPanel();
        JLabel ueberschrift = new JLabel("Währung der aktuellen Bezahlung");
        ueberschrift.setHorizontalAlignment(JLabel.CENTER);
        ueberschrift.setVerticalAlignment(JLabel.CENTER);
        
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        panel.setLayout(new BorderLayout(0,5));
        panel.add(ueberschrift, BorderLayout.NORTH);
        
        auswahl.add(_liste);
        panel.add(auswahl, BorderLayout.CENTER);
        
                
        return panel;
     }
    
    public void setVisible(boolean b)
    {
        _fenster.setVisible(b);
    }
    
    public JButton getOkButton()
    {
    	return _ok;
    }
    
    public JButton getAbbrechenButton()
    {
    	return _abbrechen;
    }
    
    public JComboBox<String> getComboBox()
    {
    	return _liste;
    }
    
   
}
