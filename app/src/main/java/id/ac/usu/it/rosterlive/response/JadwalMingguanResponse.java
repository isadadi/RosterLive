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
        "matkul_id",
        "mata_kuliah",
        "kom",
        "jam",
        "nama_dosen",
        "kode_dosen",
        "ruangan",
        "hari",
        "sks"
})
public class JadwalMingguanResponse {

    @JsonProperty("status")
    private String status;
    @JsonProperty("matkul_id")
    private String matkulId;
    @JsonProperty("mata_kuliah")
    private String mataKuliah;
    @JsonProperty("kom")
    private String kom;
    @JsonProperty("jam")
    private String jam;
    @JsonProperty("nama_dosen")
    private String namaDosen;
    @JsonProperty("kode_dosen")
    private String kodeDosen;
    @JsonProperty("ruangan")
    private String ruangan;
    @JsonProperty("hari")
    private String hari;
    @JsonProperty("sks")
    private String sks;
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

    @JsonProperty("matkul_id")
    public String getMatkulId() {
        return matkulId;
    }

    @JsonProperty("matkul_id")
    public void setMatkulId(String matkulId) {
        this.matkulId = matkulId;
    }

    @JsonProperty("mata_kuliah")
    public String getMataKuliah() {
        return mataKuliah;
    }

    @JsonProperty("mata_kuliah")
    public void setMataKuliah(String mataKuliah) {
        this.mataKuliah = mataKuliah;
    }

    @JsonProperty("kom")
    public String getKom() {
        return kom;
    }

    @JsonProperty("kom")
    public void setKom(String kom) {
        this.kom = kom;
    }

    @JsonProperty("jam")
    public String getJam() {
        return jam;
    }

    @JsonProperty("jam")
    public void setJam(String jam) {
        this.jam = jam;
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

    @JsonProperty("sks")
    public String getSks() {
        return sks;
    }

    @JsonProperty("sks")
    public void setSks(String sks) {
        this.sks = sks;
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
