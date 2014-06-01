package Controlleur;

import java.util.List;

/**
 * @author Jude Seide aka spectro
 */
public interface InterfaceGardien {

    public void addCommand(MementoCommande m);

    public void clearRedo();

    public boolean hasRedo();

    public boolean hasUndo();

    public MementoCommande redo();

    public MementoCommande undo();

    public void addMacro(MementoMacro m);

    public void clearListeMacros();

    public List<MementoMacro> listeMacros();
}
