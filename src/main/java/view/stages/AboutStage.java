package view.stages;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;
import view.utils.AppUtil;
import view.Window;

/** Informative menu, identify every informations about the application
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class AboutStage extends ExtendedStage {
    private Label appTitle, appDesc;
    private HBox descContainer;
    private Separator separatorA, separatorB;
    private TextArea textZone;
    private Hyperlink gitlabUrl;
    private Button closeBtn;

    public AboutStage() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(18));
        root.setSpacing(6);

        appTitle = new Label(AppUtil.ABOUT_TITLE);
        appTitle.setFont(new Font("Arial", 32));

        descContainer = new HBox();
        descContainer.setAlignment(Pos.CENTER);
        descContainer.setPadding(Insets.EMPTY);
        descContainer.setSpacing(4);

        HBox.setHgrow(descContainer, Priority.ALWAYS);

        appDesc = new Label(AppUtil.ABOUT_DESCRIPTION);

        gitlabUrl = new Hyperlink("Lien GitLab");
        gitlabUrl.setOnAction(this::onGitLabLinkClicked);

        descContainer.getChildren().addAll(appDesc, gitlabUrl);

        separatorA = new Separator(Orientation.HORIZONTAL);
        HBox.setHgrow(separatorA, Priority.ALWAYS);


        textZone = new TextArea(AppUtil.ABOUT_TEXT);
        textZone.setWrapText(true);
        textZone.setEditable(false);

        textZone.setPrefWidth(300);
        textZone.setPrefHeight(230);

        separatorB = new Separator(Orientation.HORIZONTAL);
        HBox.setHgrow(separatorB, Priority.ALWAYS);

        closeBtn = new Button("Fermer");
        closeBtn.setDefaultButton(true);
        closeBtn.setOnAction(this::onCloseClicked);

        root.getChildren().addAll(appTitle, descContainer, separatorA, textZone, separatorB, closeBtn);

        Scene scene = new Scene(root);
        setScene(scene);
        setResizable(false);
        initStyle(StageStyle.UTILITY);
        setTitle("À propos");

        closeBtn.requestFocus();
    }

    private void onCloseClicked(ActionEvent e) {
        close();
    }

    private void onGitLabLinkClicked(ActionEvent e) {
        Window.getServices().showDocument(AppUtil.GITLAB_LINK);
    }
}
