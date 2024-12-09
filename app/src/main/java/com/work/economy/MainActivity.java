package com.work.economy;

import com.work.economy.Piggybank;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.work.economy.Databasepiggy;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Piggybank pinggy = new Piggybank();
    Databasepiggy economyDataBase = new Databasepiggy(this);

    private EditText inputValue;
    private EditText nameValue;
    private Button inputButton;
    private Button btnConsultation;
    private Spinner spinnerSelect;

    private boolean typeValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        inputButton = findViewById(R.id.inputButton);
        nameValue = findViewById(R.id.nameValue);
        spinnerSelect = findViewById(R.id.spinnerSelect);
        btnConsultation = findViewById(R.id.btn_consultation);

        //Spinner pra recuperar se o valor é adicional ou se é um gasto
        spinnerSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String item = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Item selecionado! ",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Botão de envio de valor
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String input_Value = inputValue.getText().toString();
                String name_value = nameValue.getText().toString();
                String spinner = spinnerSelect.getSelectedItem().toString();

                // Condicional no spinner para verificar a entrada, essa verificação retira o bug

                if(spinner.equals("Nenhum")){
                    Toast.makeText(MainActivity.this, "Insira o tipo do valor!!",Toast.LENGTH_SHORT).show();

                }else if(spinner.equals("Adicional")) { // Ajustar a condição para não receber valor nulo nos campos VALOR/NOME

                    if(input_Value.equals("") && name_value.equals("")){
                        Toast.makeText(MainActivity.this, "Insira todas as informações !!",Toast.LENGTH_SHORT).show();
                    }else if(input_Value.equals("") || name_value.equals("")){
                        Toast.makeText(MainActivity.this, "Insira todas as informações !!",Toast.LENGTH_SHORT).show();
                    }else {
                        pinggy.accountEntry(name_value, Double.parseDouble(input_Value), typeValue = true );
                        pinggy.setNameValue(name_value);
                        pinggy.setInputValue(Double.parseDouble(input_Value));
                        pinggy.setTypeValue(typeValue = true);
                        nameValue.setText("");
                        inputValue.setText("");
                        Toast.makeText(MainActivity.this, "Valor iserido com sucesso !!",Toast.LENGTH_SHORT).show();
                    }


                }else if(spinner.equals("Gasto")){// Ajustar a condição para não receber valor nulo nos campos VALOR/NOME

                    if(input_Value.equals("") && name_value.equals("")){
                        Toast.makeText(MainActivity.this, "Insira todas as informações !!",Toast.LENGTH_SHORT).show();
                    }else if(input_Value.equals("") || name_value.equals("")){
                        Toast.makeText(MainActivity.this, "Insira todas as informações !!",Toast.LENGTH_SHORT).show();
                    }else {
                        pinggy.accountEntry(name_value, Double.parseDouble(input_Value), typeValue = true );
                        pinggy.setNameValue(name_value);
                        pinggy.setInputValue(Double.parseDouble(input_Value));
                        pinggy.setTypeValue(typeValue = false);
                        nameValue.setText("");
                        inputValue.setText("");
                        Toast.makeText(MainActivity.this, "Atualização feita com sucesso !!",Toast.LENGTH_SHORT).show();
                    }


                }


                economyDataBase.insert(pinggy);
                economyDataBase.displayData();

                //Limpar os campos depois de enviar as informações

            }
        });

        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, activity_consultation.class);
                startActivity(intent);
            }
        });

        //Array de itens do spinner
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Nenhum");
        arrayList.add("Adicional");
        arrayList.add("Gasto");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerSelect.setAdapter(adapter);


    }
}