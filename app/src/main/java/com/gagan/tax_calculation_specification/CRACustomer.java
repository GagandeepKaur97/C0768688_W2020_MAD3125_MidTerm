package com.gagan.tax_calculation_specification;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.ArrayList;

public class CRACustomer  implements Parcelable {
    private String firstname;
    private String lastname;
    private DatePicker Birthdate;
    private int age;
    private ArrayList<String> gender;
    private int Sinnumber;
    private TextView taxdate;
    private Double Grosssincome;
    private Double RRSP;
    private Double federal;
    private Double provincialtax;
    private Double CPP;
    private Double EI;
    private Double CFRRSP;
    private Double Totaltaxincome;
    private Double TotalTaxPayed;

    public CRACustomer(String firstname, String lastname, DatePicker birthdate, int age, ArrayList<String> gender, int sinnumber, TextView taxdate, Double grosssincome, Double RRSP, Double federal,Double provincialtax, Double CPP, Double EI, Double CFRRSP, Double totaltaxincome, Double totalTaxPayed) {
        this.firstname = firstname;
        this.lastname = lastname;

        this. Birthdate = birthdate;
        this.age = age;
        this.gender = gender;
       this. Sinnumber = sinnumber;
        this.taxdate = taxdate;
        this.Grosssincome = grosssincome;
        this.RRSP = RRSP;
        this.federal = federal;
        this.provincialtax = provincialtax;
        this.CPP = CPP;
        this.EI = EI;
        this.CFRRSP = CFRRSP;
        this.Totaltaxincome = totaltaxincome;
       this. TotalTaxPayed = totalTaxPayed;
    }



    protected CRACustomer(Parcel in) {
        firstname = in.readString();
        lastname = in.readString();

        age =in.readInt();
        gender =in.createStringArrayList();
        Sinnumber = in.readInt();





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






    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public DatePicker getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(DatePicker birthdate) {
        Birthdate = birthdate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<String> getGender() {
        return gender;
    }

    public void setGender(ArrayList<String> gender) {
        this.gender = gender;
    }

    public int getSinnumber() {
        return Sinnumber;
    }

    public void setSinnumber(int sinnumber) {
        Sinnumber = sinnumber;
    }

    public TextView getTaxdate() {
        return taxdate;
    }

    public void setTaxdate(TextView taxdate) {
        this.taxdate = taxdate;
    }

    public Double getGrosssincome() {
        return Grosssincome;
    }

    public void setGrosssincome(Double grosssincome) {
        Grosssincome = grosssincome;
    }

    public Double getRRSP() {
        return RRSP;
    }

    public void setRRSP(Double RRSP) {
        this.RRSP = RRSP;
    }

    public Double getFederal() {
        return federal;
    }

    public void setFederal(Double federal) {
        this.federal = federal;
    }

    public Double getProvincialtax() {
        return provincialtax;
    }

    public void setProvincialtax(Double provincialtax) {
        this.provincialtax = provincialtax;
    }

    public Double getCPP() {
        return CPP;
    }

    public void setCPP(Double CPP) {
        this.CPP = CPP;
    }

    public Double getEI() {
        return EI;
    }

    public void setEI(Double EI) {
        this.EI = EI;
    }

    public Double getCFRRSP() {
        return CFRRSP;
    }

    public void setCFRRSP(Double CFRRSP) {
        this.CFRRSP = CFRRSP;
    }

    public Double getTotaltaxincome() {
        return Totaltaxincome;
    }

    public void setTotaltaxincome(Double totaltaxincome) {
        Totaltaxincome = totaltaxincome;
    }

    public Double getTotalTaxPayed() {
        return TotalTaxPayed;
    }

    public void setTotalTaxPayed(Double totalTaxPayed) {
        TotalTaxPayed = totalTaxPayed;
    }

    public static Creator<CRACustomer> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(firstname);
        parcel.writeString(lastname);

        parcel.writeInt(age);
        parcel.writeStringList(gender);
        parcel.writeInt(Sinnumber);
    }
}
