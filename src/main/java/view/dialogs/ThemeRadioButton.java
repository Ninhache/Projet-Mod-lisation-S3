package view.dialogs;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

/*
*  For create new themes, you have to :
*       - Choose the color, we will called it : nameOfTheColor
*       - Create the css you want (Named `javafxTestCSS + nameOfTheColor` ex: javafxTestCSSBlack)
*       - Create the ThemeRadioButton Object, don't miss to put the group in the constructor
*               => Group is a toggleGroup
*       - Add the ThemeRadioButton in the menu you wan't
*       - Don't miss to put the setOnAction on object you've just instantiated
*               (ex : blackRadioButton.setOnAction(this::onRadioClicked)
*       ^ Might have changed
* */

public class ThemeRadioButton extends RadioMenuItem {
    public ThemeRadioButton(String s, ToggleGroup group) {
        super(s);

        // ID IS FOR CUSTOM CSS, THEME WONT WORK WITH THIS !!
        // REMEMBER TO CHANGE ID IF YOU DECIDE TO CHANGE NAME OF FILES.CSS
        this.setId("javafxTestCSS"+s);

        this.setToggleGroup(group);
    }
}
