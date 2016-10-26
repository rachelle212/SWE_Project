package view;

import view.View;
import javax.swing.*;
import java.awt.*;



public class HomePanel extends MyPanel {

    /** Home Panel constructor adds the three buttons to the home screen, appends
     * the button list with newly added buttons, and sets screen parameters.
     */

    public HomePanel(){
        super();
        setLayout(new GridLayout(6,1));  //set layout for the home panel
        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        JLabel triviaTitle = new JLabel(" Name that Thing!");
        triviaTitle.setFont(Constants.HOME_FONT);
        add(triviaTitle, BorderLayout.WEST);
        add(createButton(Constants.START));
        add(createButton(Constants.SETTINGS));
        add(createButton(Constants.LEADERBOARD));

    }


}
