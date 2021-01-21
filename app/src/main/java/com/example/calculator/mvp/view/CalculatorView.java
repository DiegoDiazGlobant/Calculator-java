package com.example.calculator.mvp.view;

import android.app.Activity;

import com.example.calculator.databinding.ActivityMainBinding;
import com.example.calculator.mvp.contract.CalculatorContract;
import static com.example.calculator.utils.StringUtils.OPERATION_STRING;
import static com.example.calculator.utils.StringUtils.RESULT_STRING;
import static com.example.calculator.utils.StringUtils.WRONG_OPERATOR_ERROR;

public class CalculatorView extends ActivityView implements CalculatorContract.View {

    private final ActivityMainBinding binding;

    public CalculatorView(Activity activity, ActivityMainBinding binding) {
        super(activity);
        this.binding = binding;
    }

    @Override
    public void showOperationValue(String operation) {
        binding.textViewOperations.setText(operation);
    }

    @Override
    public void showResultValue(String result) {
        binding.textViewResult.setText(result);
    }

    @Override
    public void showWrongOperator() {
        showResultValue(WRONG_OPERATOR_ERROR);
    }

    @Override
    public void clearValues() {
        showOperationValue(OPERATION_STRING);
        showResultValue(RESULT_STRING);
    }

    @Override
    public void clearLast(String operation) {
        showOperationValue(operation);
        showResultValue(RESULT_STRING);
    }
}
