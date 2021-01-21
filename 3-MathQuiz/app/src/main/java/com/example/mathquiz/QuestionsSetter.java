package com.example.mathquiz;

import android.util.Pair;

import java.util.ArrayList;

public class QuestionsSetter extends QuestionsManager{

    public QuestionsSetter(){
        initializeContainers();
        initializeQuestions();
    }

    private void initializeContainers(){
        freeTxtQuesAns = new ArrayList<Pair<String, String>>();
        mcqQuesAns = new ArrayList<Pair<String, String>>();
        mcqChoices = new ArrayList<ArrayList<String>>();
        maqQuesAns = new ArrayList<Pair<String, ArrayList<String>>>();
        maqChoices = new ArrayList<ArrayList<String>>();
        imgQuesAns = new ArrayList<Pair<String, String>>();
        imgResID = new ArrayList<Integer>();
    }

    private void initializeQuestions(){
        setFreeTxtQues();
        setMcq();
        setMaq();
        setImgQues();
    }

    private void setFreeTxtQues(){
        addFreeTxtQues("What is the smallest number that has at least 4 divisors and the difference between any of these divisors is at least 3?", "55");
        addFreeTxtQues("I am an odd number. Take away one letter and I become even. What number am I?", "7");
        addFreeTxtQues("Sally is 54 years old and her mother is 80, how many years ago was Sally’s mother times her age?","41");
        addFreeTxtQues("A clock strikes once at 1 o’clock, twice at 2 o’clock, thrice at 3 o’clock and so on. How many times will it strike in 24 hours?", "156");
        addFreeTxtQues("A three digit number. The second digit is four times as big as the third digit, while the first digit is three less than the second digit. What is the number?","141");
    }

    private void setMcq(){
        addMcq("What is equivalent to the percentage 7%?", "0.07");
        addMcq("What is the result of (6/2(1+2))?", "9");
        addMcq("What is the fifth prime number?", "11");
        addMcq("How many months in a century?", "1200");

        addMcqChoices("0.7", "0.07", "0.007");
        addMcqChoices("1", "6", "9");
        addMcqChoices("5", "9", "11");
        addMcqChoices("100", "120", "1200");
    }

    private void setMaq(){
        ArrayList<String>answers = new ArrayList<String>();

        answers.add("even"); answers.add("prime"); answers.add("coprime with 1");
        addMaq("Number 2 is very important number, what characteristics matches it?", answers);
        answers = new ArrayList<String>();

        answers.add("7");
        addMaq("What are the common divisors of the following three numbers (7, 28, 49)?", answers);
        answers = new ArrayList<String>();

        answers.add("1"); answers.add("2"); answers.add("3");
        addMaq("Numbers have the same answer whether they’re added or multiplied together.", answers);

        addMaqChoices("even", "prime", "coprime with 1");
        addMaqChoices("7", "2", "0");
        addMaqChoices("1", "2", "3");
    }

    private void setImgQues(){
        addImgQues("What is the number of the parking space covered by the car?", "87", R.drawable.image_q1);
        addImgQues("What is the the appropriate number of the question mark below?", "6", R.drawable.image_q2);
        addImgQues("Find the area of the red triangle.", "9", R.drawable.image_q3);
    }

    /****************************************************************/

    private void addFreeTxtQues(String question, String answer){
        freeTxtQuesAns.add(new Pair<>(question, answer));
    }

    private void addMcq(String question, String answer){
        mcqQuesAns.add(new Pair<>(question, answer));
    }

    private void addMcqChoices(String C1, String C2, String C3){
        ArrayList<String> choices = new ArrayList<String>();
        choices.add(C1); choices.add(C2); choices.add(C3);
        mcqChoices.add(choices);
    }

    private void addMaq(String question, ArrayList<String> answers){
        maqQuesAns.add(new Pair(question, answers));
    }

    private void addMaqChoices(String C1, String C2, String C3){
        ArrayList<String> choices = new ArrayList<String>();
        choices.add(C1); choices.add(C2); choices.add(C3);
        maqChoices.add(choices);
    }

    private void addImgQues(String question, String answer, int imgID){
        imgQuesAns.add(new Pair(question, answer));
        imgResID.add(imgID);
    }

}
