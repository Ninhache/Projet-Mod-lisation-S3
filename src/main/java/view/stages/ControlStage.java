package view.stages;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class ControlStage extends ExtendedStage {

    private Button closeButton;

    public ControlStage() {
        super();

        VBox root = new VBox();
        root.setPadding(new Insets(18));
        root.setSpacing(10);

        closeButton = new Button("Fermer");
        closeButton.setDefaultButton(true);

        closeButton.setOnAction(this::onCloseBtnClicked);

        Scene scene = new Scene(root, 300,150);
        setScene(scene);
        setResizable(false);

        setTitle("TitreControlStage");
        closeButton.requestFocus();

        Label kcOpen = new Label("Ctrl+O");
        Label kcCloseRequest = new Label("Ctrl+W");

        Label labelOpen = new Label("Ouvrir");
        Label labelCloseRequest = new Label("Fermer un onglet");

        Region spacerOpen = new Region();
        Region spacerCloseRequest = new Region();

        HBox hboxOpen = new HBox();
        hboxOpen.getChildren().addAll(kcOpen, spacerOpen, labelOpen);
        hboxOpen.setAlignment(Pos.CENTER);

        HBox hboxCloseRequest = new HBox();
        hboxOpen.getChildren().addAll(kcCloseRequest, spacerCloseRequest, labelCloseRequest);
        hboxOpen.setAlignment(Pos.CENTER);

        HBox.setHgrow(spacerOpen, Priority.ALWAYS); // EXTRACT METHODE !!
        HBox.setHgrow(spacerCloseRequest, Priority.ALWAYS);

        root.getChildren().addAll(hboxOpen, hboxCloseRequest, new Separator(Orientation.HORIZONTAL), closeButton);

        root.setAlignment(Pos.CENTER);

    }

    private void onCloseBtnClicked(ActionEvent e) {
        this.close();
    }


}

