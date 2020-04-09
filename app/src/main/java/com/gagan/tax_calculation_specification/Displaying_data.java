package com.gagan.tax_calculation_specification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class Displaying_data extends AppCompatActivity {
    private TextView CPP;
    private TextView EI;
    private TextView RRSP_CO;
    private TextView RRSP;
    private TextView TOTALTAXINCOME;
    private TextView ProvincialTAX;
    private TextView federalTAX;
    private TextView TotalTaxPayed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaying_data);

        CPP = findViewById(R.id.CPP);
        EI = findViewById(R.id.EI);
        RRSP = findViewById(R.id.RRSP);
        RRSP_CO =findViewById(R.id.RRSP_co);
        TOTALTAXINCOME = findViewById(R.id.TTI);
        ProvincialTAX = findViewById(R.id.PT);
        federalTAX = findViewById(R.id.FT);
        TotalTaxPayed = findViewById(R.id.TTPe);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        CRACustomer C2 = (CRACustomer) extras.getParcelable("object");

        Double gross = C2.getGrossIncome();
        Double contributedRrsp = C2.getRRSP();
        Double TotalTaxableIncome = (gross-(calcualtecpp(gross)+calculateEi(gross)+calculateMaxRrsp(gross)));

       CPP.setText(calcualtecpp(gross).toString());
       EI.setText(calculateEi(gross).toString());

        if(calculateMaxRrsp(gross) > contributedRrsp)
        {
            Double x = calculateMaxRrsp(gross) - contributedRrsp;
           RRSP_CO.setText(contributedRrsp.toString());

        }else if(calculateMaxRrsp(gross) < contributedRrsp)
        {
            Double x = calculateMaxRrsp(gross) - contributedRrsp;
           RRSP_CO.setText(x.toString());
            RRSP_CO.setTextColor(Color.RED);
            RRSP.setText(contributedRrsp.toString());

        }

      TOTALTAXINCOME.setText(TotalTaxableIncome.toString());

        if(TotalTaxableIncome >= 220000){
            Double pt = TotalTaxableIncome*0.1316;
            ProvincialTAX.setText(pt.toString());

        }else if ((TotalTaxableIncome >= 150000) &&(TotalTaxableIncome <= 220000)){
            Double pt = TotalTaxableIncome*0.1216;
            ProvincialTAX.setText(pt.toString());
        }else if ((TotalTaxableIncome >= 87813.01) &&(TotalTaxableIncome <= 150000)){
            Double pt = TotalTaxableIncome*0.1116;
            ProvincialTAX.setText(pt.toString());
        }else if ((TotalTaxableIncome >= 43906.01) &&(TotalTaxableIncome <= 87813)){
            Double pt = TotalTaxableIncome*0.0915;
            ProvincialTAX.setText(pt.toString());
        }else if ((TotalTaxableIncome >= 10582.01) &&(TotalTaxableIncome <= 43906)){
            Double pt = TotalTaxableIncome*0.0505;
            ProvincialTAX.setText(pt.toString());
        }else if (TotalTaxableIncome <= 10582){
            Double pt = TotalTaxableIncome;
            ProvincialTAX.setText(pt.toString());
        }


        if(TotalTaxableIncome >= 210371.01){
            Double pt = TotalTaxableIncome*0.33;
            federalTAX.setText(pt.toString());
        }else if ((TotalTaxableIncome >= 147667.01) &&(TotalTaxableIncome <= 210371)){
            Double pt = TotalTaxableIncome*0.29;
            federalTAX.setText(pt.toString());
        }else if ((TotalTaxableIncome >= 95259.01) &&(TotalTaxableIncome <= 147667)){
            Double pt = TotalTaxableIncome*0.26;
            federalTAX.setText(pt.toString());
        }else if ((TotalTaxableIncome >= 47630.01) &&(TotalTaxableIncome <= 95259)){
            Double pt = TotalTaxableIncome*0.2050;
            federalTAX.setText(pt.toString());
        }else if ((TotalTaxableIncome >= 12609.01) &&(TotalTaxableIncome <= 47630)){
            Double pt = TotalTaxableIncome*0.15;
            federalTAX.setText(pt.toString());
        }else if (TotalTaxableIncome <= 12069){
            Double pt = TotalTaxableIncome;
            federalTAX.setText(pt.toString());
        }




    }


    private Double calcualtecpp(Double gross)
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
    private Double calculateEi(Double gross){
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


