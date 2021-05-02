package com.hobies.beequal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
//Alert dialog que permite inseerir uma pergunta nova nas definições
public class QuestionDialog extends AppCompatDialogFragment {

    private EditText etSceneID, etQuestion, etOption1, etOption2, etOption3, etOption4, etOption, etWrongReport, etRightReport;
    private QuestionDialogListener listener;
    private View v;

    public QuestionDialog(View v) {
        this.v = v;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.add_question, null);

        builder.setView(v)
                .setTitle("ADICIONAR QUESTÃO")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("FEITO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ssceneid = etSceneID.getText().toString();
                        String squestion = etQuestion.getText().toString();
                        String soption1 = etOption1.getText().toString();
                        String soption2 = etOption2.getText().toString();
                        String soption3 = etOption3.getText().toString();
                        String soption4 = etOption4.getText().toString();
                        String scorrect = etOption.getText().toString();
                        String sWrongReport = etWrongReport.getText().toString();
                        String sRightReport = etRightReport.getText().toString();

                        listener.applyQuestion(v, ssceneid, squestion, soption1, soption2, soption3, soption4, scorrect, sWrongReport, sRightReport);
                    }
                });

        etSceneID = v.findViewById(R.id.etSceneID);
        etQuestion = v.findViewById(R.id.etQuestion);
        etOption1 = v.findViewById(R.id.etOption1);
        etOption2 = v.findViewById(R.id.etOption2);
        etOption3 = v.findViewById(R.id.etOption3);
        etOption4 = v.findViewById(R.id.etOption4);
        etOption = v.findViewById(R.id.etOption);
        etWrongReport = v.findViewById(R.id.etWrongReport);
        etRightReport = v.findViewById(R.id.etRightReport);


        return builder.create();
    }

    public interface QuestionDialogListener{
        void applyQuestion(View v, String sscene, String squestion, String soption1, String soption2, String soption3, String soption4, String scorrect, String sWrongReport, String sRightReport);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (QuestionDialogListener)context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement QuestionDialogListener");
        }
    }
}
