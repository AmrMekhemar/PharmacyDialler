package com.team.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

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

    public LoginFragment() {
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
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
        View root= inflater.inflate(R.layout.fragment_login, container, false);
        logoIV=root.findViewById(R.id.logoIV);
        welcomeTV=root.findViewById(R.id.signTV);
        emailET=root.findViewById(R.id.emailET);
        passwordET=root.findViewById(R.id.passwordET);
        signInBTN =root.findViewById(R.id.signInBTN);
        PharmacyRegisterBTN =root.findViewById(R.id.PharmacyRegisterBTN);
        UserRegisterBTN =root.findViewById(R.id.UserRegisterBTN);
        signInBTN.setOnClickListener(this);
        PharmacyRegisterBTN.setOnClickListener(this);
        UserRegisterBTN.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.signInBTN:



                break;
            case R.id.PharmacyRegisterBTN:
                FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id., new PharmacyRegisterFragment());
                ft.commit();
                break;
            case R.id.UserRegisterBTN:
                FragmentTransaction ft1=getActivity().getSupportFragmentManager().beginTransaction();
                ft1.replace(R.id., new UserRegisterFragment());
                ft1.commit();
                break;
        }

    }
}