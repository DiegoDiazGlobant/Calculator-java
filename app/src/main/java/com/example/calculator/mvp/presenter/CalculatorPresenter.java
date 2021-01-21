package com.example.calculator.mvp.presenter;

import com.example.calculator.mvp.contract.CalculatorContract;

public class CalculatorPresenter implements CalculatorContract.Presenter {

    private final CalculatorContract.Model model;
    private final CalculatorContract.View view;

    public CalculatorPresenter(CalculatorContract.Model model, CalculatorContract.View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void onNumberButtonPressed(String buttonText) {
        model.setNewOperand(buttonText);
        view.showOperationValue(model.getOperationValue());
        view.showResultValue(buttonText);
    }

    @Override
    public void onOperatorButtonPressed(String buttonText) {
        if (model.canPressOperator(buttonText)) {
            model.setNewOperator(buttonText);
            view.showOperationValue(model.getOperationValue());
            view.showResultValue(buttonText);
        } else
            view.showWrongOperator();
    }

    @Override
    public void onEqualsButtonPressed() {
        view.showResultValue(model.getResultValue());
    }

    @Override
    public void onClearLastButtonPressed() {
        model.deleteLast();
        view.clearLast(model.getOperationValue());
    }

    @Override
    public boolean onClearAllButtonPressed() {
        model.resetValues();
        view.clearValues();
        return true;
    }
}
