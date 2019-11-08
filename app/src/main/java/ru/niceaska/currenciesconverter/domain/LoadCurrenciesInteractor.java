package ru.niceaska.currenciesconverter.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ru.niceaska.currenciesconverter.data.repository.IOnLoadCurrenciesListener;
import ru.niceaska.currenciesconverter.domain.model.Currency;

public class LoadCurrenciesInteractor {

    private ICurrenciesRepository repository;

    public LoadCurrenciesInteractor(ICurrenciesRepository repository) {
        this.repository = repository;
    }

    public void loadCurrenciesNames(IGetCurrenciesName currenciesNameListener) {
        List<String> currenciesNames = new ArrayList<>();
        IOnLoadCurrenciesListener listener  = currencies -> {
            Currency ruble = new Currency(
                    "RUB", 1, "Российский рубль", new BigDecimal(1)
            );
            if (!currencies.contains(ruble)) {
                currencies.add(ruble);
            }
            for (Currency currency : currencies) {
                currenciesNames.add(currency.getName());
            }
            currenciesNameListener.onDataReady(currenciesNames, currencies);
        };
        repository.loadCurrenicesList(listener);
    }
}
