package com.example.eportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.IntentCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eportal.User.MapsActivity;
import com.example.eportal.User.MapsFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class HomePage extends AppCompatActivity {
    String phonenumber;
    CardView personalInfo , hospitals , Police ;
    TextView userphoneNumber, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        userphoneNumber = findViewById(R.id.userphoneNumber);
        Intent intent = getIntent();
        phonenumber = intent.getStringExtra("phone");
        userphoneNumber.setText(phonenumber);
        username = findViewById(R.id.username);

        personalInfo = findViewById(R.id.personalInfo);
        hospitals = findViewById(R.id.hospitals);
        Police = findViewById(R.id.Police);

        personalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage.this,PersonalInformation.class);
                intent.putExtra("number", phonenumber);
                startActivity(intent);
            }
        });

        hospitals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.eportal.User.MapsActivity.class);
                startActivity(intent);
            }
        });

        Police.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), com.example.eportal.User.MapsActivity.class);
//                intent.putExtra("police","police");
//                MapsFragment mp = new MapsFragment();
//                mp.getPlaceTypeURL(mp.userLoc[0], "police" );
                startActivity(intent);

            }
        });
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
                        username.setText(map.get("Name").toString());
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

    public void signOutt(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i = new Intent(HomePage.this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();

    }

    public void taxpay(View view) {
        Intent i = new Intent("android.intent.action.VIEW" , Uri.parse("https://fbr.gov.pk/"));
        startActivity(i);
    }

    public void citizenPortal(View view) {
        Intent i = new Intent("android.intent.action.VIEW" , Uri.parse("https://play.google.com/store/apps/details?id=com.govpk.citizensportal&hl=en&gl=US"));
        startActivity(i);
    }

    public void HEC(View view) {
        Intent i = new Intent("android.intent.action.VIEW" , Uri.parse("https://hec.gov.pk/"));
        startActivity(i);
    }

    public void kwsb(View view) {
        Intent i = new Intent("android.intent.action.VIEW" , Uri.parse("https://www.kwsb.gos.pk/"));
        startActivity(i);
    }
}


