package com.example.onnlineconsultancy.firebase;

import com.example.onnlineconsultancy.Models.Consultant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseHelper {
    private DatabaseReference databaseReference;

    public FirebaseHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("consultants");
    }

    public void addConsultant(Consultant consultant) {
        String key = databaseReference.push().getKey();
        if (key != null) {
            databaseReference.child(key).setValue(consultant);
        }
    }

    public void getConsultants(final DataStatus dataStatus) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Consultant> consultants = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Consultant consultant = snapshot.getValue(Consultant.class);
                    consultants.add(consultant);
                }
                dataStatus.DataIsLoaded(consultants);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dataStatus.DataLoadFailed(databaseError);
            }
        });
    }

    public interface DataStatus {
        void DataIsLoaded(List<Consultant> consultants);
        void DataLoadFailed(DatabaseError databaseError);
    }
}
