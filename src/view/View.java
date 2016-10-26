package view;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import model.Question;
import static view.Constants.WINDOW_HEIGHT;
import static view.Constants.WINDOW_WIDTH;

public class View extends JFrame {
    private Model model;

    private HomePanel homePanel = new HomePanel();
    private BackgroundPanel backgroundPanel = new BackgroundPanel();
    private CategoryPanel categoryPanel = new CategoryPanel();
    private ReadyPanel readyPanel = new ReadyPanel();
    private Question question;
    private JPanel lastPage = new JPanel();
    private QuestionPanel questionPanel;
    JLayeredPane layered = new JLayeredPane();


    public View() {
        try {
            question = new Question();
        } catch (IOException ex) {}
        questionPanel = new QuestionPanel();
        model = new Model();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Name that Thing! -- Home");
        layered.add(homePanel);
        layered.add(backgroundPanel);
        setLayout(new BorderLayout());
        add(layered, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public class HomePanel extends JPanel implements ActionListener{
        private boolean isStartScreen;
        public HomePanel(){
            //Add buttons
            super();
            setTitle("Name that Thing! -- Home");
            setLayout(new GridLayout(6,1));  //set layout for the home panel
            setOpaque(false);
            setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            JLabel triviaTitle = new JLabel(" Name that Thing!");
            triviaTitle.setFont(Constants.HOME_FONT);
            add(triviaTitle, BorderLayout.WEST);
            add(createButtonWithFont(Constants.START));
            add(createButtonWithFont(Constants.SETTINGS));
            add(createButtonWithFont(Constants.LEADERBOARD));

        }

        @Override
        public void actionPerformed(ActionEvent e){
            if (isStartScreen){
                //Don't do anything on the start screen
                return;
            }
        }

    }


    public class BackgroundPanel extends JPanel implements ActionListener{
        private boolean isStartScreen;
        public BackgroundPanel(){
            setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            setOpaque(true);
            isStartScreen = true;
        }
        @Override
        public void actionPerformed(ActionEvent e){
            if (isStartScreen){
                //Don't do anything on the start screen
                return;
            }
        }
        @Override
       public void paint(Graphics g){
            super.paint(g);
           //Create background
             g.setColor(Color.LIGHT_GRAY);
             g.fillRect(0,0, WINDOW_WIDTH, WINDOW_HEIGHT);

       }

    }

    public class CategoryPanel extends JPanel implements ActionListener{
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
            add(createButtonWithFont("GEEK OUT"));
            add(createButtonWithFont("EARLY 2000s JAMS"));
            add(createButtonWithFont("HOT GUY CINEMA"));
            add(createButtonWithFont("RATCHET FACTS"));
            add(createButtonWithFont("FOODIE"));
            add(createButtonWithFont("BACK"));

        }

        @Override
        public void actionPerformed(ActionEvent e){
            if (isStartScreen){

                //Don't do anything on the start screen
                return;
            }
        }

    }

    public class ReadyPanel extends JPanel implements ActionListener{
        private boolean isStartScreen;
        public ReadyPanel(){
            //Add buttons
            super();

            setOpaque(false);
            setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            setLayout(new GridLayout(7,1));
            JLabel readyLabel = new JLabel("Are you ready to play Name that Thing?!");
            readyLabel.setFont(Constants.BUTTON_FONT);
            add(readyLabel, BorderLayout.CENTER);
            add(createButtonWithFont(Constants.READY));
            add(createButtonWithFont(Constants.BACK));

        }



        @Override
        public void actionPerformed(ActionEvent e){
            if (isStartScreen){

                //Don't do anything on the start screen
                return;
            }
        }

    }



    public class QuestionPanel extends JPanel implements ActionListener{
        private boolean isStartScreen;
        public String[] possibleAnswers = {"", "", "", ""};
        JButton ansButton1 = new JButton("A");
        JButton ansButton2 = new JButton("B");
        JButton ansButton3 = new JButton("C");
        JButton ansButton4 = new JButton("D");
        JLabel questionNumLabel = new JLabel("Question ID");
        JLabel questionLabel = new JLabel("Question");
        JProgressBar progbar = new JProgressBar (0, 10);

        public QuestionPanel(){
            //Add buttons
            super();

            setOpaque(false);
            setBounds(0,0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
            setLayout(new GridLayout(9,1));
            add(progbar);
            questionNumLabel.setFont(Constants.QUESTION_FONT);
            add(questionNumLabel, BorderLayout.EAST);

            questionLabel.setFont(Constants.QUESTION_FONT);
            add(questionLabel, BorderLayout.EAST);

            add(ansButton1, Constants.QUESTION_FONT);

            answerButtonActions(ansButton1);

            add(ansButton2, Constants.QUESTION_FONT);
            answerButtonActions(ansButton2);

            add(ansButton3, Constants.QUESTION_FONT);
            answerButtonActions(ansButton3);

            add(ansButton4, Constants.QUESTION_FONT);
            answerButtonActions(ansButton4);
            updateQuestionContent();

        }

        HashMap<String, String> questionMap = new HashMap<>();
        HashMap<String, ArrayList<String>> answerMap = new HashMap<>();

        public void updateQuestionContent(){
            //get question
            int quesID = question.getCurrentQuestionNumber();
            progbar.setValue(quesID);
            questionNumLabel.setText(Integer.toString(quesID));
            //update the question label on the screen
            try {
                questionMap = question.getQuestion();
                answerMap = question.getAnswers();

            } catch (IOException ex) {}

            questionLabel.setText( questionMap.get("101"));

            ansButton1.setText(answerMap.get("101").get(0));
            ansButton2.setText(answerMap.get("101").get(1));
            ansButton3.setText(answerMap.get("101").get(2));
            ansButton4.setText(answerMap.get("101").get(3));

            System.out.println(answerMap);

        }

        private void countdown(){
            JLabel counter = new JLabel("Counter from 10 to 0", SwingConstants.CENTER);
            counter.setFont(Constants.QUESTION_FONT);
            add(counter);


            final Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                int i = Integer.parseInt(Constants.COUNTMAX);
                public void run() {
                    int temp = i--;
                    counter.setText(String.valueOf(temp));

                    if (temp >= 7)
                        counter.setForeground(Color.GREEN);
                    else if (temp > 3)
                        counter.setForeground(Color.YELLOW);
                    else
                        counter.setForeground(Color.RED);

                    if (i< 0)
                        timer.cancel();
                }
            }, 0, 1000);
        }

        @Override
        public void actionPerformed(ActionEvent e){
            if (isStartScreen){
                //Don't do anything on the start screen
                return;
            }
        }
        boolean done = false;
        public void answerButtonActions(JButton button) {

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String text = e.getActionCommand(); // returns a string with the text on hte button
                System.out.println(text);
                //text == to the answer

            }
        });

    }}

    public JButton createButtonWithFont(String text){
        JButton button = new JButton(text);
        button.setFont(Constants.BUTTON_FONT);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    switch (text) {
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
                        case "GEEK OUT":
                            layered.removeAll();
                            layered.add(readyPanel);
                            layered.add(backgroundPanel);
                            break;
                        case "EARLY 2000s JAMS":
                            layered.removeAll();
                            layered.add(readyPanel);
                            layered.add(backgroundPanel);
                            break;
                        case "FOODIE":
                            layered.removeAll();
                            layered.add(readyPanel);
                            layered.add(backgroundPanel);
                            break;
                        case "RATCHET FACTS":
                            layered.removeAll();
                            layered.add(readyPanel);
                            layered.add(backgroundPanel);
                            break;
                        case "HOT GUY CINEMA":
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

        });

        return button;
    }


    public static void main(String[] args) {
        View v = new View();


    }
}
