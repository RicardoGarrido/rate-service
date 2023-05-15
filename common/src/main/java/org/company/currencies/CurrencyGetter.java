package org.company.currencies;

import java.util.List;

public interface CurrencyGetter {
    CurrencyDTO get(String code);

    String formatPrice(Long price, CurrencyDTO currency);
}
