package id.ac.usu.it.rosterlive.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.ac.usu.it.rosterlive.R;

/**
 * Created by isadadi on 30/01/2018.
 */

public class JadwalMingguanViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.rootview_mingguan)
    public View rootviewMingguan;

    @BindView(R.id.tv_jam_mingguan)
    public TextView tvJamMingguan;

    @BindView(R.id.tv_matkul_mingguan)
    public TextView tvMataKuliahMingguan;

    @BindView(R.id.tv_ruangan_mingguan)
    public TextView tvRuanganMingguan;

    @BindView(R.id.tv_kom_mingguan)
    public TextView tvKomMingguan;

    @BindView(R.id.tv_dosen_mingguan)
    public TextView tvDosenMingguan;

    @BindView(R.id.tv_sks_mingguan)
    public TextView tvSksMingguan;

    public JadwalMingguanViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}

