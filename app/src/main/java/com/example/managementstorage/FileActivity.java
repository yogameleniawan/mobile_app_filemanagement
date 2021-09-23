package com.example.managementstorage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import android.widget.ArrayAdapter;

public class FileActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    Button btnCreateFile, btnCreateFolder;
    private String[] listOfFiles;
    private ArrayList<String> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        listOfFiles = Environment.getExternalStoragePublicDirectory (Environment.DIRECTORY_DOWNLOADS).list();

        ListView listView = findViewById(R.id.list);
        data = new ArrayList<>();
        getData();
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.list_style, data);
        listView.setAdapter(adapter);

        btnCreateFile = findViewById(R.id.btn_create);
        btnCreateFolder = findViewById(R.id.btn_create_folder);

        btnCreateFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FileActivity.this, FileCreateActivity.class);
                startActivity(i);
            }
        });

        btnCreateFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FileActivity.this, FolderCreateActivity.class);
                startActivity(i);
            }
        });

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView adapterView, View view, int position, long l) {
        String file = data.get(position);
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File filePath = new File(folder+File.separator+file);
        if(filePath.isFile()){
            Intent i = new Intent(FileActivity.this, FileReadActivity.class);
            i.putExtra("fileName", file);
            startActivity(i);
        }else if(filePath.isDirectory()){
            Intent i = new Intent(FileActivity.this, FolderReadActivity.class);
            i.putExtra("fileName", file);
            startActivity(i);
        }

    }

    private void getData(){
        Collections.addAll(data, listOfFiles);
    }
}