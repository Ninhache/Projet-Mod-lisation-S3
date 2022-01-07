package view;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.io.LoadedFile;

public class SearchBar extends TextField {

    private TableView<LoadedFile> tableView;
    private ObservableList<LoadedFile> observableFileList;

    public SearchBar(TableView<LoadedFile> tableView, ObservableList<LoadedFile> observableFileList) {
        this.tableView = tableView;
        this.observableFileList = observableFileList;

        initSearchBar();
    }

    public void initSearchBar() {

        setPromptText("Search in the .ply files list...");

        FilteredList<LoadedFile> filteredData = new FilteredList<>(this.observableFileList, b -> true);
        textProperty().addListener((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate( plyFile -> {
                if(newValue == null || newValue.isEmpty())
                    return true;

                if(plyFile.getName().contains(newValue))
                    return true;
                else
                    return false;
            });
        });
        SortedList<LoadedFile> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }
}
