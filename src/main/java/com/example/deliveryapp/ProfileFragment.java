package com.example.deliveryapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editTextAddress;
    private SharedPreferences sharedPreferences;
    private TextView textViewUsername;
    private TextView textViewEmailNavHeader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        editTextFirstName = view.findViewById(R.id.edit_text_first_name);
        editTextLastName = view.findViewById(R.id.edit_text_last_name);
        editTextEmail = view.findViewById(R.id.edit_text_email);
        editTextPhoneNumber = view.findViewById(R.id.edit_text_phone_number);
        editTextAddress = view.findViewById(R.id.edit_text_address);

        textViewUsername = getActivity().findViewById(R.id.nav_header_title);
        textViewEmailNavHeader = getActivity().findViewById(R.id.nav_header_subtitle);

        sharedPreferences = getActivity().getSharedPreferences("UserProfile", Context.MODE_PRIVATE);

        loadProfileData();

        Button buttonSave = view.findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateProfile()) {
                    saveProfileData();
                    Toast.makeText(getActivity(), "Profile saved successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void loadProfileData() {
        String firstName = sharedPreferences.getString("first_name", "");
        String lastName = sharedPreferences.getString("last_name", "");
        String email = sharedPreferences.getString("email", "");
        String phoneNumber = sharedPreferences.getString("phone_number", "");
        String address = sharedPreferences.getString("address", "");

        editTextFirstName.setText(firstName);
        editTextLastName.setText(lastName);
        editTextEmail.setText(email);
        editTextPhoneNumber.setText(phoneNumber);
        editTextAddress.setText(address);

        // Update navigation header
        textViewUsername.setText(firstName + " " + lastName);
        textViewEmailNavHeader.setText(email);
    }

    private boolean validateProfile() {
        boolean isValid = true;

        if (TextUtils.isEmpty(editTextFirstName.getText().toString().trim())) {
            editTextFirstName.setError("Please enter First Name");
            isValid = false;
        }

        if (TextUtils.isEmpty(editTextLastName.getText().toString().trim())) {
            editTextLastName.setError("Please enter Last Name");
            isValid = false;
        }

        if (TextUtils.isEmpty(editTextEmail.getText().toString().trim())) {
            editTextEmail.setError("Please enter Email");
            isValid = false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editTextEmail.getText().toString().trim()).matches()) {
            editTextEmail.setError("Please enter a valid Email address");
            isValid = false;
        }

        if (TextUtils.isEmpty(editTextPhoneNumber.getText().toString().trim())) {
            editTextPhoneNumber.setError("Please enter Phone Number");
            isValid = false;
        } else if (!editTextPhoneNumber.getText().toString().matches("\\d{10}")) { // Change this regex as per your requirements
            editTextPhoneNumber.setError("Please enter a valid 10 digit Phone Number");
            isValid = false;
        }

        if (TextUtils.isEmpty(editTextAddress.getText().toString().trim())) {
            editTextAddress.setError("Please enter Address");
            isValid = false;
        }

        return isValid;
    }

    private void saveProfileData() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("first_name", editTextFirstName.getText().toString());
        editor.putString("last_name", editTextLastName.getText().toString());
        editor.putString("email", editTextEmail.getText().toString());
        editor.putString("phone_number", editTextPhoneNumber.getText().toString());
        editor.putString("address", editTextAddress.getText().toString());
        editor.apply();

        // Update navigation header after saving
        textViewUsername.setText(editTextFirstName.getText().toString() + " " + editTextLastName.getText().toString());
        textViewEmailNavHeader.setText(editTextEmail.getText().toString());
    }
}
