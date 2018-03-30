package id.ac.usu.it.rosterlive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.usu.it.rosterlive.helper.SQLiteHandler;
import id.ac.usu.it.rosterlive.response.LoginResponse;
import id.ac.usu.it.rosterlive.service.JadwalService;
import id.ac.usu.it.rosterlive.utilities.SessionManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_username)
    EditText etUsername;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.b_login)
    Button bLogin;


    ProgressDialog progressDialog;

    private SessionManager session;
    private SQLiteHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon menunggu");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        // SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());

        // Check if user is already logged in or not
        if (session.isLoggedIn()) {
            // User is already logged in. Take him to main activity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @OnClick(R.id.b_login)
    public void bLoginClick(){
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty()){
            etUsername.setError("Username harus diisi");
            return;
        } else {
            etUsername.setError(null);
        }

        if (password.isEmpty()){
            etPassword.setError("Password harus diisi");
            return;
        } else {
            etPassword.setError(null);
        }

        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JadwalService.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        JadwalService service = retrofit.create(JadwalService.class);
        String token = FirebaseInstanceId.getInstance().getToken();
        service.dataLogin(username, password, token).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse hasil = response.body();

                progressDialog.dismiss();

                if (hasil.getError() == false){

                    // user successfully logged ins
                    // Create login session
                    session.setLogin(true);

                    // Inserting row in users table
                    db.addUser(hasil.getNim(), hasil.getNama(), hasil.getJurusan());

                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, hasil.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                progressDialog.dismiss();
            }
        });

    }
}
