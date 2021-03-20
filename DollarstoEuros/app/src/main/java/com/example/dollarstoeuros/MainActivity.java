package com.example.dollarstoeuros;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private double converted_value;
    private Button btn;
    private EditText inputNum;
    private TextView output;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn_convert);
        inputNum = (EditText) findViewById(R.id.number);
        output = (TextView) findViewById(R.id.show_converted);
    }

    public void convertToEuros(View view) {

        try {

            converted_value = Double.parseDouble(inputNum.getText().toString());
            converted_value = converted_value * 0.88;
            DecimalFormat dec = new DecimalFormat("000.000");;
            output.setText(Double.toString(Double.parseDouble(dec.format(converted_value))));
        }

        catch(Exception exc){

            Toast toast = Toast.makeText(this,"Enter A Number",Toast.LENGTH_SHORT);

            toast.show();

        }








    }
}