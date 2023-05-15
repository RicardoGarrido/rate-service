package integration;

import org.company.CurrencySrv;
import org.company.currencies.CurrencyDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest(
        classes = CurrencySrv.class
)

public class CurrencySrvIT {

    @Test
    public void getEurCurrenciesRQ() {
        CurrencySrv currencySrv = new CurrencySrv();

        CurrencyDTO currency = currencySrv.get("EUR");

        assertEquals("€", currency.getSymbol());

        assertEquals("EUR", currency.getCode());

        assertEquals(2, currency.getDecimals());
        }


    @Test
    public void getDollarCurrenciesRQ() {
        CurrencySrv currencySrv = new CurrencySrv();

        CurrencyDTO currency = currencySrv.get("USD");

        assertEquals("$", currency.getSymbol());

        assertEquals("USD", currency.getCode());

        assertEquals(2, currency.getDecimals());

         }
}