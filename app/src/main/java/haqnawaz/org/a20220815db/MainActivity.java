package haqnawaz.org.a20220815db;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonAdd, buttonViewAll, buttonUpdate, buttonDel;
    EditText editName, editRollNumber;
    Switch switchIsActive;
    ListView listViewStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAdd = findViewById(R.id.buttonAdd);
        buttonViewAll = findViewById(R.id.buttonViewAll);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDel = findViewById(R.id.buttonDel);
        editName = findViewById(R.id.editTextName);
        editRollNumber = findViewById(R.id.editTextRollNumber);
        switchIsActive = findViewById(R.id.switchStudent);
        listViewStudent = findViewById(R.id.listViewStudent);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            StudentModel studentModel;

            @Override
            public void onClick(View v) {
                try {
                    studentModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                    //Toast.makeText(MainActivity.this, studentModel.toString(), Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                dbHelper.addStudent(studentModel);
            }
        });

        buttonViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(MainActivity.this);
                List<StudentModel> list = dbHelper.getAllStudents();
                ArrayAdapter arrayAdapter = new ArrayAdapter<StudentModel>
                        (MainActivity.this, android.R.layout.simple_list_item_1,list);
                listViewStudent.setAdapter(arrayAdapter);

            }
        });

        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "You Selected " + (position+1), Toast.LENGTH_SHORT).show();
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                Toast.makeText(MainActivity.this, "ID " + dbHelper.getID(position+1), Toast.LENGTH_SHORT).show();
                buttonUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StudentModel STUDENTModel;
                        STUDENTModel = new StudentModel(editName.getText().toString(), Integer.parseInt(editRollNumber.getText().toString()), switchIsActive.isChecked());
                        DBHelper dbHelper  = new DBHelper(MainActivity.this);
                        Toast.makeText(MainActivity.this, "ID (Update) " + dbHelper.getID(position+1), Toast.LENGTH_SHORT).show();
                        dbHelper.updateStudent(STUDENTModel,dbHelper.getID(position+1));
                    }
                });
            }
        });
        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "You Selected " + (position+1), Toast.LENGTH_SHORT).show();
                DBHelper dbHelper  = new DBHelper(MainActivity.this);
                Toast.makeText(MainActivity.this, "ID " + dbHelper.getID(position+1), Toast.LENGTH_SHORT).show();
                buttonDel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DBHelper dbHelper  = new DBHelper(MainActivity.this);
                        dbHelper.deleteStudent(dbHelper.getID(position+1));
                    }
                });
            }
        });


    }
}