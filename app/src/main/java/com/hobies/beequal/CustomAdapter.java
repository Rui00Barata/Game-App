package com.hobies.beequal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//adapter para historico
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;                            //context
    private ArrayList scenes, questions, correct;       //array list com as informações necessárias
    private OnClickListener monClickListener;           //onclick listener para ir para outra atividade (AnswerHistActivity)

    CustomAdapter(Context context, ArrayList scenes, ArrayList questions, ArrayList correct, OnClickListener onClickListener){

        this.context = context;
        this.scenes = scenes;
        this.questions = questions;
        this.correct = correct;
        this.monClickListener = onClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(v, monClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.scene.setText(String.valueOf(scenes.get(position)));
        holder.question.setText(String.valueOf(questions.get(position)));

        if(Boolean.parseBoolean(String.valueOf(correct.get(position))))
            holder.row.setBackgroundColor(0x6000FF00);
        else
            holder.row.setBackgroundColor(0x60FF0000);
    }

    @Override
    public int getItemCount() {
        return scenes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView scene;
        TextView question;
        ConstraintLayout row;
        OnClickListener onClickListener;

        public MyViewHolder(@NonNull View itemView, OnClickListener onClickListener) {
            super(itemView);

            scene = itemView.findViewById(R.id.tvScene);
            question = itemView.findViewById(R.id.tvQuestion);
            row = itemView.findViewById(R.id.row);
            this.onClickListener = onClickListener;


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.OnCLick(getAdapterPosition());
        }
    }

    public interface OnClickListener{
        void OnCLick(int position);
    }
}
