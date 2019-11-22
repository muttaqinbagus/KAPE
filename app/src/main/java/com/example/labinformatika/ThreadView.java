package com.example.labinformatika;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class ThreadView extends AppCompatActivity {

    ArrayList<ModelThread> data_thread = new ArrayList<ModelThread>();
    ListView listview;
    ListThreadView adapter;

    ProgressDialog loading;

    String id_materi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_view_thread);

        id_materi = getIntent().getStringExtra("id_materi");

//        Toast.makeText(ThreadView.this, id_materi + " masukk boss", Toast.LENGTH_SHORT).show();
//        Toast.makeText(ThreadView.this, data_thread + "\n JSON", Toast.LENGTH_SHORT).show();

        listview = (ListView) findViewById(R.id.list_view_thread);
        listview.setDividerHeight(0);

    }

    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        loading = ProgressDialog.show(ThreadView.this, null, "Please wait...", true, false);
        Call<List<ModelThread>> call = service.getThreadView(id_materi);
        call.enqueue(new Callback<List<ModelThread>>() {
            @Override
            public void onResponse(Call<List<ModelThread>> call, Response<List<ModelThread>> response) {

                data_thread.clear();
                if (response.isSuccessful()) {
                    int jumlah = response.body().size();

                    for (int i = 0; i < jumlah; i++) {

                        ModelThread data = new ModelThread(
                                response.body().get(i).getIdThread(),
                                response.body().get(i).getJudul(),
                                response.body().get(i).getKeterangan(),
                                response.body().get(i).getNamaMahasiswa()

                        );

                        data_thread.add(data);

                    }
                    adapter = new ListThreadView(ThreadView.this, R.layout.item_view_thread, data_thread);
                    listview.setAdapter(adapter);
                    if (adapter.getCount() < 1) {

                        Toast.makeText(ThreadView.this, "ZONK !!!!!!1", Toast.LENGTH_SHORT).show();
                        loading.dismiss();

                    }
                    loading.dismiss();
                }
            }


            @Override
            public void onFailure(Call<List<ModelThread>> call, Throwable t) {

                Toast.makeText(ThreadView.this, "Error Retrive Data from Server!!!\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
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
        data_thread.clear();
        adapter = new ListThreadView(ThreadView.this, R.layout.item_view_thread, data_thread);
//        adapter.clear();
        listview.setAdapter(adapter);
        setup();
    }
}
