package com.mahbuba.foodappnew;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {
EditText secu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        secu=findViewById(R.id.edit);
    }

    public void security(View view) {
        String securityy = secu.getText().toString().trim();

        if (TextUtils.isEmpty(securityy)) {
           secu.setError("Please enter the security code ...");
            return;
        }

            if(secu.getText().toString().equals("admin") ){
                Intent intent=new Intent(AboutUs.this,OptionActivity.class);
                startActivity(intent);
            }
            else{

                Toast.makeText( AboutUs.this,"Security code is not verified !!!", Toast.LENGTH_SHORT).show();
            }
    }
}