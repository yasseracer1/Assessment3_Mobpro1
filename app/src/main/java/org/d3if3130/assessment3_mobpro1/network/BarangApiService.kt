package org.d3if3130.assessment3_mobpro1.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.d3if3130.assessment3_mobpro1.model.Barang
import org.d3if3130.assessment3_mobpro1.model.OpStatus
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

private const val BASE_URL = "https://unspoken.my.id/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface BarangApiService {
    @GET("api_yasser.php")
    suspend fun getBarang(
        @Header("Authorization") userId: String
    ): List<Barang>

    @Multipart
    @POST("api_yasser.php")
    suspend fun postBarang(
        @Header("Authorization") userId: String,
        @Part("nama") nama: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("api_yasser.phpx")
    suspend fun deleteBarang(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus

}

object BarangApi {
    val service: BarangApiService by lazy {
        retrofit.create(BarangApiService::class.java)
    }

    fun getBarangUrl(imageId: String): String {
        return "${BASE_URL}image.php?id=$imageId"
    }
}

enum class ApiStatus { LOADING, SUCCES, FAILED }