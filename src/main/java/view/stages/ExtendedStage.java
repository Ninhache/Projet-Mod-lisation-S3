package view.stages;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The ExtendedStage is the base of all stages
 * All stages will have the same icons
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public abstract class ExtendedStage extends Stage {
    public ExtendedStage() {
        super();
        getIcons().add(new Image("/img/logo.png"));
    }

    public Tooltip setTooltip(Node node, String text) {
        Tooltip tooltip = new Tooltip(text);
        Tooltip.install(node, tooltip);
        return tooltip;
    }
    
}
