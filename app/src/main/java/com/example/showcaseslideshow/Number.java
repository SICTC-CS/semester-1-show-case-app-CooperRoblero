package com.example.showcaseslideshow;



import android.annotation.SuppressLint;
import android.content.Context;

import android.content.SharedPreferences;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.ViewGroup;

import android.widget.Button;

import android.widget.EditText;

import android.widget.TextView;



import androidx.annotation.NonNull;

import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;



import java.util.Random;



class GuessNumberFragment extends Fragment {



    private int randomNumber;

    private int strikes;

    private int score;

    private int highScore;



    private TextView resultText;

    private TextView scoreText;

    private TextView strikesText;

    private TextView highScoreText;

    private EditText guessInput;



    private SharedPreferences sharedPreferences;



    @SuppressLint("MissingInflatedId")
    @Nullable

    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_guess_number, container, false);



        // Initialize views

        resultText = root.findViewById(R.id.result_text);

        scoreText = root.findViewById(R.id.score_text);

        strikesText = root.findViewById(R.id.strikes_text);

        highScoreText = root.findViewById(R.id.high_score_text);

        guessInput = root.findViewById(R.id.guess_input);

        Button guessButton = root.findViewById(R.id.guess_button);



        // Initialize SharedPreferences for high score

        sharedPreferences = requireActivity().getSharedPreferences("GuessNumberPrefs", Context.MODE_PRIVATE);

        highScore = sharedPreferences.getInt("high_score", 0);



        // Start a new game

        resetGame();



        // Handle guess button clicks

        guessButton.setOnClickListener(v -> {

            String guessString = guessInput.getText().toString();

            if (!guessString.isEmpty()) {

                int userGuess = Integer.parseInt(guessString);

                processGuess(userGuess);

            } else {

                resultText.setText("Please enter a valid number!");

            }

        });



        return root;

    }



    private void processGuess(int userGuess) {

        if (userGuess == randomNumber) {

            // Correct guess

            score += 50 + (10 - strikes) * 5;

            resultText.setText("Correct! You earned " + (50 + (10 - strikes) * 5) + " points.");



            // Update high score if necessary

            if (score > highScore) {

                highScore = score;

                sharedPreferences.edit().putInt("high_score", highScore).apply();

                highScoreText.setText("High Score: " + highScore);

            }



            // Start a new game

            resetGame();

        } else {

            // Incorrect guess

            strikes++;

            if (strikes >= 10) {

                // Game over

                resultText.setText("Game Over! The correct number was " + randomNumber + ". Try again!");

                resetGame();

            } else {

                // Hint to the user

                String hint = userGuess > randomNumber ? "Lower!" : "Higher!";

                resultText.setText(hint);

            }

        }



        // Update UI

        updateUI();

    }



    private void resetGame() {

        randomNumber = new Random().nextInt(100) + 1; // Generate random number between 1 and 100

        strikes = 0;

        resultText.setText("Guess a number between 1 and 100!");

        updateUI();

    }



    private void updateUI() {

        strikesText.setText("Strikes: " + strikes + "/10");

        scoreText.setText("Score: " + score);

        highScoreText.setText("High Score: " + highScore);

        guessInput.setText(""); // Clear input

    }

}

