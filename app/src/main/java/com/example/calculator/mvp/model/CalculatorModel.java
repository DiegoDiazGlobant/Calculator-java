package com.example.calculator.mvp.model;

import com.example.calculator.mvp.contract.CalculatorContract;
import static com.example.calculator.utils.StringUtils.ADD;
import static com.example.calculator.utils.StringUtils.DIV;
import static com.example.calculator.utils.StringUtils.DIVIDE_BY_0_ERROR;
import static com.example.calculator.utils.StringUtils.EMPTY;
import static com.example.calculator.utils.StringUtils.INCOMPLETE_OPERATION_ERROR;
import static com.example.calculator.utils.StringUtils.OPERATION_STRING;
import static com.example.calculator.utils.StringUtils.PRODUCT;
import static com.example.calculator.utils.StringUtils.SUB;
import static com.example.calculator.utils.StringUtils.ZERO;

public class CalculatorModel implements CalculatorContract.Model {

    private String firstOperand = EMPTY;
    private String operator = EMPTY;
    private String secondOperand = EMPTY;
    private String operationValue = EMPTY;
    public static final int STRING_BEGIN_POSITION = 0;
    public static final int STRING_LAST_POSITION = 1;

    @Override
    public void setNewOperand(String value) {
        operationValue += value;
        if (operator.isEmpty())
            firstOperand += value;
        else
            secondOperand += value;
    }

    @Override
    public void setNewOperator(String value) {
        operationValue += value;
        operator = value;
    }

    @Override
    public boolean canPressOperator(String value) {
        return !firstOperand.isEmpty() && operator.isEmpty() && secondOperand.isEmpty();
    }

    @Override
    public String getOperationValue() {
        if ((firstOperand + operator + secondOperand).equals(EMPTY))
            return OPERATION_STRING;
        return operationValue;
    }

    @Override
    public String getResultValue() {
        if (incompleteOperation())
            return INCOMPLETE_OPERATION_ERROR;
        return doOperation();
    }

    public String doOperation() {
        switch (operator) {
            case ADD:
                return String.valueOf(Double.parseDouble(firstOperand) + Double.parseDouble(secondOperand));
            case SUB:
                return String.valueOf(Double.parseDouble(firstOperand) - Double.parseDouble(secondOperand));
            case PRODUCT:
                return String.valueOf(Double.parseDouble(firstOperand) * Double.parseDouble(secondOperand));
            case DIV:
                if (secondOperand.equals(ZERO))
                    return DIVIDE_BY_0_ERROR;
                return String.valueOf(Double.parseDouble(firstOperand) / Double.parseDouble(secondOperand));
            default:
                return firstOperand;
        }
    }

    public boolean incompleteOperation() {
        return (!operator.isEmpty() && secondOperand.isEmpty());
    }

    @Override
    public void deleteLast() {
        if (!secondOperand.isEmpty()) {
            secondOperand = secondOperand.substring(STRING_BEGIN_POSITION, secondOperand.length() - STRING_LAST_POSITION);
        } else if (!operator.isEmpty()) {
            operator = operator.substring(STRING_BEGIN_POSITION, operator.length() - STRING_LAST_POSITION);
        } else if (!firstOperand.isEmpty()) {
            firstOperand = firstOperand.substring(STRING_BEGIN_POSITION, firstOperand.length() - STRING_LAST_POSITION);
        }
        operationValue = firstOperand + operator + secondOperand;
    }

    @Override
    public void resetValues() {
        firstOperand = EMPTY;
        operator = EMPTY;
        secondOperand = EMPTY;
        operationValue = EMPTY;
    }
}
