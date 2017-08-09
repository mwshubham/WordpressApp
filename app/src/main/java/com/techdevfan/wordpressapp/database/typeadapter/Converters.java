package com.techdevfan.wordpressapp.database.typeadapter;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converters {
    @TypeConverter
    public List<String> jsonStrToStringList(String jsonStr) {
        return new Gson().fromJson(jsonStr, new TypeToken<List<String>>() {
        }.getType());
    }

    @TypeConverter
    public String stringListToJsonStr(List<String> stringList) {
        return new Gson().toJson(stringList);
    }
}
 