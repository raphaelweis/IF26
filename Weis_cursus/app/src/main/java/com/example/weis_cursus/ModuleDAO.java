package com.example.weis_cursus;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ModuleDAO {
    @Insert
    void insertModule(ModuleEntity module);

//    @Update
//    void updateModule(ModuleEntity module);

//    @Delete
//    void deleteModuleByEntity(ModuleEntity module);

    @Query("DELETE FROM module")
    void deleteAllModules();

//    @Query("DELETE FROM module WHERE sigle = :moduleSigle")
//    void deleteModuleBySigle(String moduleSigle);

    @Query("SELECT * FROM module ORDER BY sigle")
    LiveData<List<ModuleEntity>> getAllModules();  // ordonné par sigle

//    @Query("SELECT * FROM module WHERE sigle = :moduleSigle")
//    LiveData<ModuleEntity> getModule(String moduleSigle); // paramètre
}
