package id.ac.usu.it.rosterlive.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.usu.it.rosterlive.R;

/**
 * Created by isadadi on 30/01/2018.
 */

public class JadwalViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rootview)
    public View rootview;

    @BindView(R.id.tv_jam)
    public TextView tvJam;

    @BindView(R.id.tv_status_jadwal)
    public TextView tvStatusJadwal;

    @BindView(R.id.tv_matakuliah)
    public TextView tvMataKuliah;

    @BindView(R.id.tv_ruangan)
    public TextView tvRuangan;

    @BindView(R.id.tv_kom)
    public TextView tvKom;

    @BindView(R.id.tv_dosen)
    public TextView tvDosen;

    @BindView(R.id.tv_sks)
    public TextView tvSks;

    @BindView(R.id.btn_ganti)
    public Button bGanti;

    @BindView(R.id.tv_pengganti)
    public  TextView tvPengganti;

    @BindView(R.id.tv_pengganti_1)
    public  TextView tvPengganti_1;

    public JadwalViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

