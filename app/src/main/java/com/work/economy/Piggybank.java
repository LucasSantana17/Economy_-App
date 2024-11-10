package com.work.economy;
import android.util.Log;

public class Piggybank {

    private static final String TAG = "Piggybank";

    private double userAccount = 0;
    private double inputValue;
    private double outputValue;

    public Piggybank(){
    }

    public double getUserAccount(){
        return userAccount;
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


    //  # A intenção é que apartir desse método aconteça uma chamada para o banco de dados

    public void accountEntry(double inputValue){ // Metodo de entrada na conta
        this.userAccount = userAccount + inputValue;
        Log.d(TAG,"valor adicionado na conta: "+ inputValue+" Valor atual: "+ getUserAccount());
    }

    public void accountExit(double outputValue){ // Metodo de saida da conta
        this.userAccount = userAccount - outputValue;
        Log.d(TAG,"valor retirado da conta: "+ outputValue + " Valor atual: "+getUserAccount());
    }

}
