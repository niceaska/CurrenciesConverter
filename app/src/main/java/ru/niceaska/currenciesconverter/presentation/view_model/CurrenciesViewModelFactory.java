package ru.niceaska.currenciesconverter.presentation.view_model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import ru.niceaska.currenciesconverter.data.model.CurrencyHelper;
import ru.niceaska.currenciesconverter.data.repository.CurrenciesConverterRepository;
import ru.niceaska.currenciesconverter.domain.LoadCurrenciesInteractor;

public class CurrenciesViewModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private final Context appContext;

    public CurrenciesViewModelFactory(Context appContext) {
        this.appContext = appContext;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ConverCurrenciesViewModel.class) {
            CurrenciesConverterRepository currenciesRepository = new CurrenciesConverterRepository(new CurrencyHelper());
            LoadCurrenciesInteractor interactor = new LoadCurrenciesInteractor(currenciesRepository);
            return (T) new ConverCurrenciesViewModel(
                    interactor);

        }
        return null;
    }
}

