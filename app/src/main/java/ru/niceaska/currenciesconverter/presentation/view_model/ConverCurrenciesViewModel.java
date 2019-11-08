package ru.niceaska.currenciesconverter.presentation.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import ru.niceaska.currenciesconverter.R;
import ru.niceaska.currenciesconverter.domain.ConvertInteractor;
import ru.niceaska.currenciesconverter.domain.IGetCurrenciesName;
import ru.niceaska.currenciesconverter.domain.LoadCurrenciesInteractor;
import ru.niceaska.currenciesconverter.domain.model.Currency;

public class ConverCurrenciesViewModel extends AndroidViewModel {

    private LoadCurrenciesInteractor loadCurrenciesInteractor;
    private ConvertInteractor convertInteractor;
    private List<Currency> currencyList;
    private MutableLiveData<List<String>> currenciesNames = new MutableLiveData<>();
    private MutableLiveData<String> convResult = new MutableLiveData<>();
    private MutableLiveData<Boolean> buttonClickable = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> convertionError = new MutableLiveData<>(false);


    public enum ConvertionIndex {
        FROM,
        TO
    }

    ConverCurrenciesViewModel(@NonNull Application application,
                              LoadCurrenciesInteractor loadCurrenciesInteractor,
                              ConvertInteractor convertInteractor) {
        super(application);
        this.loadCurrenciesInteractor = loadCurrenciesInteractor;
        this.convertInteractor = convertInteractor;
    }

    public LiveData<List<String>> getCurrenciesNames() {
        return currenciesNames;
    }

    public LiveData<String> getConvResult() {
        return convResult;
    }

    public LiveData<Boolean> getButtonClickable() {
        return buttonClickable;
    }

    public LiveData<Boolean> getConvertionError() {
        return convertionError;
    }



    public void loadCurrencies() {
        IGetCurrenciesName listener = (names, currencies) -> {
            currenciesNames.postValue(names);
            currencyList = currencies;
        };
        loadCurrenciesInteractor.loadCurrenciesNames(listener);
    }

    public void convertCurrencies(String amount) {
        String resultConvertion = convertInteractor.convert(currencyList, amount,
                getApplication().getString(R.string.formatted_string));
        if (resultConvertion == null) {
            convertionError.setValue(true);
            convertionError.postValue(false);
        } else {
            convResult.postValue(resultConvertion);
        }
    }

    public void setConvertionIndex(ConvertionIndex index, int position) {
        switch (index) {
            case FROM:
                convertInteractor.setFromPosition(position);
                break;
            case TO:
                convertInteractor.setToPosition(position);
                break;
            default:
                break;
        }
    }

    public void onTyping(String value) {
        if (value == null || value.equals("")) {
            convResult.setValue("");
            buttonClickable.setValue(false);
        } else {
            buttonClickable.setValue(true);
        }
    }
}
