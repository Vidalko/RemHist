package com.example.vidal.rememberhist;
/**
 * Created by Vidal on 02.10.2017.
 */
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Date extends Activity {

    AdapterDate mDateAdapter;
    private RecyclerView mDate;
    final Context context = this;
    List<DataDate> dataToRem = new ArrayList<DataDate>();
    List<DataDate> dataFinish = new ArrayList<DataDate>();
    Button btnRem;
    int check = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btnRem = (Button) findViewById(R.id.btnRem);
        setContentView(R.layout.activity_date);

        int position;
        int defaultValue = 0;
        Intent intent = getIntent();
        position = intent.getIntExtra("currentPosition", defaultValue);

        try {
            InputStream stream;
            if(position == 0) {
                stream = getResources().openRawResource(R.raw.date1);
            }
            else if(position == 1)
                stream = getResources().openRawResource(R.raw.date2);
            else if(position == 2)
                stream = getResources().openRawResource(R.raw.date3);
            else if(position == 3)
                stream = getResources().openRawResource(R.raw.date4);
            else if(position == 4)
                stream = getResources().openRawResource(R.raw.date5);
            else if(position == 5)
                stream = getResources().openRawResource(R.raw.date6);
            else if(position == 6)
                stream = getResources().openRawResource(R.raw.date7);
            else if(position == 7)
                stream = getResources().openRawResource(R.raw.date8);
            else if(position == 8)
                stream = getResources().openRawResource(R.raw.date9);
            else
                stream = getResources().openRawResource(R.raw.date10);
            XSSFWorkbook workbook = new XSSFWorkbook(stream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowsCount = sheet.getPhysicalNumberOfRows();
            List<DataDate> data = new ArrayList<DataDate>(rowsCount);

            for (int r = 1; r < rowsCount; r++) {
                Row row = sheet.getRow(r);
                DataFormatter formatter = new DataFormatter();
                Cell year = row.getCell(0);
                Cell name = row.getCell(1);
                DataDate mainData = new DataDate();
                mainData.event = name.getStringCellValue();
                mainData.year = formatter.formatCellValue(year);
                data.add(mainData);
            }
            mDate = (RecyclerView)findViewById(R.id.activityDate);
            mDateAdapter = new AdapterDate(Date.this, data);
            mDate.setAdapter(mDateAdapter);
            mDate.setLayoutManager(new LinearLayoutManager(Date.this));
            dataToRem = data;
        } catch (Exception e) {
            Log.e("ERROR", e.getMessage(), e);
        }

        mDate.addOnItemTouchListener(
                new RecyclerItemClickListener(context, mDate ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        DataDate mainDataFin = new DataDate();
                        mainDataFin.event = dataToRem.get(position).event;
                        mainDataFin.year = dataToRem.get(position).year;
                        dataFinish.add(mainDataFin);
                        //mDate.setBackgroundColor(Color.GREEN);
                        Toast.makeText(context,"Выбрано: " + dataToRem.get(position).event,Toast.LENGTH_SHORT).show();
                        check++;

                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // test.setText("");
                    }
                })
        );

    }

    public void onClick(View view){


            if(check != 0) {
                ArrayList<String> yearList = new ArrayList<String>();
                ArrayList<String> eventList = new ArrayList<String>();
                Iterator<DataDate> iter = dataFinish.iterator();
                while (iter.hasNext()) {
                    yearList.add(iter.next().year);
                }
                iter = dataFinish.iterator();
                while (iter.hasNext()) {
                    eventList.add(iter.next().event);
                }
                Intent intent = new Intent(Date.this, Remember.class);
                intent.putStringArrayListExtra("Year", yearList);
                intent.putStringArrayListExtra("Event", eventList);
                startActivity(intent);
            }
            else
                Toast.makeText(context,"Выберите даты",Toast.LENGTH_SHORT).show();
    }

}
