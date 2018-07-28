package com.example.nasir.database;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatabaseActivity extends AppCompatActivity {

    DbHelper myDB;

    EditText names,address,marks,id;
    Button btnAdd, btnView,btnUpdate,btnDelete;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        myDB = new DbHelper(this);

        names=(EditText)findViewById(R.id.nameET);
        address=(EditText)findViewById(R.id.addressET);
        marks= (EditText)findViewById(R.id.marksET);
        id=(EditText)findViewById(R.id.idET);
        btnAdd=(Button)findViewById(R.id.btnAdd);

        btnView = (Button)findViewById(R.id.btnViewAll);
        btnUpdate=(Button)findViewById(R.id.btnUpdate);
        btnDelete=(Button)findViewById(R.id.btnDelete);

        addData();
        viewAll();
        updateData();
        deleteData();

    }

    public void addData(){

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                boolean isInserted= myDB.insertData(names.getText().toString(),address.getText().toString(),marks.getText().toString());

                if (isInserted==true){
                    Toast.makeText(DatabaseActivity.this,"Data is Inserted",Toast.LENGTH_LONG).show();
                    EditText[] editTexts = {names,address,marks,id};

                    for (EditText et:editTexts
                            ) {
                        et.setText("");
                    }

                }else {
                    Toast.makeText(DatabaseActivity.this,"Data is not Inserted",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void viewAll(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
               Cursor res= myDB.getAllData();

               if (res.getCount()==0){
                   showMessages("Error","Nothing is found");

                   return;
               }else {

                   StringBuffer buffer = new StringBuffer();
                   while (res.moveToNext()){
                       buffer.append("id :"+res.getString(0)+"\n");
                       buffer.append("Name"+res.getString(1)+"\n");
                       buffer.append("Address:"+res.getString(2)+"\n");
                       buffer.append("Marks : "+res.getString(3)+"\n");
                   }

                   //show all

                   showMessages("Data",buffer.toString());
               }
            }
        });
    }
    public void updateData(){

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                boolean isUpdated=myDB.updateData(id.getText().toString(),
                        names.getText().toString(),address.getText().toString(),marks.getText().toString());

                if (isUpdated==true){
                    Toast.makeText(DatabaseActivity.this,"Data is updated",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(DatabaseActivity.this,"Data is not update",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void deleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {

                Integer deletedRows=myDB.deleteData(id.getText().toString());

                if (deletedRows>0){
                    Toast.makeText(DatabaseActivity.this,"Data is Deleted",Toast.LENGTH_LONG).show();
                    id.setText("");

                }else {
                    Toast.makeText(DatabaseActivity.this,"Data is not Deleted",Toast.LENGTH_LONG).show();

                }
            }
        });
    }
    public void showMessages(String title, String message){

        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
