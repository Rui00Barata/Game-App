package com.hobies.beequal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class Settings extends MainActivity {

    EditText input; // edit text for popup dialog
    TextView user;  // textview that stores current user
    Button showBtn; // button to change user
    String newUser; // aux
    AlertDialog ad; // pop up dialog


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        showBtn = (Button)findViewById(R.id.changeUsrBtn);
        user = (TextView)findViewById(R.id.userName);

        try{
            String test = readUser();   //ver se existe um utilizador no ficheiro

            user.setText(appDB.getUserString(Integer.parseInt(test)));
            userID = Integer.parseInt(test);
        }catch(NumberFormatException ex) { //utilizador "default"
            user.setText("new_user");
            userID = 1;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("MUDAR DE UTILIZADOR");

        input = new EditText(this);
        builder.setView(input);

        builder.setPositiveButton("FEITO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newUser = input.getText().toString();

                user.setText(newUser);

                if(!appDB.userExists(newUser))
                    appDB.addUser(newUser);

                userID = appDB.getUserID(newUser);
                writeUser(String.valueOf(userID));
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        ad = builder.create(); //Alert Dialog para mudar de utilizador
    }

    //onClick para mosrar alertdialog
    public void showAD(View v){
        ad.show();
    }

    //Home button onClick
    public void goToMainMenu(View v){
        Intent menu = new Intent(this, MainActivity.class);

        startActivity(menu);
    }

    //gerir base de dados onClick
    public void changeToDatabase(View v){
        Intent db = new Intent(this, ManageDBActivity.class);

        startActivity(db);
    }
}