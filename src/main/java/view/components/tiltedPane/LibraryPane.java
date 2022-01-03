package view.components.tiltedPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.FilePly;
import model.PlyReader;
import view.components.tabpane.TabCanvas;
import view.dialogs.MessageBox;
import view.nodes.TabCanvasPane;
import view.stages.OnlineLibraryStage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class LibraryPane extends TitledPane {

    private Button buttonOnLine;
    private TableView<FilePly> tableView;
    private TableColumn<FilePly, String> fileNameCol, infosCol, filePathCol;
    private ObservableList<FilePly> list;
    private OnlineLibraryStage onlineLibraryStage;

    public LibraryPane() {
        super();

        VBox root = new VBox();

        setText("Bibliothèque PLY");

        tableView = new TableView<>();
        
        fileNameCol = new TableColumn<>("Fichiers");
        infosCol = new TableColumn<>("Infos");
        filePathCol = new TableColumn<>("Chemin des fichiers.ply");

        setProperty();

        buttonOnLine = new Button("Librairie en ligne");
        buttonOnLine.setOnAction(this::openLibraryClick);

        list = getFilePly();
        tableView.setItems(list);

        tableView.getColumns().addAll(fileNameCol,infosCol,filePathCol);

        root.getChildren().addAll(buttonOnLine, tableView);

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
                        MessageBox.showError("Format Incorrect", "Le format du fichier chargé n'est pas compatible, merci de réassayer avec un format correct");
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

    private void setProperty() {
        fileNameCol.setMinWidth(tableView.getWidth()/2);
        fileNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fileNameCol.maxWidthProperty().bind(tableView.widthProperty().multiply(.30));
        fileNameCol.minWidthProperty().bind(fileNameCol.maxWidthProperty());
        fileNameCol.setResizable(false);
        
        infosCol.setResizable(false);
        
        filePathCol.setMinWidth(tableView.getWidth()/2);
        filePathCol.setCellValueFactory(new PropertyValueFactory<>("path"));
        filePathCol.maxWidthProperty().bind(tableView.widthProperty().multiply(.60));
        filePathCol.minWidthProperty().bind(filePathCol.maxWidthProperty());
        filePathCol.setResizable(false);
    }

    private ObservableList<FilePly> getFilePly() {
        String contents[] = new File("exemples").list();

        ArrayList<FilePly> tmp = new ArrayList<>();

        Arrays.stream(contents)
                .filter(o -> o.endsWith(".ply"))
                .forEach(x -> {
                    tmp.add(new FilePly(new File(x)));
                });

        return FXCollections.observableList(tmp);
    }

}
