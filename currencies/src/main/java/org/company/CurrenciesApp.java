package org.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.company.data.Currency;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Hello world!
 *
 */
public class CurrenciesApp
{
    public static void main( String[] args ) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Currency euro = Currency.builder().symbol("€").code("EUR").decimals(2).build();
        Currency dollar = Currency.builder().symbol("$").code("USD").decimals(2).build();
        List<Currency> currencies = List.of(euro, dollar);

        WireMockServer wireMockServer = new WireMockServer(8083);
        wireMockServer.start();

        configureFor("localhost", 8083);
        stubFor(get(urlEqualTo("/v1/currency/eur")).willReturn(aResponse().withBody(objectMapper.writeValueAsString(euro))));
        stubFor(get(urlEqualTo("/v1/currency/usd")).willReturn(aResponse().withBody(objectMapper.writeValueAsString(dollar))));
        stubFor(get(urlEqualTo("/v1/currency")).willReturn(aResponse().withBody(objectMapper.writeValueAsString(currencies))));
    }
}
