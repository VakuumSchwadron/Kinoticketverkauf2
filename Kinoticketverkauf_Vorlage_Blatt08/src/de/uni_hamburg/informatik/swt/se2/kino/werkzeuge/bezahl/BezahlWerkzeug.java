package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahl;

import package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahl.BezahlWerkzeugUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.ObservableSubwerkzeug;

public class BezahlWerkzeug extends ObservableSubwerkzeug
{
	private BezahlWerkzeugUI _gui;
	
	public BezahlWerkzeug()
	{
		_gui = new BezahlWerkzeugUI();
	}
	
	private void reagiereAufUIAktionen()
	{
		_gui.getOkButton().addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				guiBeende();
				resetBezahlt();
				
				informiereUeberAenderung(); 
			}
		});
		
		_gui.getAbbrechenButton().AddActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				guiBeende();
				resetBezahlt();
			}
		});
		
		_gui.getBezahltFeld().AddKeyListener(new KeyListener()
		{
			@Override
			public void keyTyped(KeyEvent e)
			{
				
				int i = e.getKeyChar();
				if (i >= 48 && i <= 57)
				{
					aktualisiereRestbetrag();
				}
				else if(e.getKeyChar()==KeyEvent.CHAR_UNDEFINED)
				{
					// Tue nichts, weil keine Unicode Eingabe
				}
				else
				{
					_gui.getBezahltFeld().setText("Idiot!"); //TODO kleine Aenderungen durchfuehren...
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}

			});
		
		//TODO Evtl das x für Dialog einbauen
		
	}
	
	private void guiZeigeAn()
	{
		_gui.setVisible(true);
	}
	
	private void guiBeende()
	{
		_gui.setVisible(false);
	}
	
	private void aktualisiereRestbetrag()
	{
		//TODO
	}
	
	private void resetBezahlt()
	{
		_gui.getBezahltFeld().setText("0");
	}
		
	private void setPreis(int i)
	{
		String s = new Integer(i).toString();
		_gui.getPreisLabel().setText(s);
		
	}
		
	private void okButtonAktualisieren()
	{
		
	}
	
	public void neuerVerkauf(int preis)
	{
		setPreis(preis);
		aktualisiereRestbetrag();
		guiZeigeAn();
	}

}
