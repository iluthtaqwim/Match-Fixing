package com.example.iluth.finalprojectpam.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iluth.finalprojectpam.DB.DatabaseHelper;
import com.example.iluth.finalprojectpam.R;

public class EditDataActivity extends AppCompatActivity {
    private Button btnSave , btnDelete;
    private EditText editName;

    DatabaseHelper databaseHelper;

    private String selectedName;
    private int selectedID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnSave = (Button) findViewById(R.id.btnSave);
        editName = (EditText) findViewById(R.id.edit_title);

        databaseHelper = new DatabaseHelper(this);

        //mengambil data dari put extra
        Intent intent = getIntent();
        selectedID = intent.getIntExtra("id",-1);
        selectedName = intent.getStringExtra("title");

        editName.setText(selectedName);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title = editName.getText().toString();

                if (!title.equals(" ")){
                    databaseHelper.updateName(title,selectedID,selectedName);
                    Toast.makeText(EditDataActivity.this,"Data berhasil diubah",Toast.LENGTH_LONG).show();

                }else {
                    Toast.makeText(EditDataActivity.this,"Gagal",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseHelper.deleteTitle(selectedID,selectedName);
                editName.setText("");
                Toast.makeText(EditDataActivity.this,selectedName+" Dihapus dari favorit",Toast.LENGTH_LONG).show();

            }
        });

    }

}