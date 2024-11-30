package com.example.weis_calculatrice;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ModuleViewModel extends AndroidViewModel {

    private final LiveData<List<Module>> modules;

    public ModuleViewModel(Application application) {
        super(application);
        ModuleRepository moduleRepository = new ModuleRepository(application);
        modules = moduleRepository.getLiveModuleEntityList();
    }

    public LiveData<List<Module>> getAllModules() {
        return modules;
    }
}
