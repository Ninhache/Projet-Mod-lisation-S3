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

        HBox hboxOpen = setupHBox("Ctrl+O", new Region(), "Ouvrir");
        HBox hboxCloseRequest = setupHBox("Ctrl+W", new Region(), "Fermer un onglet");

        root.getChildren().addAll(hboxOpen, hboxCloseRequest, new Separator(Orientation.HORIZONTAL), closeButton);
        root.setAlignment(Pos.CENTER);
    }
    
	private HBox setupHBox(String kc, Region spacer, String text) {
		HBox hbox = new HBox();
        hbox.getChildren().addAll(new Label(kc), spacer, new Label(text));
        hbox.setAlignment(Pos.CENTER);
		HBox.setHgrow(spacer, Priority.ALWAYS);
        return hbox;
	}

    private void onCloseBtnClicked(ActionEvent e) {
        this.close();
    }

//    private void setupSpacers(Region... spacers) {
//    	for (Region spacer : spacers) {
//			HBox.setHgrow(spacer, Priority.ALWAYS);
//		}
//    }
}

