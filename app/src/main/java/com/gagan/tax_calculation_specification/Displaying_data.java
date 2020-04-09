package com.gagan.tax_calculation_specification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Displaying_data extends AppCompatActivity {
    private TextView sinnumber;
    private TextView fullname;
    private TextView Birthdate;
    private TextView Age;
    private TextView Date;
    private TextView Grossincome;
    private TextView Gender;
    private TextView CPP;
    private TextView EI;
    private TextView CFRSSP;
    private TextView RRSP;
    private TextView TOTALTAXABINCOME;
    private TextView ProvincialTAX;
    private TextView federalTAX;
    private TextView TotalTaxPayed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaying_data);
        sinnumber = findViewById(R.id.SN);
        fullname = findViewById(R.id.full_name);
        Birthdate = findViewById(R.id.BD);
        Age = findViewById(R.id.age);
        Date = findViewById(R.id.CD);
        Grossincome = findViewById(R.id.GI);
        Gender = findViewById(R.id.G);
        CPP = findViewById(R.id.cp);
        EI = findViewById(R.id.EI);
        RRSP = findViewById(R.id.RP);

        CFRSSP =findViewById(R.id.CRP);
        TOTALTAXABINCOME = findViewById(R.id.TI);
        ProvincialTAX = findViewById(R.id.PT);
        federalTAX = findViewById(R.id.FT);
        TotalTaxPayed = findViewById(R.id.TTPe);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CRACustomer C2 = (CRACustomer) extras.getParcelable("object");

        Double gross = C2.getGrossIncome();
        Double RRSP_CO = C2.getRRSP();
        Double  TOTALTAXINCOME = (gross-(calcualteCPP(gross)+calculateEI(gross)+calculateMaxRrsp(gross)));


        CPP.setText(String.format("$%.2f", calcualteCPP(gross)));
        EI.setText(String.format("$%.2f",calculateEI(gross)));


        if(calculateMaxRrsp(gross) >  RRSP_CO)
        {
            Double x = calculateMaxRrsp(gross) -  RRSP_CO;
            CFRSSP.setText(String.format("$%.2f",x));
            RRSP.setText(String.format("$%.2f",RRSP_CO));


        }else if(calculateMaxRrsp(gross) <  RRSP_CO) {
            Double x = calculateMaxRrsp(gross) - RRSP_CO;
            CFRSSP.setText(String.format("$%.2f", x));
            CFRSSP.setTextColor(Color.RED);
            RRSP.setText(String.format("$%.2f", RRSP_CO));

        }

      TOTALTAXABINCOME.setText(String.format("$%.2f",TOTALTAXINCOME));

        if( TOTALTAXINCOME >= 220000){
            Double pt = TOTALTAXINCOME*0.1316;
            ProvincialTAX.setText(String.format("%.2f",pt));

        }else if (( TOTALTAXINCOME >= 150000) &&( TOTALTAXINCOME <= 220000)){
            Double pt =  TOTALTAXINCOME*0.1216;
            ProvincialTAX.setText(String.format("%.2f",pt));
        }else if (( TOTALTAXINCOME >= 87813.01) &&( TOTALTAXINCOME <= 150000)){
            Double pt =  TOTALTAXINCOME*0.1116;
            ProvincialTAX.setText(String.format("%.2f",pt));
        }else if (( TOTALTAXINCOME >= 43906.01) &&( TOTALTAXINCOME <= 87813)){
            Double pt =  TOTALTAXINCOME*0.0915;
            ProvincialTAX.setText(String.format("%.2f",pt));
        }else if (( TOTALTAXINCOME >= 10582.01) &&( TOTALTAXINCOME <= 43906)){
            Double pt =  TOTALTAXINCOME*0.0505;
            ProvincialTAX.setText(String.format("%.2f",pt));
        }else if ( TOTALTAXINCOME <= 10582){
            Double pt =  TOTALTAXINCOME;
            ProvincialTAX.setText(String.format("%.2f",pt));
        }


        if( TOTALTAXINCOME >= 210371.01){
            Double ft =  TOTALTAXINCOME*0.33;
            federalTAX.setText(String.format("%.2f",ft));
        }else if (( TOTALTAXINCOME >= 147667.01) &&( TOTALTAXINCOME <= 210371)){
            Double ft =  TOTALTAXINCOME*0.29;
            federalTAX.setText(String.format("%.2f",ft));
        }else if (( TOTALTAXINCOME >= 95259.01) &&( TOTALTAXINCOME <= 147667)){
            Double ft =  TOTALTAXINCOME*0.26;
            federalTAX.setText(String.format("%.2f",ft));
        }else if (( TOTALTAXINCOME >= 47630.01) &&( TOTALTAXINCOME <= 95259)){
            Double ft =  TOTALTAXINCOME*0.2050;
            federalTAX.setText(String.format("%.2f",ft));
        }else if (( TOTALTAXINCOME >= 12609.01) &&( TOTALTAXINCOME <= 47630)){
            Double ft =  TOTALTAXINCOME*0.15;
            federalTAX.setText(String.format("%.2f",ft));
        }else if ( TOTALTAXINCOME <= 12069){
            Double ft =  TOTALTAXINCOME;
            federalTAX.setText(String.format("%.2f",ft));
        }

        String value1= ProvincialTAX.getText().toString();
        Double Finalprovincial=Double.parseDouble(value1);
        String value2= federalTAX.getText().toString();
        Double Finalfederal=Double.parseDouble(value2);

        Double x = Finalprovincial + Finalfederal;
        TotalTaxPayed.setText("$"+x.toString());

        ProvincialTAX.setText("$"+value1);
        federalTAX.setText("$"+value2);

        sinnumber.setText(C2.getSinNo());
        fullname.setText(C2.getLastName().toUpperCase()+" "+C2.getFirstName());
        Birthdate.setText(C2.getBirthdate());
        Gender.setText(C2.getGender());
        Age.setText(C2.getAge());
        Date.setText(C2.getCurrentDate());

        Grossincome.setText("$"+C2.getGrossIncome().toString());



    }


    private Double calcualteCPP(Double gross)
    {
        Double x;
        Double y;
        if(gross < 57400){
            x = 57400 - gross;
            y = 57400 -x;
            x = y * 0.051;

        }else
        {
            x = gross - 57400;
            y = gross - x;
            x = y * 0.051;
        }

        return x;

    }
    private Double calculateEI(Double gross){
        Double x;

        if(gross >= 53100){

            x = 860.22;

        }else{

            x = gross *0.0162;

        }

        return x;
    }

    private Double calculateMaxRrsp(Double gross){
        Double x;

        x = gross * 0.18;

        return x;


    }


}


