package com.itax.billbuddies.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    String date,date2,time;
    Context context;
    int d1,m1,y1;
    int day,month,year;
    String[]monthName = {"Jan","Feb","Mar", "Apr", "May", "Jun", "Jul",
            "Aug", "Sep", "Oct", "Nov",
            "Dec"};


    public DateTime(Context context) {
        this.context = context;
        init();
    }

    private void init(){
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        SimpleDateFormat mdformat3 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat mdformat2 = new SimpleDateFormat("hh:mm:ss");
        date2 = mdformat3.format(calendar.getTime());
        time = mdformat2.format(calendar.getTime());
    }

    public String getDate(){
        return date2;
    }
    public String getTime(){
        return time;
    }
    public String getDateByName(){

        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        SimpleDateFormat mdformat3 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat mdformat2 = new SimpleDateFormat("hh:mm:ss");
        date= mdformat3.format(calendar.getTime());
        time = mdformat2.format(calendar.getTime());
//        return day+" "+monthName[month]+" "+year +" "+time +" IST";
        return monthName[month]+" " +day+" "+year +" "+time +" IST";
    }

    public String getDateYYYYMMDD_HHMMSS(){
        Calendar calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DATE);
        month = calendar.get(Calendar.MONTH);
        year = calendar.get(Calendar.YEAR);

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat dateFormat = new SimpleDateFormat(inputPattern);
        return dateFormat.format(calendar.getTime());
    }

    public void setDate(EditText ed1){
        DatePickerDialog datePicker;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                d1=dayOfMonth;
                m1=month+1;
                y1=year;
                ed1.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, year, month, day);
        datePicker.show();
    }

    public static String parseDateToddMMyyyy(String time) {

        //String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MMM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public static String getDate(String ddMMyyyy){
        //String inputPattern = "yyyy-MM-dd'T'HH:mm:ss";
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd-MM-yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(ddMMyyyy);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    public String getTimeAMPM(){
        String delegate = "hh:mm aaa";
        return (String) DateFormat.format(delegate,Calendar.getInstance().getTime());
    }

}
