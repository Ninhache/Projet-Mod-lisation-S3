package view.stages;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * The ExtendedStage is the base of all stage
 * All stage will have the same icons
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public abstract class ExtendedStage extends Stage {
    public ExtendedStage() {
        super();
        //getIcons().add(Constant.img_du_projet???);
    }

    public Tooltip setTooltip(Node node, String text) {
        Tooltip tooltip = new Tooltip(text);
        Tooltip.install(node, tooltip);
        return tooltip;
    }

}
