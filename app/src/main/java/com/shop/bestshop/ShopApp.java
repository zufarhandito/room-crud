package com.shop.bestshop;

import android.app.Application;
import androidx.room.Room;
import com.shop.bestshop.data.MhsDatabase;

public class ShopApp extends Application {

    public static MhsDatabase mhsDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        mhsDatabase = Room.databaseBuilder(getApplicationContext(), MhsDatabase.class, "math-db-1-2")
                .allowMainThreadQueries().build();
    }
}
