package view.stages;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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
        Runnable kcImport = () -> {
            try {
				toolBar.onOpenClicked(new ActionEvent());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.O, KeyCodeCombination.CONTROL_DOWN), kcImport);

        // Ctrl + W
        Runnable kcCloseRequest = () -> {
            System.out.println("CTRL W");
            toolBar.onCloseRequest(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN), kcCloseRequest);

        // Ctrl + ->
        Runnable kcNextRequest = () -> {
        	System.out.println("CTRL RIGHT");
        	toolBar.onNextRequest(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN), kcNextRequest);
        
        // Ctrl + <-
        Runnable kcPreviousRequest = () -> {
        	System.out.println("CTRL LEFT");
        	toolBar.onPreviousRequest(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN), kcPreviousRequest);
    }
}
