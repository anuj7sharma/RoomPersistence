package com.room.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.room.db.table.Favourite;

/**
 * author by Anuj Sharma on 12/6/2017.
 */

@Database(entities = {Favourite.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract Favourite.FavouriteDao favouriteDao();

    public static AppDatabase getDatabaseInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "media-db")
//                    .addMigrations(migration_1_2)
//                    .addMigrations(migration_2_3)
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

    static final Migration migration_1_2 = new Migration(1,2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE favourite ADD COLUMN thumbnail TEXT");
//            database.execSQL("ALTER TABLE favourite CHANGE COLUMN id mediaId INT PRIMARY_KEY");
        }
    };

    public void destroyInstance() {
        instance = null;
    }

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    /**
     *
     */
}
