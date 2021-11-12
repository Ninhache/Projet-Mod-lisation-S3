package view.components.misc;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Internet;
import model.ModelGet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import view.components.tiltedPane.LibraryPane;
import view.nodes.TabCanvasPane;
import view.stages.OnlineLibraryStage;

import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

public class ModelVBox extends VBox {

    private ModelGet model;
    private ImageView imageView;

    public ModelVBox(ModelGet model) {
        super();

        this.model = model;

        this.imageView = new ImageView(model.getImglink());
        this.imageView.setFitWidth(100);
        this.imageView.setFitHeight(100);

        setPadding(new Insets(5));

        getChildren().addAll(imageView, new Label(model.getName()));

        setOnMouseClicked(event -> {
            if(event.getClickCount() == 2) {
                try {
                    JSONObject data = (JSONObject) new JSONParser().parse(Internet.sendHttpGETRequest("http://40.113.148.168:3000/Model?name=" + model.getName()));

                    JSONObject dataK = (JSONObject) data.get("data");

                    //System.out.println(dataK.get("contents"));
                    OnlineLibraryStage ols = (OnlineLibraryStage) getScene().getWindow();

                    File f = new File("tmp.ply");
                    try {
                        if(f.exists()){
                            f.delete();
                        }
                        if(f.createNewFile()){
                            try (FileWriter fw
                                         = new FileWriter(f)) {
                                fw.append((String)dataK.get("contents"));
                            }
                        }
                        TabCanvasPane tp = ols.getTp();
                        tp.addModel(f);
                        f.delete();
                    } catch (Exception e){

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));


    }

}
