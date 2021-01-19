package com.example.calculator.mvp.presenter;

import com.example.calculator.mvp.contract.CalculatorContract;

public class CalculatorPresenter implements CalculatorContract.Presenter {

    private CalculatorContract.Model model;
    private CalculatorContract.View view;

    public CalculatorPresenter(CalculatorContract.Model model, CalculatorContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onButtonPressed(String buttonText) {
        model.setOperationValue(buttonText);
        view.showOperationValue(model.getOperationValue());
    }
}
