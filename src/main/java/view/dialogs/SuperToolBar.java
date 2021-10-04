package view.dialogs;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

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
        private CustomMenuItem whiteTheme, blackTheme;

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

        whiteTheme = new CustomCheckBox("Blanc");
        blackTheme = new CustomCheckBox("Noir");


        getMenus().addAll(fichier, outils, aide);
        fichier.getItems().addAll(open, openRecents, new SeparatorMenuItem(), saveAsImg, print3d,new SeparatorMenuItem(), quit);
        outils.getItems().addAll(afficherFaces, afficherLignes, afficherLumieres);
        aide.getItems().addAll(theme);
        theme.getItems().addAll(whiteTheme, blackTheme);

        //////////////////////////////////////////////////

        fileChooser = new FileChooser();
        FileChooser.ExtensionFilter[] filters = new FileChooser.ExtensionFilter[]{
                new FileChooser.ExtensionFilter("Fichier ply (*.ply)", "*.ply"),
                new FileChooser.ExtensionFilter("Tous les fichiers (*)", "*"),
        };
        fileChooser.getExtensionFilters().addAll(filters);

        open.setOnAction(this::onOpenClicked);
        quit.setOnAction(this::onQuitClicked);

    }

    private void onOpenClicked(ActionEvent e) {
        File file = fileChooser.showOpenDialog(this.getParent().getScene().getWindow());
        System.out.println(file.getPath());

    }

    private void openFiles(String path) {
        System.out.println("ON OUVRE CA : ["+path+"]");
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
