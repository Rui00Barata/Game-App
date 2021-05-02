package com.hobies.beequal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class AnswerHistActivity extends AppCompatActivity {

    int choice = -1;
    TextView tvQuestion;
    TextView tvResult;
    TextView anschoice;
    TextView sceneHist;
    TextView report;
    ArrayList<String> question = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_hist);

        Intent answer = getIntent();
        choice = answer.getIntExtra("choice", -1);
        question = answer.getStringArrayListExtra("info");


        tvQuestion = (TextView)findViewById(R.id.question);
        tvResult = (TextView)findViewById(R.id.result);
        sceneHist = (TextView)findViewById(R.id.scene);
        report = (TextView)findViewById(R.id.report);

        //Initialize widgets
        //Scene
        sceneHist.setText(question.get(0));

        //Question
        tvQuestion.setText(question.get(1));

        //Choices
        anschoice = (TextView)findViewById(R.id.choice1); //1
        anschoice.setText("1. " + question.get(2));

        anschoice = (TextView)findViewById(R.id.choice2); //2
        anschoice.setText("2. " + question.get(3));

        anschoice = (TextView)findViewById(R.id.choice3); //3
        anschoice.setText("3. " + question.get(4));

        anschoice = (TextView)findViewById(R.id.choice4); //4
        anschoice.setText("4. " + question.get(5));


        if (choice == Integer.parseInt(question.get(6))){
            report.setText(question.get(8));
            tvQuestion.setBackgroundColor(0x8000FF00);
            tvResult.setText("CORRETO");
            tvResult.setTextColor(0xFF00FF00);

            switch(choice){
                case 1:
                    anschoice = (TextView)findViewById(R.id.choice1);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
                case 2:
                    anschoice = (TextView)findViewById(R.id.choice2);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
                case 3:
                    anschoice = (TextView)findViewById(R.id.choice3);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
                case 4:
                    anschoice = (TextView)findViewById(R.id.choice4);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
            }
        }
        else{
            report.setText(question.get(7));
            tvQuestion.setBackgroundColor(0x80FF0000);
            tvResult.setText("ERRADO");
            tvResult.setTextColor(0xFFFF0000);

            switch(Integer.parseInt(question.get(6)) ){
                case 1:
                    anschoice = (TextView)findViewById(R.id.choice1);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
                case 2:
                    anschoice = (TextView)findViewById(R.id.choice2);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
                case 3:
                    anschoice = (TextView)findViewById(R.id.choice3);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
                case 4:
                    anschoice = (TextView)findViewById(R.id.choice4);
                    anschoice.setBackgroundColor(0x8000FF00);
                    break;
            }

            switch(choice){
                case 1:
                    anschoice = (TextView)findViewById(R.id.choice1);
                    anschoice.setBackgroundColor(0x80FF0000);
                    break;
                case 2:
                    anschoice = (TextView)findViewById(R.id.choice2);
                    anschoice.setBackgroundColor(0x80FF0000);
                    break;
                case 3:
                    anschoice = (TextView)findViewById(R.id.choice3);
                    anschoice.setBackgroundColor(0x80FF0000);
                    break;
                case 4:
                    anschoice = (TextView)findViewById(R.id.choice4);
                    anschoice.setBackgroundColor(0x80FF0000);
                    break;
            }
        }
    }

    public void backHist(View v){
        Intent hist = new Intent(this, HistoryActivity.class);

        startActivity(hist);
    }
}