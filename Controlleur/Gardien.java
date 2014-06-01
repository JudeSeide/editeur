package Controlleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author Jude Seide aka spectro
 */
public class Gardien implements InterfaceGardien {

    private static Gardien instance = null;
    private final Stack<MementoCommande> refaire;
    private final Stack<MementoCommande> defaire;
    private final List<MementoMacro> listeMacros;

    private Gardien() {
        refaire = new Stack<>();
        defaire = new Stack<>();
        listeMacros = new ArrayList<>();
    }

    public synchronized static Gardien getInstance() {
        if (instance == null) {
            instance = new Gardien();
        }
        return instance;
    }

    @Override
    public void addCommand(MementoCommande m) {
        defaire.push(m);
    }

    @Override
    public void clearRedo() {
        if (hasRedo()) {
            refaire.clear();
        }
    }

    @Override
    public boolean hasRedo() {
        return refaire.size() > 0;
    }

    @Override
    public boolean hasUndo() {
        return defaire.size() > 0;
    }

    @Override
    public MementoCommande redo() {
        if (hasRedo()) {
            return defaire.push(refaire.pop());
        }
        return null;
    }

    @Override
    public MementoCommande undo() {
        if (hasUndo()) {
            return refaire.push(defaire.pop());
        }
        defaire.clear();
        return null;
    }

    @Override
    public void addMacro(MementoMacro m) {
        listeMacros.add(m);
    }

    @Override
    public void clearListeMacros() {
        if (!listeMacros.isEmpty()) {
            listeMacros.clear();
        }
    }

    @Override
    public List<MementoMacro> listeMacros() {
        return listeMacros;
    }
}
