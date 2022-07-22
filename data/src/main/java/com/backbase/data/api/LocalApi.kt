package com.backbase.data.api

import android.content.Context
import com.backbase.data.R
import com.backbase.domain.dispatcherprovider.DispatcherProvider
import com.backbase.data.CityEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream

class LocalApi(
    private val context: Context,
    private val gson: Gson,
    private val dispatcherProvider: DispatcherProvider
): Api {

    override suspend fun provideData(): List<CityEntity> = withContext(dispatcherProvider.io()) {
        gson.fromJson(
            loadJSONFromAsset(R.raw.cities),
            object : TypeToken<List<CityEntity>>() {}.type
        )
    }

    private fun loadJSONFromAsset(resId: Int) = try {
        val stream: InputStream = context.resources.openRawResource(resId)
        val size = stream.available()
        val buffer = ByteArray(size)
        stream.read(buffer)
        stream.close()
        String(buffer)
    } catch (ex: IOException) {
        ex.printStackTrace()
        null
    }

}