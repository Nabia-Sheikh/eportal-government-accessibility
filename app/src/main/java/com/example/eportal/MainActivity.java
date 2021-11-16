package com.example.eportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity {
    EditText phoneNumber;
    Button getOtp;
    ProgressBar progressBarGetOtp;
    public String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phoneNumber = findViewById(R.id.phoneNumber);
        getOtp = findViewById(R.id.getOtp);
        progressBarGetOtp = findViewById(R.id.progressBarGetOtp);
        getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneNumber.getText().toString().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please Enter Ph. Number", Toast.LENGTH_SHORT).show();

                } else if (phoneNumber.getText().toString().length() == 10) {
                    progressBarGetOtp.setVisibility(View.VISIBLE);
//
                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            "+92"+phoneNumber.getText().toString(),
                            60,
                            TimeUnit.SECONDS,
                            MainActivity.this,
                            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                    progressBarGetOtp.setVisibility(View.GONE);

                                }

                                @Override
                                public void onVerificationFailed(@NonNull FirebaseException e) {
                                    progressBarGetOtp.setVisibility(View.GONE);
                                    Log.e("error" , e.getMessage());
                                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onCodeSent(@NonNull String otp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                    progressBarGetOtp.setVisibility(View.GONE);
                                    Intent movetoEnterOtp = new Intent(MainActivity.this, EnterOtp.class);
                                    movetoEnterOtp.putExtra("message", "Hello");
                                    movetoEnterOtp.putExtra("phone", phoneNumber.getText().toString());
                                    movetoEnterOtp.putExtra("otp",otp);
                                    startActivity(movetoEnterOtp);

                                }
                            }
                    );

                } else {
                    progressBarGetOtp.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Incomplete Phone Number", Toast.LENGTH_SHORT).show();
                    progressBarGetOtp.setVisibility(View.GONE);
                }
            }
        });


    }


}