package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.calculator.databinding.ActivityMainBinding;
import com.example.calculator.mvp.model.CalculatorModel;
import com.example.calculator.mvp.presenter.CalculatorPresenter;
import com.example.calculator.mvp.view.CalculatorView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new CalculatorPresenter(new CalculatorModel(), new CalculatorView(this, binding));
        setListeners();
    }

    private void setListeners() {
        setUpClickListenerToOperatorButton(binding.buttonAdd);
        setUpClickListenerToOperatorButton(binding.buttonSub);
        setUpClickListenerToOperatorButton(binding.buttonProduct);
        setUpClickListenerToOperatorButton(binding.buttonDivision);
        setUpClickListenerToEqualsButton(binding.buttonEquals);
        setUpClickListenerToClearLastButton(binding.buttonClear);
        setUpClickListenerToClearAllButton(binding.buttonClear);
        setUpClickListenerToNumberButton(binding.buttonZero);
        setUpClickListenerToNumberButton(binding.buttonOne);
        setUpClickListenerToNumberButton(binding.buttonTwo);
        setUpClickListenerToNumberButton(binding.buttonThree);
        setUpClickListenerToNumberButton(binding.buttonFour);
        setUpClickListenerToNumberButton(binding.buttonFive);
        setUpClickListenerToNumberButton(binding.buttonSix);
        setUpClickListenerToNumberButton(binding.buttonSeven);
        setUpClickListenerToNumberButton(binding.buttonEight);
        setUpClickListenerToNumberButton(binding.buttonNine);
    }

    private void setUpClickListenerToNumberButton(Button buttonPressed) {
        buttonPressed.setOnClickListener(view -> presenter.onNumberButtonPressed(buttonPressed.getText().toString()));
    }

    private void setUpClickListenerToOperatorButton(Button buttonPressed) {
        buttonPressed.setOnClickListener(view -> presenter.onOperatorButtonPressed(buttonPressed.getText().toString()));
    }

    private void setUpClickListenerToEqualsButton(Button buttonPressed) {
        buttonPressed.setOnClickListener(view -> presenter.onEqualsButtonPressed());
    }

    private void setUpClickListenerToClearLastButton(Button buttonPressed) {
        buttonPressed.setOnClickListener(view -> presenter.onClearLastButtonPressed());
    }

    private void setUpClickListenerToClearAllButton(Button buttonPressed) {
        buttonPressed.setOnLongClickListener(view -> presenter.onClearAllButtonPressed());
    }
}
