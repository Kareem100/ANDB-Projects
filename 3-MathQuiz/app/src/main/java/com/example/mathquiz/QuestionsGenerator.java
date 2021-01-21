package com.example.mathquiz;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Random;

public class QuestionsGenerator extends QuestionsManager{

    // Question are to be displayed only
    protected static ArrayList<Pair<String, String>>freeTxtSet;
    protected static ArrayList<Pair<String, String>>mcqSet;
    protected static ArrayList<ArrayList<String>> mcqChoicesSet;
    protected static ArrayList<Pair<String, ArrayList<String>>>maqSet;
    protected static ArrayList<ArrayList<String>> maqChoicesSet;
    protected static ArrayList<Pair<String, String>>imgSet;
    protected static ArrayList<Integer> imgResIdSet;

    private Random rand;
    private int randomIdx;

    public QuestionsGenerator(){
        rand = new Random();
        generateQuestions();
    }

    public void generateQuestions(){
        generateFreeTxtQues();
        generateMcqQues();
        generateMaqQues();
        generateImgQues();
    }
    /***************************************************/
    private void generateFreeTxtQues(){
        ArrayList<Pair<String, String>> tempHolder = new ArrayList<Pair<String, String>>(freeTxtQuesAns);
        freeTxtSet = new ArrayList<Pair<String, String>>();

        for (int i = 0; i < noOfFreeQues; ++i){
            randomIdx = rand.nextInt(tempHolder.size());
            freeTxtSet.add(tempHolder.get(randomIdx));
            tempHolder.remove(randomIdx);
        }
    }

    private void generateMcqQues(){
        ArrayList<Pair<String, String>> tempQueHolder = new ArrayList<Pair<String, String>>(mcqQuesAns);
        ArrayList<ArrayList<String>> tempChoices = new ArrayList<ArrayList<String>>(mcqChoices);
        mcqSet = new ArrayList<Pair<String, String>>();
        mcqChoicesSet = new ArrayList<ArrayList<String>>();

        for (int i = 0; i < noOfMcqQues; ++i){
            randomIdx = rand.nextInt(tempQueHolder.size());
            mcqSet.add(tempQueHolder.get(randomIdx));
            mcqChoicesSet.add(tempChoices.get(randomIdx));

            tempQueHolder.remove(randomIdx);
            tempChoices.remove(randomIdx);
        }
    }

    private void generateMaqQues(){
        ArrayList<Pair<String, ArrayList<String>>> tempHolder = new ArrayList<Pair<String, ArrayList<String>>>(maqQuesAns);
        ArrayList<ArrayList<String>> tempChoices = new ArrayList<ArrayList<String>>(maqChoices);
        maqSet = new ArrayList<Pair<String, ArrayList<String>>>();
        maqChoicesSet = new ArrayList<ArrayList<String>>();

        for(int i = 0; i < noOfMaqQues; ++i){
           randomIdx = rand.nextInt(tempHolder.size());
           maqSet.add(tempHolder.get(randomIdx));
           maqChoicesSet.add(tempChoices.get(randomIdx));
           tempHolder.remove(randomIdx);
           tempChoices.remove(randomIdx);
        }
    }

    private void generateImgQues(){
        ArrayList<Pair<String, String>> tempHolder = new ArrayList<Pair<String, String>>(imgQuesAns);
        ArrayList<Integer> tempImgID = new ArrayList<Integer>(imgResID);
        imgSet = new ArrayList<Pair<String, String>>();
        imgResIdSet = new ArrayList<>();

        for(int i = 0 ;i < noOfImgQues; ++i){
            randomIdx = rand.nextInt(tempHolder.size());
            imgSet.add(tempHolder.get(randomIdx));
            imgResIdSet.add(tempImgID.get(randomIdx));
            tempHolder.remove(randomIdx);
            tempImgID.remove(randomIdx);
        }

    }
    /***************************************************/
}
