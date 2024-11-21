package com.work.economy;

import com.work.economy.Piggybank;
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
    private EditText nameValue;
    private Button inputButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        outputValue = findViewById(R.id.outputValue);
        inputButton = findViewById(R.id.inputButton);
        nameValue = findViewById(R.id.nameValue);

        //Botão de envio de valor
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String input_Value = inputValue.getText().toString();
                String output_Value = outputValue.getText().toString();
                String name_Value = nameValue.getText().toString();

                // BUG: Se os dois valores forem ("") ou seja sem nenhum  o programa dar erro
                if(output_Value.equals("")) {
                    pinggy.accountEntry(name_Value, Double.parseDouble(input_Value));
                }else if(input_Value.equals("")){
                    pinggy.accountExit(name_Value, Double.parseDouble(output_Value));
                }

                economyDataBase.insert("movement", pinggy);

                //Limpar os campos depois de enviar as informações
                nameValue.setText("");
                inputValue.setText("");
                outputValue.setText("");
            }
        });
    }
}