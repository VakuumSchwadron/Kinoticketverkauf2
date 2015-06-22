package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahl;

import java.awt.Color;
//import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

/**
* Mit diesem Werkzeug kann die Bezahlung der ausgewählten Plätze vorgenommen werden. 
* Wird der Bezahlvorgang durch das Klicken auf der Verkaufen-Eingeleitet, erscheint 
* ein neues Fenster. Dort werden der Preis für die ausgewählten Tickets und der noch 
* ausstehende Betrag angegeben. Der vom Kunden bezahlte Betrag kann vom Anwender 
* eingetragen werden. 
* 
* Dieses Werkzeug ist ein eingebettetes Subwerkzeug. Es benachrichtigt sein
* Kontextwerkzeug, wenn eine Bezahlung abgeschlossen wurde.
* 
* @author VakuumSchwadron
* @version SoSe 2015
*/
public class BezahlWerkzeug extends ObservableSubwerkzeug
{
    //Exemplarvariabelen
    private BezahlWerkzeugUI _gui;
    private WaehrungUI _wgui;
    private int _preis;

    /**
     * Initialisiert das BezahlWerkzeug.
     */
    public BezahlWerkzeug()
    {
        _gui = new BezahlWerkzeugUI();
        _wgui = new WaehrungUI();
        _preis = 0;
        reagiereAufWGUIAktionen();
        reagiereAufUIAktionen();
    }

