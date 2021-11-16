package com.example.eportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.Map;

public class PersonalInformation extends AppCompatActivity {
    TextView pname,Pphonenumber,pgender,Taddress,
            Paddress,Fathername,mothername,
            Grandfathername,marriedstatus,edulevel,occupation,
            NIC,bloodgroup;
    String phonenumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        Intent intent = getIntent();
        phonenumber = intent.getStringExtra("number");
        pname = findViewById(R.id.Pname);
        Pphonenumber = findViewById(R.id.PphoneNumber);
        pgender=findViewById(R.id.Pgender);
        Taddress = findViewById(R.id.Taddress);
        Paddress = findViewById(R.id.Paddress);
        Fathername = findViewById(R.id.Fathername);
        mothername = findViewById(R.id.mothername);
        Grandfathername = findViewById(R.id.Grandfathername);
        marriedstatus = findViewById(R.id.marriedstatus);
        edulevel = findViewById(R.id.edulevel);
        occupation = findViewById(R.id.occupation);
        NIC = findViewById(R.id.citizenship);
        bloodgroup = findViewById(R.id.bloodgroup);
        getData();
    }

    private void getData(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()){
                    if (snap.getKey().equals(phonenumber)){
                        Map map = (Map)snap.getValue();
                        pname.setText(map.get("Name").toString());
                        Pphonenumber.setText(map.get("phone").toString());
                        pgender.setText(map.get("Gender").toString());
                        Taddress.setText(map.get("Temporary_Address").toString());
                        Paddress.setText(map.get("Permanent_Address").toString());
                        Fathername.setText(map.get("Father’s Name").toString());
                        mothername.setText(map.get("Mother’s Name").toString());
                        Grandfathername.setText(map.get("Grandfather’s Name").toString());
                        marriedstatus.setText(map.get("Married Status").toString());
                        edulevel.setText(map.get("Education Level").toString());
                        occupation.setText(map.get("Occupation").toString());
                        NIC.setText(map.get("NIC").toString());
                        bloodgroup.setText(map.get("Blood Group").toString());
                        break;
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });
    }

}