package com.hobies.beequal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//adapter para rv dos cenários nas definições
public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.SceneViewHolder> {

    private Context context;
    private ArrayList<ArrayList<String>> scenesInfo;

    SceneAdapter(Context context, ArrayList<ArrayList<String>> scenesInfo){

        this.context = context;
        this.scenesInfo = scenesInfo;
    }


    @NonNull
    @Override
    public SceneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.scene_row, parent, false);

        return new SceneViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SceneViewHolder holder, int position) {
        ArrayList<String> sceneRow = scenesInfo.get(position);

        holder.sceneID.setText(sceneRow.get(0));
        holder.sceneString.setText(sceneRow.get(1));
    }

    @Override
    public int getItemCount() {
        return scenesInfo.size();
    }

    public class SceneViewHolder extends RecyclerView.ViewHolder {

        TextView sceneID, sceneString;

        public SceneViewHolder(@NonNull View itemView) {
            super(itemView);

            sceneID = itemView.findViewById(R.id.sceneID);
            sceneString = itemView.findViewById(R.id.sceneString);
        }
    }
}
