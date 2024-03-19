package com.example.mathapp;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class ComposeNumberActivity extends AppCompatActivity {
    private TextView numberDisplay;
    private Button[] options = new Button[3];
    private int correctSum;
    private int correctOption1, correctOption2;
    private boolean firstCorrect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.composenumberactivity);

        numberDisplay = findViewById(R.id.numberDisplay);
        options[0] = findViewById(R.id.option1);
        options[1] = findViewById(R.id.option2);
        options[2] = findViewById(R.id.option3);

        generateQuestion();

        for (int i = 0; i < options.length; i++) {
            final int index = i;
            options[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkAnswer(index);
                }
            });
        }
        findViewById(R.id.btnHome).setOnClickListener(v -> startActivity(new Intent(ComposeNumberActivity.this, MainActivity.class)));
    }

    private void generateQuestion() {
        Random random = new Random();
        correctSum = random.nextInt(999);
        int subtractor = random.nextInt(correctSum - 1) + 1;
        correctOption1 = subtractor;
        correctOption2 = correctSum - subtractor;

        int wrongOption = random.nextInt(999);
        while (wrongOption == correctOption1 || wrongOption == correctOption2 || wrongOption == correctSum) {
            wrongOption = random.nextInt(999);
        }

        numberDisplay.setText(String.valueOf(correctSum));
        int correctPosition = random.nextInt(3);
        options[correctPosition].setText(String.valueOf(correctOption1));
        options[(correctPosition + 1) % 3].setText(String.valueOf(correctOption2));
        options[(correctPosition + 2) % 3].setText(String.valueOf(wrongOption));

        firstCorrect = false;
        resetButtonColors();
    }

    private void checkAnswer(int index) {
        Button selectedOption = options[index];
        int selectedNumber = Integer.parseInt(selectedOption.getText().toString());

        if ((selectedNumber == correctOption1 || selectedNumber == correctOption2) && !firstCorrect) {
            firstCorrect = true;
            selectedOption.setBackgroundColor(Color.GREEN);
        } else if ((selectedNumber == correctOption1 || selectedNumber == correctOption2) && firstCorrect) {
            selectedOption.setBackgroundColor(Color.GREEN);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestion();
                }
            }, 1000);
        } else {
            selectedOption.setBackgroundColor(Color.RED);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateQuestion();
                }
            }, 1000);
        }
    }

    private void resetButtonColors() {
        for (Button button : options) {
            button.setBackgroundColor(Color.parseColor("#FF6200EE"));
        }
    }
}
