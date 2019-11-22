package com.example.labinformatika;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class ThreadModul extends AppCompatActivity {

    ArrayList<ModelModul> data_modul = new ArrayList<ModelModul>();
    ListView listview;
    ListModulPraktikum adapter;

    ProgressDialog loading;

    String id_praktikum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_modul_praktikum);

        id_praktikum = getIntent().getStringExtra("id_praktikum");

//        Toast.makeText(ThreadModul.this, id_praktikum + " ===", Toast.LENGTH_SHORT).show();

        listview = (ListView) findViewById(R.id.list);
        listview.setDividerHeight(0);

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        loading = ProgressDialog.show(ThreadModul.this, null, "Please wait...", true, false);
        Call<List<ModelModul>> call = service.getThreadModul(id_praktikum);
        call.enqueue(new Callback<List<ModelModul>>() {
            @Override
            public void onResponse(Call<List<ModelModul>> call, Response<List<ModelModul>> response) {

                data_modul.clear();
                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelModul data = new ModelModul(
                                response.body().get(i).getNamaModul(),
                                response.body().get(i).getIdModul()
                        );

                        data_modul.add(data);

                    }
                    adapter = new ListModulPraktikum(ThreadModul.this, R.layout.item_thread_modul, data_modul);
                    listview.setAdapter(adapter);

                    if (adapter.getCount() < 1 ) {

                        Toast.makeText(ThreadModul.this, "ZONK gaes", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<List<ModelModul>> call, Throwable t) {

                Toast.makeText(ThreadModul.this, "Error Retrive Data from Server!!!\n" + t.getMessage(),Toast.LENGTH_SHORT).show();
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
        data_modul.clear();
        adapter = new ListModulPraktikum(ThreadModul.this, R.layout.item_thread_modul, data_modul);
//        adapter.clear();
        listview.setAdapter(adapter);
        setup();
    }
}
