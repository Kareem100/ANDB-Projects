package com.example.basketballscore;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final static int POINTS_TYPE_A = 3;
    private final static int POINTS_TYPE_B = 2;
    private final static int POINTS_TYPE_C = 1;
    private final static char TEAM_A = 'A';
    private final static char TEAM_B = 'B';

    private TextView teamAScore;
    private TextView teamBScore;
    private Button teamA3PtsBtn;
    private Button teamA2PtsBtn;
    private Button teamA1PtsBtn;
    private Button teamB3PtsBtn;
    private Button teamB2PtsBtn;
    private Button teamB1PtsBtn;
    private Button resetBtn;
    private int scoreA;
    private int scoreB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        centerTitle(getResources().getString(R.string.app_name));
        initializeViews();
        setClickListeners();
        scoreA = scoreB = 0;
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

    private void initializeViews(){
        teamAScore = findViewById(R.id.team_a_score_view);
        teamBScore = findViewById(R.id.team_b_score_view);
        teamA3PtsBtn = findViewById(R.id.team_a_3p_btn);
        teamA2PtsBtn = findViewById(R.id.team_a_2p_btn);
        teamA1PtsBtn = findViewById(R.id.team_a_throw_btn);
        teamB3PtsBtn = findViewById(R.id.team_b_3p_btn);
        teamB2PtsBtn = findViewById(R.id.team_b_2p_btn);
        teamB1PtsBtn = findViewById(R.id.team_b_throw_btn);
        resetBtn = findViewById(R.id.reset_btn);
    }

    private void setClickListeners(){
        teamA3PtsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addPointsTo(POINTS_TYPE_A, TEAM_A);
            }
        });
        teamA2PtsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addPointsTo(POINTS_TYPE_B, TEAM_A);
            }
        });
        teamA1PtsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addPointsTo(POINTS_TYPE_C, TEAM_A);
            }
        });
        teamB3PtsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addPointsTo(POINTS_TYPE_A, TEAM_B);
            }
        });
        teamB2PtsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addPointsTo(POINTS_TYPE_B, TEAM_B);
            }
        });
        teamB1PtsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addPointsTo(POINTS_TYPE_C, TEAM_B);
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetScores();
            }
        });
    }

    private void addPointsTo(int points, char team){
        if (team == 'A'){
            scoreA += points;
            teamAScore.setText(String.valueOf(scoreA));
        }
        else {
            scoreB += points;
            teamBScore.setText(String.valueOf(scoreB));
        }
    }

    private void resetScores(){
        scoreA = scoreB = 0;
        teamAScore.setText("0");
        teamBScore.setText("0");
    }
}