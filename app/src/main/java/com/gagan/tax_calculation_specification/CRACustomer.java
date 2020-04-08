package com.gagan.tax_calculation_specification;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;

public class CRACustomer  implements Parcelable {
    private String firstname;
    private String lastname;
    private String fullname;
    private DatePicker Birthdate;
    private int age;
    private ArrayList<String> gender;
    private int Sinnumber;
    private TextView taxdate;
    private Float Grosssincome;
    private Float RRSP;
    private float federal;
    private float provincialtax;
    private float CPP;
    private float EI;
    private float CFRRSP;

    public CRACustomer(String firstname, String lastname, String fullname, DatePicker birthdate, int age, ArrayList<String> gender, int sinnumber, TextView taxdate, Float grosssincome, Float RRSP, float federal, float provincialtax, float CPP, float EI, float CFRRSP, float totaltaxincome, float totalTaxPayed) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.fullname = fullname;
        Birthdate = birthdate;
        this.age = age;
        this.gender = gender;
        Sinnumber = sinnumber;
        this.taxdate = taxdate;
        Grosssincome = grosssincome;
        this.RRSP = RRSP;
        this.federal = federal;
        this.provincialtax = provincialtax;
        this.CPP = CPP;
        this.EI = EI;
        this.CFRRSP = CFRRSP;
        Totaltaxincome = totaltaxincome;
        TotalTaxPayed = totalTaxPayed;
    }

    private float Totaltaxincome;
    private float TotalTaxPayed;

    protected CRACustomer(Parcel in) {
    }

    public static final Creator<CRACustomer> CREATOR = new Creator<CRACustomer>() {
        @Override
        public CRACustomer createFromParcel(Parcel in) {
            return new CRACustomer(in);
        }

        @Override
        public CRACustomer[] newArray(int size) {
            return new CRACustomer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
