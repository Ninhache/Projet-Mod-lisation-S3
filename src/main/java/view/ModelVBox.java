package view;

import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.InternetUtil;
import model.io.OnlineModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import view.stages.OnlineLibraryStage;

import java.io.File;
import java.io.FileWriter;

/**
 * The ModelVbox adds a JavaFX Vbox Model for the preview of Model (Online Library)
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class ModelVBox extends VBox {

    private OnlineModel model;
    private ImageView imageView;

    public ModelVBox(OnlineModel model) {
        super();

        this.model = model;

        this.imageView = new ImageView(model.getImglink());
        this.imageView.setFitWidth(100);
        this.imageView.setFitHeight(100);

        setPadding(new Insets(5));

        getChildren().addAll(imageView, new Label(model.getName()));

        setOnMouseClicked(event -> {
            if(event.getButton() == MouseButton.SECONDARY) {
                openContextualMenu(event);
                setSelectioned();
            } else if(event.getClickCount() == 1) {
                setSelectioned();
            } else if(event.getClickCount() == 2) {
                openDataFromModel(model);
                ((OnlineLibraryStage) getScene().getWindow()).close();
            }
        });

    }

    /**
     * Opens the data from the model
     * @param model
     */
    private void openDataFromModel(OnlineModel model) {
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(InternetUtil.sendHttpGETRequest("http://178.170.9.238:3000/Model?name=" + model.getName()));

            JSONObject dataK = (JSONObject) data.get("data");

            OnlineLibraryStage ols = (OnlineLibraryStage) getScene().getWindow();

            File f = new File(model.getName() + ".ply");
            try {
                if(f.exists()){
                    f.delete();
                }
                if(f.createNewFile()){
                    try (FileWriter fw = new FileWriter(f)) {
                        fw.append((String)dataK.get("contents"));
                    }
                }
                TabCanvasPane tp = ols.getTp();
                tp.addModel(f);
                f.delete();

            } catch (Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a contextual menu
     * @param event
     */
    private void openContextualMenu(MouseEvent event) {
        ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("Ouvrir");
        MenuItem menuItem2 = new MenuItem("Ouvrir sans fermer");
        MenuItem menuItem3 = new MenuItem("Fermer");
        contextMenu.getItems().addAll(menuItem1, menuItem2, new SeparatorMenuItem(), menuItem3);
        contextMenu.show(this, event.getScreenX(), event.getScreenY());
        menuItem1.setOnAction(event1 -> {
            openDataFromModel(model);
            ((OnlineLibraryStage) getScene().getWindow()).close();
        });
        menuItem2.setOnAction(event1 -> openDataFromModel(model));
        menuItem3.setOnAction(event1 -> contextMenu.hide());
    }

    public void setSelectioned() {
        OnlineLibraryStage ols = (OnlineLibraryStage) getScene().getWindow();
        ols.clearSelection();
        setSelectionnedBackground();
    }

    public void setDefaultBackground() {
        setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    }

    public void setSelectionnedBackground() {
        setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, null, null)));
    }

}
