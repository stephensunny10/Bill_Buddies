package com.itax.billbuddies.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.EditText;

import java.util.Calendar;

public class DatePicker {
    Context context;
    int d1,m1,y1;
    String date;

    public DatePicker(Context context) {
        this.context = context;
    }

    private void init(){

    }

    public void setDate(EditText editText){
        DatePickerDialog datePicker;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker = new DatePickerDialog(context, (view, year1, month1, dayOfMonth) -> {
            d1 = dayOfMonth;
            m1 = month1 +1;
            y1 = year1;
            editText.setText(year1 + "-" + (month1 + 1) + "-" + dayOfMonth);
            date = editText.getText().toString();

        }, year, month, day);
        datePicker.show();
    }
}
