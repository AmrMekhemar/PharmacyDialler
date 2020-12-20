package com.team.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserRegisterFragment extends Fragment implements View.OnClickListener {

    TextView title2, User_info,password_info;
    EditText UserNameET,UserAgeET,UserEmailET,UserAddressET,UserLocationET,
            User_passwordET,confirm_User_passwordET;
    ImageButton userPlaceHolder;
    LinearLayout UserLocationLinear;
    Button UserRegisterBTN;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserRegisterFragment newInstance(String param1, String param2) {
        UserRegisterFragment fragment = new UserRegisterFragment();
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
        View root=inflater.inflate(R.layout.fragment_user_register, container, false);
        title2 =root.findViewById(R.id.title2);
        User_info=root.findViewById(R.id.User_info);
        password_info=root.findViewById(R.id.password_info);
        UserNameET=root.findViewById(R.id.UserNameET);
        UserAgeET=root.findViewById(R.id.UserAgeET);
        UserEmailET=root.findViewById(R.id.UserEmailET);
        UserAddressET=root.findViewById(R.id.UserAddressET);
        UserLocationET=root.findViewById(R.id.UserLocationET);
        User_passwordET=root.findViewById(R.id.User_passwordET);
        confirm_User_passwordET=root.findViewById(R.id.confirm_User_passwordET);
        userPlaceHolder=root.findViewById(R.id.userPlaceHolder);
        UserLocationLinear=root.findViewById(R.id.UserLocationLinear);
        UserRegisterBTN=root.findViewById(R.id.UserRegister_btn);

        UserRegisterBTN.setOnClickListener(this);
        userPlaceHolder.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.UserRegister_btn:


                break;

            case R.id.userPlaceHolder:


                break;
        }

        }
}