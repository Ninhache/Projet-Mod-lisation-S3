package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;

/**
 * A link is a clickable text.
 * You can instanciate with an event, which will be activate on click
 *
 * @author Néo ALMEIDA
 */
public class ActionLink extends Hyperlink {
    public ActionLink(String text) {
        super(text);
    }

    public ActionLink(String text, Node graphic) {
        super(text, graphic);
    }

    public ActionLink(String text, EventHandler<ActionEvent> onClick) {
        this(text, onClick, null);
    }

    public ActionLink(String text, EventHandler<ActionEvent> onClick, Node graphic) {
        super(text, graphic);
        setOnAction(onClick);
    }

    public void setAction(EventHandler<ActionEvent> onClick){
        setOnAction(onClick);
    }

}
