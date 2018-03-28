package com.example.calculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.TextView;

import java.util.*;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    /**
     * Buttons for the number fields in the application
     */
    private Button zero, one, two, three, four, five, six, seven, eight, nine;

    /**
     * Buttons which force an action in this case an arithmetic operation
     */
    private Button add, subtract, multiply, divide;

    /**
     * Clears the display
     */
    private Button clear;

    /**
     * Shows the result of an arithmetic operation
     */
    private Button result;

    /**
     * Text views which shows the actions taken of the Calculator
     */
    private TextView info, display;

    /**
     * Lists for all buttons
     */
    private ArrayList<Button> numbers = new ArrayList<>();
    private ArrayList<Button> operators = new ArrayList<>();

    /**
     * Number which will be calculated with another (storage)
     */
    private double number;

    /**
     * Result of the calculated numbers
     */
    private double res;

    /**
     * Describes the current arithmetic operation
     * 0: Default
     * 1: Addition
     * 2: Subtraction
     * 3: Multiplication
     * 4: Division
     */
    private int calculation = 0;

    /**
     * This method creates the user interface of the application
     * @param savedInstanceState updated information of the user interface
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpButtonsAndViews();
        addNumbersAndOperatorsInLists();
        setListener();
    }

    /**
     * This method finds all the buttons and views which are shown on the User Interface
     */
    public void setUpButtonsAndViews() {
        zero = findViewById(R.id.btn0);
        one = findViewById(R.id.btn1);
        two = findViewById(R.id.btn2);
        three = findViewById(R.id.btn3);
        four = findViewById(R.id.btn4);
        five = findViewById(R.id.btn5);
        six = findViewById(R.id.btn6);
        seven = findViewById(R.id.btn7);
        eight = findViewById(R.id.btn8);
        nine = findViewById(R.id.btn9);

        add = findViewById(R.id.btnPlus);
        subtract = findViewById(R.id.btnMinus);
        multiply = findViewById(R.id.btnMultiple);
        divide = findViewById(R.id.btnDivide);

        clear = findViewById(R.id.btnClear);
        result = findViewById(R.id.btnResult);

        info = findViewById(R.id.txtControl);
        display = findViewById(R.id.txtDisplay);
    }

    /**
     * Puts the numbers and operators in two different lists.
     */
    public void addNumbersAndOperatorsInLists() {
        numbers.add(zero);
        numbers.add(one);
        numbers.add(two);
        numbers.add(three);
        numbers.add(four);
        numbers.add(five);
        numbers.add(six);
        numbers.add(seven);
        numbers.add(eight);
        numbers.add(nine);

        operators.add(add);
        operators.add(subtract);
        operators.add(multiply);
        operators.add(divide);
        operators.add(clear);
        operators.add(result);
    }

    /**
     * This method sets the functionality of each button
     */
    public void setListener() {
        for (Button b : numbers) {
            b.setOnClickListener(this);
        }
        for (Button o : operators) {
            o.setOnClickListener(this);
        }
    }

    /**
     * This method reacts on Button Clicks
     * @param v current action which is chosen
     */
    @Override
    public void onClick(View v) {
        if (v.equals(result)) {
            calculate();
        } else if (v.equals(clear)) {
            info.setText(null);
            display.setText(null);
        }
        checkOperatorsAndNumbers(v);
    }

    /**
     * Checks which buttons were clicked
     * @param v current action which is chosen
     */
    public void checkOperatorsAndNumbers(View v) {
        for (int i = 0; i < numbers.size(); i++) {
            if (v.equals(numbers.get(i))) {
                display.setText(display.getText().toString() + numbers.get(i).getText().toString());
            }
        }
        for (int j = 0; j < operators.size() - 2; j++) {
            if (v.equals(operators.get(j))) {
                number = Double.parseDouble(display.getText().toString());
                info.setText(display.getText().toString() + operators.get(j).getText().toString());
                display.setText(null);
                calculation = j+1;
            }
        }
    }

    /**
     * This method does an arithmetic operation and shows it on the display
     */
    private void calculate() {
        switch (calculation) {
            case 1: res = number + Double.parseDouble(display.getText().toString());
                break;
            case 2: res = number - Double.parseDouble(display.getText().toString());
                break;
            case 3: res = number * Double.parseDouble(display.getText().toString());
                break;
            case 4:
                if (Double.parseDouble(display.getText().toString()) == 0) {
                    info.setText("Division by zero is not possible");
                    display.setText(null);
                } else {
                    res = number / Double.parseDouble(display.getText().toString());
                }
                break;
        }
        display.setText(Double.toString(res));
    }
}
