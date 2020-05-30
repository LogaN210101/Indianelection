package com.example.indianelection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button b;
    Button reset, addEntry;
    DatabaseReference db;
    List<String> people;
    EditText name, uID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.button2);
        b.setOnClickListener(this);
        reset= findViewById(R.id.button);
        reset.setOnClickListener(this);
        db = FirebaseDatabase.getInstance().getReference();
        people=new ArrayList<>();
        name=findViewById(R.id.editText1);
        uID=findViewById(R.id.editText2);
        addEntry=findViewById(R.id.button3);
        addEntry.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == b)
        {
            Intent i= new Intent(MainActivity.this, Main2Activity.class);
            startActivity(i);

        }
        if(v == reset)
        {
            reset_data();
        }
        if(v == addEntry )
        {
            if(name.getText().length()==0)
            {
                Toast.makeText(getApplicationContext(), "Please provide a name", Toast.LENGTH_SHORT).show();
                return;
            }
            if(uID.getText().length()==0)
            {
                Toast.makeText(getApplicationContext(), "Please provide a UID", Toast.LENGTH_SHORT).show();
                return;
            }
            addNew(name.getText().toString(), uID.getText().toString());
        }



    }

    private void addNew(String n, String u) {
        VoterReset vr=new VoterReset(n,0);
        db.child("voterList").child(u).setValue(vr);
        name.setText(null);
        uID.setText(null);
    }

    private void reset_data() {
        ResetData rd = new ResetData(0);
        db.child("EVM").child("A").setValue(rd);
        db.child("EVM").child("B").setValue(rd);
        db.child("EVM").child("C").setValue(rd);
        db.child("EVM").child("D").setValue(rd);


        db.child("voterList").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren())
                    {
                        VoterReset vr = ds.getValue(VoterReset.class);
                        people.add(ds.getKey()+"$"+vr.name);

                    }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }

            });

        for(String p: people)
        {
            String t=p;
            String ID = p.substring(0,p.indexOf('$'));
            p= p.substring(p.indexOf('$')+1);
            VoterReset vt = new VoterReset(p,0);
            db.child("voterList").child(ID).setValue(vt);
            Toast.makeText(getApplicationContext(), t, Toast.LENGTH_SHORT).show();
        }

    }
}
