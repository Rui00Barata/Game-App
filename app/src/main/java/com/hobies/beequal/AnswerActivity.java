package com.hobies.beequal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AnswerActivity extends MainActivity {

    int choice = -1;                                    //escolha do utilizador
    TextView tvQuestion;                                //questão
    TextView tvResult;                                  //resultado
    TextView anschoice;                                 //respostas
    TextView scene;                                     //cenário
    TextView report;                                    //report
    ArrayList<String> question = new ArrayList<>();     //informção das questões


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        Intent answer = getIntent();
        choice = answer.getIntExtra("choice", -1);
        question = answer.getStringArrayListExtra("info");

        tvQuestion = (TextView)findViewById(R.id.question);
        tvResult = (TextView)findViewById(R.id.result);
        scene = (TextView)findViewById(R.id.Scene);
        report = (TextView)findViewById(R.id.report);

        //Initialize widgets
        //Scene
        scene.setText(question.get(0));
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

        //ver se a reposta esta certa e casos para se estiver ou não
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

    //Next Question Button onClick
    public void nextQuestion(View v){

        Intent home = new Intent(this, GameActivity.class);

        startActivity(home);

    }


    //Home Button onClick
    public void backHome(View v){
        Intent home = new Intent(this, MainActivity.class);

        startActivity(home);
    }


}