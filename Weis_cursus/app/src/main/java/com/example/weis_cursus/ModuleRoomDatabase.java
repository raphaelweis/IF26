package com.example.weis_cursus;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ModuleEntity.class}, version = 1, exportSchema = false)
public abstract class ModuleRoomDatabase extends RoomDatabase {
    public abstract ModuleDAO moduleDAO();

    private static volatile ModuleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ModuleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ModuleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ModuleRoomDatabase.class,
                                    "cursus_room.db"
                            )
                            .addCallback(initDatabase).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback initDatabase = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                ModuleDAO moduleDAO = INSTANCE.moduleDAO();
                moduleDAO.deleteAllModules();

                ModuleEntity moduleEntity1 = new ModuleEntity("Room1", "MCS", "CS", 6);
                moduleDAO.insertModule(moduleEntity1);

                ModuleEntity moduleEntity2 = new ModuleEntity("Room2", "MCS", "TM", 6);
                moduleDAO.insertModule(moduleEntity2);
            });
        }
    };
}