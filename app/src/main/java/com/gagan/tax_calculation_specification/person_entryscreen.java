package com.gagan.tax_calculation_specification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;

@RequiresApi(api = Build.VERSION_CODES.N)
public class person_entryscreen extends AppCompatActivity {
    private TextView txtDate;
    private Button Register;
    private Button Clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_entryscreen);
       txtDate = findViewById(R.id.txtDate);
        Register = findViewById(R.id.register);
        Clear = findViewById(R.id.btnClear);
    final Calendar myCalendar = Calendar.getInstance();


    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM/dd/yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            txtDate.setText(sdf.format(myCalendar.getTime()));

        }

    };

       txtDate.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            new DatePickerDialog(person_entryscreen.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        }

    });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mintent = new Intent(person_entryscreen.this, Displaying_data.class);
                startActivity(mintent);
            }
        });
    }
}