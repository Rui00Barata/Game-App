package com.hobies.beequal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//adapter para rv das questões nas definições
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Context context;
    private ArrayList<ArrayList<String>> questionsInfo;

    QuestionAdapter(Context context, ArrayList<ArrayList<String>> questionsInfo){

        this.context = context;
        this.questionsInfo = questionsInfo;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.question_row, parent, false);

        return new QuestionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {

        ArrayList<String> questionRow = questionsInfo.get(position);

        holder.questionID.setText(questionRow.get(0));
        holder.questionSceneID.setText("ID DE CENÁRIO: " +  questionRow.get(1));
        holder.questionString.setText(questionRow.get(2));

    }

    @Override
    public int getItemCount() {
        return questionsInfo.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder {

        TextView questionID, questionSceneID, questionString;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);

            questionID = itemView.findViewById(R.id.questionID);
            questionSceneID = itemView.findViewById(R.id.questionSceneID);
            questionString = itemView.findViewById(R.id.questionString);
        }
    }
}
