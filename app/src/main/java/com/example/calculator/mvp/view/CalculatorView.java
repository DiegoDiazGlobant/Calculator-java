package com.example.calculator.mvp.view;

import android.app.Activity;
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
}
