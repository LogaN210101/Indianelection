package com.example.indianelection;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main2Activity extends AppCompatActivity {
    TextView result;
    DatabaseReference db;
    String s="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        result=findViewById(R.id.textView);
        db = FirebaseDatabase.getInstance().getReference();
        db.child("EVM").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                s="";
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    try {
                        ResetData rd = ds.getValue(ResetData.class);
                        s=s + ds.getKey()+"\t\t=\t\t"+rd.count+"\n";
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();


                }
                result.setText(s);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
