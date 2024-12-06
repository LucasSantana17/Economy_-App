package com.work.economy;

import com.work.economy.Piggybank;

import android.content.Intent;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.work.economy.Databasepiggy;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Piggybank pinggy = new Piggybank();
    Databasepiggy economyDataBase = new Databasepiggy(this);

    private EditText inputValue;
    private EditText outputValue;
    private EditText typeValue;
    private Button inputButton;
    private Button btnConsultation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        outputValue = findViewById(R.id.outputValue);
        inputButton = findViewById(R.id.inputButton);
        typeValue = findViewById(R.id.typeValue);

        btnConsultation = findViewById(R.id.btn_consultation);


        //Botão de envio de valor
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String input_Value = inputValue.getText().toString();
                String output_Value = outputValue.getText().toString();
                String type_Value = typeValue.getText().toString();

                // BUG: Se os dois valores forem ("") ou seja sem nenhum  o programa dar erro
                // Consertar essa verificação
                if(output_Value.equals("")) {
                    pinggy.accountEntry(type_Value, Double.parseDouble(input_Value));
                }else if(input_Value.equals("")){
                    pinggy.accountExit(type_Value, Double.parseDouble(output_Value));
                }


                pinggy.setTypeValue(type_Value);
                pinggy.setInputValue(Double.parseDouble(input_Value));

                economyDataBase.insert(pinggy);
                economyDataBase.displayData();

                //Limpar os campos depois de enviar as informações
                typeValue.setText("");
                inputValue.setText("");
                outputValue.setText("");
            }
        });

        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_consultation.class);
                startActivity(intent);
            }
        });
    }
}