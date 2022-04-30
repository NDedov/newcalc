package com.example.newcalc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonMC, buttonMP, buttonMM, buttonMR;
    Button buttonMul, buttonDiv, buttonPlus, buttonMin;
    Button buttonC, buttonDel, buttonEq, buttonPer, buttonDot;

    TextView mainCalcScreen; // число вводимое с клавиатуры калькулятора, вывод результата
    TextView memoryCalcScreen; //  для отображения факта наличия числа в памяти
    TextView operandsCalcScreen; // текущий список операндов и операций

    Typeface tf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        tf = Typeface.createFromAsset(getAssets(),"fonts/Digital.ttf");
        initViews();
    }

    private void initViews(){
        mainCalcScreen = findViewById(R.id.textViewCalc);
        memoryCalcScreen = findViewById(R.id.textViewMemory);
        operandsCalcScreen = findViewById(R.id.textViewOperands);

        mainCalcScreen.setText("123.45");
        memoryCalcScreen.setText("M");
        operandsCalcScreen.setText("123.45 + 35353 - 35353");

        mainCalcScreen.setTypeface(tf);
        memoryCalcScreen.setTypeface(tf);
        operandsCalcScreen.setTypeface(tf);


        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);
        buttonMC = findViewById(R.id.buttonMC);
        buttonMP = findViewById(R.id.buttonMP);
        buttonMM = findViewById(R.id.buttonMM);
        buttonMR = findViewById(R.id.buttonMR);
        buttonMul = findViewById(R.id.buttonMul);
        buttonDiv = findViewById(R.id.buttonDiv);
        buttonPlus = findViewById(R.id.buttonPlus);
        buttonMin = findViewById(R.id.buttonMin);
        buttonC = findViewById(R.id.buttonC);
        buttonDel = findViewById(R.id.buttonDel);
        buttonEq = findViewById(R.id.buttonEq);
        buttonPer = findViewById(R.id.buttonPer);
        buttonDot = findViewById(R.id.buttonDot);
        initOnClickListeners();
    }

    private void initOnClickListeners(){
        button0.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonMC.setOnClickListener(this);
        buttonMP.setOnClickListener(this);
        buttonMM.setOnClickListener(this);
        buttonMR.setOnClickListener(this);
        buttonMul.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);
        buttonMin.setOnClickListener(this);
        buttonC.setOnClickListener(this);
        buttonDel.setOnClickListener(this);
        buttonEq.setOnClickListener(this);
        buttonPer.setOnClickListener(this);
        buttonDot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.button0):
                Toast.makeText(this, "Нажали 0", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button1):
                Toast.makeText(this, "Нажали 1", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button2):
                Toast.makeText(this, "Нажали 2", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button3):
                Toast.makeText(this, "Нажали 3", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button4):
                Toast.makeText(this, "Нажали 4", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button5):
                Toast.makeText(this, "Нажали 5", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button6):
                Toast.makeText(this, "Нажали 6", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button7):
                Toast.makeText(this, "Нажали 7", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button8):
                Toast.makeText(this, "Нажали 8", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.button9):
                Toast.makeText(this, "Нажали 9", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonMC):
                Toast.makeText(this, "Нажали MC", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonMM):
                Toast.makeText(this, "Нажали M-", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonMP):
                Toast.makeText(this, "Нажали M+", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonMR):
                Toast.makeText(this, "Нажали MR", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonC):
                Toast.makeText(this, "Нажали C", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonDel):
                Toast.makeText(this, "Нажали Del", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonPlus):
                Toast.makeText(this, "Нажали +", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonMin):
                Toast.makeText(this, "Нажали -", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonDiv):
                Toast.makeText(this, "Нажали ÷", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonMul):
                Toast.makeText(this, "Нажали ×", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonEq):
                Toast.makeText(this, "Нажали =", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonPer):
                Toast.makeText(this, "Нажали %", Toast.LENGTH_SHORT).show();
                break;
            case (R.id.buttonDot):
                Toast.makeText(this, "Нажали .", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}