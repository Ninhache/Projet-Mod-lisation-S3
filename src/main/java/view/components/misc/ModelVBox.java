package view.components.misc;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Internet;
import model.ModelGet;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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

                    System.out.println(dataK.get("contents"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));


    }

}
