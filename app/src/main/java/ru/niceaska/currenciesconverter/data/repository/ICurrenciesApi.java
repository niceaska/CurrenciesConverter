package ru.niceaska.currenciesconverter.data.repository;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import ru.niceaska.currenciesconverter.data.model.CurrenciesData;
import ru.niceaska.currenciesconverter.data.model.CurrencyModel;

public interface ICurrenciesApi {
    @GET("/scripts/XML_daily.asp")
    Call<CurrenciesData> loadCurrencies();
}
