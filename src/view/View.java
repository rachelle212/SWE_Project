package view;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;


import model.Question;
import static view.Constants.WINDOW_HEIGHT;
import static view.Constants.WINDOW_WIDTH;

public class View extends JFrame implements ActionListener {
    private Model model;

    private HomePanel homePanel = new HomePanel();
    private BackgroundPanel backgroundPanel = new BackgroundPanel();
    private CategoryPanel categoryPanel = new CategoryPanel();
    private ReadyPanel readyPanel = new ReadyPanel();
    public Question question;
    private JPanel lastPage = new JPanel();
    private QuestionPanel questionPanel;
    JLayeredPane layered = new JLayeredPane();


    public View() {
        try {
            question = new Question();
        } catch (IOException ex) {}
        questionPanel = new QuestionPanel();
        updateQuestionContent();
        model = new Model();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Name that Thing! -- Home");
        layered.add(homePanel);
        layered.add(backgroundPanel);
        setLayout(new BorderLayout());
        add(layered, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        addActionListener(homePanel.getButton());
        addActionListener(categoryPanel.getButton());
        addActionListener(readyPanel.getButton());


    }
    // Add this as ActionListener for all buttons
    public void addActionListener(ArrayList<JButton> buttons){

        for (JButton button: buttons){
            button.addActionListener(this);
        }
    }

    public void updateQuestionContent(){
        //get question
        int quesID = question.getCurrentQuestionNumber();
        questionPanel.progbar.setValue(quesID);
        questionPanel.questionNumLabel.setText(Integer.toString(quesID));
        //update the question label on the screen
        try {
            questionPanel.questionMap = question.getQuestion();
            questionPanel.answerMap = question.getAnswers();

        } catch (IOException ex) {}

        questionPanel.questionLabel.setText( questionPanel.questionMap.get("301"));

        questionPanel.ansButton1.setText(questionPanel.answerMap.get("301").get(0));
        questionPanel.ansButton2.setText(questionPanel.answerMap.get("301").get(1));
        questionPanel.ansButton3.setText(questionPanel.answerMap.get("301").get(2));
        questionPanel.ansButton4.setText(questionPanel.answerMap.get("301").get(3));

        System.out.println(questionPanel.answerMap);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case Constants.START: // do nothing
                setTitle("Name that Thing! -- Category Selection");
                layered.removeAll();
                layered.add(categoryPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.SETTINGS:
                break;
            case Constants.LEADERBOARD:
                break;
            case Constants.GEEKOUT:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.EARLY2000JAMS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.FOODIE:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.RATCHETFACTS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.SWECLASSFUNNYFACTS:
                layered.removeAll();
                layered.add(readyPanel);
                layered.add(backgroundPanel);
                break;
            case Constants.READY:
                setTitle("Name that Thing! -- Question");
                layered.removeAll();
                layered.add(questionPanel);
                layered.add(backgroundPanel);
                questionPanel.countdown();
                break;
            case Constants.BACK:                       /*Not fully functional*/
                setTitle("Name that Thing! -- Home");  /*Implement to return to the last visited panel*/
                layered.removeAll();
                layered.add(homePanel);
                layered.add(backgroundPanel);
            default:
                break;
        }
        layered.repaint();
    }

    public static void main(String[] args) {
        View v = new View();


    }
}
