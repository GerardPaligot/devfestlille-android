package com.paligot.shared.networks

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.paligot.shared.BuildConfig
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import okhttp3.logging.HttpLoggingInterceptor.Level.NONE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface DevFestLilleService {
    @GET(value = "findAllActiveTalks?editionId=VBsJyBiyXwaM1GsDzxP7")
    fun getTalks(): Observable<Map<String, TalkNetwork>>

    @GET(value = "findAllActiveSpeakers?editionId=VBsJyBiyXwaM1GsDzxP7")
    fun getSpeakers(): Observable<Map<String, SpeakerNetwork>>

    @GET(value = "findAllActivePartners?editionId=VBsJyBiyXwaM1GsDzxP7")
    fun getPartners(): Observable<Map<String, PartnerNetwork>>

    companion object {
        fun create(): DevFestLilleService {
            val okHttp = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = if (BuildConfig.DEBUG) BODY else NONE
                })
            val retrofit = Retrofit.Builder()
                .baseUrl("https://us-central1-cms-devfest.cloudfunctions.net")
                .client(okHttp.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
            return retrofit.create(DevFestLilleService::class.java)
        }
    }
}

data class TalkNetwork(
    val title: String, val abstract: String, val level: String, val format: String, val category: String,
    val hour: HourNetwork, val room: String, val speakers: List<String>
)

data class HourNetwork(@SerializedName("_seconds") val timestamp: Long)

data class SpeakerNetwork(
    val displayName: String,
    val role: String,
    val photoURL: String,
    val company: String,
    val bio: String,
    val github: String?,
    val twitter: String?
)

data class PartnerNetwork(
    val name: String,
    val url: String,
    val logoUrl: String,
    val level: String
)