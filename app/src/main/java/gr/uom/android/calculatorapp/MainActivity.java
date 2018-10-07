package gr.uom.android.calculatorapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText result;
    private EditText newNumber;
    private TextView displayOperation;

    private Double operand1 = null;
    private Double operand2 = null;
    private String pendingOperation = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        newNumber = findViewById(R.id.newNumber);
        displayOperation = findViewById(R.id.operation);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button buttonDot = findViewById(R.id.buttonDot);

        Button buttonEquals = findViewById(R.id.buttonEquals);
        Button buttonPlus = findViewById(R.id.buttonPlus);
        Button buttonMinus = findViewById(R.id.buttonMinus);


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                newNumber.append(b.getText().toString());
            }
        };
        button0.setOnClickListener(listener);
        button1.setOnClickListener(listener);
        button2.setOnClickListener(listener);
        button3.setOnClickListener(listener);
        button4.setOnClickListener(listener);
        buttonDot.setOnClickListener(listener);

        View.OnClickListener operationListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button)view;
                String operation = b.getText().toString();
                String value = newNumber.getText().toString();
                if(value.length() > 0){
                    performOperation(value, operation);
                }
                pendingOperation = operation;
                displayOperation.setText(pendingOperation);
            }
        };

        buttonEquals.setOnClickListener(operationListener);
        buttonMinus.setOnClickListener(operationListener);
        buttonPlus.setOnClickListener(operationListener);
    }

    private void performOperation(String value, String operation) {
        if(operand1 == null){
            operand1 = Double.valueOf(value);
        }else{
            operand2 = Double.valueOf(value);

            if(pendingOperation.equals("=")){
                pendingOperation = operation;
            }
            switch (pendingOperation){
                case "=":
                    operand1 = operand2;
                    break;
                case "+":
                    operand1 += operand2;
                    break;
                case "-":
                    operand1 -= operand2;
                    break;
            }
        }

        result.setText(operand1.toString());
        newNumber.setText("");
    }

}
