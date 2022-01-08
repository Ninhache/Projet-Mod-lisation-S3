package view.components;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.io.LoadedFile;

/**
 * A Search bar for the library
 *
 * @author Simon Lagneau
 * @version %I%, %G%
 */
public class SearchBar extends TextField {

    private TableView<LoadedFile> tableView;
    private ObservableList<LoadedFile> observableList;

    public SearchBar(TableView<LoadedFile> tableView, ObservableList<LoadedFile> observableList) {
        this.tableView = tableView;
        this.observableList = observableList;

        initSearchBar();
    }

    public void initSearchBar() {

        setPromptText("Search in the .ply files list...");

        FilteredList<LoadedFile> filteredData = new FilteredList<>(this.observableList, b -> true);
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
