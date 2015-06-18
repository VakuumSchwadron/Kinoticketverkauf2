package de.uni_hamburg.informatik.swt.se2.kino.werkzeuge.bezahl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;

import java.text.NumberFormat;
import javax.swing.JFrame;

/**
 * Die UI des BezahlWerkzeug.
 * 
 * @author VakuumSchwadron
 * @version 17.06.2015
 */

public class BezahlWerkzeugUI
{
    private JDialog _dialog;
    private JButton _ok;
    private JButton _abbrechen;
    private JTextField _bezahlt;
    private JTextField _restbetrag;
    private JTextField _preis;

    private static final Color SCHRIFTFARBE_NORMAL = new Color(95, 247, 0);
    private static final Color HINTERGRUNDFARBE = new Color(30, 30, 30);
    private static final Font SCHRIFTART_GROSS = new Font("Monospaced",
            Font.BOLD, 28);
    private static final Font SCHRIFTART_KLEIN = new Font("Monospaced",
            Font.BOLD, 22);
    private static final int TEXTFELDBREITE = 12;

    /**
    * Konstruktor der Klasse BarzahlungsWerkzeugUI.java
    */
    public BezahlWerkzeugUI()
    {
        initDialog();
    }

    /**
    * Zeigt das Fenster an
    */
    public void setVisible(boolean visible)
    {
        _dialog.setLocationRelativeTo(null);
        _dialog.setVisible(visible);
    }

    /**
    * Verbirgt das Fenster.
    */
    public void verberge()
    {
        _dialog.setVisible(false);
    }

    /**
     * @return Textfeld für den gegebenen Betrag
     */
    public JTextField getBezahltFeld()
    {
        return _bezahlt;
    }

    /**
    * @return Textfeld für den Preis
    */
    public JTextField getPreisLabel()
    {
        return _preis;
    }

    /**
     * @return Textfeld für den Restbetrag
     */
    public JTextField getRestbetragLabel()
    {
        return _restbetrag;
    }

    /**
     * @return OK-Button
     */
    public JButton getOKButton()
    {
        return _ok;
    }

    /**
     * @return Abbrechen-Button
     */
    public JButton getAbbrechenButton()
    {
        return _abbrechen;
    }

    /**
     * @return das Dialogfenster, um zum Beispiel einen WindowListener daran zu
     *         registrieren.
     */
    public JDialog getDialog()
    {
        return _dialog;
    }

    private void initDialog()
    {
        _dialog = new JDialog((Frame) null, "BezahlWerkzeug");
        _dialog.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);

        _dialog.setLayout(new BorderLayout());
        _dialog.add(erstelleBetragsPanel(), BorderLayout.CENTER);
        _dialog.add(erstelleButtonPanel(), BorderLayout.SOUTH);

        _dialog.pack();
        _dialog.setResizable(false);
    }

    /**
     * Erstellt das ButtonPanel mit Ok und Abbrechen
     * 
     * @return das ButtonPanel
     */
    private JPanel erstelleButtonPanel()
    {
        JPanel rueckgabePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        _ok = new JButton("OK");
        rueckgabePanel.add(_ok);
        _ok.setEnabled(false);
        _abbrechen = new JButton("Abbrechen");
        rueckgabePanel.add(_abbrechen);
        return rueckgabePanel;
    }

    /**
     * Erstellt das Hauptpanel mit den drei Anzeigen für die Beträge und
     * beschriftet diese sinnvoll.
     * 
     * @return das Panel mit den Anzeigen für die Beträge
     */
    private JPanel erstelleBetragsPanel()
    {
        JPanel betraege = new JPanel();
        betraege.setLayout(new BoxLayout(betraege, BoxLayout.Y_AXIS));
        initPreisTextfeld();
        betraege.add(erstelleLayoutPanel("Preis", _preis));
        initGegebenTextfield();
        betraege.add(erstelleLayoutPanel("Bezahlt", _bezahlt));
        initRestTextfield();
        betraege.add(erstelleLayoutPanel("Restbetrag", _restbetrag));

        return betraege;
    }

    private void initRestTextfield()
    {
        _restbetrag = new JTextField(TEXTFELDBREITE);
        _restbetrag.setHorizontalAlignment(JTextField.RIGHT);
        _restbetrag.setBackground(HINTERGRUNDFARBE);
        _restbetrag.setForeground(SCHRIFTFARBE_NORMAL);
        _restbetrag.setEditable(false);
        _restbetrag.setFocusable(false);
        _restbetrag.setFont(SCHRIFTART_KLEIN);
    }

    private void initGegebenTextfield()
    {
        _bezahlt = new JTextField(TEXTFELDBREITE);

        _bezahlt.setCaretColor(SCHRIFTFARBE_NORMAL);
        _bezahlt.setHorizontalAlignment(JTextField.RIGHT);
        _bezahlt.setMargin(new Insets(5, 5, 5, 5));
        _bezahlt.setBackground(HINTERGRUNDFARBE);
        _bezahlt.setForeground(SCHRIFTFARBE_NORMAL);
        _bezahlt.setFont(SCHRIFTART_GROSS);
        _bezahlt.requestFocus();
    }

    private void initPreisTextfeld()
    {
        _preis = new JTextField(TEXTFELDBREITE);
        _preis.setHorizontalAlignment(JTextField.RIGHT);
        _preis.setBackground(HINTERGRUNDFARBE);
        _preis.setForeground(SCHRIFTFARBE_NORMAL);
        _preis.setFont(SCHRIFTART_KLEIN);
        _preis.setEditable(false);
        _preis.setFocusable(false);
    }

    /**
     * Erzeugt ein Textfeld mit Beschriftung in besonderer Anordnung-
     * 
     * @param titel Beschriftung des Textfeldes
     * @param component das Textfeld (oder eine andere Komponente)
     * @return ein Panel mit dem beschrifteten Textfeld
     */
    private Component erstelleLayoutPanel(String titel, JComponent component)
    {
        JPanel rueckgabePanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(titel);
        label.setHorizontalAlignment(JLabel.RIGHT);
        rueckgabePanel.add(label, BorderLayout.NORTH);
        rueckgabePanel.add(component, BorderLayout.CENTER);

        return rueckgabePanel;
    }

}
