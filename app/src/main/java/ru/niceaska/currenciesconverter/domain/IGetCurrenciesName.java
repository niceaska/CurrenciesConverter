package ru.niceaska.currenciesconverter.domain;

import java.util.List;

import ru.niceaska.currenciesconverter.domain.model.Currency;

public interface IGetCurrenciesName {
    void onDataReady(List<String> names, List<Currency> currencies);
}
