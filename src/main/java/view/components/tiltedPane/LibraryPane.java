package view.components.tiltedPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.FilePly;
import view.dialogs.MessageBox;
import view.nodes.SearchBar;
import view.nodes.TabCanvasPane;
import view.stages.OnlineConfigStage;
import view.stages.OnlineLibraryStage;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class LibraryPane extends TitledPane {

    private Button buttonOnLine;
    private Button buttonOnlineConfig;
    private TableView<FilePly> tableView;
    private TableColumn<FilePly, String> fileNameCol, auteur, nbFacesCol, nbVerticesCol;
    private ObservableList<FilePly> list;
    private OnlineLibraryStage onlineLibraryStage;
    private OnlineConfigStage onlineConfigStage;
    private TextField searchBar;

    public LibraryPane() {
        super();

        VBox root = new VBox();

        setText("Bibliothèque PLY");
        
        searchBar = new TextField();
        
        tableView = new TableView<>();
        
        fileNameCol = new TableColumn<>("Fichiers");
        auteur = new TableColumn<>("Auteur");
        nbFacesCol = new TableColumn<>("Faces");
        nbVerticesCol = new TableColumn<>("Lignes");
        

        setProperty();
        
        HBox onlineLibrary = new HBox();
        buttonOnLine = new Button("Librairie en ligne");
        buttonOnLine.setOnAction(this::openLibraryClick);
        buttonOnlineConfig = new Button("âš™ï¸�");
        buttonOnlineConfig.setOnAction(this::openConfigOnlineClick);
        
        list = getFilePly();
        
        tableView.setItems(list);

        tableView.getColumns().addAll(fileNameCol,auteur,nbFacesCol,nbVerticesCol);
        onlineLibrary.getChildren().addAll(buttonOnLine,buttonOnlineConfig);
        root.getChildren().addAll(onlineLibrary, searchBar, tableView);

        setContent(root);

        tableView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() == 2 ) {
                try {
                    File file = new File(tableView.getSelectionModel().getSelectedItem().getPath());


                    BorderPane borderPane = (BorderPane) getParent().getScene().getRoot();
                    TabCanvasPane tabPane = (TabCanvasPane) borderPane.getCenter();

                    try {
                        tabPane.addModel(file);
                    } catch (FileNotFoundException e) {
                        MessageBox.showError("Fichier introuvable", "Fichier introuvable");
                    } catch (Exception e) {
                        MessageBox.showError("Format Incorrect", "Le format du fichier chargÃ© n'est pas compatible, merci de rÃ©assayer avec un format correct");
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void openLibraryClick(ActionEvent actionEvent) {

        if (onlineLibraryStage == null || !onlineLibraryStage.isShowing()) {
            BorderPane borderPane = (BorderPane) getParent().getScene().getRoot();
            TabCanvasPane tabPane = (TabCanvasPane) borderPane.getCenter();
            onlineLibraryStage = new OnlineLibraryStage(tabPane);
            onlineLibraryStage.initOwner(this.getParent().getScene().getWindow());
            onlineLibraryStage.show();
        } else {
            onlineLibraryStage.close();
            onlineLibraryStage = null;
        }

    }
    
    private void openConfigOnlineClick(ActionEvent actionEvent) {

        if (onlineConfigStage == null || !onlineConfigStage.isShowing()) {
            BorderPane borderPane = (BorderPane) getParent().getScene().getRoot();
            TabCanvasPane tabPane = (TabCanvasPane) borderPane.getCenter();
            onlineConfigStage = new OnlineConfigStage(tabPane);
            onlineConfigStage.initOwner(this.getParent().getScene().getWindow());
            onlineConfigStage.show();
        } else {
            onlineLibraryStage.close();
            onlineLibraryStage = null;
        }

    }
    
    

    private void setProperty() {
    	
    	
        fileNameCol.setMinWidth(tableView.getWidth()/2);
        fileNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fileNameCol.maxWidthProperty().bind(tableView.widthProperty().multiply(.20));
        fileNameCol.minWidthProperty().bind(fileNameCol.maxWidthProperty());
        fileNameCol.setResizable(false);
        
        auteur.setMinWidth(tableView.getWidth()/2);
        auteur.setCellValueFactory(new PropertyValueFactory<>("author"));
        auteur.maxWidthProperty().bind(tableView.widthProperty().multiply(.30));
        auteur.minWidthProperty().bind(auteur.maxWidthProperty());
        auteur.setResizable(false);
        
        
        nbFacesCol.setMinWidth(tableView.getWidth()/2);
        nbFacesCol.setCellValueFactory(new PropertyValueFactory<>("faces"));
        nbFacesCol.maxWidthProperty().bind(tableView.widthProperty().multiply(.20));
        nbFacesCol.minWidthProperty().bind(nbFacesCol.maxWidthProperty());
        nbFacesCol.setResizable(false);
        
        nbVerticesCol.setMinWidth(tableView.getWidth()/2);
        nbVerticesCol.setCellValueFactory(new PropertyValueFactory<>("vertices"));
        nbVerticesCol.maxWidthProperty().bind(tableView.widthProperty().multiply(.20));
        nbVerticesCol.minWidthProperty().bind(nbVerticesCol.maxWidthProperty());
        nbVerticesCol.setResizable(false);
    }
    

    private ObservableList<FilePly> getFilePly() {
        String[] contents = new File("exemples").list();

        ArrayList<FilePly> tmp = new ArrayList<>();

        Arrays.stream(contents)
                .filter(o -> o.endsWith(".ply"))
                .forEach(x -> tmp.add(new FilePly(new File(x))));

        return FXCollections.observableList(tmp);
    }

}
