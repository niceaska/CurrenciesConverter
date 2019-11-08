package ru.niceaska.currenciesconverter.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import ru.niceaska.currenciesconverter.domain.model.Currency;

public class ConvertInteractor {

    private final NumberFormat numberFormat = new DecimalFormat("#.##");
    private int fromPosition;
    private int toPosition;

    private static final int SCALE = 5;
    private static final int OUT_SCALE = 2;


    public void setFromPosition(int fromPosition) {
        this.fromPosition = fromPosition;
    }

    public void setToPosition(int toPosition) {
        this.toPosition = toPosition;
    }

    public String convert(List<Currency> currencyList, String amount, String format) {

        if (amount != null && !amount.equals("")) {
            Currency from = currencyList.get(fromPosition);
            Currency to = currencyList.get(toPosition);
            BigDecimal parsedAmount;
            try {
                parsedAmount = new BigDecimal(amount);
            } catch (NumberFormatException e) {
                return null;
            }
            BigDecimal result = parsedAmount
                    .multiply(from.getValue())
                    .multiply(new BigDecimal(to.getNominal()))
                    .divide(to.getValue(), SCALE, RoundingMode.HALF_UP)
                    .divide(new BigDecimal(from.getNominal()), SCALE, RoundingMode.HALF_UP);
            try {
                String formattedResult = numberFormat.format(result.setScale(OUT_SCALE, RoundingMode.HALF_UP));
                return String.format(format, formattedResult, to.getCharCode());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }
}
