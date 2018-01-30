package id.ac.usu.it.rosterlive.response;

/**
 * Created by isadadi on 30/01/2018.
 */

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "tanggal",
        "date",
        "matkul_id",
        "matkul_name",
        "sks",
        "kelas",
        "jam_matkul",
        "nama_dosen",
        "kode_dosen",
        "ruangan",
        "hari",
        "mhs_pengganti",
        "jadwal"
})
public class JadwalHarianResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("tanggal")
    private String tanggal;
    @JsonProperty("date")
    private String date;
    @JsonProperty("matkul_id")
    private String matkulId;
    @JsonProperty("matkul_name")
    private String matkulName;
    @JsonProperty("sks")
    private String sks;
    @JsonProperty("kelas")
    private String kelas;
    @JsonProperty("jam_matkul")
    private String jamMatkul;
    @JsonProperty("nama_dosen")
    private String namaDosen;
    @JsonProperty("kode_dosen")
    private String kodeDosen;
    @JsonProperty("ruangan")
    private String ruangan;
    @JsonProperty("hari")
    private String hari;
    @JsonProperty("mhs_pengganti")
    private String mhsPengganti;
    @JsonProperty("jadwal")
    private String jadwal;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("tanggal")
    public String getTanggal() {
        return tanggal;
    }

    @JsonProperty("tanggal")
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("matkul_id")
    public String getMatkulId() {
        return matkulId;
    }

    @JsonProperty("matkul_id")
    public void setMatkulId(String matkulId) {
        this.matkulId = matkulId;
    }

    @JsonProperty("matkul_name")
    public String getMatkulName() {
        return matkulName;
    }

    @JsonProperty("matkul_name")
    public void setMatkulName(String matkulName) {
        this.matkulName = matkulName;
    }

    @JsonProperty("sks")
    public String getSks() {
        return sks;
    }

    @JsonProperty("sks")
    public void setSks(String sks) {
        this.sks = sks;
    }

    @JsonProperty("kelas")
    public String getKelas() {
        return kelas;
    }

    @JsonProperty("kelas")
    public void setKelas(String kelas) {
        this.kelas = kelas;
    }

    @JsonProperty("jam_matkul")
    public String getJamMatkul() {
        return jamMatkul;
    }

    @JsonProperty("jam_matkul")
    public void setJamMatkul(String jamMatkul) {
        this.jamMatkul = jamMatkul;
    }

    @JsonProperty("nama_dosen")
    public String getNamaDosen() {
        return namaDosen;
    }

    @JsonProperty("nama_dosen")
    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    @JsonProperty("kode_dosen")
    public String getKodeDosen() {
        return kodeDosen;
    }

    @JsonProperty("kode_dosen")
    public void setKodeDosen(String kodeDosen) {
        this.kodeDosen = kodeDosen;
    }

    @JsonProperty("ruangan")
    public String getRuangan() {
        return ruangan;
    }

    @JsonProperty("ruangan")
    public void setRuangan(String ruangan) {
        this.ruangan = ruangan;
    }

    @JsonProperty("hari")
    public String getHari() {
        return hari;
    }

    @JsonProperty("hari")
    public void setHari(String hari) {
        this.hari = hari;
    }

    @JsonProperty("mhs_pengganti")
    public String getMhsPengganti() {
        return mhsPengganti;
    }

    @JsonProperty("mhs_pengganti")
    public void setMhsPengganti(String mhsPengganti) {
        this.mhsPengganti = mhsPengganti;
    }

    @JsonProperty("jadwal")
    public String getJadwal() {
        return jadwal;
    }

    @JsonProperty("jadwal")
    public void setJadwal(String jadwal) {
        this.jadwal = jadwal;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
