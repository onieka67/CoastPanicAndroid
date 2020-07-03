package com.example.ta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class AdapterKapal extends RecyclerView.Adapter<AdapterKapal.DataViewHolder>{
    private Context context;
    private List<DataKapal> dataList;
    RequestOptions option;

    public AdapterKapal(Context context, List<DataKapal> dataList) {
        this.context = context;
        this.dataList = dataList;
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.kapal_item,null);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        final DataKapal data = dataList.get(position);
        holder.idkapal.setText(data.getIdkapal());
        holder.idtipekapal.setText(data.getIdtipekapal());
        holder.idpanjang.setText(data.getIdpanjang());
        holder.idlebar.setText(data.getIdlebar());
//        Picasso.with(context).load(data.getIdimg()).into(holder.idkapalimg);
//        Picasso.with(context)
//                .load(data.getIdimg())
//                .fit().centerCrop()
//                .placeholder(R.drawable.drawable)
//                .into(holder.idkapalimg);
//        Picasso.with(context).load(data.getIdimg()).resize(50, 50).
//                centerCrop().into(holder.idkapalimg);
        Glide.with(context)
                .load(dataList.get(position).getIdimg())
                .apply(option)
                .into(holder.idkapalimg);
//        holder.idlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(context,DetailKapal.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.putExtra("data",data);
//                context.startActivity(i);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{
        TextView idkapal,idtipekapal,idpanjang,idlebar;
        ImageView idkapalimg;
        LinearLayout idlayout;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            idkapal = itemView.findViewById(R.id.idkapal);
            idtipekapal = itemView.findViewById(R.id.idtipekapal);
            idpanjang = itemView.findViewById(R.id.idpanjang);
            idlebar = itemView.findViewById(R.id.idlebar);
            idkapalimg = itemView.findViewById(R.id.idkapalimg);
            idlayout = itemView.findViewById(R.id.idlayout);
        }
    }
}
