package com.example.superquizz.ui.quizz;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superquizz.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizzFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizzFragment extends Fragment {

    public QuizzFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static QuizzFragment newInstance() {
        QuizzFragment fragment = new QuizzFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("QuizzFragment Lyfecycle", "onCreate() called");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("QuizzFragment Lyfecycle", "onCreateView() called");
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quizz, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("QuizzFragment Lyfecycle", "onViewCreated() called");
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