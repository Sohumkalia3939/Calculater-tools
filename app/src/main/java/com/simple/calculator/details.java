package com.simple.calculator;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class details extends AppCompatActivity {

    Calendar mNow = Calendar.getInstance();
    public long month;
    int[] mDates;
    int[] resDate;
    long resDay;
    long resMonth;
    long resYear;
    long reshours;
    long resMinutes;
    long resSeconds;
    long Temp;
    long calculatedDay;
    long diff,Temp2;

    private List<ConvertedVal> mConversionList;

    private static final int DAY_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int YEAR_INDEX = 2;

    //CurrentAge --> First CardView's Widget
    TextView CurrentYear,CurrentMonth,CurrentDays;

    //NextBirthDay --> Second CardView's Widget
    TextView remainingMonth,remainingDays;

    //DetailAge --> Third CardView's Widget
    TextView totalY,totalM,totalW,totalD,totalHr,totalMints,totalSec;

    ImageView imageView;


    //Admob

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        imageView = findViewById(R.id.img_share);

        //Initialize xml Widgets
        //CurrentAge --> First CardView Widget
        CurrentYear = findViewById(R.id.ageYears);
        CurrentMonth = findViewById(R.id.ageMonths);
        CurrentDays = findViewById(R.id.ageDays);

        //NextBirthDay --> Second CardView's Widget
        remainingMonth = findViewById(R.id.nextBDMonth);
        remainingDays = findViewById(R.id.nextDay);

        //DetailAge --> Third CardView's Widget
        totalY = findViewById(R.id.totalYear);
        totalM = findViewById(R.id.totalMonth);
        totalW = findViewById(R.id.totalWeek);
        totalD = findViewById(R.id.totalDay);
        totalHr = findViewById(R.id.totalHour);
        totalMints = findViewById(R.id.totalMinute);
        totalSec = findViewById(R.id.totalSecond);



        /*MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("ABCDEF012345"))
                        .build());*/




        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {



                    }
                });

            }
        },30,30, TimeUnit.SECONDS);

        Intent intent = getIntent();
        mDates =intent.getIntArrayExtra("pickDate");
        initialiseData();

        calculatedDay = getDiff(DifferenceIn.DAYS);

        Temp=((int)calculatedDay/365);
        resYear=Temp;
        //Years
        CurrentYear.setText(String.valueOf(resYear));
        //Months
        Temp=((int)calculatedDay%365);
        Temp= Temp/31;
        resMonth=Temp;
        CurrentMonth.setText(String.valueOf(resMonth));

        //Days

        Temp=((int)calculatedDay%365);
        Temp=Temp%31;
        resDay=Temp;
        CurrentDays.setText(String.valueOf(resDay));


        totalY.setText(String.valueOf(getDiff(DifferenceIn.YEARS)));
        totalM.setText(String.valueOf(getDiff(DifferenceIn.MONTHS)));
        totalW.setText(String.valueOf(getDiff(DifferenceIn.WEEKS)));
        totalD.setText(String.valueOf(getDiff(DifferenceIn.DAYS)));
        totalHr.setText(String.valueOf(getDiff(DifferenceIn.DAYS)*24));
        totalMints.setText(String.valueOf(getDiff(DifferenceIn.DAYS)*24*60));
        totalSec.setText(String.valueOf(getDiff(DifferenceIn.DAYS)*24*60*60));

        Calendar cal = Calendar.getInstance();

        cal.set(Calendar.DAY_OF_MONTH, mDates[DAY_INDEX]);
        cal.set(Calendar.MONTH, mDates[MONTH_INDEX]);
        cal.set(Calendar.YEAR, mDates[YEAR_INDEX]);


        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(mDates[YEAR_INDEX], mDates[MONTH_INDEX], mDates[DAY_INDEX]);

        LocalDate nextBDay = birthday.withYear(today.getYear());

        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1);
        }

        Period p = Period.between(today, nextBDay);
        long p2 = ChronoUnit.DAYS.between(today, nextBDay);

        //  Log.d("abcd",p2+"");
        Temp2= (long) (p2/30.5);
        long mont=Temp2;
        if(mont!=12)
            remainingMonth.setText(String.valueOf(mont));

        p2= (long) (p2%30.5);
        remainingDays.setText(String.valueOf(p2));


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                shareApp();
            }
        });

    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public long getDiff(DifferenceIn duration) {
        int currentMonth = mNow.get(Calendar.MONTH)+1; //+1 since months are 0 indexed
//        Log.d(TAG, "current month: " + currentMonth);

        int currentDayOfMonth = mNow.get(Calendar.DAY_OF_MONTH);
//        Log.d(TAG, "current day: " + currentDayOfYear);

        int currentYear = mNow.get(Calendar.YEAR);
//        Log.d(TAG, "current year: " + currentYear);

        LocalDate startDate = LocalDate.of(mDates[YEAR_INDEX], mDates[MONTH_INDEX], mDates[DAY_INDEX]);
        LocalDate endDate = LocalDate.of(currentYear, currentMonth, currentDayOfMonth);




        long diff = 0; //default value

        switch (duration) {

            case SECONDS:
                LocalDateTime startDateSec = LocalDateTime.of(mDates[YEAR_INDEX], mDates[MONTH_INDEX],
                        mDates[DAY_INDEX], 0, 0, 0);

                LocalDateTime endDateSec = LocalDateTime.of(currentYear, currentMonth,
                        currentDayOfMonth,
                        mNow.get(Calendar.HOUR_OF_DAY),
                        mNow.get(Calendar.MINUTE),
                        mNow.get(Calendar.SECOND));

                LocalDateTime temp = LocalDateTime.from(startDateSec);

                diff = temp.until(endDateSec, ChronoUnit.SECONDS);
                //       Log.d(TAG, "seconds diff: " + diff);
                break;

            case DAYS:
                diff = ChronoUnit.DAYS.between(startDate, endDate);
                //     Log.d(TAG, "days diff: " + diff);
                break;

            case WEEKS:
                diff = ChronoUnit.WEEKS.between(startDate, endDate);
                //   Log.d(TAG, "weeks diff: " + diff);
                break;

            case MONTHS:
                diff = ChronoUnit.MONTHS.between(startDate, endDate);
                // Log.d(TAG, "months diff: " + diff);
                break;
            case YEARS:
                diff = ChronoUnit.YEARS.between(startDate, endDate);
                // Log.d(TAG, "months diff: " + diff);
                break;
            //case HOURS:
            //      diff = ChronoUnit.HOURS.between(startDate, endDate);
            // Log.d(TAG, "months diff: " + diff);
            //    break;
            //  case MINUTES:
            //    diff = ChronoUnit.MINUTES.between(startDate, endDate);
            // Log.d(TAG, "months diff: " + diff);
            //  break;


        }

        return diff;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initialiseData() {
        mConversionList = new ArrayList<>();
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.MONTHS), "months"));
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.WEEKS), "weeks"));
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.DAYS), "days"));
        mConversionList.add(new ConvertedVal(" " + getDiff(DifferenceIn.SECONDS), "seconds"));

    }

    private void shareApp()
    {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void loadInterstitial()
    {

        //Test InterstitialAd : ca-app-pub-3940256099942544/1033173712


    }
}
