package ru.niceaska.currenciesconverter.domain;

import java.util.ArrayList;
import java.util.List;

import ru.niceaska.currenciesconverter.data.repository.CurrenciesConverterRepository;
import ru.niceaska.currenciesconverter.data.repository.IOnLoadCurrenciesListener;
import ru.niceaska.currenciesconverter.domain.model.Currency;

public class LoadCurrenciesInteractor {

    private CurrenciesConverterRepository repository;

    public LoadCurrenciesInteractor(CurrenciesConverterRepository repository) {
        this.repository = repository;
    }

    public void loadCurrenciesNames(IGetCurrenciesName currenciesNameListener) {
        List<String> currenciesNames = new ArrayList<>();
        IOnLoadCurrenciesListener listener  = currencies -> {
            for (Currency currency : currencies) {
                currenciesNames.add(currency.getName());
            }
            currenciesNameListener.onDataReady(currenciesNames, currencies);
        };
        repository.loadCurrenicesList(listener);
    }
}
