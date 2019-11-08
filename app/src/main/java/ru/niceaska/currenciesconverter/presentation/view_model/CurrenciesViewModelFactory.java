package ru.niceaska.currenciesconverter.presentation.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.niceaska.currenciesconverter.data.model.CurrencyHelper;
import ru.niceaska.currenciesconverter.data.repository.CurrenciesConverterRepository;
import ru.niceaska.currenciesconverter.domain.ConvertInteractor;
import ru.niceaska.currenciesconverter.domain.ICurrenciesRepository;
import ru.niceaska.currenciesconverter.domain.LoadCurrenciesInteractor;

public class CurrenciesViewModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private final Application app;

    public CurrenciesViewModelFactory(Application app) {
        this.app = app;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == ConverCurrenciesViewModel.class) {
            ICurrenciesRepository currenciesRepository = new CurrenciesConverterRepository(new CurrencyHelper());
            LoadCurrenciesInteractor loadInteractor = new LoadCurrenciesInteractor(currenciesRepository);
            ConvertInteractor convertInteractor = new ConvertInteractor();
            return (T) new ConverCurrenciesViewModel(
                    app,
                    loadInteractor,
                    convertInteractor);

        }
        return null;
    }
}

