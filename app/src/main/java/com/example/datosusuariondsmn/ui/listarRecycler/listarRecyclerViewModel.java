package com.example.datosusuariondsmn.ui.listarRecycler;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class listarRecyclerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public listarRecyclerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is listarRecycler fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}