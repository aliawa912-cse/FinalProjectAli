package aliawad.finalprojectali;

import aliawad.finalprojectali.Data.ThingsAdapter;
import aliawad.finalprojectali.Data.Thing;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fbAdd;
    private ListView lstV;
    private SearchView svSearch;
    private ThingsAdapter thingsAdapter;

    @SuppressLint("WrongViewCast")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        thingsAdapter=new ThingsAdapter(getApplicationContext());
        lstV = findViewById(R.id.lstV);
        lstV.setAdapter(thingsAdapter);
        fbAdd = findViewById(R.id.fbAdd);
        svSearch = findViewById(R.id.svSeach);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, SigninActivity.class));
        }
        fbAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        //6 search: delete method calling
        // readTasksFromFirebase("");
    }
    //4 search: add parameter toi search
    public void readTasksFromFirebase(final String stTosearch) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();//to connect to database
        FirebaseAuth auth = FirebaseAuth.getInstance();//to get current UID
        String uid = auth.getUid();
        DatabaseReference reference = database.getReference();
        //orderByChild("title").equalTo(stTosearch)// 5+6
        reference.child("tasks");
        reference.child(uid);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                thingsAdapter.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    Thing thing = d.getValue(Thing.class);
                    Log.d("MYTASK", thing.toString());
                    //5 search:
                    if (stTosearch == null || stTosearch.length() == 0) {
                        thingsAdapter.add(thing);
                    } else //6 search:
                        if (thing.getTitle().contains(stTosearch)) {
                            thingsAdapter.add(thing);

                        }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}