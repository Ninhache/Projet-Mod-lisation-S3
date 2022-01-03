package view.stages;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import model.Internet;
import model.ModelGet;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import view.components.misc.ModelVBox;
import view.components.tabpane.TabCanvas;
import view.nodes.TabCanvasPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.application.Platform;
import javafx.concurrent.Task;

public class OnlineLibraryStage extends ExtendedStage {

    private Scene scene;
    private VBox root;
    public static TilePane tilePane;
    public TabCanvasPane tp;

    public OnlineLibraryStage(TabCanvasPane tabPane) {

    	setTitle("Bibliothèque en ligne");
        root = new VBox();
        tilePane = new TilePane();
        tp = tabPane;

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {

                Platform.runLater(new Runnable() {
                    public void run() {
                        loadData();
                    }
                });
                return null;
            }
        };

        root.getChildren().addAll(tilePane);
        scene = new Scene(root, 670,500);
        setScene(scene);

        root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        task.run();
        new Thread(task).start();

    }

    private void loadData() {

        String data = null;
        ArrayList<ModelGet> modelList = new ArrayList<>();
        JSONParser jsonParser = new JSONParser();
        JSONObject jsObject = null;

        try {
            data = Internet.sendHttpGETRequest("http://40.113.148.168:3000/3dModel");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsObject = (JSONObject) jsonParser.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray dataJs = (JSONArray) jsObject.get("data");
        Iterator<JSONObject> it = dataJs.iterator();
        while (it.hasNext()) {
            JSONObject key = it.next();
            modelList.add(new ModelGet((int) Long.parseLong(key.get("id").toString()), (String) key.get("name"), (String) key.get("imglink")));
        }

        modelList.forEach(x -> {
            tilePane.getChildren().add(new ModelVBox(x));
        });

    }

    public void clearSelection() {
        tilePane.getChildren().forEach(x -> {
            ModelVBox tmp = (ModelVBox) x;

            tmp.setDefaultBackground();
        });
    }

    public TabCanvasPane getTp() {
        return tp;
    }
}
