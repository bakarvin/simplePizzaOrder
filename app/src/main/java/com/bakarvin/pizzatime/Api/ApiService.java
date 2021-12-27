package com.bakarvin.pizzatime.Api;

import com.bakarvin.pizzatime.Model.Alamat.AlamatResponse;
import com.bakarvin.pizzatime.Model.CustomTransaksi.CustomTransaksiResponse;
import com.bakarvin.pizzatime.Model.DetailTransaksi.DetailTransaksiResponse;
import com.bakarvin.pizzatime.Model.Menu.MenuResponse;
import com.bakarvin.pizzatime.Model.Transaksi.TransaksiResponse;
import com.bakarvin.pizzatime.Model.User.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.bakarvin.pizzatime.BuildConfig.*;

public interface ApiService {


    /*MENU*/
    @GET(READ_MENU)
    Call<MenuResponse> readMenu(@Query("kode") String kode,
                                @Query("query") String query);

    @GET(SEARCH_MENU)
    Call<MenuResponse> SearchMenu(@Query("nama_menu") String nama_menu);

    /*USER*/
    @FormUrlEncoded
    @POST(INSERT_USER)
    Call<UserResponse> insertUser(@Field("username") String username,
                                  @Field("nama_user") String nama_user,
                                  @Field("telp_user") String telp_user,
                                  @Field("password_user") String password_user);

    @GET(CHECK_USER)
    Call<UserResponse> checkUser(@Query("username") String username);

    @FormUrlEncoded
    @POST(LOGIN_USER)
    Call<UserResponse> loginUser(@Field("username") String username,
                                 @Field("password_user") String password_user);

    @FormUrlEncoded
    @POST(UPDATE_USER)
    Call<UserResponse> updateUser(@Field("username") String username,
                                  @Field("nama_user") String nama_user,
                                  @Field("telp_user") String telp_user);

    @FormUrlEncoded
    @POST(CHANGE_PASSWORD)
    Call<UserResponse> changePassword(@Field("username") String username,
                                      @Field("password_user") String password_user);

    /*TRANSAKSI*/
    @FormUrlEncoded
    @POST(INSERT_TRANSAKSI)
    Call<TransaksiResponse> insertTransaksi(@Field("id_trans") String id_trans,
                                            @Field("username") String username,
                                            @Field("tgl_trans") String tgl_trans,
                                            @Field("total_trans") String total_trans,
                                            @Field("alamat_user") String alamat_user);

    @GET(FILTER_TRANSAKSI)
    Call<TransaksiResponse> filterTransaksi(@Query("username")String username,
                                            @Query("status_trans")String status_trans);

    /*DETAIL TRANSAKSI*/
    @FormUrlEncoded
    @POST(INSERT_DETAIL_TRANSAKSI)
    Call<DetailTransaksiResponse> insertDetailTransaksi(@Field("jsonArrays") String jsonArrays);

    @GET(READ_DETAIL_TRANSAKSI)
    Call<DetailTransaksiResponse> readDetailTransaksi(@Query("id_trans") String id_trans);

    /*DETAIL CUSTOM TRANSAKSI*/
    @FormUrlEncoded
    @POST(INSERT_CUSTOM_TRANSAKSI)
    Call<CustomTransaksiResponse > insertCustomTransaksi(@Field("id_trans") String id_trans,
                                                        @Field("size_custom") String size_custom,
                                                        @Field("free_topping") String free_topping,
                                                        @Field("extra_topping") String extra_topping,
                                                        @Field("total_trans") String total_trans);

    /*ALAMAT USER*/
    @FormUrlEncoded
    @POST(INSERT_ALAMAT)
    Call<AlamatResponse> insertAlamat(@Field("username") String username,
                                      @Field("nama_user") String nama_user,
                                      @Field("telp_user") String telp_user,
                                      @Field("tipe_alamat_user") String tipe_alamat_user,
                                      @Field("kota_user") String kota_user,
                                      @Field("kode_pos_user") String kode_pos_user,
                                      @Field("alamat_user") String alamat_user);

    @GET(READ_ALAMAT)
    Call<AlamatResponse> readAlamatUser(@Query("username")String username);


    @GET(FILTER_ALAMAT_USER)
    Call<AlamatResponse> filterAlamatUser(@Query("username") String username,
                                          @Query("tipe_alamat_user") String tipe_alamat_user);



}
