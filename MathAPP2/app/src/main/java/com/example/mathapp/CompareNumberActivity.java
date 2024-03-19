package com.example.mathapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Random;
import android.content.Intent;


public class CompareNumberActivity extends AppCompatActivity {
    private TextView number1, number2;
    private final Random random = new Random();
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparenumberactivity);

        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);

        refreshNumbers();

        View.OnClickListener numberClickListener = view -> {
            boolean isNumber1Clicked = view.getId() == R.id.number1;
            int num1 = Integer.parseInt(number1.getText().toString());
            int num2 = Integer.parseInt(number2.getText().toString());

            // Check which number is bigger
            if ((isNumber1Clicked && num1 > num2) || (!isNumber1Clicked && num2 > num1)) {
                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pure_green));
            } else {
                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.pure_red));
            }


            // Wait a bit before refreshing numbers
            handler.postDelayed(this::refreshNumbers, 500);
        };

        number1.setOnClickListener(numberClickListener);
        number2.setOnClickListener(numberClickListener);
        findViewById(R.id.btnHome).setOnClickListener(v -> startActivity(new Intent(CompareNumberActivity.this, MainActivity.class)));

    }

    private void refreshNumbers() {
        // Generate two random numbers (including negatives)
        int num1 = random.nextInt(999) * (random.nextBoolean() ? 1 : -1);
        int num2 = random.nextInt(999) * (random.nextBoolean() ? 1 : -1);
        // Ensure numbers are different
        while (num1 == num2) {
            num2 = random.nextInt(999) * (random.nextBoolean() ? 1 : -1);
        }

        number1.setText(String.valueOf(num1));
        number2.setText(String.valueOf(num2));

        // Reset backgrounds
        number1.setBackgroundColor(getResources().getColor(android.R.color.white));
        number2.setBackgroundColor(getResources().getColor(android.R.color.white));
    }
}
