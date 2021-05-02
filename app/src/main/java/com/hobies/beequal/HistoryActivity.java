package com.hobies.beequal;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

public class HistoryActivity extends MainActivity implements CustomAdapter.OnClickListener {

    RecyclerView recyclerView;                  //rv de todas as respostas
    CustomAdapter customAdapter;                //adapter de cada linha
    TextView tvTopBar;                          //topBar texto

    ArrayList<String> scenes, questions;        //arrays com informações de cada resposta
    ArrayList<Boolean> correct;
    ArrayList<Integer> questionsID, answers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = (RecyclerView)findViewById(R.id.rView);

        try{                                //ler utilizador atual
            String test = readUser();

            userID = Integer.parseInt(test);
        }catch(NumberFormatException ex) {  //se ficheiro vazio, utilizar utilizador "default"
            userID = 1;
        }

        scenes = new ArrayList<>();
        questions = new ArrayList<>();
        correct = new ArrayList<>();
        questionsID = new ArrayList<>();
        answers = new ArrayList<>();

        tvTopBar = (TextView)findViewById(R.id.tvTopBar);
        tvTopBar.setText("UTILIZADOR: " + appDB.getUserString(userID)); //atualizar topBar para mostrar o utiliozador de qual estamos a ver o historico

        appDB.getAnswerInfo(userID, scenes, questions, correct, questionsID, answers); //preenchar arrays com as informações do utilizador

        customAdapter = new CustomAdapter(this, scenes, questions, correct, this);
        recyclerView.setAdapter(customAdapter);                                                             //rv com todas as respostas do utilizador
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }




    //Home button onClick
    public void goToMainMenu(View v){
        Intent menu = new Intent(this, MainActivity.class);

        startActivity(menu);
    }


    //Activity button onClick to go AnswerHistActivity
    @Override
    public void OnCLick(int position) {
        Intent answer = new Intent(this, AnswerHistActivity.class);

        answer.putExtra("info", appDB.getQuestion(questionsID.get(position)));
        answer.putExtra("choice", answers.get(position));

        startActivity(answer);
    }
}