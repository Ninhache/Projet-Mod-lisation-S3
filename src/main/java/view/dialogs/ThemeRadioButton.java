package view.dialogs;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


/**
 *  A ThemeRadioButton is a button, able to change the theme of the app
 *  Tips : : tTo create new themes, you have to :
 *           - Choose the color, we will called it : nameOfTheColor
 *           - Create the css you want (Named `javafxTestCSS + nameOfTheColor` ex: javafxTestCSSBlack)
 *           - Create the ThemeRadioButton Object, don't miss to put the group in the constructor
 *                   => Group is a toggleGroup
 *           - Add the ThemeRadioButton in the menu you wan't
 *           - Don't miss to put the setOnAction on object you've just instantiated
 *                   (ex : blackRadioButton.setOnAction(this::onRadioClicked)
 *
 * @author Néo ALMEIDA
 * @version %I%, %G%
 */
public class ThemeRadioButton extends RadioMenuItem {

    private Canvas tmp;

    public ThemeRadioButton(String color, ToggleGroup group) {
        super(color);

        try {
            setGraphic(getCurrentColor(color));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            setGraphic(new Rectangle(15,15, Color.RED));
        }

        //tmp = new Canvas()

        /* ID IS FOR CUSTOM CSS, THEME WONT WORK WITH THIS !!
           REMEMBER TO CHANGE ID IF YOU DECIDE TO CHANGE NAME OF FILES.CSS */
        this.setId("Theme" + color);

        this.setToggleGroup(group);
    }

    public Rectangle getCurrentColor(String color) throws FileNotFoundException {
        //Rectangle tmp = new Rectangle(Color.web())
        File f = new File("src/main/resources/css/theme/Theme" + color + ".css");
        Scanner sc = new Scanner(f);

        String rectColor = "white";
        while(sc.hasNext()) {
            String tmp = sc.nextLine();
            if(tmp.contains("-fx-base:")) {
                rectColor = tmp.substring(14,tmp.length()-1);
                break;
            }
        }
        super.setStyle("-fx-base: "+ rectColor + ";");
        if(rectColor.length() > 7) rectColor = "#ffffff";
        return new Rectangle(15,15, Color.web(rectColor));
    }
}
