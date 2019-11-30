package com.example.labinformatika;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ThreadDetail extends AppCompatActivity {

    ArrayList<ModelDetailThread> data_thread = new ArrayList<ModelDetailThread>();
    ListView listview;
    ListDetailThread adapter;

    ProgressDialog loading;

    String id_thread;

    TextView JUDULTHREAD, NAMA, KET;
    Button btn_komendosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_thread);

        id_thread = getIntent().getStringExtra("id_thread");

        btn_komendosen = (Button) findViewById(R.id.komendosen);
        btn_komendosen.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThreadDetail.this, Komen.class);
                intent.putExtra("id_thread", id_thread);
                Toast.makeText(ThreadDetail.this, id_thread + " CEK DATAaaaaaaaa", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        JUDULTHREAD = (TextView) findViewById(R.id.detail_judul);
        NAMA = (TextView) findViewById(R.id.detail_nama);
        KET = (TextView) findViewById(R.id.detail_keterangan);

        Toast.makeText(ThreadDetail.this, id_thread + " CEK DATA", Toast.LENGTH_SHORT).show();

        detailThread();

        listview = (ListView) findViewById(R.id.list_Detail_thread);
        listview.setDividerHeight(0);

    }

    public void detailThread() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        loading = ProgressDialog.show(ThreadDetail.this, null, "Please wait...", true, false);
        Call<ModelDetailThread> call = service.getThreadDetail(id_thread);
        call.enqueue(new Callback<ModelDetailThread>() {
            @Override
            public void onResponse(Call<ModelDetailThread> call, Response<ModelDetailThread> response) {

                ModelDetailThread moDetail = response.body();
                assert moDetail != null;

                JUDULTHREAD.setText(moDetail.getJudulThread());
                NAMA.setText("Name : " + moDetail.getNamaMahasiswa() + " // NPM : " + moDetail.getUsername() + " at " + moDetail.getPosted() +  " asked");
                KET.setText(moDetail.getKet());

                loading.dismiss();
            }
            @Override
            public void onFailure(Call<ModelDetailThread> call, Throwable t) {

                Toast.makeText(ThreadDetail.this, "Error Retrive Data from Server!!!\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                loading.dismiss();
            }
        });
    }

    public void setup(){

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
        adapter = new ListDetailThread(ThreadDetail.this, R.layout.detail_thread, data_thread);
//        adapter.clear();
        listview.setAdapter(adapter);
        setup();
    }
}
