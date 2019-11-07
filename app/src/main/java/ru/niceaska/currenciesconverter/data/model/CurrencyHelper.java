package ru.niceaska.currenciesconverter.data.model;

import java.util.ArrayList;
import java.util.List;

import ru.niceaska.currenciesconverter.domain.model.Currency;

public class CurrencyHelper {

    public List<Currency> convertCurrecies(List<CurrencyModel> currencies) {
        List<Currency> convertionRes = new ArrayList<>();
        for (CurrencyModel currency : currencies) {
            Currency cur = new Currency(currency.getCharCode(), currency.getNominal(),
                    currency.getName(), currency.getValue());
            convertionRes.add(cur);
        }
        return convertionRes;
    }
}
