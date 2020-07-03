package com.example.ta;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterMonitoring extends RecyclerView.Adapter<AdapterMonitoring.DataViewHolder>{
    private Context context;
    private List<DataMonitoring> dataList;

    public AdapterMonitoring(Context context, List<DataMonitoring> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.mot_item,null);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        final DataMonitoring data = dataList.get(position);
        holder.idkapal.setText(data.getIdkapal());
        holder.idlatitude.setText(data.getIdlatitude());
        holder.idlongitude.setText(data.getIdlongitude());
        holder.idstatus.setText(data.getIdstatus());
        holder.idtime.setText(data.getIdtime());
        holder.idlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context,DetailMonitoring.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("data",data);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{
        TextView idkapal,idlatitude,idlongitude,idstatus,idtime;
        LinearLayout idlayout;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            idkapal = itemView.findViewById(R.id.idkapal);
            idlatitude = itemView.findViewById(R.id.idlatitude);
            idlongitude = itemView.findViewById(R.id.idlongitude);
            idstatus = itemView.findViewById(R.id.idstatus);
            idtime = itemView.findViewById(R.id.idtime);
            idlayout = itemView.findViewById(R.id.idlayout);
        }
    }
}
