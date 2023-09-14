package com.example.rickandmorty20.common

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun storedStringToDate(data: String): Date {
        return gson.fromJson(data, object : TypeToken<Date>() {}.type)

    }

    @TypeConverter
    fun dateToStoredString(date: Date): String {
        return gson.toJson(date)
    }


    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return gson.fromJson(listOfString, object : TypeToken<List<String>>() {}.type)
    }

    @TypeConverter
    fun saveList(listOfString: List<String>): String {
        return gson.toJson(listOfString)
    }
}

class CustomDateAdapter : JsonAdapter<Date>() {
    private val dateFormat = SimpleDateFormat(SERVER_FORMAT, Locale.getDefault())

    @FromJson
    override fun fromJson(reader: JsonReader): Date? {
        return try {

            val dateAsString = reader.nextString()
            dateFormat.parse(dateAsString)
        } catch (e: Exception) {
            null
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Date?) {
        if (value != null) {
            writer.value(value.toString())
        }
    }

    companion object {
        const val SERVER_FORMAT = ("yyyy-MM-dd'T'HH:mm") // define your server format here
    }
}

