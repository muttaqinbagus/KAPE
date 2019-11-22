package com.example.labinformatika;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {

    ArrayList<ModelUser> dataLoginUser = new ArrayList<ModelUser>();

    TextView reg;
    EditText log_ussername, log_password;
    Button btn_login;

//    String em, pas;
//    String USSERNAME, PASSWORD;

    Context ctx = this;

    private CheckBox checkbox;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        log_ussername = (EditText) findViewById(R.id.textusername);
        log_password = (EditText) findViewById(R.id.textpassword);
        btn_login = (Button) findViewById(R.id.btnlogin);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = log_ussername.getText().toString().trim();
                String p = log_password.getText().toString().trim();

                if (e.equals("")) {
                    log_ussername.setError("Masukkan Ussername !!");
                }
                if (p.equals("")) {
                    log_password.setError("Masukkan Password !!");
                }

                if (!e.equals("") && !p.equals("")) {
                    LoginDataUser(log_ussername.getText().toString(), log_password.getText().toString());
//                    Toast.makeText(Login.this, "" + log_ussername.getText().toString() + " | " + log_password.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public void LoginDataUser(final String username, final String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MainActivity.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService service = retrofit.create(ApiService.class);

        loading = ProgressDialog.show(Login.this, null, "Please wait...", true, false);
        Call<ModelUser> call = service.getLoginData(username, password);
        call.enqueue(new Callback<ModelUser>() {
            @Override
            public void onResponse(Call<ModelUser> call, Response<ModelUser> response) {

                ModelUser modus = response.body();
                assert modus != null;

//                Toast.makeText(Login.this, "CEK : " + modus.getStatus(), Toast.LENGTH_SHORT).show();

//                Toast.makeText(Login.this, "" + username + " | " + password, Toast.LENGTH_SHORT).show();

                if (modus.getStatus() == 200)
                {
                    Intent intent = new Intent(Login.this, ThreadPraktikum.class);
//                    intent.putExtra("aidi_user", modus.getId_user());
//                    intent.putExtra("nama_user", modus.getNama_user());
//                    intent.putExtra("nomor_user", modus.getNotelp_user());
                    startActivity(intent);
                    saveData(modus.getId(), modus.getUsername(), modus.getJenis_user());
                    Toast.makeText(Login.this, "Selamat Datang, " + modus.getId() + " !!", Toast.LENGTH_LONG).show();
                    loading.dismiss();
                    finish();
                } else {
                    Toast.makeText(Login.this, "Email / Password salah !!", Toast.LENGTH_SHORT).show();
                }
                loading.dismiss();
            }

            @Override
            public void onFailure(Call<ModelUser> call, Throwable t) {
                Toast.makeText(Login.this, "Error Retrive Data from Server!!! \n" + t.getMessage() , Toast.LENGTH_LONG).show();
                loading.dismiss();
            }
        });


    }
public void saveData(int id, String username, int jenis_user  ){
    SharedPreferences sharedPreferences = getSharedPreferences("data_user", MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putInt("kirim_id", id);
    editor.putString("kirim_username", username);
    editor.putInt("kirim_jenis_user", jenis_user);
    editor.apply();

}
    public void login (View view) {
        Intent intent = new Intent(Login.this,MainActivity .class);
        startActivity(intent); }
}