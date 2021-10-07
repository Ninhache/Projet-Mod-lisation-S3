package view.dialogs;

import javafx.application.Platform;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;

public class SuperToolBar extends MenuBar {

    /*

    Fichier |
            | Ouvrir
            | Ouvrir récent
            ----------------
            | Exporter en IMG
            | Imprimer en 3D
            ----------------
            | Quitter

    Outils  | ✓ Afficher Faces
            | ✓ Afficher lignes
            | ✓ Afficher lumières

    Aide    | Thème | · Blanc
                    | · Noir

    */

    // Top Menus
    private Menu fichier, outils, aide;

    // Sub Menus
        // Fichier
        private MenuItem open, saveAsImg, print3d, quit;
        private Menu openRecents;
        // Outils
        private CustomCheckBox cbFaces, cbLines, cbLight;
        private CustomMenuItem afficherFaces, afficherLignes, afficherLumieres;

        // Aide
        private Menu theme;
        private CheckBox cbWhite, cbBlack;
        private ThemeRadioButton whiteTheme, blackTheme, orangeTheme;

        /////

    private FileChooser fileChooser;


    public SuperToolBar() {
        super();

        // Create both top menu :
        fichier = new Menu("Fichier");
        outils = new Menu("Outils");
        aide = new Menu("Aide");

        // Create top sub-menus :
        // File
        open = new MenuItem("Ouvrir");
        saveAsImg = new MenuItem("Exporter en png");
        print3d = new MenuItem("Imprimer en 3D");
        quit = new MenuItem("Quitter");

        // Trouver une solution ????
        // - Lire fichier ?
        openRecents = new Menu("Ouvrir récent");
        openRecents.getItems().addAll(getRecentFiles("tmp/openRecentFile.txt"));


        afficherFaces = new CustomCheckBox("Afficher faces");
        afficherLignes = new CustomCheckBox("Afficher lignes");
        afficherLumieres = new CustomCheckBox("Afficher lumières");

        // Help
        theme = new Menu("Thème");

        ToggleGroup group = new ToggleGroup();
        whiteTheme = new ThemeRadioButton("Blanc", group);
        blackTheme = new ThemeRadioButton("Noir", group);
        orangeTheme = new ThemeRadioButton("Orange", group);


        whiteTheme.setSelected(true);


        getMenus().addAll(fichier, outils, aide);
        fichier.getItems().addAll(open, openRecents, new SeparatorMenuItem(), saveAsImg, print3d,new SeparatorMenuItem(), quit);
        outils.getItems().addAll(afficherFaces, afficherLignes, afficherLumieres);
        aide.getItems().addAll(theme);
        theme.getItems().addAll(whiteTheme, blackTheme, orangeTheme);

        //////////////////////////////////////////////////




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
        whiteTheme.setOnAction(this::onRadioClick);
        blackTheme.setOnAction(this::onRadioClick);
        orangeTheme.setOnAction(this::onRadioClick);

        // OBSERVABLE



    }

    private void onOpenClicked(ActionEvent e) {
        File file = fileChooser.showOpenDialog(this.getParent().getScene().getWindow());
        System.out.println(file.getPath());
    }


    private void openFiles(String path) {
        System.out.println("ON OUVRE CA : ["+path+"]");
    }

    private void onRadioClick(ActionEvent e) {

        String newCss = ((RadioMenuItem)e.getSource()).getId();

        getParent().getScene().getStylesheets().clear();
        getParent().getScene().getStylesheets().add(getClass().getResource("/css/theme/"+newCss+".css").toExternalForm());
    }

    private void onSaveImg(ActionEvent e) {
        Canvas canvas = (Canvas) ((BorderPane) getParent().getScene().getRoot()).getCenter();
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

    // FIXME Mettre une fenêtre de confirmation ??
    private void onQuitClicked(ActionEvent e) {
        Platform.exit();
    }
}
