package com.example.mathquiz;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private TextView question_1;
    private TextView question_2;
    private TextView question_3;
    private TextView question_4;
    private TextView question_5;
    private TextView question_6;
    private TextView question_7;
    private TextView question_8;
    private EditText answer_1;
    private EditText answer_2;
    private EditText answer_3;
    private EditText answer_8;
    private RadioGroup leftGroup;
    private RadioGroup rightGroup;
    private RadioButton answer_4_R1;
    private RadioButton answer_4_R2;
    private RadioButton answer_4_R3;
    private RadioButton answer_5_R1;
    private RadioButton answer_5_R2;
    private RadioButton answer_5_R3;
    private CheckBox answer_6_B1;
    private CheckBox answer_6_B2;
    private CheckBox answer_6_B3;
    private CheckBox answer_7_B1;
    private CheckBox answer_7_B2;
    private CheckBox answer_7_B3;
    private ImageView imageView;
    private Button submitBtn;
    private Button resetBtn;
    private QuestionsGenerator generator;
    private UserAnswer userAnswer;
    private AutoGrader autoGrader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centerTitle(getResources().getString(R.string.app_name));
        new QuestionsSetter();
        generator = new QuestionsGenerator();
        autoGrader = new AutoGrader(MainActivity.this);
        userAnswer = new UserAnswer();
        linkViews();
        setViews();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storeUserAnswers();
                autoGrader.setUserAnswer(userAnswer);
                autoGrader.verifyAnswers();
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAnswers();
                generator.generateQuestions();
                setViews();
                scrollView.scrollTo(0,0);
            }
        });
    }

    private void centerTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.colorFont));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }

    private void linkViews(){
        scrollView = findViewById(R.id.scrollView);
        question_1 = findViewById(R.id.ques_1);
        question_2 = findViewById(R.id.ques_2);
        question_3 = findViewById(R.id.ques_3);
        question_4 = findViewById(R.id.ques_4);
        question_5 = findViewById(R.id.ques_5);
        question_6 = findViewById(R.id.ques_6);
        question_7 = findViewById(R.id.ques_7);
        question_8 = findViewById(R.id.ques_8);
        answer_1 = findViewById(R.id.ans_1);
        answer_2 = findViewById(R.id.ans_2);
        answer_3 = findViewById(R.id.ans_3);
        answer_8 = findViewById(R.id.ans_8);
        leftGroup = findViewById(R.id.left_radio_group);
        rightGroup = findViewById(R.id.right_radio_group);
        answer_4_R1 = findViewById(R.id.ans_4_radio_1);
        answer_4_R2 = findViewById(R.id.ans_4_radio_2);
        answer_4_R3 = findViewById(R.id.ans_4_radio_3);
        answer_5_R1 = findViewById(R.id.ans_5_radio_1);
        answer_5_R2 = findViewById(R.id.ans_5_radio_2);
        answer_5_R3 = findViewById(R.id.ans_5_radio_3);
        answer_6_B1 = findViewById(R.id.ans_6_box_1);
        answer_6_B2 = findViewById(R.id.ans_6_box_2);
        answer_6_B3 = findViewById(R.id.ans_6_box_3);
        answer_7_B1 = findViewById(R.id.ans_7_box_1);
        answer_7_B2 = findViewById(R.id.ans_7_box_2);
        answer_7_B3 = findViewById(R.id.ans_7_box_3);
        imageView = findViewById(R.id.image);
        submitBtn = findViewById(R.id.submit_btn);
        resetBtn = findViewById(R.id.reset_btn);
    }

    private void setViews(){
        highlightQuesNumber(1, generator.freeTxtSet.get(0).first, question_1);
        highlightQuesNumber(2, generator.freeTxtSet.get(1).first, question_2);
        highlightQuesNumber(3, generator.freeTxtSet.get(2).first, question_3);
        highlightQuesNumber(4, generator.mcqSet.get(0).first, question_4);
        highlightQuesNumber(5, generator.mcqSet.get(1).first, question_5);
        highlightQuesNumber(6, generator.maqSet.get(0).first, question_6);
        highlightQuesNumber(7, generator.maqSet.get(1).first, question_7);
        highlightQuesNumber(8, generator.imgSet.get(0).first, question_8);
        answer_4_R1.setText(generator.mcqChoicesSet.get(0).get(0));
        answer_4_R2.setText(generator.mcqChoicesSet.get(0).get(1));
        answer_4_R3.setText(generator.mcqChoicesSet.get(0).get(2));
        answer_5_R1.setText(generator.mcqChoicesSet.get(1).get(0));
        answer_5_R2.setText(generator.mcqChoicesSet.get(1).get(1));
        answer_5_R3.setText(generator.mcqChoicesSet.get(1).get(2));
        answer_6_B1.setText(generator.maqChoicesSet.get(0).get(0));
        answer_6_B2.setText(generator.maqChoicesSet.get(0).get(1));
        answer_6_B3.setText(generator.maqChoicesSet.get(0).get(2));
        answer_7_B1.setText(generator.maqChoicesSet.get(1).get(0));
        answer_7_B2.setText(generator.maqChoicesSet.get(1).get(1));
        answer_7_B3.setText(generator.maqChoicesSet.get(1).get(2));
        imageView.setImageResource(generator.imgResIdSet.get(0));
    }

    private void highlightQuesNumber(int questionNumber, String question, TextView textView){
        String highlighted = "Q" + questionNumber + ") ";
        textView.setText(highlighted + question, TextView.BufferType.SPANNABLE);
        Spannable sp = (Spannable)textView.getText();
        sp.setSpan(new ForegroundColorSpan(0xFFFFFFFF), 0, highlighted.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private void storeUserAnswers(){
        ArrayList<String> answers = new ArrayList<String>();
        // Setting Free Text Answers
        answers.add(answer_1.getText().toString());
        answers.add(answer_2.getText().toString());
        answers.add(answer_3.getText().toString());
        userAnswer.setFreeTxtAns(answers);
        // Setting MCQ Answers
        answers = new ArrayList<String>();
        RadioButton checkedRadio = findViewById(leftGroup.getCheckedRadioButtonId());
        if (checkedRadio==null)
            answers.add("");
        else
            answers.add(checkedRadio.getText().toString());
        checkedRadio = findViewById(rightGroup.getCheckedRadioButtonId());
        if (checkedRadio==null)
            answers.add("");
        else
            answers.add(checkedRadio.getText().toString());
        userAnswer.setMcqAns(answers);
        // Setting image-based question answer
        answers = new ArrayList<String>();
        answers.add(answer_8.getText().toString());
        userAnswer.setImgAns(answers);
        // Setting MAQ Answers
        answers = new ArrayList<String>();
        ArrayList<ArrayList<String>> mAnswers = new ArrayList<ArrayList<String>>();
        if (answer_6_B1.isChecked())
            answers.add(answer_6_B1.getText().toString());
        if (answer_6_B2.isChecked())
            answers.add(answer_6_B2.getText().toString());
        if (answer_6_B3.isChecked())
            answers.add(answer_6_B3.getText().toString());
        if(answers.isEmpty())
            answers.add("");
        mAnswers.add(answers);
        answers = new ArrayList<String>();
        if (answer_7_B1.isChecked())
            answers.add(answer_7_B1.getText().toString());
        if (answer_7_B2.isChecked())
            answers.add(answer_7_B2.getText().toString());
        if (answer_7_B3.isChecked())
            answers.add(answer_7_B3.getText().toString());
        if(answers.isEmpty())
            answers.add("");
        mAnswers.add(answers);
        userAnswer.setMaqAns(mAnswers);
    }

    private void resetAnswers(){
        answer_1.setText("");
        answer_2.setText("");
        answer_3.setText("");
        answer_8.setText("");
        answer_1.clearFocus();
        answer_2.clearFocus();
        answer_3.clearFocus();
        answer_8.clearFocus();
        answer_4_R1.setChecked(false);
        answer_4_R2.setChecked(false);
        answer_4_R3.setChecked(false);
        answer_5_R1.setChecked(false);
        answer_5_R2.setChecked(false);
        answer_5_R3.setChecked(false);
        answer_6_B1.setChecked(false);
        answer_6_B2.setChecked(false);
        answer_6_B3.setChecked(false);
        answer_7_B1.setChecked(false);
        answer_7_B2.setChecked(false);
        answer_7_B3.setChecked(false);
    }
}