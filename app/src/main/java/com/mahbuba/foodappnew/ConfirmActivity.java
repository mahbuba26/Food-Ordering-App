package com.mahbuba.foodappnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class ConfirmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

    }

    public void submit(View view) {

        Toast.makeText(ConfirmActivity.this,"Your orders have been submitted . It will reach within half an hour :-)",Toast.LENGTH_SHORT).show();

    }
}