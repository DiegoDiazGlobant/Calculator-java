package com.example.calculator;

import android.os.Bundle;
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
        binding.buttonAdd.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonAdd.getText().toString()));
        binding.buttonSub.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonSub.getText().toString()));
        binding.buttonProduct.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonProduct.getText().toString()));
        binding.buttonDivision.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonDivision.getText().toString()));
        binding.buttonClear.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonClear.getText().toString()));
        binding.buttonEquals.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonEquals.getText().toString()));
        binding.buttonZero.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonZero.getText().toString()));
        binding.buttonOne.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonOne.getText().toString()));
        binding.buttonTwo.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonTwo.getText().toString()));
        binding.buttonThree.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonThree.getText().toString()));
        binding.buttonFour.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonFour.getText().toString()));
        binding.buttonFive.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonFive.getText().toString()));
        binding.buttonSix.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonSix.getText().toString()));
        binding.buttonSeven.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonSeven.getText().toString()));
        binding.buttonEight.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonEight.getText().toString()));
        binding.buttonNine.setOnClickListener(view -> presenter.onButtonPressed(binding.buttonNine.getText().toString()));
    }
}
