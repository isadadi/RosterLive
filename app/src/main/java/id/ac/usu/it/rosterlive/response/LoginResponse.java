package id.ac.usu.it.rosterlive.response;

/**
 * Created by  on 30/01/2018.
 */

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error",
        "nim",
        "nama",
        "jurusan",
        "error_msg"
})
public class LoginResponse {

    @JsonProperty("error")
    private Boolean error;
    @JsonProperty("nim")
    private String nim;
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("jurusan")
    private String jurusan;
    @JsonProperty("error_msg")
    private String errorMsg;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("error")
    public Boolean getError() {
        return error;
    }

    @JsonProperty("error")
    public void setError(Boolean error) {
        this.error = error;
    }

    @JsonProperty("nim")
    public String getNim() {
        return nim;
    }

    @JsonProperty("nim")
    public void setNim(String nim) {
        this.nim = nim;
    }

    @JsonProperty("nama")
    public String getNama() {
        return nama;
    }

    @JsonProperty("nama")
    public void setNama(String nama) {
        this.nama = nama;
    }

    @JsonProperty("jurusan")
    public String getJurusan() {
        return jurusan;
    }

    @JsonProperty("jurusan")
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    @JsonProperty("error_msg")
    public String getErrorMsg() {
        return errorMsg;
    }

    @JsonProperty("error_msg")
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
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
