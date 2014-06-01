package Vue;

import Controlleur.Arreter;
import Controlleur.Coller;
import Controlleur.Copier;
import Controlleur.Couper;
import Controlleur.Defaire;
import Controlleur.Demarer;
import Controlleur.Enregistrer;
import Controlleur.Observer;
import Controlleur.Refaire;
import Controlleur.Rejouer;
import Controlleur.Selectionner;
import Controlleur.Supprimer;
import Modele.Buffer;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.Caret;
import javax.swing.text.DefaultCaret;

/**
 * @author Jude Seide aka spectro
 */
public class UserInterface extends javax.swing.JFrame implements Observer {

    private static UserInterface instance = null;

    /**
     * Creates new form UserInterface
     */
    private UserInterface() {
        this.initComponents();
        setLocationRelativeTo(null);
        add(textArea);
        pack();
        setVisible(true);
    }

    public synchronized static UserInterface getInstance() {
        if (instance == null) {
            instance = new UserInterface();
        }
        return instance;
    }

    public int getDebut() {
        return textArea.getSelectionStart();
    }

    public int getFin() {
        return textArea.getSelectionEnd();
    }

    public void setPosition(int pos) {
        textArea.setCaretPosition(pos);
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }

    public String getInsertion() {
        return insertion;
    }

    public void setInsertion(String insertion) {
        this.insertion = insertion;
    }

    void displayCursor() {
        textArea.getCaret().setVisible(true);
    }

    public void setMacro(boolean b_macro) {
        this.b_macro = b_macro;
        demarer.setEnabled(!b_macro);
        arreter.setEnabled(b_macro);
        rejouer.setEnabled(!b_macro);
    }

    public boolean isMacro() {
        return b_macro;
    }

    public void setUndo(boolean b) {
        defaire.setEnabled(b);
    }

    public void setRedo(boolean b) {
        refaire.setEnabled(b);
    }

    private void initComponents() {
        textArea = new JTextArea(30, 60);
        panneau = new JPanel();
        setTitle("Editeur de texte");
        setContentPane(panneau);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu = new JPopupMenu();
        edition = new JMenu("Edition");
        macro = new JMenu("Macro");

        demarer = new Demarer();
        demarer.setText("Demarer Enregistrement");

        arreter = new Arreter();
        arreter.setText("Arreter Enregistrement");
        arreter.setEnabled(false);

        rejouer = new Rejouer();
        rejouer.setText("Rejouer Enregistrement");
        rejouer.setEnabled(false);

        defaire = new Defaire();
        defaire.setText("Defaire");
        defaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
        defaire.setEnabled(false);

        refaire = new Refaire();
        refaire.setText("Refaire");
        refaire.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.CTRL_MASK));
        refaire.setEnabled(false);

        selectionner = new Selectionner();
        selectionner.setText("Selectionner");
        selectionner.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_UP | KeyEvent.VK_DOWN | KeyEvent.VK_RIGHT
                | KeyEvent.VK_LEFT, InputEvent.SHIFT_MASK));

        supprimer = new Supprimer();
        supprimer.setText("Supprimer");
        supprimer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));

        copier = new Copier();
        copier.setText("Copier");
        copier.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));

        couper = new Couper();
        couper.setText("Couper");
        couper.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));

        coller = new Coller();
        coller.setText("Coller");
        coller.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));

        enregistrer = new Enregistrer();
        enregistrer.setText("Enregistrer");
        enregistrer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));

        /* Ajouter les choix aux menus  */
        macro.add(demarer).addActionListener(new Listener());
        macro.add(arreter).addActionListener(new Listener());
        macro.add(rejouer).addActionListener(new Listener());

        edition.add(selectionner).addActionListener(new Listener());
        edition.add(supprimer).addActionListener(new Listener());
        edition.add(defaire).addActionListener(new Listener());
        edition.add(refaire).addActionListener(new Listener());
        edition.add(copier).addActionListener(new Listener());
        edition.add(couper).addActionListener(new Listener());
        edition.add(coller).addActionListener(new Listener());
        edition.add(enregistrer).addActionListener(new Listener());

        /* Inserer le menu macro dans le menu edition */
        edition.addSeparator();
        edition.add(macro);

        /* Ajouter le menu sur la pop up de menu */
        menu.add(edition);

        fonte = new Font("TimesRoman", Font.PLAIN, TAILLE);

        DefaultCaret caret = new DefaultCaret();
        caret.setBlinkRate(0);
        caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        caret.setVisible(true);
        cursor = caret;

        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setCaret(cursor);
        textArea.getCaret().setVisible(true);
        textArea.setFont(fonte);
        textArea.setCaretColor(Color.BLACK);
        textArea.addKeyListener(new Listener());
        textArea.setComponentPopupMenu(menu);
    }

    @Override
    public void update() {
        UserInterface.getInstance().setText(Buffer.getInstance().getState());
        UserInterface.getInstance().setPosition(Buffer.getInstance().getPosition());
    }
    private Caret cursor;
    private String insertion = "";
    private JPopupMenu menu;
    private JMenu edition;
    private JMenu macro;
    private JMenuItem selectionner;
    private JMenuItem supprimer;
    private JMenuItem demarer;
    private JMenuItem arreter;
    private JMenuItem rejouer;
    private JMenuItem defaire;
    private JMenuItem refaire;
    private JMenuItem copier;
    private JMenuItem couper;
    private JMenuItem coller;
    private JMenuItem enregistrer;
    private JPanel panneau;
    private JTextArea textArea;
    private Font fonte;
    private final int TAILLE = 12;
    private boolean b_macro = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Buffer.getInstance().attach(UserInterface.getInstance());
                UserInterface.getInstance().setVisible(true);
            }
        });
    }
}
