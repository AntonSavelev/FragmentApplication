package com.example.fragmentapplication.managers;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.fragmentapplication.model.AppDatabase;

public class App extends Application {

    public static App instance;

    private AppDatabase database;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database").allowMainThreadQueries()
                .build();
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
