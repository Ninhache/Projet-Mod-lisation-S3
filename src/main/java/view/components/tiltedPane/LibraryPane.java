package view.components.tiltedPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import model.FilePly;
import model.PlyReader;
import view.components.tabpane.TabCanvas;
import view.nodes.TabCanvasPane;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class LibraryPane extends TitledPane {

    private TableView<FilePly> tableView;
    private TableColumn<FilePly, String> fileNameCol, filePathCol;
    private ObservableList<FilePly> list;

    public LibraryPane() {
        super();

        VBox root = new VBox();

        setText("Bibliothèque PLY");

        tableView = new TableView<>();

        fileNameCol = new TableColumn<>("Fichiers");
        filePathCol = new TableColumn<>("Chemin des fichiers.ply");

        setProperty();


        list = getFilePly();
        tableView.setItems(list);

        tableView.getColumns().addAll(fileNameCol,filePathCol);

        root.getChildren().add(tableView);

        setContent(root);

        tableView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() ==2 ) {
                try {
                    File file = new File(tableView.getSelectionModel().getSelectedItem().getPath());


                    BorderPane borderPane = (BorderPane) getParent().getScene().getRoot();
                    TabCanvasPane tabPane = (TabCanvasPane) borderPane.getCenter();

                    TabCanvas tab = new TabCanvas(new PlyReader(file).readPly(), tabPane.getWidth(), tabPane.getHeight());

                    tabPane.getTabs().add(tab);
                    tab.updateDraw();
                    tabPane.getSelectionModel().select(tab);

                } catch (NullPointerException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setProperty() {
        fileNameCol.setMinWidth(tableView.getWidth()/2);
        fileNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        fileNameCol.maxWidthProperty().bind(tableView.widthProperty().multiply(.30));
        fileNameCol.minWidthProperty().bind(fileNameCol.maxWidthProperty());
        fileNameCol.setResizable(false);

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
