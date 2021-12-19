package com.example.relifemedicare.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.relifemedicare.R;
import com.example.relifemedicare.database.DBHelper;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {
//    @BindView(R.id.loginbtn)
    Button loginbtn,signup;
    TextInputLayout passtextinputlayout;
    TextInputLayout usertextinputlayout;
    TextInputEditText usertextinput;
    TextInputEditText passtextinput;

    private DBHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
        databaseHelper = new DBHelper(this);
        usertextinput=findViewById(R.id.usertextinput);
        passtextinput=findViewById(R.id.passtextinput);
        usertextinputlayout=findViewById(R.id.usertextinputlayout);
        passtextinputlayout=findViewById(R.id.passtextinputlayout);
        signup=findViewById(R.id.signup);
        loginbtn=findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              String  username=usertextinput.getText().toString().trim();
              String  password=passtextinput.getText().toString().trim();
                if (username.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Username", Toast.LENGTH_SHORT).show();
                }else if (password.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }else if (databaseHelper.checkUser(username,password)) {
                    OnSendHome();


            } else {
                // Snack Bar to show success message that record is wrong
                Snackbar.make(view, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
            }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SignupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void OnSendHome() {
        ProgressDialog progressBar = new ProgressDialog(MainActivity.this);
        progressBar.setCancelable(true);//you can cancel it by pressing back button
        progressBar.setMessage("downloading ...");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(2);//initially progress is 0
        progressBar.setMax(150);//sets the maximum value 100
        progressBar.show();//displays the progress bar
        Intent i=new Intent(MainActivity.this,HomeActivity.class);
        startActivity(i);
        progressBar.dismiss();

    }

//    @OnClick(R.id.usertextinput)
//    public void
//
//    @OnClick(R.id.loginbtn)
//    public void onButtonClick(View view) {
////        Toast.makeText(getApplicationContext(), "You have entered: " + inputName.getText().toString(),
////                Toast.LENGTH_SHORT).show();
//    }
}