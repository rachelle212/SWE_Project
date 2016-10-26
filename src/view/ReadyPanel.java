package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class ReadyPanel extends MyPanel {
    public ReadyPanel(){
        //Add buttons
        super();

        setOpaque(false);
        setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        setLayout(new GridLayout(7,1));
        JLabel readyLabel = new JLabel("Are you ready to play Name that Thing?!");
        readyLabel.setFont(Constants.BUTTON_FONT);
        add(readyLabel, BorderLayout.CENTER);
        add(createButton(Constants.READY));
        add(createButton(Constants.BACK));

    }


}