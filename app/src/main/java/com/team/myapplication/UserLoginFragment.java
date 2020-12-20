package com.team.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserLoginFragment extends Fragment implements View.OnClickListener {

    ImageView logoIV;
    TextView welcomeTV;
    EditText emailET,passwordET;
    Button signInBTN, PharmacyRegisterBTN, UserRegisterBTN;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment startFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserLoginFragment newInstance(String param1, String param2) {
        UserLoginFragment fragment = new UserLoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_login_user, container, false);
        logoIV=root.findViewById(R.id.logoIV);
        welcomeTV=root.findViewById(R.id.signTV);
        emailET=root.findViewById(R.id.email_ET);
        passwordET=root.findViewById(R.id.password_ET);
        signInBTN =root.findViewById(R.id.signIn_btn);
        PharmacyRegisterBTN =root.findViewById(R.id.PharmacyRegister_btn);
        UserRegisterBTN =root.findViewById(R.id.UserRegister_btn);
        signInBTN.setOnClickListener(this);
        PharmacyRegisterBTN.setOnClickListener(this);
        UserRegisterBTN.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signIn_btn:
                break;
            case R.id.PharmacyRegister_btn:
                break;
            case R.id.UserRegister_btn:
                break;
        }

    }
}