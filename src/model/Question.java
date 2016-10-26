package model;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Question {

        int num = 0;
        public Question() throws IOException {
            this.getQuestion();
        }

        public int getCurrentQuestionNumber() {
            if (num > 11){
                return num = 0;
            }
            return num + 1;
        }
         /**
          * Create a map that maps every question to its corresponding value
          */
        public HashMap<String, String> getQuestion() throws IOException {

            ArrayList<String> questions = new ArrayList<>();
            ArrayList<String> questionID = new ArrayList<>();
            HashMap<String, String> retMap = new HashMap<>();
            HashMap<String, ArrayList<String>> answers = new HashMap<>();

            getListOfQuestions(questions, questionID, answers);
            getListOfAnswers(answers);

            for (int i = 0; i < questions.size(); i++){
                retMap.put(questionID.get(i), questions.get(i));
            }

            return retMap;
         }

    /**
     * Gets a map of answers. Each question's ID maps to a specific ArrayList
     * of answers.
     */
         public HashMap<String, ArrayList<String>> getAnswers() throws IOException{
             HashMap<String, ArrayList<String>> ans = new HashMap<>();
            getListOfAnswers(ans);
             return ans;
        }

    /**
     * This function reads from a file a list of questions from a specific category
     * and stores them in a list.
     */
         public void getListOfQuestions(ArrayList<String> questions, ArrayList<String> questionID, HashMap<String, ArrayList<String>> answers) throws IOException {
             ArrayList<String> temp = new ArrayList<>();

             boolean isOdd = true;

             for (String line : Files.readAllLines(Paths.get("src/model/geekout.txt"))) {

                    if (isOdd){
                        answers.put(line, temp);
                        questionID.add(line);
                        isOdd = false;
                } else{
                    questions.add(line);
                    isOdd = true;
                 }
             }
         }

    /**
     * Read the file containing the answers
     * These answers are mapped to a key value corresponding to the
     * questionID
     */

         public void getListOfAnswers(HashMap<String, ArrayList<String>> ans) throws IOException {

             int num = 1;
             String key = "";
             ArrayList<String> temp = new ArrayList<>();

          for (String line : Files.readAllLines(Paths.get("src/model/geekoutAnswers.txt"))) {
             if ((num % 6) == 1){
                 temp = new ArrayList<>();
                 key = line;
              }else{
                 temp.add(line);
                 ans.put(key, temp);
              }
              num++;
         }
     }

}