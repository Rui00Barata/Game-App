package com.hobies.beequal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Util {

    AppDB appDB;                                                                                    //Instancia da base de dados
    int userID;                                                                                     //ID do Utilizador atual


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDB = new AppDB(this);                                                            //criar instancia da base de dados
    }

    @Override
    protected void onResume() {
        super.onResume();

        try{                                                                                        //atualizar utilizador atual
            String test = readUser();

            userID = Integer.parseInt(test);
        }catch(NumberFormatException ex) {
            userID = 1;
        }

    }

    //Settings button onClick
    public void goToSettings(View v){                                                               //ir para as settings
        Intent settings = new Intent(this, Settings.class);

        startActivity(settings);
    }

    //Play button onClick
    public void playGame(View v){
        Intent settings = new Intent(this, GameActivity.class);                       //jogar

        startActivity(settings);
    }

    //History button onClick
    public void goToHistory(View v){
        Intent history = new Intent(this, HistoryActivity.class);                    //ver o historico

        startActivity(history);
    }


}