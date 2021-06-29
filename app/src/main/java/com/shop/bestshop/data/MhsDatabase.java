package com.shop.bestshop.data;


import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {
                EntityMhs.class  //Tambah entity disini..
        },
        version = 1,
        exportSchema = false
)
public abstract class MhsDatabase extends RoomDatabase {
    public abstract MhsDao getMhsDao();
}
