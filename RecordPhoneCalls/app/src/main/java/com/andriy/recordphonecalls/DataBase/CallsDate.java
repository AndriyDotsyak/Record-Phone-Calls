package com.andriy.recordphonecalls.DataBase;

import android.annotation.SuppressLint;

import com.andriy.recordphonecalls.Stopwatch;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CallsDate {

    private DatabaseReference reference;

    public CallsDate() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        reference = database.getReference("Calls");
    }

    public void addItem() {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

        DatabaseReference ref = reference.push();
        ref.setValue(new ObjectData.Data(sdf.format(new Date()), Stopwatch.time));
    }
}
