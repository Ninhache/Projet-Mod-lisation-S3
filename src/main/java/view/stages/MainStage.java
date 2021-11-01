package view.stages;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.BorderPane;
import view.CanvasModel;
import view.CustomTabPaneSkin;
import view.TabCanvas;
import view.dialogs.SuperToolBar;
import view.dialogs.TabCanvasPane;

public class MainStage extends ExtendedStage {

    private SuperToolBar toolBar;

    public MainStage() {
        super();

        BorderPane root = new BorderPane();
        toolBar = new SuperToolBar();

        TabCanvasPane tabPane = new TabCanvasPane();

        tabPane.setSkin(new CustomTabPaneSkin(tabPane, toolBar.getOpenActionsLink()));

        TabCanvas tab = new TabCanvas(null);
        tabPane.getTabs().add(tab);
        tabPane.getTabs().clear();

        root.setTop(toolBar);
        root.setCenter(tabPane);

        Scene scene = new Scene(root);
        setScene(scene);
        setTitle("test");

        scene.getStylesheets().add(getClass().getResource("/css/theme/ThemeBlanc.css").toExternalForm());

        widthProperty().addListener((observable, oldValue, newValue) -> {
            tabPane.getTabs().forEach(tabs -> {
                CanvasModel c = (CanvasModel) tabs.getContent();
                c.setWidth(newValue.doubleValue());
            });
        });

        heightProperty().addListener((observable, oldValue, newValue) -> {
            tabPane.getTabs().forEach(tabs -> {
                CanvasModel c = (CanvasModel) tabs.getContent();
                c.setHeight(newValue.doubleValue());
            });
        });

        setupRunnable();

    }

    private void setupRunnable() {

        // Ctrl + O
        Runnable kcImport = ()-> {
            toolBar.onOpenClicked(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCodeCombination.CONTROL_DOWN), kcImport);



    }
}
