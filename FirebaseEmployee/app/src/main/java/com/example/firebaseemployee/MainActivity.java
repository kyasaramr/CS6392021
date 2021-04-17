package com.example.firebaseemployee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private Button btnSubmit;
    private EditText FName;
    private EditText LName;
    private TextView tvEmployee;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Variable Declaration
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        Log.i("MainActivity",database.toString());

        btnSubmit = findViewById(R.id.btnSubmit);
        FName = (EditText)findViewById(R.id.etFirstName);
        LName =  (EditText)findViewById(R.id.etLastName);
        tvEmployee = (TextView) findViewById(R.id.tvEmp);

        DatabaseReference myReference = database.getReference("employees");

        // Variable Declaration End

        //Submit Button Listener
        btnSubmit.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                String firstName = FName.getText().toString();
                String lastName = LName.getText().toString();

                if (firstName.isEmpty()) FName.setError("Please enter the first Name");
                else if (lastName.isEmpty()) LName.setError("Please enter the last Name");
                else  {
                    Employee emp = new Employee(firstName,lastName);
                    myReference.child(String.valueOf(counter)).setValue(emp);
                    FName.setText("");
                    LName.setText("");
                }
            }
        });

        //Adding Employee
        myReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                StringBuilder strEmployee = new StringBuilder();
                Employee employee;
                counter = 0;

                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    employee = ds.getValue(Employee.class);
                    Log.i("MainActivity",counter+" - First name : "+employee.getFirstName()+" Last name : "+employee.getLastName());
                    counter++;
                    strEmployee.append(employee.toString());
                    strEmployee.append("\n" + "\n");
                }

                tvEmployee.setText(strEmployee);
                Log.d("MainActivity", "Counter : " + counter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w("MainActivity", "Failed to read value.", error.toException());
            }
        });

    }
}