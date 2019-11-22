package com.example.labinformatika;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ThreadMateri extends AppCompatActivity {
    ArrayList<ModelMateri> data_materi = new ArrayList<ModelMateri>();
    ListView listview;
    ListMateriPraktikum adapter;

    ProgressDialog loading;

    String id_modul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_materi);

        id_modul = getIntent().getStringExtra("id_modul");

//        Toast.makeText(ThreadMateri.this, id_modul + " ===", Toast.LENGTH_SHORT).show();

        listview = (ListView) findViewById(R.id.list_materi);
        listview.setDividerHeight(0);

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        loading = ProgressDialog.show(ThreadMateri.this, null, "Please wait...", true, false);
        Call<List<ModelMateri>> call = service.getThreadMateri(id_modul);
        call.enqueue(new Callback<List<ModelMateri>>() {
            @Override
            public void onResponse(Call<List<ModelMateri>> call, Response<List<ModelMateri>> response) {

                data_materi.clear();
                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelMateri data = new ModelMateri(
                                response.body().get(i).getNamaMateri(),
                                response.body().get(i).getIdM()
                        );

                        data_materi.add(data);

                    }
                    adapter = new ListMateriPraktikum(ThreadMateri.this, R.layout.item_thread_materi, data_materi);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1) {

                        Toast.makeText(ThreadMateri.this, "ZONK !!!!!!1", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ModelMateri>> call, Throwable t) {

                Toast.makeText(ThreadMateri.this, "Error Retrive Data from Server!!!\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
                     data_materi.clear();
                      adapter = new ListMateriPraktikum(ThreadMateri.this, R.layout.item_thread_materi, data_materi);
//        adapter.clear();
                 listview.setAdapter(adapter);
                setup();
    }
}