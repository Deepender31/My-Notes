package com.example.mynotes;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
        Notes db;
        Button add_data;
        EditText add_name;
        ArrayList<String> listItem;
        ArrayAdapter adapter;
        ListView List1;
        Button delete_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db= new Notes(this);

        listItem =new ArrayList<>();

        delete_data=findViewById(R.id.delete_data);
        add_data=findViewById(R.id.add_data);
        add_name=findViewById(R.id.add_name);
        List1=findViewById(R.id.list1);
         viewData();
         List1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                 String text = List1.getItemAtPosition(i).toString();
                 Toast.makeText(MainActivity.this, ""+text,Toast.LENGTH_SHORT).show();
             }
         });

        add_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = add_name.getText().toString();
                if(!name.equals("") && db.insertData(name)){
                    Toast.makeText(MainActivity.this,"Notes Entered",Toast.LENGTH_SHORT).show();
                    add_name.setText("");
                    listItem.clear();
                    viewData();
                }else{
                    Toast.makeText(MainActivity.this,"Notes Not Entered",Toast.LENGTH_SHORT).show();
                }

            }
        });
        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(db.deletedata()){
                    Toast.makeText(MainActivity.this,"Notes Deleted",Toast.LENGTH_SHORT).show();
                    viewData();
                    listItem.clear();
                }else{
                    Toast.makeText(MainActivity.this,"Noting To Delete",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void viewData() {
        Cursor cursor =db.viewdata();
        if(cursor.getCount() ==0){
            Toast.makeText(this,"Nothing To show",Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                listItem.add(cursor.getString(1));
            }
            adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
            List1.setAdapter(adapter);
        }
    }
}