package ru.niceaska.currenciesconverter.data.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import ru.niceaska.currenciesconverter.data.model.CurrenciesData;
import ru.niceaska.currenciesconverter.data.model.CurrencyHelper;
import ru.niceaska.currenciesconverter.data.model.CurrencyModel;
import ru.niceaska.currenciesconverter.domain.ICurrenciesRepository;

public class CurrenciesConverterRepository implements ICurrenciesRepository {
    private static final String BASE_URL = "http://www.cbr.ru";

    private final ICurrenciesApi currenciesApi;
    private final CurrencyHelper currencyHelper;
    private String LOAD_ERROR = "loadError";

    public CurrenciesConverterRepository(@NonNull CurrencyHelper currencyHelper) {
        this.currencyHelper = currencyHelper;
        Strategy strategy = new AnnotationStrategy();
        Serializer serializer = new Persister(strategy);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
                .build();
        currenciesApi = retrofit.create(ICurrenciesApi.class);
    }

    @Override
    public void loadCurrenicesList(IOnLoadCurrenciesListener listener) {
        currenciesApi.loadCurrencies().enqueue(new Callback<CurrenciesData>() {
            @Override
            public void onResponse(Call<CurrenciesData> call, Response<CurrenciesData> response) {
                if (response.body() != null) {
                    List<CurrencyModel> modelList = response.body().getCurrencies();
                    listener.onLoadFinish(currencyHelper.convertCurrecies(modelList));
                    Log.d("LOAD", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<CurrenciesData> call, Throwable t) {
                Log.d(LOAD_ERROR, "onFailure: " + t.getMessage());
            }
        });
    }
}
