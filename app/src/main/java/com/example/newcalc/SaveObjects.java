package com.example.newcalc;

import android.os.Parcel;
import android.os.Parcelable;

public class SaveObjects implements Parcelable {

    MainCalcScreenString mainCalcScreenString;//переменные для хранения требуемых объектов
    Calc calc;
    Memory memory;

    public SaveObjects(MainCalcScreenString mainCalcScreenString, Calc calc, Memory memory) {
        this.mainCalcScreenString = mainCalcScreenString;
        this.calc = calc;
        this.memory = memory;
    }

    protected SaveObjects(Parcel in) {
    }

    public static final Creator<SaveObjects> CREATOR = new Creator<SaveObjects>() {
        @Override
        public SaveObjects createFromParcel(Parcel in) {
            return new SaveObjects(in);
        }

        @Override
        public SaveObjects[] newArray(int size) {
            return new SaveObjects[size];
        }
    };

    public MainCalcScreenString getMainCalcScreenString() {
        return mainCalcScreenString;
    }

    public Calc getCalc() {
        return calc;
    }

    public Memory getMemory() {
        return memory;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
