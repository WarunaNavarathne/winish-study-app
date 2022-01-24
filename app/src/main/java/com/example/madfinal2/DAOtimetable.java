package com.example.madfinal2;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

import javax.security.auth.Subject;

public class DAOtimetable {
    private DatabaseReference databaseReference;
    public DAOtimetable() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(timetable.class.getSimpleName());
    }
    public Task<Void> add(timetable time){

        return databaseReference.push().setValue(time);
    }

    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
       return databaseReference.child(key).removeValue();
    }

    public Query get(){
            return databaseReference.orderByKey();
    }
}
