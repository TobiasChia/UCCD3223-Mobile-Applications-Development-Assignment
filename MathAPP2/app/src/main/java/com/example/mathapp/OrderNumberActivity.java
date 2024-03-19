package com.example.mathapp;
import android.os.Bundle;
import java.util.Random;
import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Collections;
import android.content.Intent;

public class OrderNumberActivity extends AppCompatActivity {
    private Button sortButton;
    private TextView num1, num2, num3;
    private Button swapUpButton, swapDownButton;
    private ArrayList<Integer> numbers = new ArrayList<>();
    private boolean isAscending = true;
    private int selectedNumIndex = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ordernumberactivity);

        sortButton = findViewById(R.id.sortButton);
        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        num3 = findViewById(R.id.num3);
        swapUpButton = findViewById(R.id.swapUpButton);
        swapDownButton = findViewById(R.id.swapDownButton);
        updateSortButtonText();
        generateNumbers();
        updateUI();

        sortButton.setOnClickListener(v -> {
            isAscending = !isAscending;
            updateSortButtonText();
            checkAndGenerateNewNumbers();
        });

        num1.setOnClickListener(v -> updateNumberSelection(0));
        num2.setOnClickListener(v -> updateNumberSelection(1));
        num3.setOnClickListener(v -> updateNumberSelection(2));

        swapUpButton.setOnClickListener(v -> {
            if (selectedNumIndex > 0) {
                Collections.swap(numbers, selectedNumIndex, selectedNumIndex - 1);
                updateNumberSelection(selectedNumIndex - 1); //
                updateUI();
                checkAndGenerateNewNumbers();
            }
        });

        swapDownButton.setOnClickListener(v -> {
            if (selectedNumIndex < numbers.size() - 1) {
                Collections.swap(numbers, selectedNumIndex, selectedNumIndex + 1);
                updateNumberSelection(selectedNumIndex + 1); //
                updateUI();
                checkAndGenerateNewNumbers(); //
            }
        });
        findViewById(R.id.btnHome).setOnClickListener(v -> startActivity(new Intent(OrderNumberActivity.this, MainActivity.class)));
    }


    private void generateNumbers() {
        do {
            numbers.clear(); //
            Random random = new Random();
            while (numbers.size() < 3) {
                int nextNum = random.nextInt(999);
                if (!numbers.contains(nextNum)) {
                    numbers.add(nextNum);
                }
            }
        } while (isSorted()); //

        updateUI(); //
    }

    private boolean isSorted() {
        boolean ascending = numbers.get(0) < numbers.get(1) && numbers.get(1) < numbers.get(2);
        boolean descending = numbers.get(0) > numbers.get(1) && numbers.get(1) > numbers.get(2);
        return ascending || descending;
    }

    private void sortNumbers() {
        if (isAscending) {
            Collections.sort(numbers);
        } else {
            Collections.sort(numbers, Collections.reverseOrder());
        }
    }

    private void updateUI() {
        num1.setText(String.valueOf(numbers.get(0)));
        num2.setText(String.valueOf(numbers.get(1)));
        num3.setText(String.valueOf(numbers.get(2)));
    }

    private void updateNumberSelection(int index) {
        selectedNumIndex = index;
        num1.setBackgroundColor(Color.TRANSPARENT);
        num2.setBackgroundColor(Color.TRANSPARENT);
        num3.setBackgroundColor(Color.TRANSPARENT);
        if (index == 0) {
            num1.setBackgroundColor(Color.GREEN);
        } else if (index == 1) {
            num2.setBackgroundColor(Color.GREEN);
        } else if (index == 2) {
            num3.setBackgroundColor(Color.GREEN);
        }
    }

    private void checkAndGenerateNewNumbers() {
        boolean isSortedCorrectly = isAscending ?
                numbers.get(0) < numbers.get(1) && numbers.get(1) < numbers.get(2) :
                numbers.get(0) > numbers.get(1) && numbers.get(1) > numbers.get(2);

        if (isSortedCorrectly) {
            generateNumbers();
            updateUI();
            selectedNumIndex = -1;
            updateNumberSelection(selectedNumIndex);
        }
    }
    private void updateSortButtonText() {
        sortButton.setText(isAscending ? "Ascending to Descending" : "Descending to Ascending");
    }
}