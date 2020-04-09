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
import android.view.GestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiresApi(api = Build.VERSION_CODES.N)

public class person_entryscreen extends AppCompatActivity {

    private TextView firstname;
    private TextView lastname;
    private TextView  txtDate;
    private TextView Age;
    private Spinner gender;
    private String[] genderArray;
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

        firstname = findViewById(R.id.first_name);
        lastname = findViewById(R.id.last_name);
        taxdate = findViewById(R.id.taxdate);
        Age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        sinnumber = findViewById(R.id.sinNumber);
        Grosspay = findViewById(R.id.Grossincome);
        RRSP = findViewById(R.id.RRSP);
        txtDate = findViewById(R.id.txtDate);
        Register = findViewById(R.id.register);
        Clear = findViewById(R.id.btnClear);

        final Calendar myCalendar = Calendar.getInstance();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpledateformat = new SimpleDateFormat(" EEEE ,dd-mm-yyyy hh:mm:ss a");
        String dateTime = simpledateformat.format(calendar.getTime());
        taxdate.setText( dateTime);
        taxdate.setTextColor(Color.BLACK);

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
                txtDate.setTextColor(Color.BLACK);
                txtDate.setTypeface(null,Typeface.BOLD);
                if (calculateAge(myCalendar.getTimeInMillis()) > 18) {
                    Age.setText( Integer.toString(calculateAge(myCalendar.getTimeInMillis())));
                    Age.setTextColor(Color.BLACK);
                    Age.setTypeface(null, Typeface.NORMAL);
                    Register.setEnabled(true);


                } else {
                    Age.setText(" Not eligible to file tax for  this  year!");
                    Age.setTextColor(Color.RED);
                    Age.setTypeface(null, Typeface.BOLD);
                    Register.setEnabled(false);


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



                if ((isValidSin(sinnumber.getText().toString())) == false) {
                    sinnumber.setError("not valid");
                }else if (firstname.getText().toString().matches("")){
                    firstname.setError("enter First name!");

                }else if (lastname.getText().toString().matches("")){
                    lastname.setError("enter Last name!");

                }else if (Age.getText().toString().matches("")) {
                    Age.setError("enter Date of birth");

                } else if (gender.getSelectedItem().toString().matches("Choose One Type")){

                    Snackbar.make(findViewById(R.id.gender), R.string.G,
                            Snackbar.LENGTH_SHORT)
                            .show();

                }else if (Grosspay.getText().toString().matches("")) {

                    Grosspay.setError("enter gross income!");

                }else if ( Double.parseDouble(Grosspay.getText().toString()) <= 0){
                    Grosspay.setError("gross income cannot be Zero");

                }else if (RRSP.getText().toString().matches("")){
                    RRSP.setError("enter Last name!");



                } else {
                    String value1 = Grosspay.getText().toString();
                    Double GrossValue = Double.parseDouble(value1);
                    String value2 = RRSP.getText().toString();
                    Double RRSPvalue = Double.parseDouble(value2);

                    sinnumber.setText(sinnumber.getText().toString());
                    Intent mintent = new Intent(person_entryscreen.this, Displaying_data.class);
                    CRACustomer C1 = new CRACustomer(taxdate.getText().toString(),sinnumber.getText().toString(),firstname.getText().toString(), lastname.getText().toString(), txtDate.getText().toString(), Age.getText().toString(), gender.getSelectedItem().toString(), GrossValue, RRSPvalue);
                    mintent.putExtra("object",C1);
                    startActivity(mintent);
                }
            }
        });


  Clear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            firstname.setText("");
            lastname.setText("");
            sinnumber.setText("");
            txtDate.setText("");
           Grosspay.setText("");
            RRSP.setText("");
            Age.setText("Age");

        }
  });


    }


    int calculateAge ( long date){
        java.util.Calendar dob = java.util.Calendar.getInstance();
        dob.setTimeInMillis(date);
        java.util.Calendar today = java.util.Calendar.getInstance();
        int age = today.get(java.util.Calendar.YEAR) - dob.get(java.util.Calendar.YEAR);
        if (today.get(java.util.Calendar.DAY_OF_MONTH) < dob.get(java.util.Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

            private boolean isValidSin(String sinnumber) {
                String sinValidation = "\\d{9}";
                Pattern pattern = Pattern.compile(sinValidation);
                Matcher matcher = pattern.matcher(sinnumber);
                return matcher.matches();
            }





}