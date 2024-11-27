package com.example.weis_cursus;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ModuleViewModel extends AndroidViewModel {

    private final LiveData<List<ModuleEntity>> modules;

    public ModuleViewModel(Application application) {
        super(application);
        ModuleRepository moduleRepository = new ModuleRepository(application);
        modules = moduleRepository.getLiveModuleEntityList();
    }

    public LiveData<List<ModuleEntity>> getAllModules() {
        return modules;
    }

//    public void insert(ModuleEntity module) {
//        moduleRepository.insertModule(module);
//    }

//    public void delete(String sigle) {
//        moduleRepository.deleteBySigle(sigle);
//    }
}
