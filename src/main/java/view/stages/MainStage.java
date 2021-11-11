package view.stages;

import java.io.FileNotFoundException;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import model.Rotation;
import view.components.CanvasModel;
import view.components.misc.CustomTabPaneSkin;
import view.components.tiltedPane.LibraryPane;
import view.components.tiltedPane.ModelePane;
import view.components.tiltedPane.SlidersModel;
import view.components.tabpane.TabCanvas;
import view.nodes.RightMenu;
import view.nodes.SuperToolBar;
import view.nodes.TabCanvasPane;

public class MainStage extends ExtendedStage {

    private SuperToolBar toolBar;
    private TabCanvasPane tabPane;
    private ToggleButton translate, rotate;
    private ToggleGroup tg;
    private RightMenu rightMenu;

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

        rightMenu = new RightMenu();

        root.setTop(toolBar);
        root.setCenter(tabPane);
        root.setRight(rightMenu);

        tg = new ToggleGroup();
        translate = new ToggleButton("Translate");
        translate.setToggleGroup(tg);
        rotate = new ToggleButton("Rotate");
        rotate.setToggleGroup(tg);
        HBox movementButton = new HBox(translate,rotate);
        root.setBottom(movementButton);

        //movementButton.setVisible(false);


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
        setupDisabledComponents();

    }

    private void setupDisabledComponents() {
        IntegerBinding integerBinding = Bindings.size(tabPane.getTabs());
        BooleanBinding listGreaterThanZero = integerBinding.greaterThan(0);
        rightMenu.getModelAccordion().getSlidersModel().disableProperty().bind(listGreaterThanZero.not());
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

        // Ctrl + R
        Runnable kcRotateModel = () -> {
            tabPane.rotateModel(Rotation.X,15);
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN), kcRotateModel);

        // Ctrl + shift + r
        Runnable kcRotateInverseModel = () -> {
            tabPane.rotateModel(Rotation.X,-15);
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN), kcRotateInverseModel);

        // Ctrl + T
        Runnable kcTranslateModel = () -> {
            tabPane.translateModel(0,10,0);
        };
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN), kcTranslateModel);

        // Ctrl + Shift + T
        Runnable kcTranslateInverseModel = () -> {
            tabPane.translateModel(0,-10,0);
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
                tabPane.rotateModel(Rotation.X,(mousePosY - mouseOldY));
            } else if(selectedToggleButton.equals(translate)){
                tabPane.translateModel((mousePosX-mouseOldX),(mousePosY-mouseOldY),0);
            }
        }
        mouseOldX = mousePosX;
        mouseOldY = mousePosY;
    }

    public SuperToolBar getToolBar() {
        return toolBar;
    }
}
