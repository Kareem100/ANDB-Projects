package com.example.mathquiz;

import android.content.Context;
import android.util.Pair;
import android.widget.Toast;

import java.util.ArrayList;

public class AutoGrader extends QuestionsGenerator{

    private ArrayList<String> givenFreeTxtAns;
    private ArrayList<String> givenMcqAns;
    private ArrayList<ArrayList<String>> givenMaqAns;
    private ArrayList<String> givenImgAns;

    private int grantedMarks;
    private Context context;

    public AutoGrader(Context context){
        this.context = context;
    }

    public void setUserAnswer(UserAnswer givenAnswers){
        givenFreeTxtAns = givenAnswers.getFreeTxtAns();
        givenMcqAns = givenAnswers.getMcqAns();
        givenMaqAns = givenAnswers.getMaqAns();
        givenImgAns = givenAnswers.getImgAns();
    }

    public void verifyAnswers(){
        grantedMarks = 0;
        verifySingleAnswerQuestions (freeTxtSet, givenFreeTxtAns);
        verifySingleAnswerQuestions (mcqSet, givenMcqAns);
        verifySingleAnswerQuestions (imgSet, givenImgAns);
        verifyMultiAnswerQuestions (maqSet, givenMaqAns);
        showResults();
    }

    private void verifySingleAnswerQuestions(ArrayList<Pair<String, String>> expectedAnswer, ArrayList<String> givenAnswer){
        for (int i = 0; i < expectedAnswer.size(); ++i)
            if (expectedAnswer.get(i).second.equals(givenAnswer.get(i).trim()))
                grantedMarks++;
    }

    private void verifyMultiAnswerQuestions(ArrayList<Pair<String, ArrayList<String>>> expectedAnswer, ArrayList<ArrayList<String>> givenAnswer){
        for (int i = 0; i < expectedAnswer.size(); ++i){
            boolean found = true;
            for (int j = 0; j < expectedAnswer.get(i).second.size(); ++j)
                if (!givenAnswer.get(i).contains(expectedAnswer.get(i).second.get(j))){
                    found = false;
                    break;
                }
            if(found && givenAnswer.get(i).size() == expectedAnswer.get(i).second.size())
                grantedMarks++;
        }

    }

    private void showResults(){
        if (grantedMarks == totalMarks)
            Toast.makeText(context, "Your Grade is " +grantedMarks+ "/" +totalMarks+ "\nWell Done !!", Toast.LENGTH_LONG).show();
        else if (grantedMarks > totalMarks - 3)
            Toast.makeText(context, "Your Grade is " +grantedMarks+ "/" +totalMarks+ "\nToo Close !!", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(context, "Your Grade is " +grantedMarks+ "/" +totalMarks+ "\nToo Bad !!", Toast.LENGTH_LONG).show();
    }
}
