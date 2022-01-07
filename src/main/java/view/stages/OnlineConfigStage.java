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
import model.InternetUtil;
import view.TabCanvasPane;

public class OnlineConfigStage extends ExtendedStage {

    private Scene scene;
    private VBox root;
    private Label httpLabel, httpsLabel, portLabel, portsLabel;
    private TextField httpField, httpsField, portField, portsField;
    private Button applyButton, resetButton, univLilleButton;
    public OnlineConfigStage(TabCanvasPane tabPane) {

        root = new VBox();
        setTitle("Configuration du réseau");
        loadForm();
        applyButton.setOnAction(this::setProxy);
		resetButton.setOnAction(this::clearText);
		univLilleButton.setOnAction(this::setUnivProxy);
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
		resetButton = new Button("Reset");
		univLilleButton = new Button("Paramètres Univ-Lille");
    	

    	httpsContent.getChildren().addAll(httpsLabel,httpsField,portsLabel,portsField);
    	root.getChildren().addAll(httpContent,httpsContent,applyButton, resetButton, univLilleButton);
    	
    }

	private void clearText(ActionEvent actionEvent) {
		portField.clear();
		portsField.clear();
		httpField.clear();
		httpsField.clear();
	}

	private void setUnivProxy(ActionEvent actionEvent) {
		portField.setText("3128");
		portsField.setText("3128");
		httpField.setText("cache.univ-lille.fr");
		httpsField.setText("cache.univ-lille.fr");
	}

    private void setProxy(ActionEvent actionEvent) {

		if(isValidHttp()) {
			InternetUtil.setupProperty("http.proxyHost", httpField.getText());
			InternetUtil.setupProperty("http.proxyPort", portField.getText());
		}

		if(isValidHttps()) {
			InternetUtil.setupProperty("https.proxyHost", httpsField.getText());
			InternetUtil.setupProperty("https.proxyPort", portsField.getText());
		}

		InternetUtil.setupProperty("java.net.useSystemProxies", isValidHttp() || isValidHttps() ? "true" : "false");
    }

	private boolean isValidHttp() {
		return this.httpField.getText().length() == 0 || this.portField.getText().length() == 0;
	}

	private boolean isValidHttps() {
		return this.httpsField.getText().length() == 0 || this.portsField.getText().length() == 0;
	}

}
