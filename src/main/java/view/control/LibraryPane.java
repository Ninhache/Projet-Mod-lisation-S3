package view.control;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.io.LoadedFile;
import view.components.items.SearchBar;
import view.utils.MessageBoxUtil;
import view.stages.OnlineConfigStage;
import view.stages.OnlineLibraryStage;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Containing every elements in the library
 * @author Néo ALMEIDA - Paul VANHEE - Simon LAGNEAU - Matteo MACIEIRA
 * @version %I%, %G%
 */
public class LibraryPane extends TitledPane {

    private Button buttonOnLine;
    private Button buttonOnlineConfig;
    private TableView<LoadedFile> tableView;
    private TableColumn<LoadedFile, String> fileNameCol, auteur, nbFacesCol, nbVerticesCol;
    private final ObservableList<LoadedFile> fileList;
    private OnlineLibraryStage onlineLibraryStage;
    private OnlineConfigStage onlineConfigStage;
    private SearchBar searchBar;

    public LibraryPane() {
        super();

        VBox root = new VBox();

        setText("Bibliothèque PLY");
        
        tableView = new TableView<>();
        tableView.setRowFactory(tv -> new TableRow<>() {
            private Tooltip tooltip = new Tooltip();
            @Override
            public void updateItem(LoadedFile model, boolean empty) {
                super.updateItem(model, empty);
                if (model == null) {
                    setTooltip(null);
                } else {
                    tooltip.setText(model.getDescription() == null ? "Pas de description" : model.getDescription() );
                    setTooltip(tooltip);
                }
            }
        });
        
        fileNameCol = new TableColumn<>("Fichiers");
        auteur = new TableColumn<>("Auteur");
        nbFacesCol = new TableColumn<>("Faces");
        nbVerticesCol = new TableColumn<>("Lignes");

        setProperty();
        
        HBox onlineLibrary = new HBox();
        buttonOnLine = new Button("Librairie en ligne");
        buttonOnLine.setOnAction(this::openLibraryClick);
        buttonOnlineConfig = new Button("⚙️");
        buttonOnlineConfig.setOnAction(this::openConfigOnlineClick);
        
        fileList = getFilePly();
        
        searchBar = new SearchBar(tableView, fileList);

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
                        MessageBoxUtil.showError("Fichier introuvable", "Fichier introuvable");
                    } catch (Exception e) {
                        MessageBoxUtil.showError("Format Incorrect", "Le format du fichier chargé n'est pas compatible, merci de réessayer avec un format correct");
                        e.printStackTrace();
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
        setColumnProperty(fileNameCol, "name", .20);
        setColumnProperty(auteur, "author", .30);
        setColumnProperty(nbFacesCol, "faces", .20);
        setColumnProperty(nbVerticesCol, "vertices", .20);
    }

    private void setColumnProperty(TableColumn<LoadedFile, String> columnName, String name, double v) {
        columnName.setMinWidth(tableView.getWidth() / 2);
        columnName.setCellValueFactory(new PropertyValueFactory<>(name));
        columnName.maxWidthProperty().bind(tableView.widthProperty().multiply(v));
        columnName.minWidthProperty().bind(columnName.maxWidthProperty());
        columnName.setResizable(false);
    }

    private ObservableList<LoadedFile> getFilePly() {
        String[] contents = new File("exemples").list();

        ArrayList<LoadedFile> tmp = new ArrayList<>();

        Arrays.stream(contents)
                .filter(o -> o.endsWith(".ply"))
                .forEach(x -> tmp.add(new LoadedFile(new File(x))));

        return FXCollections.observableList(tmp);
    }
}