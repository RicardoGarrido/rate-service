package org.company;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.AllArgsConstructor;
import org.company.currencies.CurrencyDTO;
import org.company.currencies.CurrencyGetter;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

@Service
@AllArgsConstructor
public class CurrencySrv implements CurrencyGetter {

    private final CurrencyClient currencyClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(CurrencyClient.class))
            .logLevel(Logger.Level.FULL)
            .target(CurrencyClient.class, "http://localhost:8083/v1/currency");
    @Override
    public CurrencyDTO get(String code) {
        return currencyClient.findByCode(code.toLowerCase());
    }

    public String formatPrice(Long Price, CurrencyDTO currency){
        String pattern = "#." + "#".repeat(currency.getDecimals()) + currency.getSymbol();
        NumberFormat numberFormat = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(Locale.getDefault()));
        return numberFormat.format(Price);
    }
}
