package id.ac.usu.it.rosterlive.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.ac.usu.it.rosterlive.R;
import id.ac.usu.it.rosterlive.models.Jadwal;
import id.ac.usu.it.rosterlive.viewholder.JadwalMingguanViewHolder;

/**
 * Created by isadadi on 30/01/2018.
 */

public class JadwalMingguanAdapter extends RecyclerView.Adapter<JadwalMingguanViewHolder> {

    private List<Jadwal> listJadwal;

    public JadwalMingguanAdapter(List<Jadwal> listJadwal){
        this.listJadwal = listJadwal;
    }

    @Override
    public JadwalMingguanViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal_mingguan, parent, false);

        return new JadwalMingguanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalMingguanViewHolder holder, int position) {
        Jadwal data = listJadwal.get(position);

        holder.tvJamMingguan.setText(data.getJam());
        holder.tvMataKuliahMingguan.setText(data.getMataKuliah());
        holder.tvRuanganMingguan.setText(data.getRuangan());
        holder.tvKomMingguan.setText(data.getKom());
        holder.tvDosenMingguan.setText(data.getDosen());
        holder.tvSksMingguan.setText(data.getSks());
    }

    @Override
    public int getItemCount() {
        return listJadwal.size();
    }
}
