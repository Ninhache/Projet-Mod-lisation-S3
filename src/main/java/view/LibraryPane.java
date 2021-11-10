package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.FilePly;

import java.io.File;
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

        fileNameCol = new TableColumn<>("fileNameCol");
        fileNameCol.setMinWidth(tableView.getWidth()/2);
        fileNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        filePathCol = new TableColumn<>("filePathCol");
        filePathCol.setMinWidth(tableView.getWidth()/2);
        filePathCol.setCellValueFactory(new PropertyValueFactory<>("path"));


        list = getFilePly();
        list.forEach(System.out::println);
        tableView.setItems(list);


        tableView.getColumns().addAll(fileNameCol,filePathCol);

        root.getChildren().add(tableView);

        setContent(root);

        tableView.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() ==2 ) {
                System.out.println(tableView.getSelectionModel().getSelectedItem().getName());
            }
        });

    }

    private ObservableList<FilePly> getFilePly() {
        String contents[] = new File("C:\\Users\\Neo\\IdeaProjects\\projetmodeg5\\exemples").list();

        ArrayList<FilePly> tmp = new ArrayList<>();

        Arrays.stream(contents)
                .filter(o -> o.endsWith(".ply"))
                .forEach(x -> {
                    tmp.add(new FilePly(new File(x)));
                });

        return FXCollections.observableList(tmp);
    }

}
