package view.stages;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import view.CanvasModel;
import view.CustomTabPaneSkin;
import view.SlidersModel;
import view.dialogs.TabCanvas;
import view.dialogs.SuperToolBar;
import view.dialogs.TabCanvasPane;

public class MainStage extends ExtendedStage {

    private SuperToolBar toolBar;
    private TabCanvasPane tabPane;
    private ToggleButton translate, rotate;
    private ToggleGroup tg;
    private SlidersModel slidModel;

    private double mousePosX, mouseOldY, mousePosY, mouseOldX;
    public MainStage() {
        super();

        BorderPane root = new BorderPane();
        toolBar = new SuperToolBar();

        tabPane = new TabCanvasPane();

        tabPane.setSkin(new CustomTabPaneSkin(tabPane, toolBar.getOpenActionsLink()));

        TabCanvas tab = new TabCanvas(null);
        tabPane.getTabs().add(tab);
        tabPane.getTabs().clear();

        slidModel = new SlidersModel();
        
        root.setTop(toolBar);
        root.setCenter(tabPane);
        root.setRight(slidModel);


        tg = new ToggleGroup();
        translate = new ToggleButton("Translate");
        translate.setToggleGroup(tg);
        rotate = new ToggleButton("Rotate");
        rotate.setToggleGroup(tg);
        HBox movementButton = new HBox(translate,rotate);
        root.setBottom(movementButton);



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


        //Déplacement souris
        tabPane.setOnMouseDragged(event -> {
            mousePosX = event.getSceneX();
            mousePosY = event.getSceneY();
            movingModel(event);
        });
        tabPane.setOnMousePressed(this::movingModel);

        // Ctrl + W
        Runnable kcCloseRequest = () -> {
            tabPane.onCloseRequest(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN), kcCloseRequest);

        //Ctrl shift ==>
        Runnable kcTabMoveToRight = () -> {
            tabPane.onSwitchToRight(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN), kcTabMoveToRight);

        //Ctrl shift <==
        Runnable kcTabMoveToLeft = () -> {
            tabPane.onSwitchToLeft(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN), kcTabMoveToLeft);

        // Ctrl + R
        Runnable kcRotateModel = () -> {
            tabPane.rotateModel(new ActionEvent(),15);
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN), kcRotateModel);

        // Ctrl + shift + r
        Runnable kcRotateInverseModel = () -> {
            tabPane.rotateInverseModel(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN), kcRotateInverseModel);

        // Ctrl + T
        Runnable kcTranslateModel = () -> {
            tabPane.translateModel(new ActionEvent(),10,0);
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN), kcTranslateModel);

        // Ctrl + Shift + T
        Runnable kcTranslateInverseModel = () -> {
            tabPane.translateInverseModel(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN), kcTranslateInverseModel);

        /*

        // deja implémenté, suffit de faire flèche droite/gauche
        // Ctrl + ->
        Runnable kcNextRequest = () -> {
        	System.out.println("CTRL RIGHT");
            tabPane.onNextRequest(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.RIGHT, KeyCombination.CONTROL_DOWN), kcNextRequest);

        // Ctrl + <-
        Runnable kcPreviousRequest = () -> {
        	System.out.println("CTRL LEFT");
            tabPane.onPreviousRequest(new ActionEvent());
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.LEFT, KeyCombination.CONTROL_DOWN), kcPreviousRequest);
         */
    }

    private void movingModel(MouseEvent event) {
        mousePosX = event.getSceneX();
        mousePosY = event.getSceneY();
        ToggleButton selectedToggleButton = (ToggleButton) tg.getSelectedToggle();
        if(selectedToggleButton != null){
            if(selectedToggleButton.equals(rotate)){
                tabPane.rotateModel(event, (mousePosY - mouseOldY));
            } else if(selectedToggleButton.equals(translate)){
                tabPane.translateModel(event, (mousePosX-mouseOldX),(mousePosY-mouseOldY));
            }
        }
        mouseOldX = mousePosX;
        mouseOldY = mousePosY;
    }

    public SuperToolBar getToolBar() {
        return toolBar;
    }
}
