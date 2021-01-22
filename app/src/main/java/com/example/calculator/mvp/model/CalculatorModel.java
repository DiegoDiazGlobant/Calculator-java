package com.example.calculator.mvp.model;

import com.example.calculator.mvp.contract.CalculatorContract;
import com.example.calculator.utils.ResultEnum;
import static com.example.calculator.utils.StringUtils.ADD;
import static com.example.calculator.utils.StringUtils.DIV;
import static com.example.calculator.utils.StringUtils.EMPTY;
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
    public boolean equalsPressed = false;
    private ResultEnum errorEnum;

    @Override
    public void setNewOperand(String value) {
        operationValue += value;
        if (operator.isEmpty()) {
            firstOperand += value;
        } else {
            secondOperand += value;
        }
    }

    @Override
    public void setNewOperator(String value) {
        operationValue += value;
        if (firstOperand.isEmpty()) {
            firstOperand += value;
        } else if (operator.isEmpty()) {
            operator = value;
        } else if (secondOperand.isEmpty()) {
            secondOperand += value;
        }
    }

    @Override
    public boolean canPressOperator(String value) {
        return (
                (firstOperand.isEmpty() && (value.equals(SUB))) ||
                        (!firstOperand.isEmpty() && !firstOperand.equals(SUB) && operator.isEmpty()) ||
                        (!operator.isEmpty() && secondOperand.isEmpty() && (value.equals(SUB)))
        );
    }

    @Override
    public String getOperationValue() {
        return operationValue;
    }

    @Override
    public String getResultValue() {
        errorEnum = ResultEnum.SUCCES;
        if (incompleteOperation()) {
            errorEnum = ResultEnum.INCOMPLETE_OPERATION_ERROR;
            return EMPTY;
        }
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
                if (secondOperand.equals(ZERO)) {
                    errorEnum = ResultEnum.DIVIDE_BY_ZERO_ERROR;
                    return EMPTY;
                }
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
        equalsPressed = false;
        errorEnum = ResultEnum.SUCCES;
    }

    @Override
    public void setEqualsPressed() {
        equalsPressed = true;
    }

    @Override
    public boolean getEqualsPressed() {
        return equalsPressed;
    }

    @Override
    public void updateValues() {
        String result = getResultValue();
        if (!errorEnum.equals(ResultEnum.DIVIDE_BY_ZERO_ERROR) && !errorEnum.equals(ResultEnum.INCOMPLETE_OPERATION_ERROR)) {
            firstOperand = EMPTY;
            operator = EMPTY;
            secondOperand = EMPTY;
            operationValue = EMPTY;
            equalsPressed = false;
            setNewOperand(result);
        }
    }

    @Override
    public ResultEnum getErrorEnum() {
        return errorEnum;
    }
}
