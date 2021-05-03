package com.simple.calculator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;


public class Emi extends AppCompatActivity{


    DatePicker datePicker;
    Button calAge;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);



        //Initialize

init();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
        //For Current Date
        String strDate =  mdformat.format(cal.getTime());

        final int minage=1;
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,-minage);
        datePicker.setMaxDate(calendar.getTimeInMillis());

        calAge.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                int dayPicked=datePicker.getDayOfMonth();
                int monthPicked= datePicker.getMonth()+1;
                int yearPicked = datePicker.getYear();
                final int selectedDates[] = {dayPicked, monthPicked, yearPicked};

                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                if(currentYear-yearPicked<=minage)
                {
                    Toast.makeText(Emi.this, "Your Age should be more than One year", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Emi.this, details.class);
                    intent.putExtra("pickDate",selectedDates);
                    startActivity(intent);
                }



            }
        });

    }

    public void init()
    {

        calAge = findViewById(R.id.calculate);
        datePicker=findViewById(R.id.datePicker);


    }





}
