package view.stages;

import java.io.FileNotFoundException;
import java.util.Objects;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.IntegerBinding;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import model.maths.Rotation;
import view.control.RightMenu;
import view.control.SuperToolBar;
import view.control.TabCanvasPane;
import view.utils.AppUtil;
import view.utils.CustomTabPaneSkin;

/**
 * The Main Stage where everything happens like control, rendering, button..
 * @author Paul VANHEE - Neo ALMEIDA
 */
public class MainStage extends ExtendedStage {

    private SuperToolBar toolBar;
    private TabCanvasPane tabPane;
    private ToggleButton translate, rotate;
    private RightMenu rightMenu;

    private double mousePosX, mouseOldY, mousePosY, mouseOldX;
    public MainStage() {
        super();

        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1920,1080);
            setScene(scene);

        setMinWidth(1000);

        toolBar = new SuperToolBar();
        tabPane = new TabCanvasPane();
            tabPane.setSkin(new CustomTabPaneSkin(tabPane, toolBar.getOpenActionsLink()));
            loadingUpdate();


        rightMenu = new RightMenu();



        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/css/theme/ThemeBlanc.css")).toExternalForm());

        root.setTop(toolBar);
        root.setCenter(tabPane);
        root.setRight(rightMenu);

        setTitle(AppUtil.APPLICATION_NAME);

        setupListeners();
        setupRunnable();
        setupDisabledComponents();

        setWidth(Screen.getPrimary().getBounds().getWidth()/2);
        setHeight(Screen.getPrimary().getBounds().getHeight()/2);
        setMaximized(true);



    }

    // FONCTION QUI SERT A CE QUE LE SKIN SOIT PRIS EN COMPTE
    private void loadingUpdate() {
        Tab tab = new Tab();
        tabPane.getTabs().add(tab);
        tabPane.getTabs().clear();
    }

    private void setupListeners() {
        widthProperty().addListener((observable, oldValue, newValue) -> tabPane.getTabs().forEach(tabs -> {
            System.out.println(tabs.getContent());
            //CanvasModelTop c = (CanvasModelTop) tabs.getContent();
            //c.setWidth(newValue.doubleValue());
        }));

        heightProperty().addListener((observable, oldValue, newValue) -> tabPane.getTabs().forEach(tabs -> {
            System.out.println(tabs.getContent());
            //CanvasModelTop c = (CanvasModelTop) tabs.getContent();
            //c.setHeight(newValue.doubleValue());
        }));
    }

    private void setupDisabledComponents() {
        IntegerBinding integerBinding = Bindings.size(tabPane.getTabs());
        BooleanBinding listNotNull = integerBinding.greaterThan(0);
        rightMenu.getModelAccordion().getSlidersModel().disableProperty().bind(listNotNull.not());

        this.toolBar.setupDisabledStuff();
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
        Runnable kcCloseRequest = () -> tabPane.onCloseRequest(new ActionEvent());
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.W, KeyCombination.CONTROL_DOWN), kcCloseRequest);

        // Ctrl + R
        Runnable kcRotateH = () -> tabPane.rotateModel(Rotation.X,15);
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN), kcRotateH);

        // Ctrl + shift + r
        Runnable kcRotateAH = () -> tabPane.rotateModel(Rotation.X,-15);
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN), kcRotateAH);

        // Ctrl + T
        Runnable kcTranslateR = () -> tabPane.translateModel(0,10,0);
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN), kcTranslateR);

        // Ctrl + Shift + T
        Runnable kcTranslateL = () -> tabPane.translateModel(0,-10,0);
        getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.T, KeyCombination.CONTROL_DOWN, KeyCombination.SHIFT_DOWN), kcTranslateL);

    }


    public SuperToolBar getToolBar() {
        return toolBar;
    }
    
}
