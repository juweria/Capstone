package com.example.fatim.makeawish;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity {


    EditText emailControl;
    EditText passwordControl;
    Button log_inControl;
    Button create_accountControl;
    FirebaseAuth firebaseAuth;
    private StorageReference mStorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mStorageRef = FirebaseStorage.getInstance().getReference();

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Button btn_login_login_button =(Button) findViewById(R.id.login_login_button);
        Button btn_login_create_new_account_button =(Button) findViewById(R.id.login_create_new_account_button);

        //Initialize controls
        emailControl=(EditText)findViewById(R.id.login_email_editText);
        passwordControl=(EditText)findViewById(R.id.login_password_editText);
        log_inControl=(Button)findViewById(R.id.login_login_button);
        create_accountControl=(Button)findViewById(R.id.login_create_new_account_button);
        firebaseAuth = FirebaseAuth.getInstance();



        log_inControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()) {
                    final String email = emailControl.getText().toString().trim();
                    final String password_ = passwordControl.getText().toString().trim();

                    firebaseAuth.signInWithEmailAndPassword(email, password_).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this,Profile.class));
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
        create_accountControl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateAccount.class));
            }
        });

        btn_login_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//NOTE: after validation
                startActivity(new Intent(MainActivity.this,Profile.class));
              //  startActivity(new Intent(MainActivity.this,UploadImage.class));

            }
        });

        btn_login_create_new_account_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateAccount.class));

            }
        });
    }

    public boolean validate(){
        if(emailControl.getText().toString().isEmpty() || passwordControl.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            return true;
        }
    }}
//}
//    Uri file = Uri.fromFile(new File("path/to/images/rivers.jpg"));
//    StorageReference riversRef = storageRef.child("images/rivers.jpg");
//
//riversRef.putFile(file)
//        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//@Override
//public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//        // Get a URL to the uploaded content
//        Uri downloadUrl = taskSnapshot.getDownloadUrl();
//        }
//        })
//        .addOnFailureListener(new OnFailureListener() {
//@Override
//public void onFailure(@NonNull Exception exception) {
//        // Handle unsuccessful uploads
//        // ...
//        }
//        });