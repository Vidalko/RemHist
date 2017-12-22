package com.example.vidal.rememberhistory;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Vidal on 04.10.2017.
 */

public class Remember extends AppCompatActivity {
    final Context context = this;

    Button nextBtn;
    Button prevBtn;
    ArrayList<String> yearList = new ArrayList<String>();
    ArrayList<String> eventList = new ArrayList<String>();

    TextView textData;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remember);
        nextBtn = (Button) findViewById(R.id.btnNext);
        prevBtn = (Button) findViewById(R.id.btnPrev);
        textData = (TextView)findViewById(R.id.textData);
        if(getIntent().getStringArrayListExtra("Year") != null) {
            yearList = getIntent().getStringArrayListExtra("Year");
            eventList = getIntent().getStringArrayListExtra("Event");
        }
        else {

        }
        textData.setText(yearList.get(i));
    }
    public Remember(){

    }
    public  void btnPrev(View view){
        if(yearList == null){

            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Даты зазубрены");
            alert.setMessage("Выберите другие даты или тему")
                    .setCancelable(true)
                    .setPositiveButton("Вернуться к выбору дат",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    onBackPressed();
                                    dialog.cancel();
                                }
                            });
            alert.show();
        }
        else if(i == yearList.size()-1 ) {
            String text = eventList.get(i) + "\n Это была последняя дата";
            textData.setText(text);

        }
        else if(i<yearList.size()-1) {
            textData.setText(eventList.get(i));
            yearList.add(yearList.get(i));
            eventList.add(eventList.get(i));
            nextBtn.setText("Далее");
        }

    }

    public void btnNext(View view){

            nextBtn.setText("Помню");

            if (yearList == null) {

                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Даты зазубрены");
                alert.setMessage("Выберите другие даты или тему")
                            .setCancelable(true)
                            .setPositiveButton("Вернуться к выбору дат",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        onBackPressed();
                                        dialog.cancel();
                                    }
                                });
                alert.show();
            } else if (i == yearList.size() - 1) {
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("Даты зазубрены");
                alert.setMessage("Выберите другие даты или тему")
                        .setCancelable(true)
                        .setPositiveButton("Вернуться к выбору дат",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        onBackPressed();
                                        dialog.cancel();
                                    }
                                });
                alert.show();
                yearList = null;
                eventList = null;
            } else {
                i++;
                textData.setText(yearList.get(i));
            }

    }
    public void btnSave(View view){


    }

}
