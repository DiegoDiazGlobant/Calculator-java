package com.example.calculator.mvp.contract;

public interface CalculatorContract {

    interface Model {
        void setOperationValue(String value);
        String getOperationValue();
    }

    interface Presenter {
        void onButtonPressed(String value);
    }

    interface View {
        void showOperationValue(String operation);
    }
}
