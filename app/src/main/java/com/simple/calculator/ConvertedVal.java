package com.simple.calculator;

public class ConvertedVal {

    String strCalculatedValue;
    String strUnit;

    public ConvertedVal(String strCalculatedValue, String strUnit) {
        this.strCalculatedValue = strCalculatedValue;
        this.strUnit = strUnit;
    }

    public String getStrCalculatedValue() {
        return strCalculatedValue;
    }

    public void setStrCalculatedValue(String strCalculatedValue) {
        this.strCalculatedValue = strCalculatedValue;
    }

    public String getStrUnit() {
        return strUnit;
    }

    public void setStrUnit(String strUnit) {
        this.strUnit = strUnit;
    }
}

