package view.nodes;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.Model;
import model.PlyReader;

import java.awt.image.RenderedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javafx.embed.swing.SwingFXUtils;
import view.dialogs.ActionLink;
import view.components.CanvasModel;
import view.dialogs.MessageBox;
import view.components.CustomCheckBox;
import view.components.ThemeRadioButton;
import view.components.tabpane.TabCanvas;
import view.stages.ControlStage;

import javax.imageio.ImageIO;

/**
 * The SuperToolBar is a toolbar, which contains much menus
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class SuperToolBar extends MenuBar {

    // Top Menus
    private Menu fichier, outils, aide;

    // Sub Menus
        // Fichier
        private MenuItem open, saveAsImg, print3d, quit, saveAsPly, clearRecent;
        private Menu openRecents, exportAs;

        // Outils
        private CustomCheckBox afficherFaces, afficherLignes, afficherLumieres;
        private Menu sortMenu;
        private MenuItem sortAlpha, sortRevert, sortRandom;

        // Aide
        private Menu theme;
        private MenuItem controlHelp;
        private ThemeRadioButton whiteTheme, blackTheme, orangeTheme, redTheme, pinkTheme, blueTheme, purpleTheme, greenTheme, yellowTheme, secretTheme, secretTheme2, secretTheme3;
        // TODO REMOVE SECRETTHEME !!!!????

        /////

    private ControlStage controlWindow;

    private FileChooser fileChooser;
    private PlyReader reader = new PlyReader();
    
    public SuperToolBar() {
        super();

        // Create both top menu :
        fichier = new Menu("Fichier");
        outils = new Menu("Outils");
        aide = new Menu("Aide");

        // Create top sub-menus :
        // File
        open = new MenuItem("Ouvrir");
        saveAsImg = new MenuItem("en png");
        print3d = new MenuItem("Imprimer en 3D");
        saveAsPly = new MenuItem("en .ply");
        quit = new MenuItem("Quitter");
        clearRecent = new MenuItem("Nettoyer");

        // Trouver une solution ????
        // - Lire fichier ?
        openRecents = new Menu("Ouvrir récent...");
        openRecents.getItems().addAll(getRecentFiles());
        manageRecents();

        exportAs = new Menu("Exporter...");
        exportAs.getItems().addAll(saveAsImg, saveAsPly);

        afficherFaces = new CustomCheckBox("Afficher faces");
            afficherFaces.getSelectedProperty().set(true);
        afficherLignes = new CustomCheckBox("Afficher lignes");
            afficherLignes.getSelectedProperty().set(true);
        afficherLumieres = new CustomCheckBox("Afficher lumières");
            afficherLumieres.getSelectedProperty().set(false);

        sortMenu = new Menu("Trier par...");
            sortAlpha = new MenuItem("A . . . Z");
            sortRevert = new MenuItem("Z . . . A");
            sortRandom = new MenuItem("Aléatoire");

        sortMenu.getItems().addAll(sortAlpha, sortRevert, sortRandom);

        // Help
        theme = new Menu("Thème");
            ToggleGroup group = new ToggleGroup();
            setThemes(group);
        controlHelp = new MenuItem("Contrôles");

        whiteTheme.setSelected(true);

        getMenus().addAll(fichier, outils, aide);
        fichier.getItems().addAll(open, openRecents, new SeparatorMenuItem(), exportAs, print3d,new SeparatorMenuItem(), quit);
        outils.getItems().addAll(afficherFaces, afficherLignes, afficherLumieres, new SeparatorMenuItem(), sortMenu);
        aide.getItems().addAll(theme, controlHelp);
        theme.getItems().addAll(whiteTheme, blackTheme, new SeparatorMenuItem(), orangeTheme, redTheme, pinkTheme, purpleTheme, blueTheme, greenTheme, yellowTheme, secretTheme, secretTheme2, secretTheme3);

        // FILE CHOOSER
        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter[] filters = new FileChooser.ExtensionFilter[]{
                new FileChooser.ExtensionFilter("Fichier ply (*.ply)", "*.ply"),
                new FileChooser.ExtensionFilter("Tous les fichiers (*)", "*"),
        };
        fileChooser.getExtensionFilters().addAll(filters);

        // EVENT HANDLERS
        open.setOnAction(event -> {
			try { onOpenClicked(event); } catch (FileNotFoundException e) { e.printStackTrace(); }
		});
        quit.setOnAction(this::onQuitClicked);
        saveAsImg.setOnAction(this::onSaveImg);
        exportAs.setOnAction(this::onExportAsPly);
        print3d.setOnAction(this::onPrint3dClick);
        controlHelp.setOnAction(this::onControlClick);
        clearRecent.setOnAction(this::onClearRecentClick);

        sortAlpha.setOnAction(this::onSortAlphaClick);
        sortRevert.setOnAction(this::onSortAlphaReverseClick);
        sortRandom.setOnAction(this::onShuffleClick);

        theme.getItems().forEach(button -> {
            button.setOnAction(this::onRadioClick);
        });
    }

    private void manageRecents() {

        if(openRecents.getItems().get(0).getText() != "Pas de fichiers récents") {
            openRecents.getItems().addAll(new SeparatorMenuItem(), clearRecent);
        }

    }

    private void setThemes(ToggleGroup group) {
        whiteTheme = new ThemeRadioButton("Blanc", group);
        blackTheme = new ThemeRadioButton("Noir", group);
        orangeTheme = new ThemeRadioButton("Orange", group);
        redTheme = new ThemeRadioButton("Rouge", group);
        pinkTheme = new ThemeRadioButton("Rose", group);
        blueTheme = new ThemeRadioButton("Bleu", group);
        purpleTheme = new ThemeRadioButton("Violet", group);
        greenTheme = new ThemeRadioButton("Vert", group);
        yellowTheme = new ThemeRadioButton("Jaune", group);
        secretTheme = new ThemeRadioButton("Secret", group);
        secretTheme2 = new ThemeRadioButton("Secret2", group);
        secretTheme3 = new ThemeRadioButton("Secret3", group);
    }

    public void onOpenClicked(ActionEvent e) throws FileNotFoundException {
        File file = fileChooser.showOpenDialog(this.getParent().getScene().getWindow());

        if(file == null) {

            MessageBox.showWarning("Fichier introuvable", "Gros problème la");
            return;
        }
        TabCanvasPane tabPane = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();

        try {
            tabPane.addModel(file);
            addToOpenRecent(file);
        } catch (Exception ex) {
            MessageBox.showError("Format Incorrect", "Le format du fichier chargé n'est pas compatible, merci de réassayer avec un format correct");
            return;
        }
    }

    public void addToOpenRecent(File fileToAdd) {
        if(fileToAdd == null) return;
        Path path = Paths.get("src/main/resources/tmp/openRecentFile.txt");

        try {

            if(!fileAlreadyInRecent(fileToAdd)) {

            File mFile = path.toFile();
            FileInputStream fis = new FileInputStream(mFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String result = "";
            String line = "";
            while( (line = br.readLine()) != null){
                result = result + line + "\n";
            }

            result = fileToAdd.getName() + "#" + fileToAdd.getAbsolutePath() + "\n" + result;

            mFile.delete();
            FileOutputStream fos = new FileOutputStream(mFile);
            fos.write(result.getBytes());
            fos.flush();

              /*  Files.write(path, (fileToAdd.getName() + "#" + fileToAdd.getAbsolutePath() + "\n").getBytes(StandardCharsets.UTF_8),
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND);
               */

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            openRecents.getItems().clear();
            openRecents.getItems().addAll(getRecentFiles());
            manageRecents();
        }
    }

    private boolean fileAlreadyInRecent(File fileToAdd) {
        File file = new File("src/main/resources/tmp/openRecentFile.txt");
        
        System.out.println("FILE EXIST :" + file.exists());

        Scanner sc;
        StringBuilder sb = new StringBuilder();

        try {
            sc = new Scanner(file);

            int nbLine = 0;
            while (sc.hasNext()) {
                if (sc.hasNext()) {
                    sb.append(sc.nextLine()).append("-");
                } else {
                    sb.append(sc.nextLine());
                }
                nbLine++;
            }
        } catch (FileNotFoundException e) {

            try {
                file.createNewFile();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        for(String s : sb.toString().split("-")) {
            if(s.contains(fileToAdd.getName())) {
                return true;
            }
        }

        return false;
    }

    private void openFiles(List<String> list) {

        TabCanvasPane tabCanvas = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();

        Path path = Paths.get(list.get(1));
        Model model = null;

        try {
            model = new PlyReader(path).readPly();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
			e.printStackTrace();
		}
        
        TabCanvas tab = new TabCanvas( model , list.get(0), tabCanvas.getWidth(), tabCanvas.getHeight());

        tabCanvas.getTabs().add(tab);

        SingleSelectionModel<Tab> selectionModel = tabCanvas.getSelectionModel();

        selectionModel.select(tab);
        tab.updateDraw();

    }

    private void onRadioClick(ActionEvent e) {

        String newCss = ((RadioMenuItem)e.getSource()).getId();

        getParent().getScene().getStylesheets().clear();
        getParent().getScene().getStylesheets().add(getClass().getResource("/css/theme/"+newCss+".css").toExternalForm());

    }

    private void onControlClick(ActionEvent e) {
        if (controlWindow == null || !controlWindow.isShowing()) {
            controlWindow = new ControlStage();
            controlWindow.initOwner(this.getParent().getScene().getWindow());
            controlWindow.show();
        } else {
            controlWindow.close();
            controlWindow = null;
        }
    }

    private void onSortAlphaClick(ActionEvent e) {
        TabCanvasPane tabPane = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();

        if(tabPane.getTabs().size() > 0) {
            tabPane.getTabs().sort(Comparator.comparing(Tab::getText));
        }

    }

    private void onSortAlphaReverseClick(ActionEvent e) {
        TabCanvasPane tabPane = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();

        if(tabPane.getTabs().size() > 0) {
            tabPane.getTabs().sort((o1, o2) -> o2.getText().compareTo(o1.getText()));
        }

    }

    private void onShuffleClick(ActionEvent e) {
        TabCanvasPane tabPane = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();

        if(tabPane.getTabs().size() > 0) {
            tabPane.getTabs().sort(((o1, o2) -> {
                return new Random().nextInt(3) - 1;
            }));
        }

    }

    private void onClearRecentClick(ActionEvent e) {

        if(MessageBox.showConfirm("Supprimer la liste de fichier récents ?", "Êtes-vous sûr de vouloir supprimer la liste de fichier récents ?\nCe choix est irréversible.") == ButtonType.OK) {
            File file = new File("src/main/resources/tmp/openRecentFile.txt");

            try {
                PrintWriter writer = new PrintWriter(file.getAbsolutePath());

                writer.print("");
                writer.close();

                openRecents.getItems().clear();
                openRecents.getItems().addAll(getRecentFiles());
                manageRecents();

            } catch (FileNotFoundException ex) {
                try {
                    file.createNewFile();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
                ex.printStackTrace();
            }
        }

    }

    private void onPrint3dClick(ActionEvent e) {
        MessageBox.showError("Feature non implémentée !", "Cette feature sera ajoutée si on trouve une librairie qui gère l'imprimerie en 3D...");
    }

    private void onExportAsPly(ActionEvent e) {
        System.out.println("Export ply");
    }

    private void onSaveImg(ActionEvent e) {
        Canvas canvas = (Canvas)((TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter()).getSelectionModel().getSelectedItem().getContent();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        //Show save file dialog
        File file = fileChooser.showSaveDialog(this.getParent().getScene().getWindow());

        if(file != null){
            try {
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int)canvas.getHeight());
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private MenuItem[] getRecentFiles() {

        Path pathDir = Paths.get("src/main/resources/tmp");
        if(!Files.exists(pathDir)) {
            try {
                Files.createDirectory(pathDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File("src/main/resources/tmp/openRecentFile.txt");

        Scanner sc;
        StringBuilder sb;
        MenuItem[] itemList = new MenuItem[0]; // < Degeu mais sinn c'est relou

        try {
            sc = new Scanner(file);
            sb = new StringBuilder();

            int nbLine = 0;
            while (sc.hasNext()) {
                if(sc.hasNext()) {
                    sb.append(sc.nextLine()+"-");
                } else {
                    sb.append(sc.nextLine());
                }
                nbLine++;
            }
            if(nbLine == 0) {
                itemList = new MenuItem[] {
                        new MenuItem("Pas de fichiers récents")
                };
            } else {
                itemList = new MenuItem[Math.min(nbLine, 10)];
                int x = 0;
                for(String s : sb.toString().split("-")) {
                    List<String> list = Arrays.asList(s.split("#"));
                    MenuItem tmp = new MenuItem(list.get(0));
                    if(x >= 10) break;
                    itemList[x] = tmp;

                    tmp.setOnAction(event -> {
                        openFiles(list);
                    });

                    x++;
                }
            }
        } catch (FileNotFoundException | ArrayIndexOutOfBoundsException e) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            itemList = new MenuItem[] {
                    new MenuItem("Pas de fichiers récents")
            };
        }

        return itemList;
    }

    public ActionLink getOpenActionsLink() {
       /* ArrayList<ActionLink> array = new ArrayList<>();
        array.add(new ActionLink("Ouvrir un modèle", this::onOpenClicked));
        return array;*/
        return new ActionLink("Ouvrir un modèle", event -> {
			try { onOpenClicked(event); } catch (FileNotFoundException e) { e.printStackTrace(); }
		});
    }

    private void onQuitClicked(ActionEvent e) {
        if(MessageBox.showConfirm("Quitter ?", "Êtes-vous sûr de vouloir quitter ?\nCe choix est irréversible.") == ButtonType.OK) Platform.exit();
    }
}
