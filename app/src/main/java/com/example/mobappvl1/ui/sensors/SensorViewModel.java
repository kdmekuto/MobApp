package com.example.mobappvl1.ui.sensors;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SensorViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SensorViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String s) {
       mText.setValue(s);
    }
}