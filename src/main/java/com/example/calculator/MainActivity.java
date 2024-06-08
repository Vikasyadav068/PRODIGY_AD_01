package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.button.MaterialButton;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBracketOpen, buttonBracketClose, buttonDivide, button7, button8, button9, buttonMul, button4, button5, button6,
            buttonPlus, button1, button2, button3, buttonSub, buttonAc, button0,
            buttonDot, buttonEquals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);


        assignId(buttonC, R.id.c);
        assignId(buttonBracketOpen, R.id.button_open_bracket);
        assignId(buttonBracketClose, R.id.button_close_bracket);
        assignId(buttonDivide, R.id.button_divide);
        assignId(button7, R.id.button_7);
        assignId(button8, R.id.button_9);
        assignId(button9, R.id.button_multiply);
        assignId(buttonMul, R.id.button_8);

        assignId(button4, R.id.button_4);
        assignId(button5, R.id.button_5);
        assignId(button6, R.id.button_6);
        assignId(buttonPlus, R.id.button_add);
        assignId(button1, R.id.button_1);
        assignId(button2, R.id.button_2);
        assignId(button3, R.id.button_3);
        assignId(buttonSub, R.id.button_sub);

        assignId(buttonAc, R.id.button_ac);
        assignId(button0, R.id.button_zero);
        assignId(buttonDot, R.id.button_dot);
        assignId(buttonEquals, R.id.button_equals);

    }

    Void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
        return null;
    }

    @Override
    public void onClick(View v) {
        MaterialButton button = (MaterialButton) v;
        String buttonText = button.getText().toString();

        String dataToCalculate = solutionTv.getText().toString();
        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }

        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }

        if (buttonText.equals("C")) {
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
        } else {
            dataToCalculate = dataToCalculate + buttonText;

        }


        solutionTv.setText(dataToCalculate);


        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("Err")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data) {

        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable, data, "javascript", 1, null).toString();
            if (finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }

            return finalResult;
        } catch (Exception e) {
            return "Err";
        }
    }
}