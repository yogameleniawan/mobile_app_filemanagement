package com.example.managementstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class FolderReadActivity extends AppCompatActivity {
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    EditText folderName;
    Button btnDelete, btnRename;
    File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_read);
        folderName = findViewById(R.id.txt_foldername);
        btnDelete = findViewById(R.id.btn_delete);
        btnRename = findViewById(R.id.btn_rename);
        String file = getIntent().getStringExtra("fileName");
        folderName.setText(file);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFile();
            }
        });

        btnRename.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                renameFile(file,folderName.getText().toString());
            }
        });
    }

    public void deleteFile(){
        String fileName = getIntent().getStringExtra("fileName");
        File file = new File(folder, fileName);
        file.delete();
        if(file.exists()){
            file.delete();
        }
        Toast.makeText(this, "File " + fileName + " deleted from Download", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void renameFile(String oldName, String newName){
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        if(dir.exists()){
            File from = new File(dir,oldName);
            File to = new File(dir,newName);
            if(from.exists())
                from.renameTo(to);
            Toast.makeText(this, "File " + oldName + " was Renamed to " + newName, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }


}