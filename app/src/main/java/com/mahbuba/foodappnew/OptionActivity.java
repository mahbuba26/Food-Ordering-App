package com.mahbuba.foodappnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
    }

    public void add(View view) {

        Intent intent=new Intent(OptionActivity.this,ExistingAddItem.class);
        startActivity(intent);
    }

    public void history(View view) {
        Intent intent=new Intent(OptionActivity.this,ActivityHonda.class);
        startActivity(intent);
    }
}