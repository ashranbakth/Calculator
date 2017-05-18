package com.example.ashranbakth.calculator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.ashranbakth.calculator.R.id.main_number;
import static java.lang.Integer.parseInt;

public class MainActivity extends Activity implements View.OnClickListener {

    private double first_number; //Scheme is (first_number (operation) second_number = final_number)
    private double second_number;
    private double final_number;
    private char operation;
    private boolean reset; //This reset flag is for resetting to input another number after pressing any of the operations



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DefineButtons();
    }

    @Override
    public void onClick(View v){
        TextView main_view = (TextView) findViewById(main_number);
        int id= v.getId();
        switch (id){
            case R.id.clear:
                main_view.setText("0");
                break;
            case R.id.zero:
                main_view.setText(add_digit(main_view,"0"));
                break;
            case R.id.one:
                main_view.setText(add_digit(main_view,"1"));
                break;
            case R.id.two:
                main_view.setText(add_digit(main_view,"2"));
                break;
            case R.id.three:
                main_view.setText(add_digit(main_view,"3"));
                break;
            case R.id.four:
                main_view.setText(add_digit(main_view,"4"));
                break;
            case R.id.five:
                main_view.setText(add_digit(main_view,"5"));
                break;
            case R.id.six:
                main_view.setText(add_digit(main_view,"6"));
                break;
            case R.id.seven:
                main_view.setText(add_digit(main_view,"7"));
                break;
            case R.id.eight:
                main_view.setText(add_digit(main_view,"8"));
                break;
            case R.id.nine:
                main_view.setText(add_digit(main_view,"9"));
                break;
            case R.id.decimal:
                //If there is a decimal already present in the string then do not add the decimal and keep the string the same.
                if(main_view.getText().toString().indexOf('.') >= 0){
                    main_view.setText(main_view.getText().toString());
                    break;
                }
                main_view.setText(add_digit(main_view,"."));
                break;
            case R.id.percent:
                double number = Double.parseDouble(main_view.getText().toString());
                main_view.setText(Double.toString(number/100.0));
                break;
            case R.id.change_signs:
                main_view.setText(Change_Signs(main_view));
                break;
            case R.id.equal:
                second_number = Double.parseDouble(main_view.getText().toString());
                double result = Operation();
                if(result % 1 == 0){
                    main_view.setText(Integer.toString((int)result));
                }
                else{
                    main_view.setText(Double.toString(result));
                }
                reset = false;
                break;
            case R.id.plus:
                SetFirstNumberAndOperation(main_view, '+');
                reset = true;
                break;
            case R.id.minus:
                SetFirstNumberAndOperation(main_view, '-');
                reset = true;
                break;
            case R.id.multiply:
                SetFirstNumberAndOperation(main_view, '*');
                reset = true;
                break;
            case R.id.divide:
                SetFirstNumberAndOperation(main_view, '/');
                reset = true;
                break;



        }
    }

    //Description: Adds digits and decimals to a number. example 0.4 -> 0.43
    private String add_digit(TextView main_view, String number){
        if((main_view.getText().toString().equals("0") && !number.equals("."))){
            return number;
        }
        String main_number = main_view.getText().toString();
        main_number += number;
        return main_number;
    }

    //Description: Changes the sign of a number
    private String Change_Signs(TextView main_view){
        String main_number = main_view.getText().toString();
        if(main_number.indexOf('-') == 0){
            return main_number.substring(1);
        }
        else{
            StringBuilder str = new StringBuilder(main_number);
            str.insert(0, '-');
            return str.toString();
        }
    }

    //Description: Doing the sign operation calculation
    private double Operation(){
        if(operation == '+'){
            return first_number + second_number;
        }
        else if(operation == '-'){
            return first_number - second_number;
        }
        else if(operation == '*'){
            return first_number * second_number;
        }
        else{
            return first_number / second_number;
        }
    }

    //Description: Just sets the first_number and operation private variables.
    private void SetFirstNumberAndOperation(TextView main_view, char chosen_operation){
        first_number = Double.parseDouble(main_view.getText().toString());
        operation = chosen_operation;
    }

    private void DefineButtons(){

        /*
        //Make a button object for each button
        Button b0 = (Button) findViewById(R.id.zero);
        Button b1 = (Button) findViewById(R.id.one);
        Button b2 = (Button) findViewById(R.id.two);
        Button b3 = (Button) findViewById(R.id.three);
        Button b4 = (Button) findViewById(R.id.four);
        Button b5 = (Button) findViewById(R.id.five);
        Button b6 = (Button) findViewById(R.id.six);
        Button b7 = (Button) findViewById(R.id.seven);
        Button b8 = (Button) findViewById(R.id.eight);
        Button b9 = (Button) findViewById(R.id.nine);
        Button bDecimal = (Button) findViewById(R.id.decimal);
        Button bPercent = (Button) findViewById(R.id.percent);
        Button bClear = (Button) findViewById(R.id.clear);
        Button bChangeSigns = (Button) findViewById(R.id.change_signs);
        Button bEqual = (Button) findViewById(R.id.equal);
        Button bPlus = (Button) findViewById(R.id.plus);
        Button bMinus = (Button) findViewById(R.id.minus);
        Button bMultiply = (Button) findViewById(R.id.multiply);
        Button bDivide = (Button) findViewById(R.id.divide);*/

        findViewById(R.id.zero).setOnClickListener(this);
        findViewById(R.id.one).setOnClickListener(this);
        findViewById(R.id.two).setOnClickListener(this);
        findViewById(R.id.three).setOnClickListener(this);
        findViewById(R.id.four).setOnClickListener(this);
        findViewById(R.id.five).setOnClickListener(this);
        findViewById(R.id.six).setOnClickListener(this);
        findViewById(R.id.seven).setOnClickListener(this);
        findViewById(R.id.eight).setOnClickListener(this);
        findViewById(R.id.nine).setOnClickListener(this);
        findViewById(R.id.decimal).setOnClickListener(this);
        findViewById(R.id.percent).setOnClickListener(this);
        findViewById(R.id.clear).setOnClickListener(this);
        findViewById(R.id.change_signs).setOnClickListener(this);
        findViewById(R.id.equal).setOnClickListener(this);
        findViewById(R.id.plus).setOnClickListener(this);
        findViewById(R.id.minus).setOnClickListener(this);
        findViewById(R.id.multiply).setOnClickListener(this);
        findViewById(R.id.divide).setOnClickListener(this);

        /*
        //Listening on the buttons
        b0.setOnClickListener(this);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
        b9.setOnClickListener(this);
        bDecimal.setOnClickListener(this);
        bPercent.setOnClickListener(this);
        bClear.setOnClickListener(this);
        bChangeSigns.setOnClickListener(this);
        bEqual.setOnClickListener(this);
        bPlus.setOnClickListener(this);
        bMinus.setOnClickListener(this);
        bMultiply.setOnClickListener(this);
        bDivide.setOnClickListener(this);*/
    }

}
