package com.work.economy;
import android.util.Log;

public class Piggybank {

    private static final String TAG = "Piggybank";

    private double userAccount = 0;
    private double inputValue;
    private double outputValue;
    private String nameValue;

    public Piggybank(){
    }

    public double getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(double userAccount) {
        this.userAccount = userAccount;
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    //  # A intenção é que apartir desse método aconteça uma chamada para o banco de dados

    public void accountEntry(String nameValue, double inputValue){ // Metodo de entrada na conta
        this.userAccount = userAccount + inputValue;
        Log.d(TAG,"valor adicionado referente a "+nameValue+": "+ inputValue+" Valor atual: "+ getUserAccount());
    }

    public void accountExit(String nameValue, double outputValue){ // Metodo de saida da conta
        this.userAccount = userAccount - outputValue;
        this.nameValue = nameValue;
        Log.d(TAG,"valor retirado referente a "+nameValue+": "+ outputValue + " Valor atual: "+getUserAccount());
    }

}
