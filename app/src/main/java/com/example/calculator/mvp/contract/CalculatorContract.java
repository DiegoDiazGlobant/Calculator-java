package com.example.calculator.mvp.contract;

import com.example.calculator.utils.ResultEnum;

public interface CalculatorContract {

    interface Model {
        void setNewOperand(String value);
        void setNewOperator(String value);
        boolean canPressOperator(String value);
        String getOperationValue();
        String getResultValue();
        void deleteLast();
        void resetValues();
        void setEqualsPressed();
        boolean getEqualsPressed();
        void updateValues();
        ResultEnum getErrorEnum();
    }

    interface Presenter {
        void onNumberButtonPressed(String buttonText);
        void onOperatorButtonPressed(String buttonText);
        void onEqualsButtonPressed();
        boolean onClearAllButtonPressed();
        void onClearLastButtonPressed();
    }

    interface View {
        void showOperationValue(String operation);
        void showResultValue(String result);
        void showWrongOperator();
        void showIncompleteOperation();
        void showDivideByZero();
        void clearValues();
        void clearLast(String operation);
    }
}
