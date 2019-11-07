package ru.niceaska.currenciesconverter.domain.model;

import org.simpleframework.xml.Element;

import java.math.BigDecimal;

public class Currency {
    private String charCode;
    private long nominal;
    private String name;
    private BigDecimal value;

    public Currency(String charCode, long nominal, String name, BigDecimal value) {
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getCharCode() {
        return charCode;
    }

    public long getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getValue() {
        return value;
    }
}
