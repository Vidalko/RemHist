package com.example.vidal.rememberhist;

/**
 * Created by Vidal on 02.10.2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

public class AdapterMainData extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    List<DataMain> data= Collections.emptyList();
    DataMain current;

    // create constructor to innitilize context and data sent from MainActivity
    public AdapterMainData(Context context, List<DataMain> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    // Inflate the layout when viewholder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item, parent,false);
        MyHolder holder=new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in recyclerview to bind data and assign values from list
        MyHolder myHolder= (MyHolder) holder;
        current=data.get(position);
        myHolder.textEvent.setText(current.event);
        myHolder.textYear.setText(current.year);


    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textEvent;
        TextView textYear;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textEvent= (TextView) itemView.findViewById(R.id.textEvent);
            textYear = (TextView) itemView.findViewById(R.id.textYear);
        }

    }

}
