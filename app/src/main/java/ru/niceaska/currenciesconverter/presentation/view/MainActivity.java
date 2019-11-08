package ru.niceaska.currenciesconverter.presentation.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import ru.niceaska.currenciesconverter.R;
import ru.niceaska.currenciesconverter.presentation.view_model.ConverCurrenciesViewModel;
import ru.niceaska.currenciesconverter.presentation.view_model.CurrenciesViewModelFactory;

import static ru.niceaska.currenciesconverter.presentation.view_model.ConverCurrenciesViewModel.ConvertionIndex.FROM;
import static ru.niceaska.currenciesconverter.presentation.view_model.ConverCurrenciesViewModel.ConvertionIndex.TO;

public class MainActivity extends AppCompatActivity {

    private ConverCurrenciesViewModel converCurrenciesViewModel;
    private Spinner fromSpinner;
    private Spinner toSpinner;
    private Button convertButton;
    private EditText convertEdit;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setListeners();
        setUp();
    }

    private void setUp() {
        converCurrenciesViewModel = ViewModelProviders.of(this,
                new CurrenciesViewModelFactory(getApplication()))
                .get(ConverCurrenciesViewModel.class);
        converCurrenciesViewModel.getCurrenciesNames().observe(this, currenciesNames -> {
                    ArrayAdapter<String> adapterFrom = new ArrayAdapter<>(
                            this, android.R.layout.simple_spinner_item, currenciesNames
                    );
                    adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            fromSpinner.setAdapter(adapterFrom);
                    ArrayAdapter<String> adapterTo = new ArrayAdapter<>(
                            this, android.R.layout.simple_spinner_item, currenciesNames
                    );
                    adapterTo.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            toSpinner.setAdapter(adapterTo);
                });
        converCurrenciesViewModel.getButtonClickable().observe(this, isClickable -> convertButton.setEnabled(isClickable));
        converCurrenciesViewModel.getConvResult().observe(this, resText -> result.setText(resText));
        converCurrenciesViewModel.getConvertionError().observe(this, isError -> {
            if (isError) {
                Toast.makeText(this, getResources().getString(R.string.convertation_error), Toast.LENGTH_LONG).show();
            }
        });
        converCurrenciesViewModel.loadCurrencies();
    }

    private void init() {
        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        convertEdit = findViewById(R.id.convert_value);
        result = findViewById(R.id.result);
        convertButton = findViewById(R.id.convert_button);
    }

    private void setListeners() {
        convertButton.setOnClickListener(v -> converCurrenciesViewModel.convertCurrencies(
                convertEdit.getText().toString())
        );

        fromSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                converCurrenciesViewModel.setConvertionIndex(FROM, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        toSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                converCurrenciesViewModel.setConvertionIndex(TO, position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        convertEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                converCurrenciesViewModel.onTyping(s.toString());


            }
        });
    }
}
