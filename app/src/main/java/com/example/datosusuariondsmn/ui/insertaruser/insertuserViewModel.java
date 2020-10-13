package com.example.datosusuariondsmn.ui.insertaruser;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class insertuserViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public insertuserViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}