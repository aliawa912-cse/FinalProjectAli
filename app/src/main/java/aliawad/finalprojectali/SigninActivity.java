package aliawad.finalprojectali;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

/**
 * SigninActivity is the first Screen that opend and in the Screen you Enter the email and Password next it go the the  MainActivty that have a list fot the important things.
 */


public class SigninActivity extends AppCompatActivity {
    private EditText edEmail, edPassWord;
    private Button btnLogIn, btnSignUp;
    private FirebaseAuth auth;


    @Override
    /**
     * in this function the system is check if you was in contact before and if have a sense.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigin_in);
        //CHEKE IF I SIGNED IN BEFORE
        FirebaseAuth auth=FirebaseAuth.getInstance();
        if (auth.getCurrentUser()!=null)
        {
            Intent i=new Intent(getBaseContext(),MainActivity.class);
            finish();
            startActivity(i);
        }

        edEmail = findViewById(R.id.edEmail);
        edPassWord = findViewById(R.id.edPassWord);
        btnLogIn = findViewById(R.id.btnLogIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        //4
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //5
                validateForm();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SigninActivity.this, SignUpActivity.class);
                startActivity(i);

            }
        });
    }

    private void validateForm() {
        String stEmail = edEmail.getText().toString();
        String stPassw = edPassWord.getText().toString();
        boolean isOk = true;
        if(stEmail.length()<5||stEmail.indexOf('@')==0||stEmail.indexOf('@')>stEmail.length()-2||stEmail.indexOf('.')==0||stEmail.indexOf('.')>stEmail.length()-1||stEmail.lastIndexOf(".")<stEmail.indexOf('@') )
        {
            isOk = false;
            edEmail.setError("Wrong Email syntax");
        }
        if(isOk)
        {
            Signin(stEmail, stPassw);
        }

    }

    private void Signin(String stEmail, String stPassw) {
        //  FirebaseDatabase auth=FirebaseDatabase.getInstance();
        auth.signInWithEmailAndPassword(stEmail,stPassw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful())
                {
                    Intent i=new Intent(SigninActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(SigninActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    edEmail.setError(task.getException().getMessage());
                }
            }
        });

    }
}