package com.example.labinformatika;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThreadPraktikum extends AppCompatActivity {


    ArrayList<ModelPraktikum> data_praktikum = new ArrayList<ModelPraktikum>();
    ListView listview;
    ListThreadPraktikum adapter;

//    LinearLayout layout_loading;
//    TextView text_load;
//    ImageView icon_load;

    ProgressDialog loading;
    //EditText textnomer;
//    TextView aid;
    String sp_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_praktikum);

        SharedPreferences sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
        sp_username = sharedPreferences.getString("kirim_username", "");

        listview = (ListView) findViewById(R.id.list_thread_praktikum);
        listview.setDividerHeight(0);

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        loading = ProgressDialog.show(ThreadPraktikum.this, null, "Please wait...", true, false);
        Call<List<ModelPraktikum>> call = service.getThreadPraktikum(sp_username);
        call.enqueue(new Callback<List<ModelPraktikum>>() {
            @Override
            public void onResponse(Call<List<ModelPraktikum>> call, Response<List<ModelPraktikum>> response) {

                data_praktikum.clear();
                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelPraktikum data = new ModelPraktikum(
                                response.body().get(i).getNamaPraktikum(),
                                response.body().get(i).getidPraktikum()
                        );

                        data_praktikum.add(data);

                    }
                    adapter = new ListThreadPraktikum(ThreadPraktikum.this, R.layout.item_thread_praktikum, data_praktikum);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {

                        Toast.makeText(ThreadPraktikum.this, "ZONK KAK", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ModelPraktikum>> call, Throwable t) {

                Toast.makeText(ThreadPraktikum.this, "Error Retrive Data from Server!!!\n" + t.getMessage(),Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            adapter.clear();
            setup();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        data_praktikum.clear();
        adapter = new ListThreadPraktikum(ThreadPraktikum.this, R.layout.item_thread_praktikum, data_praktikum);
//        adapter.clear();
        listview.setAdapter(adapter);
        setup();
    }
}