    /**
     * Fügt der UI die Funktionalität hinzu mit entsprechenden Listenern.
     */
    private void reagiereAufUIAktionen()
    {
        _gui.getOKButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    guiBeende();
                    resetBezahlt();

                    informiereUeberAenderung();
                }
            });

        _gui.getAbbrechenButton()
            .addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    guiBeende();
                    resetBezahlt();
                }
            });

        _gui.getBezahltFeld()
            .addKeyListener(new KeyListener()
            {
                @Override
                public void keyReleased(KeyEvent e)
                {
                    char c = e.getKeyChar();
                    if (Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE
                            || c == KeyEvent.VK_DELETE)
                    {
                        aktualisiereRestbetrag();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e)
                {

                }

                @Override
                public void keyTyped(KeyEvent e)
                {
                    char c = e.getKeyChar();
                    if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE))
                    {
                        e.consume();
                    }
                }

            });

        _gui.getWaehrungLabel().addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {

			}
			
			@Override
			public void mouseExited(MouseEvent e) {
//				Font font = _gui.getWaehrungLabel().getFont();
//				// same font but bold
//				Font boldFont = new Font(font.getFontName(), Font.PLAIN, font.getSize()-1);
//				_gui.getWaehrungLabel().setFont(boldFont);
				
				_gui.getWaehrungLabel().setForeground(Color.black);
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
//				Font font = _gui.getWaehrungLabel().getFont();
//				// same font but bold
//				Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize()+1);
//				_gui.getWaehrungLabel().setFont(boldFont);
				
				_gui.getWaehrungLabel().setForeground(Color.red);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				_gui.setVisible(false);
				_wgui.setVisible(true);
				
			}
		});
        
    }
    
    private void reagiereAufWGUIAktionen()
    {
    	_wgui.getAbbrechenButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				_wgui.setVisible(false);
				_gui.setVisible(true);
				
				
			}
		
			
			
		});
    	
    	_wgui.getOkButton().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				passeWaehrungAn((String) _wgui.getComboBox().getSelectedItem());
				_wgui.setVisible(false);
				_gui.setVisible(true);
				
			}
		});
    	
    }
    
    /**
     * Die Benutzerschnittstelle für die Barzahlung ist für den Anwender sichtbar.
     */
    private void guiZeigeAn()
    {
        _gui.setVisible(true);
    }

    /**
     * Die Benutzerschnittstelle für die Barzahlung ist für den Benutzer nicht mehr sichtbar.
     */
    private void guiBeende()
    {
        _gui.setVisible(false);
    }

    /**
     * Der Restbetrag wird neu aus dem Preis der ausgewählten Plätze und der vom Kunden bereits getätigten
     * Zahlung berechnet. Der neue Restbetrag wird dann in der Benutzerschnittstelle angezeigt.
     */
    private void aktualisiereRestbetrag()
    {
        int preis = Integer.parseInt(_gui.getPreisLabel()
            .getText());
        int restbetrag;

        if (_gui.getBezahltFeld()
            .getText()
            .length() > 0)
        {
            int bezahlt = Integer.parseInt(_gui.getBezahltFeld()
                .getText());
            restbetrag = bezahlt - preis;
        }
        else
        {
            restbetrag = -preis;
        }
        _gui.getRestbetragLabel()
            .setText(Integer.toString(restbetrag));
        okButtonAktualisieren();
    }

    //TODO zu lange Strings verbieten! 

    /**
     * Leert das Feld, in dem der Anwender die vom Kunden gezahlte Summe eintragen kann.
     */
    private void resetBezahlt()
    {
        _gui.getBezahltFeld()
            .setText("");
    }

    /**
     * Setzt den vom Preislabels angezeigten Wert. 
     * 
     * @param i Preis, der vom Preislabel angezeigt werden soll
     */
    private void setPreis(int i)
    {
        String s = new Integer(i).toString();
        _gui.getPreisLabel()
            .setText(s);
    }
    
    /**
     * Diese Methode gibt an, ob der Kunde eine ausreichende Summe gezahlt hat.
     *
     * @return true, wenn der Kunde mindestens den Preis der ausgewählten Tickets bezahlt hat
     */
    private boolean istVerkaufenMoeglich()
    {
        int preis = Integer.parseInt(_gui.getPreisLabel()
            .getText());
        int restbetrag;

        if (_gui.getBezahltFeld()
            .getText()
            .length() > 0)
        {
            int bezahlt = Integer.parseInt(_gui.getBezahltFeld()
                .getText());
            restbetrag = bezahlt - preis;
        }
        else
        {
            restbetrag = -preis;
        }

        return restbetrag >= 0;
    }

    /**
     * Der Ok-Button wird der aktuellen Bezahl-Sitation angepasst. Ist der Verkauf der ausgewählten
     * Plätze möglich, dh. wurde eine ausreichende Summe gezahlt, soll der Button zu betätigen sein.
     * Andernfalls nicht.
     */
    private void okButtonAktualisieren()
    {
        _gui.getOKButton()
            .setEnabled(istVerkaufenMoeglich());
    }

    /**
     * Passt die Anzeige der Benutzerschnittstelle für einen neuen Verkauf ohne bisherige Zahlung
     * und für einen gegeben Preis an. 
     *
     * @param preis Preis der ausgewählten Tickets
     * 
     * @require preis > 0
     */
    public void neuerVerkauf(int preis)
    {
        assert preis > 0 : "Vorbedingung verletzt"; 
        
        _preis = preis;
        setPreis(preis);
        _gui.getRestbetragLabel()
            .setText(new Integer(-preis).toString());
        guiZeigeAn();
    }
    
    private void passeWaehrungAn(String s)
    {
    	//Preis umrechnen und setzen
    	// Bezahlt auf ""
    	// Restbetrag auf -Preis (aktualisiere Restbetrag)
    	
    	_gui.getWaehrungLabel().setText(s);
    	int preis = _preis;
    	
    	if (s.equals("Eurocent"))
    	{
       	}
    	else if (s.equals("Steine"))
    	{
    		preis = _preis * 4;
    	}
    	else if (s.equals("Bonbons"))
    	{
    		preis = _preis / 10;
    	}
    	else if (s.equals("Blowjob"))
    	{
    		preis = 1;
    	}
    	else if (s.equals("Hamster"))
    	{
    		preis = _preis / 200;
    	}
    	else if (s.equals("Ich bin Chuck Norris"))
    	{
    		preis = 0;
    	}
    	else if (s.equals("Dollarcent"))
    	{
    		preis = (114 * _preis)/100;
    	}
    	
    	_gui.getPreisLabel()
        .setText(new Integer(preis).toString());
		_gui.getRestbetragLabel()
        .setText(new Integer(-preis).toString());
		_gui.getBezahltFeld().setText("");
		_gui.getDialog().pack();
    }

}
