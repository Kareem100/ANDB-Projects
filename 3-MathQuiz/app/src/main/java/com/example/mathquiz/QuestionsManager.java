package com.example.mathquiz;

import android.util.Pair;

import java.util.ArrayList;

public abstract class QuestionsManager {

    protected static ArrayList<Pair<String, String>> freeTxtQuesAns;         // free text questions and answers set
    protected static ArrayList<Pair<String, String>> mcqQuesAns;             // multi choices questions set
    protected static ArrayList<ArrayList<String>> mcqChoices;                // mcq choices set
    protected static ArrayList<Pair<String, ArrayList<String>>> maqQuesAns;  // multi answers questions set
    protected static ArrayList<ArrayList<String>> maqChoices;                // maq choices set
    protected static ArrayList<Pair<String, String>> imgQuesAns;             // questions and answers set of image based-questions
    protected static ArrayList<Integer> imgResID;                            // Resource ID of the chosen image

    protected final int noOfFreeQues = 3;                                    // Number of free text questions
    protected final int noOfMcqQues = 2;                                     // Number Of multi choices question questions
    protected final int noOfMaqQues = 2;                                     // Number of multi answer question questions
    protected final int noOfImgQues = 1;                                     // Number of questions of image-based
    protected final int totalMarks = 8;                                      // Quiz total mark, 1 for each question
}
