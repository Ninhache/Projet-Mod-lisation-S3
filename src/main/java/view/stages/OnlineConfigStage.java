package view.stages;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import view.nodes.TabCanvasPane;

public class OnlineConfigStage extends ExtendedStage {

    private Scene scene;
    private VBox root;
    private Label httpLabel, httpsLabel, portLabel, portsLabel;
    private TextField httpField, httpsField, portField, portsField;
    private Button applyButton;
    public OnlineConfigStage(TabCanvasPane tabPane) {

        root = new VBox();
        setTitle("Configuration du réseau");
        loadForm();
        applyButton.setOnAction(this::setProxy);
        scene = new Scene(root, 670,500);
        setScene(scene);

        root.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

    }

    private void loadForm() {
    	HBox httpContent = new HBox();
    	httpLabel = new Label("Proxy HTTP:");
    	httpField = new TextField();
    	portLabel = new Label("Port HTTP:");
    	portField = new TextField();
    	httpContent.getChildren().addAll(httpLabel,httpField, portLabel, portField);
    	
    	HBox httpsContent = new HBox();
    	httpsLabel = new Label("Proxy HTTPS:");
    	httpsField = new TextField();
    	portsLabel = new Label("Port HTTPS:");
    	portsField = new TextField();
    	
    	applyButton = new Button("Appliquer");
    	

    	httpsContent.getChildren().addAll(httpsLabel,httpsField,portsLabel,portsField);
    	root.getChildren().addAll(httpContent,httpsContent,applyButton);
    	
    }
    
    private void setProxy(ActionEvent actionEvent) {
		System.setProperty("http.proxyHost", httpField.getText());
		System.setProperty("http.proxyPort", portField.getText());
		System.setProperty("https.proxyHost", httpsField.getText());
		System.setProperty("https.proxyPort", portsField.getText());

		System.setProperty("java.net.useSystemProxies", "true");
		
    }


}
