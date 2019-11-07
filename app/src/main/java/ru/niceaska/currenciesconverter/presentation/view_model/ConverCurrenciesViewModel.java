package ru.niceaska.currenciesconverter.presentation.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ru.niceaska.currenciesconverter.domain.IGetCurrenciesName;
import ru.niceaska.currenciesconverter.domain.LoadCurrenciesInteractor;
import ru.niceaska.currenciesconverter.domain.model.Currency;

public class ConverCurrenciesViewModel extends ViewModel {
    private LoadCurrenciesInteractor loadCurrenciesInteractor;
    private List<Currency> currencyList;
    private MutableLiveData<List<String>> currenciesNames = new MutableLiveData<>();


    public ConverCurrenciesViewModel(LoadCurrenciesInteractor loadCurrenciesInteractor) {
        this.loadCurrenciesInteractor = loadCurrenciesInteractor;
    }

    public LiveData<List<String>> getCurrenciesNames() {
        return currenciesNames;
    }

    public void loadCurrencies() {
        IGetCurrenciesName listener = (names, currencies) -> {
            currenciesNames.postValue(names);
            currencyList = currencies;
        };
        loadCurrenciesInteractor.loadCurrenciesNames(listener);
    }

}
