package com.example.vidal.rememberhist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Vidal on 04.10.2017.
 */

public class Remember extends Activity{
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
           // Remember myObject;

           // yearList = yearPref.getString(SAVED_TEXT, "");

        }
        textData.setText(yearList.get(i));

    }
    public Remember(){

    }
    public  void btnPrev(View view){
        if(yearList == null){

            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Даты зазубрены");
            alert.setMessage("Выберите другие даты или тему\"");
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
        }

    }

    public void btnNext(View view){

        if(yearList == null){

            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Даты зазубрены");
            alert.setMessage("Выберите другие даты или тему");
            alert.show();
        }
        else if(i == yearList.size()-1) {
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Даты зазубрены");
            alert.setMessage("Выберите другие даты или тему");
            alert.show();
            yearList = null;
            eventList = null;
        }
        else{

            i++;
            textData.setText(yearList.get(i));

        }


    }
    public void btnSave(View view){
       /* Remember myObject = null;
        myObject.eventList = eventList;
        myObject.yearList = yearList;
        String year = new Gson().toJson(myObject, Remember.class);
        rPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = rPref.edit();
        ed.putString(SAVED_YEAR, year);
        ed.commit();


        String savedText = rPref.getString(SAVED_YEAR, "");


        Remember yearList1 =new Gson().fromJson(savedText, Remember.class);
        yearList = yearList1.yearList;
        eventList = yearList1.eventList;
        //Toast.makeText(context, eventList.get(0), Toast.LENGTH_SHORT);
        */
    }

}
