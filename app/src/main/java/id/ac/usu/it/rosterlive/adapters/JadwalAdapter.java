package id.ac.usu.it.rosterlive.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.ac.usu.it.rosterlive.GantiJadwalActivity;
import id.ac.usu.it.rosterlive.R;
import id.ac.usu.it.rosterlive.models.Jadwal;
import id.ac.usu.it.rosterlive.viewholder.JadwalViewHolder;

/**
 * Created by isadadi on 30/01/2018.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalViewHolder> {

    private List<Jadwal> listJadwal;

    public JadwalAdapter(List<Jadwal> listJadwal){
        this.listJadwal = listJadwal;
    }

    @Override
    public JadwalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jadwal, parent, false);

        return new JadwalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JadwalViewHolder holder, int position) {
        final Jadwal data = listJadwal.get(position);
        holder.tvPengganti.setVisibility(View.GONE);
        holder.tvPengganti_1.setVisibility(View.GONE);
        if(data.getStatus()=="ujian"){
            holder.tvStatusJadwal.setText(data.getStatus());
        }else{
            holder.tvStatusJadwal.setText(data.getStatusJadwal());
            holder.tvPengganti.setText(data.getMhsPengganti());
            if(data.getStatusJadwal().equalsIgnoreCase("diganti")){
                holder.tvStatusJadwal.setBackgroundResource(R.color.red);
                holder.bGanti.setVisibility(View.GONE);
                holder.tvPengganti.setVisibility(View.VISIBLE);
                holder.tvPengganti_1.setVisibility(View.VISIBLE);
            }else if(data.getStatusJadwal().equalsIgnoreCase("pengganti")){
                holder.tvStatusJadwal.setBackgroundResource(R.color.orange);
                holder.bGanti.setVisibility(View.GONE);
                holder.tvPengganti.setVisibility(View.VISIBLE);
                holder.tvPengganti_1.setVisibility(View.VISIBLE);
            }
        }
        holder.tvJam.setText(data.getJam());
        holder.tvStatusJadwal.setText(data.getStatusJadwal());
        holder.tvMataKuliah.setText(data.getMataKuliah());
        holder.tvRuangan.setText(data.getRuangan());
        holder.tvKom.setText(data.getKom());
        holder.tvDosen.setText(data.getDosen());
        holder.tvSks.setText(data.getSks());

        holder.bGanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), GantiJadwalActivity.class);
                intent.putExtra("MATKULID", data.getMatkulID());
                intent.putExtra("KOM", data.getKom());
                intent.putExtra("TANGGAL", data.getTanggal());
                v.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listJadwal.size();
    }
}

