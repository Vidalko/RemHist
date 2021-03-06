package com.example.vidal.rememberhistory;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

/**
 * Created by Vidal on 02.10.2017.
 */

public class AdapterDate  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private LayoutInflater inflater;
    List<DataDate> data= Collections.emptyList();
    DataDate current;

    public AdapterDate(Context context, List<DataDate> data){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_date, parent,false);
        AdapterDate.MyHolder holder=new AdapterDate.MyHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        final AdapterDate.MyHolder myHolder= (AdapterDate.MyHolder) holder;
        current = data.get(position);
        myHolder.textEvent.setText(current.event);
        myHolder.textYear.setText(current.year);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder{

        TextView textEvent;
        TextView textYear;

        public MyHolder(View itemView) {
            super(itemView);
            textEvent= (TextView) itemView.findViewById(R.id.textEvent);
            textYear = (TextView) itemView.findViewById(R.id.textYear);
        }

    }
}
