package com.hobies.beequal;

import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

public class Util extends AppCompatActivity {

    private static final String FILE_NAME = "data.txt";


    public void MSG(String s){  //para mostrar toasts

        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    public void writeUser(String s){ //para escrever utilizador atual no ficheiro

        FileOutputStream fos = null;

        try {

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);

            fos.write(s.getBytes());

            MSG("Utilizador atualizado");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String readUser(){   //para ler utilizador do ficheiro

        FileInputStream fis = null;

        try {
            fis = openFileInput(FILE_NAME);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;

            while ((text = br.readLine()) != null){
                sb.append(text).append("");
            }

            return sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }

    public ArrayList<Integer> generateArray(int s){ //gerar um array com o numero total das questões disponiveis
        ArrayList<Integer> r = new ArrayList<>();

        for(int i = 1; i<=s; i++)
            r.add(i);

        return r;
    }

    public int generateNumber(int size){    //gerar a posição para escolher do array de cima
        Random rand = new Random(Calendar.getInstance().getTime().getTime());

        return rand.nextInt(size);
    }
}
