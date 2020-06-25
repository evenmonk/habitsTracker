package com.example.habitsTracker

import android.app.Application
import androidx.room.Room
import android.util.Log
import com.example.habitsTracker.model.Database
import com.example.habitsTracker.model.HabitApi
import com.example.habitsTracker.model.Repository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val db = Room.databaseBuilder(
            this,
            Database::class.java, "db"
        ).fallbackToDestructiveMigration().build()

        val habitDao = db.habitDao()

        val interceptor = Interceptor { chain ->
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder().header(
                "Authorization",
                resources.getString(R.string.token)
            )
            val newRequest = builder.build()

            val response = proceed(chain, newRequest)

            if (!response.isSuccessful) {
                Log.w("TAG", response.message())
                Log.w("TAG", response.body().toString())
            }

            response
        }

        val okHttpClient = okhttp3.OkHttpClient().newBuilder().addInterceptor(interceptor).build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(HabitApi::class.java)

        repository = Repository(habitDao, api)
    }

    private fun proceed(chain: Interceptor.Chain, request: Request) : Response {
        return try {
            chain.proceed(request)
        } catch (e: Exception) {
            e.printStackTrace()
            Thread.sleep(3000)
            proceed(chain, request)
        }
    }

    companion object {
        lateinit var repository: Repository
        lateinit var api: HabitApi
    }
}
