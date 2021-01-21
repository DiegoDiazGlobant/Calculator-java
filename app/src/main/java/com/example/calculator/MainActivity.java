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
        binding.buttonEquals.setOnClickListener(view -> presenter.onEqualsButtonPressed());
        binding.buttonClear.setOnClickListener(view -> presenter.onClearLastButtonPressed());
        binding.buttonClear.setOnLongClickListener(view -> presenter.onClearAllButtonPressed());
    }

    private void setUpClickListenerToNumberButton(Button buttonPressed) {
        buttonPressed.setOnClickListener(view -> presenter.onNumberButtonPressed(buttonPressed.getText().toString()));
    }

    private void setUpClickListenerToOperatorButton(Button buttonPressed) {
        buttonPressed.setOnClickListener(view -> presenter.onOperatorButtonPressed(buttonPressed.getText().toString()));
    }
}
