package id.ac.usu.it.rosterlive.service;

import java.util.List;

import id.ac.usu.it.rosterlive.response.GantiJadwalResponse;
import id.ac.usu.it.rosterlive.response.JadwalHarianResponse;
import id.ac.usu.it.rosterlive.response.JadwalMingguanResponse;
import id.ac.usu.it.rosterlive.response.JadwalResponse;
import id.ac.usu.it.rosterlive.response.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Fiqih Fatwa on 30/01/2018.
 */

public interface JadwalService {
    public String baseUrl = "http://192.168.1.102/rosterlive/";

    @GET("api.php")
    Call<List<JadwalResponse>> listJadwal();

    @POST("mahasiswa/harian")
    @FormUrlEncoded
    Call<List<JadwalHarianResponse>> jadwalHarian(
            @Field("nim") String nim
    );


    @POST("mahasiswa")
    @FormUrlEncoded
    Call<LoginResponse> dataLogin(
            @Field("username") String username,
            @Field("password") String password,
            @Field("token") String token
    );

    @POST("mahasiswa/mingguan")
    @FormUrlEncoded
    Call<List<JadwalMingguanResponse>> jadwalMingguan(
            @Field("nim") String nim
    );

    @POST("mahasiswa/jadwalganti")
    @FormUrlEncoded
    Call<GantiJadwalResponse> responseGantiJadwal(
            @Field("matkul_id") String matkul_id,
            @Field("kom") String kom,
            @Field("ruangan") String ruangan,
            @Field("tgl_sebelum") String tgl_sebelum,
            @Field("tgl_setelah") String tgl_setelah,
            @Field("jam") String jam,
            @Field("nim") String nim
    );
}
