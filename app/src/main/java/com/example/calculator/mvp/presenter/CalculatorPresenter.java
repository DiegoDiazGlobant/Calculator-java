package com.example.calculator.mvp.presenter;

import com.example.calculator.mvp.contract.CalculatorContract;
import static com.example.calculator.utils.StringUtils.EMPTY;

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
        if (model.getEqualsPressed()) {
            model.updateValues();
        }
        if (model.canPressOperator(buttonText)) {
            model.setNewOperator(buttonText);
            view.showOperationValue(model.getOperationValue());
            view.showResultValue(buttonText);
        } else {
            view.showWrongOperator();
        }
    }

    @Override
    public void onEqualsButtonPressed() {
        String result = model.getResultValue();
        switch (model.getResultEnum()) {
            case DIVIDE_BY_ZERO_ERROR:
                view.showDivideByZero();
                break;
            case INCOMPLETE_OPERATION_ERROR:
                view.showIncompleteOperation();
                break;
            case SUCCESS:
                view.showResultValue(result);
                model.setEqualsPressed();
                break;
        }
    }

    @Override
    public void onClearLastButtonPressed() {
        model.deleteLast();
        if (model.getOperationValue().equals(EMPTY)) {
            view.clearValues();
        } else {
            view.clearLast(model.getOperationValue());
        }
    }

    @Override
    public boolean onClearAllButtonPressed() {
        model.resetValues();
        view.clearValues();
        return true;
    }
}
