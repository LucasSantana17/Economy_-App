package com.work.economy;
import com.work.economy.Piggybank;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    Piggybank pinggy = new Piggybank();

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

        //Botão de envio de valor
        inputButton.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String input_Value = inputValue.getText().toString();
                String output_Value = outputValue.getText().toString();

                // BUG: Se os dois valores forem ("") ou seja sem nenhum  o programa dar erro

                if(output_Value.equals("")) {
                    pinggy.accountEntry(Double.parseDouble(input_Value));
                }else if(input_Value.equals("")){
                    pinggy.accountExit(Double.parseDouble(output_Value));
                }


            }
        });
    }
}