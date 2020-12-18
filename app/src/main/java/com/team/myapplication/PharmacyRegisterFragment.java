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
 * Use the {@link PharmacyRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PharmacyRegisterFragment extends Fragment implements View.OnClickListener {

    TextView title, PH_info,password_info;
    EditText pharmacyNameET,pharmacyOwnerET,pharmacyEmailET,pharmacyAddressET,pharmacyLocationET,
            PH_passwordET,confirm_PH_passwordET;
    ImageButton PH_placeholder;
    LinearLayout locationLinear;
    Button pharmacyRegisterBTN2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PharmacyRegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PharmacyRegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PharmacyRegisterFragment newInstance(String param1, String param2) {
        PharmacyRegisterFragment fragment = new PharmacyRegisterFragment();
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
        View root=inflater.inflate(R.layout.fragment_pharmacy_register, container, false);

        title =root.findViewById(R.id.title);
        PH_info =root.findViewById(R.id.PH_info);
        password_info =root.findViewById(R.id.password_info);
        pharmacyNameET =root.findViewById(R.id.pharmacyNameET);
        pharmacyOwnerET =root.findViewById(R.id.pharmacyOwnerET);
        pharmacyEmailET =root.findViewById(R.id.pharmacyEmailET);
        pharmacyAddressET =root.findViewById(R.id.pharmacyAddressET);
        pharmacyLocationET =root.findViewById(R.id.pharmacyLocationET);
        PH_passwordET =root.findViewById(R.id.PH_passwordET);
        confirm_PH_passwordET =root.findViewById(R.id.confirm_PH_passwordET);
        PH_placeholder =root.findViewById(R.id.PH_placeholder);
        locationLinear =root.findViewById(R.id.locationLinear);
        pharmacyRegisterBTN2 =root.findViewById(R.id.pharmacyRegisterBTN2);

        PH_placeholder.setOnClickListener(this);
        pharmacyRegisterBTN2.setOnClickListener(this);

        return root;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.PH_placeholder:


                break;

            case R.id.pharmacyRegisterBTN2:


                break;
        }

    }
}