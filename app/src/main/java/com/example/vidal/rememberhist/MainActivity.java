package com.example.vidal.rememberhist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private RecyclerView mRVData;
    private AdapterMainData mAdapter;

    final Context context = this;

    String[] event = new String[] {"IX - начало XVI века", "XVI - XVII века", "Конец XVII - XVIII века", "Первая половина XIX века", "Вторая половина XIX века", "Начало XX века", " 1920-1930-e ", "ВОВ и послевоенные годы", "1950-е - начало 1980-х гг.\n", "Перестройка. Гибель СССР. Российская Федерация\n",};
    String[] year = new String[] {"(862-1521)", "(1533 - 1698)", "(1682 - 1799)", "(1801-1853)", "(1855-1899)", "(1901-1922)", "(1920-1930)", "(1941-1953)", "(1950-1980)", "(1985-2008)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<DataMain> data = new ArrayList<DataMain>(10);
        for(int i = 0 ; i < 10 ; i++)
        {
            DataMain mainData = new DataMain();
            mainData.event = event[i];
            mainData.year = year[i];
            data.add(mainData);
        }
        mRVData = (RecyclerView)findViewById(R.id.fishPriceList);
        mAdapter = new AdapterMainData(MainActivity.this, data);
        mRVData.setAdapter(mAdapter);
        mRVData.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mRVData.addOnItemTouchListener(
                new RecyclerItemClickListener(context, mRVData ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(MainActivity.this, Date.class);
                        intent.putExtra("currentPosition", position);

                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // test.setText("");
                    }
                })
        );
    }
}
