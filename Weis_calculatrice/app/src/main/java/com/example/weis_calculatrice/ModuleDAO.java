package com.example.weis_calculatrice;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ModuleDAO {
    @Insert
    void insertModule(Module module);

    @Query("SELECT * FROM module ORDER BY sigle")
    LiveData<List<Module>> getAllModules();

    @Query("DELETE FROM module")
    void deleteAllModules();
}
