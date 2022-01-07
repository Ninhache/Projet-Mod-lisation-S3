package view.stages;

import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.ApiConnection;
import view.ModelVBox;
import view.TabCanvasPane;

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

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() {
                Platform.runLater(() -> ApiConnection.loadData(tilePane));
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
