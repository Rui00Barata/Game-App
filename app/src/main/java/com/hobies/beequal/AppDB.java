package com.hobies.beequal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

public class AppDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "AppDB";
    private static final String USERS_TABLE = "Users";
    private static final String SCENES_TABLE = "Scenes";
    private static final String QUESTIONS_TABLE = "Questions";
    private static final String ANSWERS_TABLE = "Answers";


    AppDB(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {   //criar tabelas e inserir dados inicias
        db.execSQL("create table Users(userID INTEGER PRIMARY KEY, userString TEXT)");
        db.execSQL("create table Scenes(sceneID INTEGER PRIMARY KEY, sceneString TEXT)");
        db.execSQL("create table Questions(questionID INTEGER PRIMARY KEY, sceneID INTEGER, question TEXT, option1 TEXT, option2 TEXT, option3 TEXT, option4 TEXT, answer INTEGER, wrongReport TEXT, correctReport  TEXT, FOREIGN KEY(sceneID) REFERENCES Scenes(sceneID))");
        db.execSQL("create table Answers(answerID INTEGER PRIMARY KEY, userID INTEGER, questionID INTEGER, answer INTEGER, FOREIGN KEY(userID) REFERENCES Users(userID), FOREIGN KEY(questionID) REFERENCES Questions(questionID))");

        //Users
/*1*/   db.execSQL("insert into " + USERS_TABLE + " values (null, \"new_user\")");

        //Scenes
/*1*/   db.execSQL("insert into " + SCENES_TABLE + " values (null, \"O/A meu/minha namorado/a diz-me diariamente que gosta muito de mim e pediu-me as chaves de acesso ao meu telemóvel.\")");
/*2*/   db.execSQL("insert into " + SCENES_TABLE + " values (null, \"Uma excelente gestora foi convidada para um cargo de chefia. Por ser uma senhora e devido à pressão atual no sentido da igualdade do género, estão a oferecer-lhe o dobro do salário que dariam a um senhor.\")");
/*3*/   db.execSQL("insert into " + SCENES_TABLE + " values (null, \"Um barco de refugiados chega a costa de um pais vizinho. Estes eram perseguidos no seu pais por questoes politicas.\")");

        //Questions
/*1*/   db.execSQL("insert into " + QUESTIONS_TABLE + " values (null, 1,\"Se fosse contigo, achas que devias dar-lhe essa informação?\", \"Sim, ele diz que gosta de mim e, por isso, os ciúmes justificam que ele queira controlar o que faço com o meu telemóvel.\", \"Sim, porque a nossa relação é baseada na confiança e sei que ele não vai fazer-me mal.\", \"Não dou as chaves de acesso ao meu telemóvel porque são pessoais.\", \"Só dou essa informação se ele também me der as chaves de acesso ao seu telemóvel.\", 3, \"Note que os dados ou objetos pessoais são do foro privado e não devem ter implicação\n" +
                "ou depender da relação com o/a namorado/a.\", \"A sua resposta vai de encontro ao que se acredita ser a melhor abordagem nesta situação.\")");
/*2*/   db.execSQL("insert into " + QUESTIONS_TABLE + " values (null, 2,\"Acha que a senhora devia aceitar o cargo e o salário?\", \"Devia rejeitar o cargo e o salário\", \"Devia aceitar ambos.\", \"Devia aceitar o cargo mas pedir para ter um salário adequado à função\", \"Devia aceitar o cargo mas pedir para ter um salário inferior ao esperado para a função.\", 3, \"Note que as oportunidades devem ser iguais para ambos os géneros.\", \"A sua resposta vai de encontro ao que se acredita ser o princípio da igualdade do género.\")");
/*3*/   db.execSQL("insert into " + QUESTIONS_TABLE + " values (null, 3,\"O que deverá fazer o pais vizinho?\", \"Devolver todos os refugiados ao seu pais natal.\", \"Colocar todos os refugiados em jaulas sem condições minimas necessárias.\", \"Pelo menos fornecer habitações com condições minimas de habitação.\", \"Ignorar a chegada dos refugiados.\", 3, \"Note que todos os humanos devem ter condições mínimas para poder viver.\", \"A sua resposta vai de encontro ao que se acredita ser a melhor abordagem nesta situação.\")");

    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  //limpar tabelas e voltar a cria-las com os dados incias
        db.execSQL("DROP TABLE IF EXISTS Users");
        db.execSQL("DROP TABLE IF EXISTS Scenes");
        db.execSQL("DROP TABLE IF EXISTS Questions");
        db.execSQL("DROP TABLE IF EXISTS Answers");

        onCreate(db);
    }

    public ArrayList<String> getQuestion(int id){   //fetch informação sobre as questões

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<String> question = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(("select * from Questions where questionID = " + String.valueOf(id)), (String[]) null);
        cursor.moveToFirst();

        int sceneID = cursor.getInt(1);
        question.add(cursor.getString(2)); //question
        question.add(cursor.getString(3)); //option 1
        question.add(cursor.getString(4)); //option 2
        question.add(cursor.getString(5)); //option 3
        question.add(cursor.getString(6)); //option 4
        question.add(String.valueOf(cursor.getInt(7))); //correct answer

        Cursor cursor2 = sqLiteDatabase.rawQuery(("select * from Scenes where sceneID = " + String.valueOf(sceneID)), (String[]) null);
        cursor2.moveToFirst();
        question.add(0,cursor2.getString(1));

        question.add(cursor.getString(8));
        question.add(cursor.getString(9));

        //question -->> 1 - scene || 2 - question || 3 - option 1 || 4 - option 2 || 5 - option 3 || 6 - option 4 || 7 - right answer(should be turned to int) || 8 -> wrong report || 9 -> correct report

        return question;

    }

    public int getNumberOfQuestions(){  //fetch numero de questões

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select COUNT(questionID) FROM Questions", (String[]) null);
        cursor.moveToFirst();


        return cursor.getInt(0);
    }

    public void storeAnswer(int userID, int questionID, int choice){ //guardar respostas do utilizador na base de dados

        SQLiteDatabase sqlW = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userID", userID);
        contentValues.put("questionID", questionID);
        contentValues.put("answer", choice);

        sqlW.insert(ANSWERS_TABLE, null, contentValues);

    }

    public String getUserString(int userID){    //ler nome de utilizador a partir do ID

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select userString from Users where userID = " + String.valueOf(userID), (String[]) null);
        cursor.moveToFirst();

        return cursor.getString(0);
    }

    public boolean userExists(String userString){   //ver se o utilizador ja existe

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select COUNT(userString) from Users where userString = \"" + userString + "\"", (String[]) null);
        cursor.moveToFirst();


        return (cursor.getInt(0) >  0);
    }

    public int getUserID(String userString){    //fetch ID de utilizador a partir da string

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from Users where userString = \"" + userString + "\"",  null);
        cursor.moveToFirst();


        return  cursor.getInt(0);
    }

    public void addUser(String newUser) {   //adicionar novo utilizador

        SQLiteDatabase sqlW = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("userString", newUser);
        sqlW.insert(USERS_TABLE, null, contentValues);

    }

    public void getAnswerInfo(int userID, ArrayList<String> scenes, ArrayList<String> questions, ArrayList<Boolean> correct, ArrayList<Integer> questionsID, ArrayList<Integer> answers){   //fetch informação das respostas

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from Answers where userID = " + String.valueOf(userID),  null); //select * from Answers where userID = 2


        while(cursor.moveToNext()){

            Cursor questionCursor = sqLiteDatabase.rawQuery("select * from Questions where questionID = " + String.valueOf(cursor.getInt(2)),  null); //select * from Questions where questionID = 3
            questionCursor.moveToFirst();
            Cursor sceneCursor = sqLiteDatabase.rawQuery("select * from Scenes where sceneID = " + String.valueOf(questionCursor.getInt(1)),  null); //select * from Scenes where sceneID = 3
            sceneCursor.moveToFirst();

            //questionsID
            questionsID.add(cursor.getInt(2));
            //questions
            questions.add(questionCursor.getString(2));
            //correct
            correct.add(questionCursor.getInt(7) == cursor.getInt(3));
            //scene
            scenes.add(sceneCursor.getString(1));
            //answers
            answers.add(cursor.getInt(3));
        }

    }

    public void getScenesInfo(ArrayList<ArrayList<String>> table){  //fetch informação dos cenários

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from Scenes",  null);

        while(cursor.moveToNext()){

            ArrayList<String> row = new ArrayList<>();

            row.add(String.valueOf(cursor.getInt(0)));
            row.add(cursor.getString(1));

            table.add(row);
        }

    }

    public void getQuestionsInfo(ArrayList<ArrayList<String>> table){   //fetch informção das questões (for RViwer)

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select questionID, sceneID, question from Questions",  null);

        while(cursor.moveToNext()){

            ArrayList<String> row = new ArrayList<>();

            row.add(String.valueOf(cursor.getInt(0)));
            row.add(String.valueOf(cursor.getInt(1)));
            row.add(cursor.getString(2));

            table.add(row);
        }
    }

    public boolean insertScene(Context context, String scene){  //inserir cenário

        SQLiteDatabase sqlW = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if (scene.length() == 0){

            Toast.makeText(context,"ERRO: Impossível de adicionar um cenário com um campo por preencher",Toast.LENGTH_LONG).show();

            return false;
        }

        contentValues.put("sceneString", scene);
        sqlW.insert(SCENES_TABLE, null, contentValues);

        return true;
    }

    public boolean insertQuestion(Context context,String sscene, String squestion, String soption1, String soption2, String soption3, String soption4, String scorrect, String sWrongReport, String sRightReport){  //inserir questão

        SQLiteDatabase sqlW = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SQLiteDatabase sqlR = getReadableDatabase();

        Cursor cursor = sqlR.rawQuery("select COUNT(sceneID) from Scenes",  null);
        cursor.moveToFirst();

        if ((Integer.parseInt(sscene) < 1) || (Integer.parseInt(sscene) > cursor.getInt(0))){

            Toast.makeText(context,"ERRO: Insere um ID de cenário válido",Toast.LENGTH_LONG).show();

            return false;
        }

        if ((Integer.parseInt(scorrect) < 1) || (Integer.parseInt(scorrect) > 4)){

            Toast.makeText(context,"ERRO: Insere um numero de 1 a 4 na opção correta",Toast.LENGTH_LONG).show();

            return false;
        }

        if ( sscene.length() == 0 || squestion.length() == 0 || soption1.length() == 0 || soption2.length() == 0 || soption3.length() == 0 || soption4.length() == 0 || scorrect.length() == 0 || sWrongReport.length() == 0 || sRightReport.length() == 0){

            Toast.makeText(context,"ERRO: Impossível de adicionar um cenário com um campo por preencher",Toast.LENGTH_LONG).show();

            return false;
        }

        contentValues.put("sceneID", sscene);
        contentValues.put("question", squestion);
        contentValues.put("option1", soption1);
        contentValues.put("option2", soption2);
        contentValues.put("option3", soption3);
        contentValues.put("option4", soption4);
        contentValues.put("answer", scorrect);
        contentValues.put("wrongReport", sWrongReport);
        contentValues.put("correctReport", sRightReport);

        sqlW.insert(QUESTIONS_TABLE, null, contentValues);

        return true;
    }

}
