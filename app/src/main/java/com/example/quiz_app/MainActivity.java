package com.example.quiz_app;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView questionTextView;
    TextView TotalQuestionsTextView;
    Button ansA, ansB, ansC, ansD;
    Button btn_submit;
    //change 1

    int score = 0;
    int Totalquestions = question_ans.question.length;
    int CurrentquestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TotalQuestionsTextView = findViewById(R.id.Total_questions);
        questionTextView = findViewById(R.id.Question);
        ansA = findViewById(R.id.answer_a);
        ansB = findViewById(R.id.answer_b);
        ansC = findViewById(R.id.answer_c);
        ansD = findViewById(R.id.answer_d);
        btn_submit = findViewById(R.id.btn_submit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        btn_submit.setOnClickListener(this);

        TotalQuestionsTextView.setText("Total Questions: " + Totalquestions);
        loadNewQuestion();
    }

    private void loadNewQuestion() {
        if (CurrentquestionIndex == Totalquestions) {
            finishQuiz();
            return;
        }
        questionTextView.setText(question_ans.question[CurrentquestionIndex]);
        ansA.setText(question_ans.choices[CurrentquestionIndex][0]);
        ansB.setText(question_ans.choices[CurrentquestionIndex][1]);
        ansC.setText(question_ans.choices[CurrentquestionIndex][2]);
        ansD.setText(question_ans.choices[CurrentquestionIndex][3]);

        selectedAnswer = "";
    }

    private void finishQuiz() {
        String passStatus;
        if (score >= Totalquestions * 0.6) {
            passStatus = "passed";
        } else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Your Score is" + score + "Oute of" + Totalquestions)
                .setPositiveButton("Restart", ((dialog, i) -> restartQuiz()))
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        CurrentquestionIndex = 0;
        loadNewQuestion();
    }
    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;

        if (clickedButton.getId() == R.id.btn_submit) {
            if (!selectedAnswer.isEmpty()) {
                if (selectedAnswer.equals(question_ans.correctAnswers[CurrentquestionIndex])) {
                    score++;
                } else {
                    clickedButton.setBackgroundColor(Color.GREEN);
                }
                CurrentquestionIndex++;
                loadNewQuestion();
            } else {

            }
        } else {
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.YELLOW);
        }
    }
}