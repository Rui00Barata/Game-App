package com.hobies.beequal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GameActivity extends MainActivity {

    RadioGroup radioGroup;                              //respostas
    RadioButton radioButton;                            //inicializar botões das repostas
    TextView tvScene;                                   //cenário
    TextView tvQuestion;                                //questões
    int choice = -1;                                    //resposta escolhida
    ArrayList<String> question = new ArrayList<>();     //array de informação das questões
    int questionID;                                     //id da questão escolhida
    Integer[] arr;                                      //array com as possibilidades das respostas


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ArrayList<Integer> availableQuestions = new ArrayList<>();

        try{    //ler utilizazdor atual
            String test = readUser();

            userID = Integer.parseInt(test);
        }catch(NumberFormatException ex) {
            userID = 1; //se ainda n existir um id no ficheiro, usar utilizador "default"
        }

        availableQuestions = generateArray(appDB.getNumberOfQuestions());
        questionID = availableQuestions.get(generateNumber(availableQuestions.size()));     //gerar o ID da questão a usar

        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        tvScene = (TextView)findViewById(R.id.scene);
        tvQuestion = (TextView)findViewById(R.id.question);

        question = appDB.getQuestion(questionID);   //fetch informação da questão selecionada


        //Initialize widgets
        //Scene
        tvScene.setText(question.get(0));

        //Question
        tvQuestion.setText(question.get(1));

        arr = genChoicesOrder();

        //Choices
        radioButton = (RadioButton)findViewById(R.id.rChoice1); //1
        radioButton.setText("1. " + question.get(arr[0]));

        radioButton = (RadioButton)findViewById(R.id.rChoice2); //2
        radioButton.setText("2. " + question.get(arr[1]));

        radioButton = (RadioButton)findViewById(R.id.rChoice3); //3
        radioButton.setText("3. " + question.get(arr[2]));

        radioButton = (RadioButton)findViewById(R.id.rChoice4); //4
        radioButton.setText("4. " + question.get(arr[3]));

    }

    private Integer[] genChoicesOrder() { //gerar ordem das questões

        Integer[] arr = {2,3,4,5};

        Collections.shuffle(Arrays.asList(arr));

        return arr;

    }


    //Check Button onClick
    public void checkAnswer(View v){

        if (choice == -1)
            MSG("Antes de conferir a resposta escolha uma opção");
        else{
            Intent answer = new Intent(this, AnswerActivity.class);

            answer.putExtra("choice", (arr[choice-1]-1));
            answer.putExtra("info", question);

            appDB.storeAnswer(userID,questionID,choice);

            startActivity(answer);
        }

    }

    //option buttons onClick
    public void checkChoice(View v){
       choice = radioGroup.getCheckedRadioButtonId();

       switch (choice){

           case R.id.rChoice1:
               choice = 1;
               break;
           case R.id.rChoice2:
               choice = 2;
               break;
           case R.id.rChoice3:
               choice = 3;
               break;
           case R.id.rChoice4:
               choice = 4;
               break;
       }

    }

    //Home Button onClick
    public void backHome(View v){
        Intent home = new Intent(this, MainActivity.class);

        startActivity(home);
    }




}