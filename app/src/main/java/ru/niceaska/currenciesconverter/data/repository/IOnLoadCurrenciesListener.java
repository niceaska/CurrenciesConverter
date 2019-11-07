package ru.niceaska.currenciesconverter.data.repository;

import java.util.List;

import ru.niceaska.currenciesconverter.domain.model.Currency;

public interface IOnLoadCurrenciesListener {
    void onLoadFinish(List<Currency> currencies);
}
