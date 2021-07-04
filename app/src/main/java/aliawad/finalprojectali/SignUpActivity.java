package aliawad.finalprojectali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Signup Activty is if you want to add a new account you put a name,lastname,phone,email,password and you click on save then you have an account.
 */

public class SignUpActivity extends AppCompatActivity {
    private EditText etFirstName,etLastName,etPhone,etEmail2,etPassword,etPassword2;
    private Button btnsave;


    @Override
    /**
     * מעגל החיים ובונה אקתיויתי ועובדת פעם אחת
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        etFirstName=findViewById(R.id .etFirstName);
        etLastName=findViewById(R.id .etLastName);
        etPhone=findViewById(R.id .etPhone);
        etEmail2=findViewById(R.id .etEmail2);
        etPassword=findViewById(R.id .etPassword);
        etPassword2=findViewById(R.id .etPassword2);
        btnsave=findViewById(R.id .btnsave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    validateForm();
            }
        });


    }

    private void validateForm()
    {
        String FirstName = etFirstName.getText().toString();
        String LastName = etLastName.getText().toString();
        String Phone = etPhone.getText().toString();
        String Email = etEmail2.getText().toString();
        String Password = etPassword.getText().toString();
        String Password2 = etPassword2.getText().toString();

        boolean isOk=true;
        if(FirstName.length()<2)
        {
            isOk=false;
            etFirstName.setError("at least to letters");
        }
        if (LastName.length()<2)
        {
            isOk=false;
            etLastName.setError("at least to letters");
        }
        if (Email.length()<5 || Email.indexOf('@')==0 || Email.indexOf('@')>Email.length()-2 || Email.indexOf('.')==0 || Email.indexOf('.')>Email.length()-1 || Email.lastIndexOf(".")<Email.indexOf('@') )
        {
            isOk=false;
            etEmail2.setError("Wrong Email syntax");
        }
        if (Password.equals(Password2)==false)
        {
            isOk=false;
            etPassword2.setError("passwors must be the same");
        }

     if (isOk)//isok==ture
     {
         //todo create acount and return to sign in screen/ close this screen
             createNewAccount (Email,Password,FirstName,LastName,Phone);
     }

    }

    private void createNewAccount(String email, String password, String firstName, String lastName, String phone) {
        FirebaseAuth auth;
        {
            auth = FirebaseAuth.getInstance();
        }
        OnCompleteListener<AuthResult> listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())//// הורשאה הצליחה כמו שצריך
                {
                    Toast.makeText(getApplicationContext(), "Successfully Signiup up", Toast.LENGTH_SHORT).show();
                    //next screen or close this screen
                    Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();//close this screen
                    //                  Intent i=new Intent(getBaseContext(),MainActivity.class);
                    //                  startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Signing up,Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                    etEmail2.setError("Signing up,Failed" + task.getException());
                    /**
                     * GETEXCEPTION Returns the exception that caused the Task to fail.
                     */
                }

            }
        };
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(listener);
        /**
         * בונה אנד שולח איתו ליסנר
         */
    }
}