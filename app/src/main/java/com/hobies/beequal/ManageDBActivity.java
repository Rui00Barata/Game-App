package com.hobies.beequal;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class ManageDBActivity extends MainActivity implements sceneDialog.SceneDialogListener, QuestionDialog.QuestionDialogListener {

    ArrayList<ArrayList<String>> scenesInfo;    //array com arrays que contem informções sobre os cenários
    ArrayList<ArrayList<String>> questionsInfo; //array com arrays que contem informções sobre as questões

    SceneAdapter sceneAdapter;          //layout da rv dos cenários
    QuestionAdapter questionAdapter;    //layout da rv das questões
    RecyclerView rvScenes;              //rv cenários
    RecyclerView rvQuestions;           //rv questões

    //add scene button



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_d_b);

        scenesInfo = new ArrayList<>();
        questionsInfo = new ArrayList<>();

        rvScenes = (RecyclerView)findViewById(R.id.rvScenes);
        rvQuestions = (RecyclerView)findViewById(R.id.rvQuestions);

        appDB.getScenesInfo(scenesInfo);            //preencher arrays dos cenários
        appDB.getQuestionsInfo(questionsInfo);      //preencher arrays das questões

        sceneAdapter = new SceneAdapter(this, scenesInfo);              //inicializar rv dos cenários
        rvScenes.setAdapter(sceneAdapter);
        rvScenes.setLayoutManager(new LinearLayoutManager(this));

        questionAdapter = new QuestionAdapter(this, questionsInfo);     //inicializar rv das questões
        rvQuestions.setAdapter(questionAdapter);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));
    }

    public void goToSettings(View v){   //onClick para voltar para as definições
        Intent settings = new Intent (this, Settings.class);

        startActivity(settings);
    }

    public void goToHome(View v){   //onClick para ir logo para o menu principal
        Intent home = new Intent (this, MainActivity.class);

        startActivity(home);
    }

    //ADD SCENE BUTTON ADD-ONS
    public void openAddSceneMenu(View v){ //alert dialog para adicionar um cenário
        sceneDialog sceneDialog = new sceneDialog();
        sceneDialog.show(getSupportFragmentManager(), "sceneDialog");
    }

    @Override
    public void applyScene(View v, String scene) {  //função para atualizar rv dos cenários quando é adicionado um novo cenário

        scenesInfo = new ArrayList<>();

        if(!appDB.insertScene(this, scene))
            openAddSceneMenu(v);

        appDB.getScenesInfo(scenesInfo);

        sceneAdapter = new SceneAdapter(this, scenesInfo);
        rvScenes.setAdapter(sceneAdapter);
        rvScenes.setLayoutManager(new LinearLayoutManager(this));
    }



    //ADD QUESTION BUTTON ADD-ONS
    public void openAddQuestionMenu(View v){    //alert dialog para adicionar uma questão

        QuestionDialog questionDialog = new QuestionDialog(v);
        questionDialog.show(getSupportFragmentManager(), "questionDialog");
    }

    @Override
    public void applyQuestion(View v, String sscene, String squestion, String soption1, String soption2, String soption3, String soption4, String scorrect, String sWrongReport, String sRightReport) { //função para atualizar rv dos cenários quando é adicionado uma nova questão

        questionsInfo = new ArrayList<>();

        if(!appDB.insertQuestion(this, sscene, squestion, soption1, soption2, soption3, soption4, scorrect, sWrongReport, sRightReport))
            openAddQuestionMenu(v);

        appDB.getQuestionsInfo(questionsInfo);

        questionAdapter = new QuestionAdapter(this, questionsInfo);
        rvQuestions.setAdapter(questionAdapter);
        rvQuestions.setLayoutManager(new LinearLayoutManager(this));
    }
}