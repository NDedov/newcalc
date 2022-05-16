package com.example.newcalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;
import java.util.function.DoubleUnaryOperator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Constants {

    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonMC, buttonMP, buttonMM, buttonMR;
    Button buttonMul, buttonDiv, buttonPlus, buttonMin;
    Button buttonC, buttonDel, buttonEq, buttonPer, buttonDot;
    Button buttonTheme;

    TextView mainCalcScreen; // основной экран - число вводимое с клавиатуры, вывод результата
    TextView memoryCalcScreen; //  для отображения факта наличия числа в памяти
    TextView operandsCalcScreen; // текущий список операндов и операций

    Typeface tf;

    MainCalcScreenString mainCalcScreenString;//класс для работы с основным экраном калькулятора
    Calc calc; // класс для хранения обработки списка чисел и операндов
    Memory memory; // класс для работы с памятью калькулятора

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        tf = Typeface.createFromAsset(getAssets(),"fonts/Digital.ttf");
        initViews();
        initCalc();

        //обработка вызова из другого приложения
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null)
            return;
        String textApplication = bundle.getString(KEY_APPLICATION);
        if (textApplication == null)
            return;
        Toast.makeText(this, "Меня вызвал: " + textApplication,Toast.LENGTH_LONG).show();
    }

    private void initCalc() {// создаем объекты для работы калькультора
        mainCalcScreenString = new MainCalcScreenString(MAX_LENGTH_CALC_DIGITS);
        calc = new Calc();
        memory = new Memory();
        mainCalcScreenString.addElement("0");
        printCalc();
    }

    private void initViews(){
        mainCalcScreen = findViewById(R.id.textViewCalc);
        memoryCalcScreen = findViewById(R.id.textViewMemory);
        operandsCalcScreen = findViewById(R.id.textViewOperands);
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
        buttonTheme = findViewById(R.id.buttonTheme);
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
        buttonTheme.setOnClickListener(this);
    }

    //сохранение объектов при уничтожении основной активити
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_SAVE_CALC, new SaveObjects(mainCalcScreenString, calc, memory));
    }

    //восстановление объектов при пересоздании основной активити
    @Override
    public void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        SaveObjects saveObjects = savedInstanceState.getParcelable(KEY_SAVE_CALC);
        memory = saveObjects.getMemory();
        calc = saveObjects.getCalc();
        mainCalcScreenString = saveObjects.getMainCalcScreenString();
        printCalc();
    }

    /**
     * вывод на экран всех полей калькулятора
     */
    public void printCalc(){
        if (mainCalcScreenString.toString().length()<13)
            mainCalcScreen.setTextSize(52);

        else
            if (mainCalcScreenString.toString().length()<17)
                mainCalcScreen.setTextSize(40);
            else
                mainCalcScreen.setTextSize(32);


        memoryCalcScreen.setText(memory.toString());
        mainCalcScreen.setText(mainCalcScreenString.toString());
        operandsCalcScreen.setText(calc.toString());
    }

    //обработчик нажатий всех кнопок
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case (R.id.button0):
                mainCalcScreenString.addElement("0");
                break;
            case (R.id.button1):
                mainCalcScreenString.addElement("1");
                break;
            case (R.id.button2):
                mainCalcScreenString.addElement("2");
                break;
            case (R.id.button3):
                mainCalcScreenString.addElement("3");
                break;
            case (R.id.button4):
                mainCalcScreenString.addElement("4");
                break;
            case (R.id.button5):
                mainCalcScreenString.addElement("5");
                break;
            case (R.id.button6):
                mainCalcScreenString.addElement("6");
                break;
            case (R.id.button7):
                mainCalcScreenString.addElement("7");
                break;
            case (R.id.button8):
                mainCalcScreenString.addElement("8");
                break;
            case (R.id.button9):
                mainCalcScreenString.addElement("9");
                break;
            case (R.id.buttonMC):
                memory.clear();
                break;
            case (R.id.buttonMM):
                if (!mainCalcScreenString.isError())//проверка если на экране калькулятора ошибка
                    memory.memoryMinus(mainCalcScreenString.toDouble());
                break;
            case (R.id.buttonMP):
                if (!mainCalcScreenString.isError())//проверка если на экране калькулятора ошибка
                    memory.memoryPlus(mainCalcScreenString.toDouble());
                break;
            case (R.id.buttonMR):
                if (memory.isMemory()){
                    mainCalcScreenString.clear();
                    mainCalcScreenString.setDouble(memory.getNum());
                }
                break;
            case (R.id.buttonC):
                try {
                    if (mainCalcScreenString.toDouble() == 0) // если на основном экране 0 - очищаем
                        // операции
                        calc.clear();
                    mainCalcScreenString.clear();
                }
                catch (NumberFormatException e){
                    calc.clear();
                    mainCalcScreenString.clear();
                }
                break;
            case (R.id.buttonDel):
                mainCalcScreenString.delLast();
                break;
            case (R.id.buttonPlus):
                calc.addOperand(mainCalcScreenString.toString());
                calc.addOperand("+");
                mainCalcScreenString.clear();
                break;
            case (R.id.buttonMin):
                calc.addOperand(mainCalcScreenString.toString());
                calc.addOperand("-");
                mainCalcScreenString.clear();
                break;
            case (R.id.buttonDiv):
                calc.addOperand(mainCalcScreenString.toString());
                calc.addOperand("/");
                mainCalcScreenString.clear();
                break;
            case (R.id.buttonMul):
                calc.addOperand(mainCalcScreenString.toString());
                calc.addOperand("*");
                mainCalcScreenString.clear();
                break;
            case (R.id.buttonEq):// кнопка равно
                calc.addOperand(mainCalcScreenString.toString());
                mainCalcScreenString.clear();
                try{
                    if (calc.makeCalc() == Double.POSITIVE_INFINITY ||//если выходим за предел float
                            calc.makeCalc() == Double.NEGATIVE_INFINITY) {
                        calc.clear();
                        mainCalcScreenString.setError();
                        break;
                    }
                    mainCalcScreenString.setDouble(calc.makeCalc());
                }
                catch (ArithmeticException e){//ошибка математической операции
                    calc.clear();
                    mainCalcScreenString.setError();
                }
                calc.clear();
                break;
            case (R.id.buttonPer):
                calc.addOperand(mainCalcScreenString.toString());
                calc.addOperand("%");
                mainCalcScreenString.clear();
                break;
            case (R.id.buttonDot):
                mainCalcScreenString.addElement(".");
                break;
            case (R.id.buttonTheme):
                Intent runSettings = new Intent(this, SettingsActivity.class);
                runSettings.putExtra(THEME, AppCompatDelegate.getDefaultNightMode());
                startActivityForResult(runSettings, REQUEST_CODE_SETTING_ACTIVITY);
                break;
        }
       printCalc();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    { //получение результата только из активити, с установлением темы
        if (requestCode != REQUEST_CODE_SETTING_ACTIVITY) {
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }
        if (resultCode == RESULT_OK)
            AppCompatDelegate.setDefaultNightMode(data.getIntExtra(THEME,
                    AppCompatDelegate.getDefaultNightMode()));
    }
}
