package ru.niceaska.currenciesconverter.data.model;

import androidx.annotation.NonNull;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(name = "ValCurs", strict = false)
public class CurrenciesData {

    @ElementList(inline = true)
    private List<CurrencyModel> currencies;

    @NonNull
    public List<CurrencyModel> getCurrencies() {
        return new ArrayList<>(currencies);
    }
}
