package com.example.boot.entities.otherModel;


public enum Currency {
    USD("USD"),
    EUR("EUR"),
    UAH("UAH"),
    CHF("CHF"),
    GBP("GBP");

    private  String value;
    private Currency() {}
 private Currency(String value) {this.value = value;}
    @Override
    public String toString(){
        return value;
    }
}
