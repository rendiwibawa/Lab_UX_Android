package com.example.lab_ux.HomePage;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab_ux.ItemPage.DataClass;
import com.example.lab_ux.ItemPage.DetailActivity;
import com.example.lab_ux.R;

import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalViewHolder> {
    private Context context;
    private List<DataClass> dataList;

    public void setSearchList(List<DataClass> dataSearchList){
        this.dataList = dataSearchList;
        notifyDataSetChanged();
    }


    public HorizontalAdapter(Context context, List<DataClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        holder.horiImage.setImageResource(dataList.get(position).getDataImage());

        holder.horiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("Image", dataList.get(holder.getAdapterPosition()).getDataImage());
                intent.putExtra("Title", dataList.get(holder.getAdapterPosition()).getDataTitle());
                intent.putExtra("Desc", dataList.get(holder.getAdapterPosition()).getDataDesc());
                intent.putExtra("Price", dataList.get(holder.getAdapterPosition()).getDataPrice());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

class HorizontalViewHolder extends RecyclerView.ViewHolder{
    ImageView horiImage;
    CardView cardView;
    public HorizontalViewHolder(@NonNull View itemView) {
        super(itemView);

        horiImage = itemView.findViewById(R.id.itemImage);
        cardView = itemView.findViewById(R.id.horiCard);
    }
}

