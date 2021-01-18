package com.example.calculator.mvp.presenter;

import com.example.calculator.mvp.contract.CalculatorContract;
import com.example.calculator.mvp.model.CalculatorModel;
import com.example.calculator.mvp.view.CalculatorView;

public class CalculatorPresenter implements CalculatorContract.Presenter {

    private CalculatorModel model;
    private CalculatorView view;

    public CalculatorPresenter(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onButtonPressed(String buttonText) {
        model.setOperationValue(buttonText);
        view.showOperationValue(model.getOperationValue());
    }
}
