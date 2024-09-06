package com.example.superquizz.ui.quizz;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.superquizz.R;
import com.example.superquizz.data.Question;
import com.example.superquizz.data.QuestionBank;
import com.example.superquizz.data.QuestionRepository;
import com.example.superquizz.databinding.FragmentQuizzBinding;
import com.example.superquizz.databinding.FragmentWelcomeBinding;
import com.example.superquizz.injection.ViewModelFactory;
import com.example.superquizz.ui.welcome.WelcomeFragment;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizzFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizzFragment extends Fragment {

    private QuizzViewModel quizzViewModel;
    private FragmentQuizzBinding binding;

    // TODO: Rename and change types and number of parameters
    public static QuizzFragment newInstance() {
        QuizzFragment fragment = new QuizzFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        quizzViewModel = new ViewModelProvider(this, ViewModelFactory.getInstance()).get(QuizzViewModel.class);
        Log.d("QuizzFragment Lyfecycle", "onCreate() called");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d("QuizzFragment Lyfecycle", "onCreateView() called");
        // Inflate the layout for this fragment
        binding = FragmentQuizzBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        quizzViewModel.startQuizz();
        quizzViewModel.currentQuestion.observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                updateQuestion(question);
            }
        });

        binding.quizzFragmentAnswer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.quizzFragmentAnswer1Button, 0);
            }
        });

        binding.quizzFragmentAnswer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.quizzFragmentAnswer2Button, 1);
            }
        });

        binding.quizzFragmentAnswer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.quizzFragmentAnswer3Button, 2);
            }
        });

        binding.quizzFragmentAnswer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateAnswer(binding.quizzFragmentAnswer4Button, 3);
            }
        });

        quizzViewModel.isLastQuestion.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLastQuestion) {
                if (isLastQuestion){
                    binding.validityFragmentNextButton.setText("Finish");
                } else {
                    binding.validityFragmentNextButton.setText("Next");
                }
            }
        });

        binding.validityFragmentNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isLastQuestion = quizzViewModel.isLastQuestion.getValue();
                if(isLastQuestion != null && isLastQuestion){
                    displayResultDialog();
                } else {
                    quizzViewModel.nextQuestion();
                    resetQuestion();
                }
            }
        });

        Log.d("QuizzFragment Lyfecycle", "onViewCreated() called");
    }

    private void resetQuestion(){
        List<Button> allAnswers = Arrays.asList(binding.quizzFragmentAnswer1Button, binding.quizzFragmentAnswer2Button, binding.quizzFragmentAnswer3Button, binding.quizzFragmentAnswer4Button);
        allAnswers.forEach( answer -> {
            answer.setBackgroundColor(Color.parseColor("#6200EE"));
        });
        binding.validityFragmentValidityText.setVisibility(View.INVISIBLE);
        enableAllAnswers(true);
    }

    private void updateAnswer(Button button, Integer index){
        showAnswerValidity(button, index);
        enableAllAnswers(false);
        binding.validityFragmentNextButton.setVisibility(View.VISIBLE);
    }

    private void showAnswerValidity(Button button, Integer index){
        Boolean isValid = quizzViewModel.isAnswerValid(index);
        if (isValid) {
            button.setBackgroundColor(Color.parseColor("#388e3c")); // dark green
            binding.validityFragmentValidityText.setText("\uD83D\uDCAA Good Answer !");
            button.announceForAccessibility("Good Answer !");
        } else {
            button.setBackgroundColor(Color.RED);
            binding.validityFragmentValidityText.setText("Bad answer \uD83D\uDE22");
            button.announceForAccessibility("Bad Answer !");
        }
        binding.validityFragmentValidityText.setVisibility(View.VISIBLE);

    }

    private void enableAllAnswers(Boolean enable){
        List<Button> allAnswers = Arrays.asList(binding.quizzFragmentAnswer1Button, binding.quizzFragmentAnswer2Button, binding.quizzFragmentAnswer3Button, binding.quizzFragmentAnswer4Button);
        allAnswers.forEach( answer -> {
            answer.setEnabled(enable);
        });
    }

    private void displayResultDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("Finished !");
        Integer score = quizzViewModel.score.getValue();
        builder.setMessage("Your final score is "+ score);
        builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                goToWelcomeFragment();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void goToWelcomeFragment(){
        WelcomeFragment welcomeFragment = WelcomeFragment.newInstance();
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, welcomeFragment).commit();
    }

    public void updateQuestion(Question question) {
        binding.quizzFragmentQuestion.setText(question.getQuestion());
        binding.quizzFragmentAnswer1Button.setText(question.getChoiceList().get(0));
        binding.quizzFragmentAnswer2Button.setText(question.getChoiceList().get(1));
        binding.quizzFragmentAnswer3Button.setText(question.getChoiceList().get(2));
        binding.quizzFragmentAnswer4Button.setText(question.getChoiceList().get(3));
        binding.validityFragmentNextButton.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("QuizzFragment Lyfecycle", "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("QuizzFragment Lyfecycle", "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("QuizzFragment Lyfecycle", "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("QuizzFragment Lyfecycle", "onStop() called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("QuizzFragment Lyfecycle", "onDestroyView() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("QuizzFragment Lyfecycle", "onDestroy() called");
    }
}