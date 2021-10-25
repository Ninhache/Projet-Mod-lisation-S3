package view.dialogs;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import model.Reader;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import view.ActionLink;
import view.CanvasModel;
import view.MessageBox;
import view.TabCanvas;

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
        private MenuItem open, saveAsImg, print3d, quit, saveAsPly;
        private Menu openRecents, exportAs;

        // Outils
        private CustomCheckBox cbFaces, cbLines, cbLight;
        private CustomMenuItem afficherFaces, afficherLignes, afficherLumieres;

        // Aide
        private Menu theme;
        private CheckBox cbWhite, cbBlack;
        private ThemeRadioButton whiteTheme, blackTheme, orangeTheme, redTheme, pinkTheme, blueTheme, purpleTheme, greenTheme, yellowTheme, secretTheme, secretTheme2, secretTheme3;
        // TODO REMOVE SECRETTHEME !!!!????

        /////

    private FileChooser fileChooser;
    private Reader r = new Reader();

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

        // Trouver une solution ????
        // - Lire fichier ?
        openRecents = new Menu("Ouvrir récent...");
        openRecents.getItems().addAll(getRecentFiles("tmp/openRecentFile.txt"));
        exportAs = new Menu("Exporter...");
        exportAs.getItems().addAll(saveAsImg, saveAsPly);

        afficherFaces = new CustomCheckBox("Afficher faces");
        afficherLignes = new CustomCheckBox("Afficher lignes");
        afficherLumieres = new CustomCheckBox("Afficher lumières");

        // Help
        theme = new Menu("Thème");

        ToggleGroup group = new ToggleGroup();
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

        whiteTheme.setSelected(true);

        System.out.println(group.getToggles());

        getMenus().addAll(fichier, outils, aide);
        fichier.getItems().addAll(open, openRecents, new SeparatorMenuItem(), exportAs, print3d,new SeparatorMenuItem(), quit);
        outils.getItems().addAll(afficherFaces, afficherLignes, afficherLumieres);
        aide.getItems().addAll(theme);
        theme.getItems().addAll(whiteTheme, blackTheme, new SeparatorMenuItem(), orangeTheme, redTheme, pinkTheme, purpleTheme, blueTheme, greenTheme, yellowTheme, secretTheme, secretTheme2, secretTheme3);




        // FILE CHOOSER

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter[] filters = new FileChooser.ExtensionFilter[]{
                new FileChooser.ExtensionFilter("Fichier ply (*.ply)", "*.ply"),
                new FileChooser.ExtensionFilter("Tous les fichiers (*)", "*"),
        };
        fileChooser.getExtensionFilters().addAll(filters);


        
        // EVENT HANDLERS
        
        open.setOnAction(this::onOpenClicked);
        quit.setOnAction(this::onQuitClicked);
        saveAsImg.setOnAction(this::onSaveImg);
        exportAs.setOnAction(this::onExportAsPly);

        theme.getItems().forEach(button -> {
            button.setOnAction(this::onRadioClick);
        });

    }

    private void onOpenClicked(ActionEvent e) {
        File file = fileChooser.showOpenDialog(this.getParent().getScene().getWindow());
        if(file == null) {
        	
        	MessageBox.showWarning("Fichier introuvable", "Gros problème la");
        	return;
        }
        TabCanvasPane cp = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();
        r.setFile(file);
        try {
			cp.getTabs().add(new TabCanvas(r.readPly()));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        CanvasModel t = (CanvasModel) cp.getTabs().get(cp.getTabs().size()-1).getContent();
        t.draw();
        
    }


    private void openFiles(String path) {

        MessageBox.showWarning("Cette fonctionnalité n'est pas terminée !", "Oui.. il respecte à recupérer un vrai modele et cie..");

        TabCanvasPane cp = (TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter();

        TabCanvas tb = new TabCanvas(null, path);

        cp.getTabs().add(tb);

        SingleSelectionModel<Tab> selectionModel = cp.getSelectionModel();

        selectionModel.select(tb);

    }

    private void onRadioClick(ActionEvent e) {

        String newCss = ((RadioMenuItem)e.getSource()).getId();

        getParent().getScene().getStylesheets().clear();
        getParent().getScene().getStylesheets().add(getClass().getResource("/css/theme/"+newCss+".css").toExternalForm());
    }

    private void onExportAsPly(ActionEvent e) {
        System.out.println("Export ply");
    }

    private void onSaveImg(ActionEvent e) {
        Canvas canvas = (Canvas)((TabCanvasPane)((BorderPane) getParent().getScene().getRoot()).getCenter()).getSelectionModel().getSelectedItem().getContent();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTBLUE);
        gc.fillRect(0,0,canvas.getHeight(), canvas.getWidth());
        gc.fill();


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

    private MenuItem[] getRecentFiles(String path) {
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
                itemList = new MenuItem[] {new MenuItem("Pas de fichiers récents")};
            } else {
                itemList = new MenuItem[Math.min(nbLine, 10)];
                int x = 0;
                for(String s : sb.toString().split("-")) {
                    MenuItem tmp = new MenuItem(s);
                    itemList[x] = tmp;
                    // FIXME Créer la fonction {openFiles}
                    tmp.setOnAction(event -> {
                        openFiles(s);
                    });
                    x++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            itemList = new MenuItem[] {new MenuItem("Pas de fichiers récents")};
        }
        return itemList;
    }

    public ActionLink getOpenActionsLink() {
       /* ArrayList<ActionLink> array = new ArrayList<>();
        array.add(new ActionLink("Ouvrir un modèle", this::onOpenClicked));
        return array;*/
        return new ActionLink("Ouvrir un modèle", this::onOpenClicked);
    }


    private void onQuitClicked(ActionEvent e) {
        if(MessageBox.showConfirm("Quitter ?", "Êtes-vous sûr de vouloir quitter ?\nCe choix est irréversible.") == ButtonType.OK) Platform.exit();
    }
}
