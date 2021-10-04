package view.stages;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

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
