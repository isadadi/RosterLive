package id.ac.usu.it.rosterlive;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.ac.usu.it.rosterlive.helper.SQLiteHandler;
import id.ac.usu.it.rosterlive.response.GantiJadwalResponse;
import id.ac.usu.it.rosterlive.service.JadwalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class GantiJadwalActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_ganti_jadwal)
    Toolbar toolbarJadwalGanti;

    @BindView(R.id.et_tanggal_ganti)
    EditText etTanggal;

    @BindView(R.id.spinner_jam_ganti)
    Spinner spinnerJamGanti;

    @BindView(R.id.spinner_ruangan_ganti)
    Spinner spinnerRuanganGanti;

    @BindView(R.id.b_save_jadwal_ganti)
    Button bSaveJadwalGanti;

    List<String> listJam, listRuangan;
    String matkulID, kom, tanggalSebelum, tanggalGanti, jamGanti, ruanganGanti, mhsPengganti;
    ProgressDialog progressDialog;
    private SQLiteHandler db;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_jadwal);
        ButterKnife.bind(this);

        this.setSupportActionBar(toolbarJadwalGanti);
        this.setTitle("Ganti Jadwal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        mhsPengganti = user.get("username");
        matkulID = getIntent().getStringExtra("MATKULID");
        kom = getIntent().getStringExtra("KOM");
        tanggalSebelum = getIntent().getStringExtra("TANGGAL");

        Calendar calendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        etTanggal.setText("" + dateFormatter.format(calendar.getTime()));

        listJam = new ArrayList<>();
        listJam.add("08:00");
        listJam.add("08:50");
        listJam.add("09:40");
        listJam.add("10:30");
        listJam.add("11:20");
        listJam.add("12:10");
        listJam.add("13:00");
        listJam.add("13:50");
        listJam.add("14:40");

        ArrayAdapter<String> jamAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listJam);
        jamAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerJamGanti.setAdapter(jamAdapter);

        spinnerJamGanti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jamGanti = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        listRuangan = new ArrayList<>();
        listRuangan.add("TI101");
        listRuangan.add("TI102");
        listRuangan.add("TI103");
        listRuangan.add("TI104");
        listRuangan.add("TI105");
        listRuangan.add("TI106");

        ArrayAdapter<String> ruanganAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listRuangan);
        ruanganAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRuanganGanti.setAdapter(ruanganAdapter);

        spinnerRuanganGanti.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ruanganGanti = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon menunggu");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
    }


    @OnClick(R.id.et_tanggal_ganti)
    public void etTanggalGantiClick() {
        showDateDialog();
    }


    private void showDateDialog() {

        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                etTanggal.setText("" + dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }

    @OnClick(R.id.b_save_jadwal_ganti)
    public void saveJadwalGantiClick() {
        tanggalGanti = etTanggal.getText().toString();
        //Toast.makeText(this, tanggalGanti + " " + jamGanti + " " + ruanganGanti, Toast.LENGTH_SHORT).show();

        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JadwalService.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        JadwalService service = retrofit.create(JadwalService.class);

        service.responseGantiJadwal(matkulID, kom, ruanganGanti, tanggalSebelum, tanggalGanti, jamGanti, mhsPengganti).enqueue(new Callback<GantiJadwalResponse>() {
            @Override
            public void onResponse(Call<GantiJadwalResponse> call, Response<GantiJadwalResponse> response) {
                GantiJadwalResponse hasil = response.body();
                Log.d("GantiJadwal", hasil.getMessage());

                if(!hasil.getError()) {
                    finish();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<GantiJadwalResponse> call, Throwable t) {
                Log.d("GantiJadwal",t.getMessage()+ " "+ t.getLocalizedMessage());
                Toast.makeText(GantiJadwalActivity.this, "Gagal Mengganti Jadwal", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });


    }
}
