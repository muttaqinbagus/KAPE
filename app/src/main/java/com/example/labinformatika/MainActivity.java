package com.example.labinformatika;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String ROOT_URL = "http://192.168.1.9/lab-forum/public/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Pindah(View view) {
        Intent intent = new Intent(MainActivity.this, ModelModul.class);
        startActivity(intent);
    }

    public void Pindah1(View view) {
        Intent intent = new Intent(MainActivity.this, ModelModul.class);
        startActivity(intent);
    }

    public void Pindah2(View view) {
        Intent intent = new Intent(MainActivity.this, ModelModul.class);
        startActivity(intent);
    }

    public void Pindah3(View view) {
        Intent intent = new Intent(MainActivity.this, ModelModul.class);
        startActivity(intent);
    }

    public void Pindah4(View view) {
        Intent intent = new Intent(MainActivity.this, ModelModul.class);
        startActivity(intent);
    }

    public void Pindah5(View view) {
        Intent intent = new Intent(MainActivity.this, ModelModul.class);
        startActivity(intent);
    }
}
