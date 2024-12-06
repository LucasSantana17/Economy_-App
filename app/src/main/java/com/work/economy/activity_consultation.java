package com.work.economy;

import androidx.appcompat.app.AppCompatActivity;
import com.work.economy.Databasepiggy;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_consultation extends AppCompatActivity {

    Databasepiggy database = new Databasepiggy(this);

    private TextView exitConsultation;
    private Button btnConsultation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        exitConsultation = findViewById(R.id.exit_consultation);
        btnConsultation = findViewById(R.id.btn_consultation);


        btnConsultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // String result = String.valueOf(databaseList());
            exitConsultation.setText(database.displayData());

            }
        });
    }




}