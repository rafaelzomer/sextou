package net.unesc.sextou.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CustomInputDate {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt", "BR"));

    public static Date toDate(String value) {
        try {
            return SIMPLE_DATE_FORMAT.parse(value);
        } catch (ParseException e) {
            return null;
        }
    }

    public static void make(Context context, EditText editText) {
        Calendar myCalendar = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                (view, year, monthOfYear, dayOfMonth) -> {
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthOfYear);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    editText.setText(SIMPLE_DATE_FORMAT.format(myCalendar.getTime()));
                },
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        );
        editText.setKeyListener(null);
        editText.setShowSoftInputOnFocus(true);
        editText.setOnClickListener(v -> datePickerDialog.show());
    }
}
