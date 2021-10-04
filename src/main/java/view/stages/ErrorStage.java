package view.stages;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ErrorStage extends ExtendedStage {

    private Button closeBtn;
    private Label labelError;

    public ErrorStage(String errorString) {
        super();

        VBox root = new VBox();
        root.setPadding(new Insets(18));
        root.setSpacing(10);

        Separator separator = new Separator(Orientation.HORIZONTAL);
        /*ImageView image = new ImageView(GNEUGNUEIMAGE????);

        image.setPreserveRatio(true);
        image.setFitHeight(50);
        image.setSmooth(true);
        image.setCache(true);*/

        closeBtn = new Button("Fermer");
        closeBtn.setDefaultButton(true);
        closeBtn.setId("toolbarButton");

        closeBtn.setOnAction(this::onCloseBtnClicked);
        labelError = new Label(errorString);

        //ADD IMAGE?????????
        root.getChildren().addAll(labelError, separator,closeBtn);
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 300,150);
        setScene(scene);
        setResizable(false);

        setTitle("Error");
        closeBtn.requestFocus();

        //getIcons().add(CONSTANTIMGDEJSPKOA);
    }

    private void onCloseBtnClicked(ActionEvent e) {
        this.close();
    }


}
