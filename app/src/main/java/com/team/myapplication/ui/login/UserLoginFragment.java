package com.team.myapplication.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.myapplication.R;

import static com.team.myapplication.MainActivity.navView;


public class UserLoginFragment extends Fragment implements View.OnClickListener {

    ImageView logoIV;
    TextView welcomeTV;
    EditText emailET, passwordET;
    Button signInBTN, PharmacyRegisterBTN, UserRegisterBTN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_login_user, container, false);
        logoIV = root.findViewById(R.id.logoIV);
        welcomeTV = root.findViewById(R.id.signTV);
        emailET = root.findViewById(R.id.email_ET);
        passwordET = root.findViewById(R.id.password_ET);
        signInBTN = root.findViewById(R.id.signIn_btn);
        PharmacyRegisterBTN = root.findViewById(R.id.PharmacyRegister_btn);
        UserRegisterBTN = root.findViewById(R.id.UserRegister_btn);
        signInBTN.setOnClickListener(this);
        PharmacyRegisterBTN.setOnClickListener(this);
        UserRegisterBTN.setOnClickListener(this);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.signIn_btn:
                navigateToNextNavigation(view);
                break;
            case R.id.PharmacyRegister_btn:
                break;
            case R.id.UserRegister_btn:
                break;
        }

    }

    private void navigateToNextNavigation(View v) {
        Navigation.findNavController(v).navigate(UserLoginFragmentDirections.Companion.actionUserLoginFragmentToMobileNavigation());
        navView.setVisibility(View.VISIBLE);
    }
}