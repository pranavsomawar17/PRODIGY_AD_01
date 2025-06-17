package com.example.calci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView display;
    private StringBuilder input = new StringBuilder();
    private String operator = "";
    private double firstOperand = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);

        // Number buttons
        setupNumberButton(R.id.button_0, "0");
        setupNumberButton(R.id.button_1, "1");
        setupNumberButton(R.id.button_2, "2");
        setupNumberButton(R.id.button_3, "3");
        setupNumberButton(R.id.button_4, "4");
        setupNumberButton(R.id.button_5, "5");
        setupNumberButton(R.id.button_6, "6");
        setupNumberButton(R.id.button_7, "7");
        setupNumberButton(R.id.button_8, "8");
        setupNumberButton(R.id.button_9, "9");
        setupNumberButton(R.id.button_decimal, ".");

        // Operator buttons
        setupOperatorButton(R.id.button_add, "+");
        setupOperatorButton(R.id.button_subtract, "-");
        setupOperatorButton(R.id.button_multiply, "×");
        setupOperatorButton(R.id.button_divide, "÷");

        // Equals button
        Button buttonEquals = findViewById(R.id.button_equals);
        buttonEquals.setOnClickListener(v -> calculateResult());

        // Clear button
        Button buttonClear = findViewById(R.id.button_clear);
        buttonClear.setOnClickListener(v -> clearDisplay());

        // Delete button
        Button buttonDelete = findViewById(R.id.button_delete);
        buttonDelete.setOnClickListener(v -> deleteLastCharacter());
    }

    private void setupNumberButton(int buttonId, String value) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            input.append(value);
            display.setText(input.toString());
        });
    }

    private void setupOperatorButton(int buttonId, String op) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> {
            if (input.length() > 0) {
                firstOperand = Double.parseDouble(input.toString());
                operator = op;
                input.setLength(0); // Clear input for next number
                display.setText(""); // Clear display for next input
            }
        });
    }

    private void calculateResult() {
        if (input.length() > 0 && !operator.isEmpty()) {
            double secondOperand = Double.parseDouble(input.toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "×":
                    result = firstOperand * secondOperand;
                    break;
                case "÷":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            input.setLength(0); // Clear input for next calculation
            input.append(result);
            display.setText(input.toString());
            operator = ""; // Reset operator
        }
    }

    private void clearDisplay() {
        input.setLength(0);
        operator = "";
        display.setText("0");
    }

    private void deleteLastCharacter() {
        if (input.length() > 0) {
            input.deleteCharAt(input.length() - 1);
            display.setText(input.length() > 0 ? input.toString() : "0");
        }
    }
}
