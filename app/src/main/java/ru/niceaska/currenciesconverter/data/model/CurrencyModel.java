package ru.niceaska.currenciesconverter.data.model;

import android.renderscript.Sampler;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.convert.Convert;

import java.math.BigDecimal;

import ru.niceaska.currenciesconverter.data.BigDecimalConverter;

@Root(name = "Valute", strict = false)
public class CurrencyModel {

    @Element(name = "CharCode")
    private String charCode;

    @Element(name = "Nominal")
    private long nominal;

    @Element(name = "Name")
    private String name;

    @Element(name = "Value")
    @Convert(BigDecimalConverter.class)
    private BigDecimal value;


    public CurrencyModel() {
    }

    public CurrencyModel(String charCode, long nominal, String name, BigDecimal value) {
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public long getNominal() {
        return nominal;
    }

    public void setNominal(long nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
