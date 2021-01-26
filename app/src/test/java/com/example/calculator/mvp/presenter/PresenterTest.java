package com.example.calculator.mvp.presenter;

import com.example.calculator.mvp.contract.CalculatorContract;
import com.example.calculator.mvp.model.CalculatorModel;
import com.example.calculator.mvp.view.CalculatorView;
import org.junit.Before;
import org.junit.Test;
import static com.example.calculator.utils.StringUtilsTest.ADD;
import static com.example.calculator.utils.StringUtilsTest.DIV;
import static com.example.calculator.utils.StringUtilsTest.DOUBLE_EIGHT;
import static com.example.calculator.utils.StringUtilsTest.DOUBLE_FIFTEEN;
import static com.example.calculator.utils.StringUtilsTest.DOUBLE_NEGATIVE_EIGHT;
import static com.example.calculator.utils.StringUtilsTest.DOUBLE_NEGATIVE_TWO;
import static com.example.calculator.utils.StringUtilsTest.DOUBLE_THREE;
import static com.example.calculator.utils.StringUtilsTest.DOUBLE_TWO;
import static com.example.calculator.utils.StringUtilsTest.EMPTY;
import static com.example.calculator.utils.StringUtilsTest.FIVE;
import static com.example.calculator.utils.StringUtilsTest.INCOMPLETE_OPERATION;
import static com.example.calculator.utils.StringUtilsTest.NINE;
import static com.example.calculator.utils.StringUtilsTest.PRODUCT;
import static com.example.calculator.utils.StringUtilsTest.SUB;
import static com.example.calculator.utils.StringUtilsTest.THREE;
import static com.example.calculator.utils.StringUtilsTest.ZERO;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PresenterTest {

    private CalculatorContract.Model model;
    private CalculatorContract.View view;
    private CalculatorContract.Presenter presenter;

    @Before
    public void setup() {
        model = new CalculatorModel();
        view = mock(CalculatorView.class);
        presenter = new CalculatorPresenter(model, view);
    }

    @Test
    public void numberButtonPressedTest() {
        presenter.onNumberButtonPressed(FIVE);
        assertEquals(FIVE, model.getOperationValue());
        verify(view).showOperationValue(model.getOperationValue());
        verify(view).showResultValue(FIVE);
    }

    @Test
    public void operatorButtonPressedTest() {
        model.setNewOperand(FIVE);
        presenter.onOperatorButtonPressed(ADD);
        assertEquals(INCOMPLETE_OPERATION, model.getOperationValue());
        verify(view).showOperationValue(model.getOperationValue());
        verify(view).showResultValue(ADD);
    }

    @Test
    public void operatorButtonPressedWrongOperatorErrorTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(SUB);
        presenter.onOperatorButtonPressed(DIV);
        verify(view).showWrongOperator();
    }

    @Test
    public void equalsButtonPressedDivideByZeroErrorTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(DIV);
        model.setNewOperand(ZERO);
        presenter.onEqualsButtonPressed();
        verify(view).showDivideByZero();
    }

    @Test
    public void equalsButtonPressedIncompleteOperationErrorTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(DIV);
        presenter.onEqualsButtonPressed();
        verify(view).showIncompleteOperation();
    }

    @Test
    public void equalsButtonPressedWithAddOperationTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(ADD);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_EIGHT, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }

    @Test
    public void equalsButtonPressedWithSubOperationTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(SUB);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_TWO, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }

    @Test
    public void equalsButtonPressedWithProductOperationTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(PRODUCT);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_FIFTEEN, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }

    @Test
    public void equalsButtonPressedWithDivOperationTest() {
        model.setNewOperand(NINE);
        model.setNewOperator(DIV);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_THREE, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }

    @Test
    public void equalsButtonPressedWithAddOperationAndNegativeNumbersTest() {
        model.setNewOperator(SUB);
        model.setNewOperand(FIVE);
        model.setNewOperator(ADD);
        model.setNewOperator(SUB);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_NEGATIVE_EIGHT, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }

    @Test
    public void equalsButtonPressedWithSubOperationAndNegativeNumbersTest() {
        model.setNewOperator(SUB);
        model.setNewOperand(FIVE);
        model.setNewOperator(SUB);
        model.setNewOperator(SUB);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_NEGATIVE_TWO, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }

    @Test
    public void equalsButtonPressedWithProductOperationAndNegativeNumbersTest() {
        model.setNewOperator(SUB);
        model.setNewOperand(FIVE);
        model.setNewOperator(PRODUCT);
        model.setNewOperator(SUB);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_FIFTEEN, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }

    @Test
    public void equalsButtonPressedWithDivOperationAndNegativeNumbersTest() {
        model.setNewOperator(SUB);
        model.setNewOperand(NINE);
        model.setNewOperator(DIV);
        model.setNewOperator(SUB);
        model.setNewOperand(THREE);
        presenter.onEqualsButtonPressed();
        assertEquals(DOUBLE_THREE, model.getResultValue());
        verify(view).showResultValue(model.getResultValue());
    }


    @Test
    public void clearAllTest() {
        presenter.onClearAllButtonPressed();
        assertEquals(EMPTY, model.getResultValue());
        assertEquals(EMPTY, model.getOperationValue());
        verify(view).clearValues();
    }

    @Test
    public void clearLastValueTest() {
        model.setNewOperand(FIVE);
        presenter.onClearLastButtonPressed();
        assertEquals(EMPTY, model.getOperationValue());
        verify(view).clearValues();
    }

    @Test
    public void clearLastFirstOperandTest() {
        model.setNewOperand(FIVE);
        model.setNewOperand(THREE);
        presenter.onClearLastButtonPressed();
        assertEquals(FIVE, model.getOperationValue());
        verify(view).clearLast(model.getOperationValue());
    }

    @Test
    public void clearLastOperatorTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(PRODUCT);
        presenter.onClearLastButtonPressed();
        assertEquals(FIVE, model.getOperationValue());
        verify(view).clearLast(model.getOperationValue());
    }

    @Test
    public void clearLastSecondOperandTest() {
        model.setNewOperand(FIVE);
        model.setNewOperator(ADD);
        model.setNewOperand(THREE);
        presenter.onClearLastButtonPressed();
        assertEquals(INCOMPLETE_OPERATION, model.getOperationValue());
        verify(view).clearLast(model.getOperationValue());
    }
}
