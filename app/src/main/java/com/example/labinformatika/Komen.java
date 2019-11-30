package com.example.labinformatika;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Komen extends AppCompatActivity {
    ArrayList<ModelKomen> data_komen = new ArrayList<ModelKomen>();
    ListView listview;
    ListKomen adapter;

    ProgressDialog loading;

    String id_thread;
    Button btn_kirim;
    TextView  NAMA, KOMEN;
    EditText inputkomen;

    int user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.komen);

        id_thread = getIntent().getStringExtra("id_thread");

        SharedPreferences sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
        user_id = sharedPreferences.getInt("kirim_id", 0);

        NAMA = (TextView) findViewById(R.id.detail_nama);
        KOMEN = (TextView) findViewById(R.id.detail_komen);
        inputkomen = (EditText) findViewById(R.id.textkomen);

        Toast.makeText(Komen.this, id_thread + " CEK DATA", Toast.LENGTH_SHORT).show();

//        detailThread();

        listview = (ListView) findViewById(R.id.list_Detail_komen);
        listview.setDividerHeight(0);

        btn_kirim = (Button) findViewById(R.id.btnkirim);
        btn_kirim.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                kirim();

            }
        });

    }
    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        loading = ProgressDialog.show(Komen.this, null, "Please wait...", true, false);
        Call<List<ModelKomen>> call = service.getKomen(id_thread);
        call.enqueue(new Callback<List<ModelKomen>>() {
            @Override
            public void onResponse(Call<List<ModelKomen>> call, Response<List<ModelKomen>> response) {

                data_komen.clear();
                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelKomen data = new ModelKomen(
                                response.body().get(i).getNama(),
                                response.body().get(i).getComment(),
                                response.body().get(i).getCreated_at(),
                                response.body().get(i).getUsername()
                        );

                        data_komen.add(data);

                    }
                    adapter = new ListKomen(Komen.this, R.layout.item_komen, data_komen);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1) {

                        Toast.makeText(Komen.this, "ZONK !!!!!!1", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }
                    loading.dismiss();
                }
            }
            @Override
            public void onFailure(Call<List<ModelKomen>> call, Throwable t) {

                Toast.makeText(Komen.this, "Error Retrive Data from Server!!!\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }


    public void kirim() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

//        loading = ProgressDialog.show(Komen.this, null, "Please wait...", true, false);
        Call<ModelKomen> call = service.addComent(inputkomen.getText().toString().trim(), String.valueOf(user_id), id_thread);
        call.enqueue(new Callback<ModelKomen>() {
            @Override
            public void onResponse(Call<ModelKomen> call, Response<ModelKomen> response) {
                    setup();
                    inputkomen.setText("");
//                    loading.dismiss();
            }

            @Override
            public void onFailure(Call<ModelKomen> call, Throwable t) {
                Toast.makeText(Komen.this, "Error Retrive Data from Server!!! \n" + t.getMessage() , Toast.LENGTH_LONG).show();
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
        data_komen.clear();
        adapter = new ListKomen(Komen.this, R.layout.item_komen, data_komen);
//        adapter.clear();
        listview.setAdapter(adapter);
        setup();
    }
}
