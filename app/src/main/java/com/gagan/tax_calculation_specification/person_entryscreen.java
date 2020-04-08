package com.gagan.tax_calculation_specification;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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

    private TextView firstname;
    private TextView lastname;
    private TextView  txtDate;
    private TextView Age;
    private TextView gender;
    private TextView sinnumber;
    private TextView taxdate;
    private TextView Grosspay;
    private TextView RRSP;

    private Button Register;
    private Button Clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_entryscreen);
        firstname=findViewById(R.id.first_name);
        lastname=findViewById(R.id.last_name);
taxdate =findViewById(R.id.taxdate);
Age=findViewById(R.id.age);
       txtDate = findViewById(R.id.txtDate);
        Register = findViewById(R.id.register);
        Clear = findViewById(R.id.btnClear);
    final Calendar myCalendar = Calendar.getInstance();
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat simpledateformat = new SimpleDateFormat(" EEEE ,dd-mm-yyyy hh:mm:ss a");
    String dateTime = simpledateformat.format(calendar.getTime());
     taxdate.setText("Tax Filing Date: "+dateTime);

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "MM-dd-yy"; //In which you need put here
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            txtDate.setText(sdf.format(myCalendar.getTime()));
            if(calculateAge(myCalendar.getTimeInMillis()) > 18 )
            {
                Age.setText("Age: " + Integer.toString(calculateAge(myCalendar.getTimeInMillis())));
                Age.setTextColor(Color.BLACK);
                Age.setTypeface(null, Typeface.NORMAL);


            }else {
                Age.setText(" Not eligible to file tax for  this  year!");
                Age.setTextColor(Color.RED);
                Age.setTypeface(null, Typeface.BOLD);


            }


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
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstname.setText("");
                lastname.setText("");



            }
        });
    }


        int calculateAge(long date){
            java.util.Calendar dob = java.util.Calendar.getInstance();
            dob.setTimeInMillis(date);
            java.util.Calendar today = java.util.Calendar.getInstance();
            int age = today.get(java.util.Calendar.YEAR) - dob.get(java.util.Calendar.YEAR);
            if(today.get(java.util.Calendar.DAY_OF_MONTH) < dob.get(java.util.Calendar.DAY_OF_MONTH)){
                age--;
            }
            return age;}
    };
