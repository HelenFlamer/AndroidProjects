package com.example.new_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int CONST_NUMBER = 1;
    private static final int CONST_POINT = 2;
    private static final int CONST_ACTION = 5;
    private static final int CONST_CLEAN = 3;
    private static final int CONST_RESULT = 4;

    String buttonText; //текст, считанный с кнопки
    String enteredText;//текст, считанный с поля

    char enteredAction;

    TextView allEnteredField;//поле ввода всех символов
    TextView currentField;// поле ввода текущего числа + результат

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9, button0;
    Button buttonAdd, buttonSub, buttonMult, buttonDiv, buttonRes, buttonClean, buttonPoint, buttonBack;

    int lastEntry;
    boolean number2entered = false, actionEntered = false, resultEntered = false, firstActionEntered = false;
    double currentNumber = 0;
    double result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allEnteredField = (TextView) findViewById(R.id.allEnteredField);
        currentField = (TextView) findViewById(R.id.currentField);

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonSub = (Button) findViewById(R.id.buttonSub);
        buttonMult = (Button) findViewById(R.id.buttonMult);
        buttonDiv = (Button) findViewById(R.id.buttonDiv);
        buttonRes = (Button) findViewById(R.id.buttonRes);
        buttonPoint = (Button) findViewById(R.id.buttonPoint);

        buttonClean = (Button) findViewById(R.id.buttonClean);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
            case R.id.button0:
                pressedNumber(v);
                break;
            case R.id.buttonPoint:
                pressedPoint();
                break;
            case R.id.buttonAdd:
            case R.id.buttonSub:
            case R.id.buttonMult:
            case R.id.buttonDiv:
                pressedAction(v);
                break;
            case R.id.buttonRes:
                countResult();
                break;
            case R.id.buttonClean:
                cancelLastAction();
                break;
            default:
                break;
        }
    }

    private void pressedNumber(View a) {
        lastEntry = CONST_NUMBER;
        buttonText = ((TextView) a).getText().toString();
        if (actionEntered || resultEntered) {
            currentField.setText("");
            currentField.append(buttonText);
            actionEntered = false;
            number2entered = false;
            resultEntered = false;
            return;
        } else {
            currentField.append(buttonText);
        }
    }

    private void pressedPoint() {
        if (actionEntered) {
            currentField.setText("");
            actionEntered = false;
        }
        buttonPoint.setEnabled(false);
        if (currentField.getText().length() > 0)
            currentField.append(".");
        else {
            currentField.append("0.");
        }
        lastEntry = CONST_POINT;
    }

    private void pressedAction(View a) {
        buttonText = ((TextView) a).getText().toString();
        switch (lastEntry) {
            case CONST_NUMBER:
                if (firstActionEntered) {
                    enteredText = allEnteredField.getText().toString();//считали верхнее поле до ввода числа со знаком
                    enteredAction = enteredText.charAt(enteredText.length() - 1);//счтали предыдущий введенный знак

                    enteredText = currentField.getText().toString();//считали только что введенное чило
                    currentNumber = Double.parseDouble(enteredText);//записали это число в переменную

                    allEnteredField.append(enteredText);//добавили число в верхнее поле
                    allEnteredField.append(buttonText);//добавили знак в верхнее поле

                    switch (enteredAction) {
                        case '+':
                            result = result + currentNumber;
                            break;
                        case '-':
                            result = result - currentNumber;
                            break;
                        case '*':
                            result = result * currentNumber;
                            break;
                        case '/':
                            result = result / currentNumber;
                            break;
                        default:
                            break;
                    }
                }
                if (!firstActionEntered) {
                    enteredText = currentField.getText().toString();//считали только что введенное чило
                    result = Double.parseDouble(enteredText);//записали это число в переменную

                    allEnteredField.append(enteredText);//добавили число в верхнее поле
                    allEnteredField.append(buttonText);//добавили знак в верхнее поле
                    firstActionEntered = true;
                }
                currentField.setText(result + " ");
                buttonPoint.setEnabled(true);
                lastEntry = CONST_ACTION;
                number2entered = true;
                break;
            case CONST_ACTION:
            case CONST_CLEAN:
                enteredText = allEnteredField.getText().toString();//считали, что уже есть у полном поле ввода
                enteredText = enteredText.substring(0, enteredText.length() - 1);//удалили последний знак
                allEnteredField.setText(enteredText);
                allEnteredField.append(buttonText);
                buttonPoint.setEnabled(true);
                lastEntry = CONST_ACTION;
                number2entered = true;
                resultEntered = true;
                break;
            case CONST_POINT:
                enteredText = currentField.getText().toString();
                enteredText = enteredText.substring(0, enteredText.length() - 1);
                currentField.setText(enteredText);
                allEnteredField.append(enteredText);
                allEnteredField.append(buttonText);
                lastEntry = CONST_ACTION;
                break;
            case CONST_RESULT:
                //todo method
                break;
            default:
                break;
        }
        actionEntered = true;
        buttonPoint.setEnabled(true);
    }

    private void countResult() {

        if (!resultEntered) {
            enteredText = allEnteredField.getText().toString();//считали число из нижнего поля
            if (enteredText.length()>0)
            enteredAction = enteredText.charAt(enteredText.length() - 1);//счтали предыдущий введенный знак
        }
        enteredText = currentField.getText().toString();
        currentNumber = Double.parseDouble(enteredText);

        switch (enteredAction) {
            case '+':
                result = result + currentNumber;
                break;
            case '-':
                result = result - currentNumber;
                break;
            case '*':
                result = result * currentNumber;
                break;
            case '/':
                result = result / currentNumber;
                break;
            default:
                result = currentNumber;
                break;
        }
        currentField.setText(result + " ");
        allEnteredField.setText("");
        result = currentNumber;
        number2entered = true;
        buttonPoint.setEnabled(true);
        resultEntered = true;
        firstActionEntered = false;
        lastEntry = CONST_RESULT;
    }

    private void cancelLastAction() {
        if (lastEntry == CONST_CLEAN) {
            allEnteredField.setText("");
        }
        currentField.setText("");
        buttonPoint.setEnabled(true);
        lastEntry = CONST_CLEAN;
        number2entered = false;
        resultEntered = false;
        firstActionEntered = false;
        actionEntered = false;//проверить
        currentNumber = 0;
        result = 0;

    }

}
