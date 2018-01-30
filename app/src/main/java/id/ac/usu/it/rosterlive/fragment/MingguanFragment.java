package id.ac.usu.it.rosterlive.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.usu.it.rosterlive.R;
import id.ac.usu.it.rosterlive.adapters.JadwalMingguanAdapter;
import id.ac.usu.it.rosterlive.helper.SQLiteHandler;
import id.ac.usu.it.rosterlive.models.Jadwal;
import id.ac.usu.it.rosterlive.response.JadwalMingguanResponse;
import id.ac.usu.it.rosterlive.service.JadwalService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class MingguanFragment extends Fragment {

    @BindView(R.id.rv_senin)
    RecyclerView rvSenin;

    @BindView(R.id.rv_selasa)
    RecyclerView rvSelasa;

    @BindView(R.id.rv_rabu)
    RecyclerView rvRabu;

    @BindView(R.id.rv_kamis)
    RecyclerView rvKamis;

    @BindView(R.id.rv_jumat)
    RecyclerView rvJumat;

    @BindView(R.id.rv_sabtu)
    RecyclerView rvSabtu;

    @BindView(R.id.rv_minggu)
    RecyclerView rvMinggu;

    @BindView(R.id.pb_mingguan)
    RelativeLayout pbMingguan;

    List<Jadwal> jadwalListSenin, jadwalListSelasa, jadwalListRabu, jadwalListKamis, jadwalListJumat, jadwalListSabtu, jadwalListMinggu;
    JadwalMingguanAdapter adapterSenin, adapterSelasa, adapterRabu, adapterKamis, adapterJumat, adapterSabtu, adapterMinggu;

    private SQLiteHandler db;
    private String username;

    public MingguanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mingguan, container, false);
        ButterKnife.bind(this, view);

        jadwalListSenin = new ArrayList<>();
        jadwalListSelasa = new ArrayList<>();
        jadwalListRabu = new ArrayList<>();
        jadwalListKamis = new ArrayList<>();
        jadwalListJumat = new ArrayList<>();
        jadwalListSabtu = new ArrayList<>();
        jadwalListMinggu = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterSenin = new JadwalMingguanAdapter(jadwalListSenin);
        rvSenin.setAdapter(adapterSenin);
        rvSenin.setLayoutManager(layoutManager);

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterSelasa = new JadwalMingguanAdapter(jadwalListSelasa);
        rvSelasa.setAdapter(adapterSelasa);
        rvSelasa.setLayoutManager(layoutManager1);

        LinearLayoutManager layoutManager2 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterRabu = new JadwalMingguanAdapter(jadwalListRabu);
        rvRabu.setAdapter(adapterRabu);
        rvRabu.setLayoutManager(layoutManager2);

        LinearLayoutManager layoutManager3 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterKamis = new JadwalMingguanAdapter(jadwalListKamis);
        rvKamis.setAdapter(adapterKamis);
        rvKamis.setLayoutManager(layoutManager3);

        LinearLayoutManager layoutManager4 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterJumat = new JadwalMingguanAdapter(jadwalListJumat);
        rvJumat.setAdapter(adapterJumat);
        rvJumat.setLayoutManager(layoutManager4);

        LinearLayoutManager layoutManager5 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterSabtu = new JadwalMingguanAdapter(jadwalListSabtu);
        rvSabtu.setAdapter(adapterSabtu);
        rvSabtu.setLayoutManager(layoutManager5);

        LinearLayoutManager layoutManager6 = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        adapterMinggu = new JadwalMingguanAdapter(jadwalListMinggu);
        rvMinggu.setAdapter(adapterMinggu);
        rvMinggu.setLayoutManager(layoutManager6);

        // SqLite database handler
        db = new SQLiteHandler(view.getContext());

        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();
        username = user.get("username");

        loadData(view);

        return view;
    }

    private void loadData(final View view){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JadwalService.baseUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        JadwalService service = retrofit.create(JadwalService.class);

        service.jadwalMingguan(username).enqueue(new Callback<List<JadwalMingguanResponse>>() {
            @Override
            public void onResponse(Call<List<JadwalMingguanResponse>> call, Response<List<JadwalMingguanResponse>> response) {
                List<JadwalMingguanResponse> hasil = response.body();

                for (JadwalMingguanResponse jadwalMingguan : hasil) {
                    if (jadwalMingguan.getHari().equals("Senin")) {
                        jadwalListSenin.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Selasa")) {
                        jadwalListSelasa.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Rabu")) {
                        jadwalListRabu.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Kamis")) {
                        jadwalListKamis.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Jumat")) {
                        jadwalListJumat.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Sabtu")) {
                        jadwalListSabtu.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    } else if (jadwalMingguan.getHari().equals("Minggu")) {
                        jadwalListMinggu.add(new Jadwal(jadwalMingguan.getJam(), jadwalMingguan.getMataKuliah(), jadwalMingguan.getRuangan(), jadwalMingguan.getKom(), jadwalMingguan.getKodeDosen(), jadwalMingguan.getSks()));
                    }
                }

                adapterSenin.notifyDataSetChanged();
                adapterSelasa.notifyDataSetChanged();
                adapterRabu.notifyDataSetChanged();
                adapterKamis.notifyDataSetChanged();
                adapterJumat.notifyDataSetChanged();
                adapterSabtu.notifyDataSetChanged();
                adapterMinggu.notifyDataSetChanged();

                pbMingguan.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<JadwalMingguanResponse>> call, Throwable t) {

            }
        });
    }
}
