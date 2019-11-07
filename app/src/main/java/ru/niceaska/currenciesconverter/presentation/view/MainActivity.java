package ru.niceaska.currenciesconverter.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import ru.niceaska.currenciesconverter.R;
import ru.niceaska.currenciesconverter.presentation.view_model.ConverCurrenciesViewModel;
import ru.niceaska.currenciesconverter.presentation.view_model.CurrenciesViewModelFactory;

public class MainActivity extends AppCompatActivity {

    private ConverCurrenciesViewModel converCurrenciesViewModel;
    private Spinner from_spinner;
    private Spinner to_spinner;
    private EditText convert_edit;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        converCurrenciesViewModel = ViewModelProviders.of(this, new CurrenciesViewModelFactory(getApplicationContext()))
                .get(ConverCurrenciesViewModel.class);
        setUp();
    }

    private void setUp() {
        converCurrenciesViewModel.getCurrenciesNames().observe(this, currenciesNames -> {
                    ArrayAdapter<String> adapterFrom = new ArrayAdapter<>(
                            this, android.R.layout.simple_spinner_item, currenciesNames
                    );
                    adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    from_spinner.setAdapter(adapterFrom);
                    ArrayAdapter<String> adapterTo = new ArrayAdapter<>(
                            this, android.R.layout.simple_spinner_item, currenciesNames
                    );
                    adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    to_spinner.setAdapter(adapterTo);
                });
        converCurrenciesViewModel.loadCurrencies();
    }

    private void init() {
        from_spinner = findViewById(R.id.from_spinner);
        to_spinner = findViewById(R.id.to_spinner);
        convert_edit = findViewById(R.id.convert_value);
        result = findViewById(R.id.result);
    }
}
