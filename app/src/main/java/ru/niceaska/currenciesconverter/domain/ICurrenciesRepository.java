package ru.niceaska.currenciesconverter.domain;

import ru.niceaska.currenciesconverter.data.repository.IOnLoadCurrenciesListener;

public interface ICurrenciesRepository {
    void loadCurrenicesList(IOnLoadCurrenciesListener listener);
}
