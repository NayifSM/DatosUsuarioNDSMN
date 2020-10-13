package com.example.datosusuariondsmn.ui.listarlview;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class listarlviewViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public listarlviewViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}