package com.mahbuba.foodappnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Rider_In extends AppCompatActivity {
    EditText secuRIDER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_in);
        secuRIDER=findViewById(R.id.editRIDER);
    }

    public void securityrider(View view) {
        String securityy = secuRIDER.getText().toString().trim();

        if (TextUtils.isEmpty(securityy)) {
            secuRIDER.setError("Please enter the security code ...");
            return;
        }

        if(secuRIDER.getText().toString().equals("rider") ){
            Intent intent=new Intent(Rider_In.this,RiderActivity.class);
            startActivity(intent);
        }
        else{

            Toast.makeText( Rider_In.this,"Security code is not verified !!!", Toast.LENGTH_SHORT).show();
        }
    }
}