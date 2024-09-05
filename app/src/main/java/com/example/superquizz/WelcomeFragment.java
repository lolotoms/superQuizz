package com.example.superquizz;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.superquizz.databinding.ActivityMainBinding;
import com.example.superquizz.databinding.FragmentWelcomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WelcomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WelcomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentWelcomeBinding binding;

    public WelcomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WelcomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WelcomeFragment newInstance(String param1, String param2) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        Log.d("WelcomeFragment Lyfecycle", "onAttach() called");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.d("WelcomeFragment Lyfecycle", "onCreate() called");
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        Log.d("WelcomeFragment Lyfecycle", "onCreateView() called");
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.welcomeFragmentPlayButton.setEnabled(false);
        binding.welcomeFragmentUsernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                binding.welcomeFragmentPlayButton.setEnabled(!s.toString().isEmpty());
            }
        });
        binding.welcomeFragmentPlayButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = getParentFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                QuizzFragment quizFragment = QuizzFragment.newInstance();
                fragmentTransaction.add(R.id.fragment_container_view, quizFragment);
                //fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Log.d("Fragment Lyfecycle", "onViewCreated() called");
    }

    @Override
    public void onStart() {
        super.onStart();
        binding.welcomeFragmentPlayButton.setEnabled(false);
        Log.d("WelcomeFragment Lyfecycle", "onStart() called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("WelcomeFragment Lyfecycle", "onResume() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("WelcomeFragment Lyfecycle", "onPause() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("WelcomeFragment Lyfecycle", "onStop() called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("WelcomeFragment Lyfecycle", "onDestroyView() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("WelcomeFragment Lyfecycle", "onDestroy() called");
    }
}