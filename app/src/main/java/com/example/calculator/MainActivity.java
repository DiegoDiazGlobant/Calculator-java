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
        setUpClickListenerToButton(binding.buttonAdd);
        setUpClickListenerToButton(binding.buttonSub);
        setUpClickListenerToButton(binding.buttonProduct);
        setUpClickListenerToButton(binding.buttonDivision);
        setUpClickListenerToButton(binding.buttonEquals);
        setUpClickListenerToButton(binding.buttonClear);
        setUpClickListenerToButton(binding.buttonZero);
        setUpClickListenerToButton(binding.buttonOne);
        setUpClickListenerToButton(binding.buttonTwo);
        setUpClickListenerToButton(binding.buttonThree);
        setUpClickListenerToButton(binding.buttonFour);
        setUpClickListenerToButton(binding.buttonFive);
        setUpClickListenerToButton(binding.buttonSix);
        setUpClickListenerToButton(binding.buttonSeven);
        setUpClickListenerToButton(binding.buttonEight);
        setUpClickListenerToButton(binding.buttonNine);
    }

    private void setUpClickListenerToButton(Button buttonPressed) {
        buttonPressed.setOnClickListener(view -> presenter.onButtonPressed(buttonPressed.getText().toString()));
    }
}
