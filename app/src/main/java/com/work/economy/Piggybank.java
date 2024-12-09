package com.work.economy;
import android.content.Context;
import android.util.Log;
import com.work.economy.Databasepiggy;

public class Piggybank {

    private static final String TAG = "Piggybank";

    private String nameValue;
    private double inputValue;
    private double userAccount = 0;
    private boolean typeValue; // Esse valor será o responsavel sobre a adição ou substração da conta

    public Piggybank(){
    }

    public double getUserAccount() {
        return userAccount;
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public boolean getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(boolean typeValue) {
        this.typeValue = typeValue;
    }

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    //  # A intenção é que apartir desse método aconteça uma chamada para o banco de dados
    public void accountEntry(String nameValue, double inputValue, boolean typeValue){ // Metodo de entrada na conta
        this.userAccount = userAccount + inputValue;

        Log.d(TAG,"Movimetações: "+
                "| Nome = "+nameValue+
                "| Valor = "+inputValue+
                "| Tipo = "+typeValue+" |");
    }

}
