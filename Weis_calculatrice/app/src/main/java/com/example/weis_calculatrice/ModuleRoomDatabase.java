package com.example.weis_calculatrice;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Module.class}, version = 1, exportSchema = false)
public abstract class ModuleRoomDatabase extends RoomDatabase {
    public abstract ModuleDAO moduleDAO();

    private static volatile ModuleRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = Runtime.getRuntime().availableProcessors();
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static ModuleRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ModuleRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    ModuleRoomDatabase.class,
                                    "calculatrice.db"
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

                final List<Module> initialModules = new ArrayList<>();

                initialModules.add(new Module("C06", "TM", 3));
                initialModules.add(new Module("EG23", "TM", 6));
                initialModules.add(new Module("GL02", "CS", 6));
                initialModules.add(new Module("IF10", "CS", 6));
                initialModules.add(new Module("IF17", "CS", 6));
                initialModules.add(new Module("IF22", "CS", 6));
                initialModules.add(new Module("IF26", "TM", 6));
                initialModules.add(new Module("IF29", "CS", 6));
                initialModules.add(new Module("IF31", "TM", 6));
                initialModules.add(new Module("IF34", "TM", 6));
                initialModules.add(new Module("LO10", "TM", 6));
                initialModules.add(new Module("LO12", "CS", 6));
                initialModules.add(new Module("NF21", "TM", 6));
                initialModules.add(new Module("ST09", "ST", 30));
                initialModules.add(new Module("ST10", "ST", 30));

                for (Module module : initialModules) {
                    moduleDAO.insertModule(module);
                }
            });
        }
    };
}