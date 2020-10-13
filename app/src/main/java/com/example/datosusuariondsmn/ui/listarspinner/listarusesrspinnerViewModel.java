package com.example.datosusuariondsmn.ui.listarspinner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class listarusesrspinnerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public listarusesrspinnerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is share fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}