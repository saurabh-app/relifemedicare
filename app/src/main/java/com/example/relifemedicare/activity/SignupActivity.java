package com.example.relifemedicare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.relifemedicare.R;
import com.example.relifemedicare.database.DBHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText nametextinput,emailtextinput,phonetextinput,passwordtextinput;
    private TextInputLayout nametextinputlayout,emailtextinputlayout,phonetextinputlayout,passwordtextinputlayout;
Button submitbtn;
    private DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nametextinput=findViewById(R.id.nametextinput);
        emailtextinput=findViewById(R.id.emailtextinput);
        phonetextinput=findViewById(R.id.phonetextinput);
        passwordtextinput=findViewById(R.id.passwordtextinput);

        databaseHelper = new DBHelper(this);

        nametextinputlayout=findViewById(R.id.nametextinputlayout);
        emailtextinputlayout=findViewById(R.id.emailtextinputlayout);
        phonetextinputlayout=findViewById(R.id.phonetextinputlayout);
        passwordtextinputlayout=findViewById(R.id.passwordtextinputlayout);
        submitbtn=findViewById(R.id.submitbtn);
        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  name=nametextinput.getText().toString().trim();
                String  email=emailtextinput.getText().toString().trim();
                String  phone=phonetextinput.getText().toString().trim();
                String  password=passwordtextinput.getText().toString().trim();
                if (name.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please Enter name", Toast.LENGTH_SHORT).show();
                }else if (email.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please Enter email", Toast.LENGTH_SHORT).show();
                }else if (phone.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please Enter phone", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                }else if (databaseHelper.insertContact(name,email,phone,password)) {
                    OnSendHome();


                } else {
                    // Snack Bar to show success message that record is wrong
                    Snackbar.make(view, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();

            }
        }

    private void OnSendHome() {
        ProgressDialog progressBar = new ProgressDialog(SignupActivity.this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(2);//initially progress is 0
        progressBar.setMax(150);//sets the maximum value 100
        progressBar.show();//displays the progress bar
        Intent intent=new Intent(SignupActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        progressBar.dismiss();
    }
    });
    }
}