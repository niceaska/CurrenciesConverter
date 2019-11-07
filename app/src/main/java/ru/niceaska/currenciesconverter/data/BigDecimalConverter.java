package ru.niceaska.currenciesconverter.data;

import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

import java.math.BigDecimal;

public class BigDecimalConverter implements Converter<BigDecimal> {

    @Override
    public BigDecimal read(InputNode node) throws Exception {
        return new BigDecimal(node.getValue().replace(',', '.'));
    }

    @Override
    public void write(OutputNode node, BigDecimal value) throws Exception {
        throw new UnsupportedOperationException("Serialization is not supported");
    }
}
