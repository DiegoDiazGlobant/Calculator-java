package com.example.calculator.mvp.model;

import com.example.calculator.mvp.contract.CalculatorContract;

public class CalculatorModel implements CalculatorContract.Model {

    private String operationValue;

    @Override
    public void setOperationValue(String value) {
        operationValue = value;
    }

    @Override
    public String getOperationValue() {
        return operationValue;
    }
}
