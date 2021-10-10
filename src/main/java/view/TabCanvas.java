package view;

import javafx.scene.control.Tab;
import model.Model;

public class TabCanvas extends Tab {

    private CanvasModel canvas;

    public TabCanvas(Model model, String title) {
        super(title);

        this.canvas = new CanvasModel(model, 500,500);

        setText(title);
        setContent(canvas);
    }

    public TabCanvas(Model model) {
        this(model, "Fichier.ply");
    }
}
