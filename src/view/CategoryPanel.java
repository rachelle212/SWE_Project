package view;

import javax.swing.*;
import java.awt.*;


public class CategoryPanel extends MyPanel {
    private boolean isStartScreen;
    public CategoryPanel(){
        //Add buttons
        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(8,1));
        JLabel categoryTitle = new JLabel("Please select one category for game play.");
        categoryTitle.setFont(Constants.BUTTON_FONT);
        add(categoryTitle, BorderLayout.EAST);
        add(createButton("GEEK OUT"));
        add(createButton("EARLY 2000s JAMS"));
        add(createButton("HOT GUY CINEMA"));
        add(createButton("RATCHET FACTS"));
        add(createButton("FOODIE"));
        add(createButton("BACK"));

    }


}