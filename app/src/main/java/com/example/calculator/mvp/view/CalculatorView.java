package com.example.calculator.mvp.view;

import android.app.Activity;
import com.example.calculator.R;
import com.example.calculator.databinding.ActivityMainBinding;
import com.example.calculator.mvp.contract.CalculatorContract;

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
        showResultValue(getContext().getString(R.string.activity_main_calculator_wrong_operator_error));
    }

    @Override
    public void showIncompleteOperation() {
        showResultValue(getContext().getString(R.string.activity_main_calculator_incomplete_operation_error));
    }

    @Override
    public void showDivideByZero() {
        showResultValue(getContext().getString(R.string.activity_main_calculator_divide_by_zero_error));
    }

    @Override
    public void clearValues() {
        showOperationValue(getContext().getString(R.string.activity_main_calculator_operation_text));
        showResultValue(getContext().getString(R.string.activity_main_calculator_result_text));
    }

    @Override
    public void clearLast(String operation) {
        showOperationValue(operation);
        showResultValue(getContext().getString(R.string.activity_main_calculator_result_text));
    }
}
