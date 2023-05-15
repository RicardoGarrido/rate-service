package org.company;

import feign.Param;
import feign.RequestLine;
import org.company.currencies.CurrencyDTO;

import java.util.List;

public interface CurrencyClient {
    @RequestLine("GET /{code}")
    CurrencyDTO findByCode(@Param("code") String code);

    @RequestLine("GET")
    List<CurrencyDTO> findAll();
}
