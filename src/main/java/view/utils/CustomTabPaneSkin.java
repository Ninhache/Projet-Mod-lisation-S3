package view.utils;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.skin.TabPaneSkin;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import view.components.render.TabCanvas;

import java.util.Arrays;
import java.util.Collection;

/**
 * The CustomTabPaneSkin adds a placeholder when the TabPane's parent has no child
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 * @see javafx.scene.control.skin.TabPaneSkin
 * @see TabCanvas
 */
public class CustomTabPaneSkin extends TabPaneSkin {

    private final VBox placeHolder;
    private final Label placeHolderText;

    public CustomTabPaneSkin(TabPane tabPane) {
        this(tabPane, new Node[0]);
    }

    public CustomTabPaneSkin(TabPane tabPane, Node... widgets) {
        this(tabPane, Arrays.asList(widgets));
    }

    public CustomTabPaneSkin(TabPane tabPane, Collection<Node> widgets) {
        super(tabPane);

        placeHolderText = new Label("test");
        placeHolderText.fontProperty().setValue(Font.font( null, FontWeight.BOLD, 20 ));
        placeHolderText.setAlignment( Pos.CENTER );
        placeHolderText.minWidthProperty().bind(getSkinnable().widthProperty());
        placeHolderText.minHeightProperty().bind(getSkinnable().heightProperty());
        placeHolder = new VBox();

        if (widgets != null && widgets.size() > 0) {

            Region spacer = new Region();
            spacer.setMinHeight(0);
            spacer.setPrefHeight(4);
            spacer.maxHeightProperty().bind(spacer.prefHeightProperty());
            HBox.setHgrow(spacer, Priority.ALWAYS);

            FlowPane container = new FlowPane(Orientation.HORIZONTAL);

            container.setPadding(Insets.EMPTY);
            container.setAlignment(Pos.CENTER);
            container.setVgap(2);
            container.setHgap(4);

            HBox.setHgrow(container, Priority.ALWAYS);

            container.getChildren().addAll(widgets);

            placeHolder.getChildren().addAll(spacer, container);
        }

        for ( Node node : getChildren() ) {
            if ( node.getStyleClass().contains("tab-header-area" )) {
                Pane headerArea = (Pane) node;
                headerArea.visibleProperty().addListener( ( observable, oldValue, newValue ) -> {
                            if (newValue) {
                                getChildren().remove(placeHolder);
                            } else {
                                getChildren().add(placeHolder);
                            }
                });
                break;
            }
        }
    }
}