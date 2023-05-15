package org.company.services;

import lombok.AllArgsConstructor;
import org.company.currencies.CurrencyDTO;
import org.company.currencies.CurrencyGetter;
import org.company.models.Rate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RateMapper {
    private final CurrencyGetter currencySrv;
    public RateDTO toDTO(Rate entity) {
        CurrencyDTO currency = this.currencySrv.get(entity.getCurrencyCode());
        String finalPrice = this.currencySrv.formatPrice(entity.getPrice(), currency);
        return RateDTO.builder()
                .id(entity.getId())
                .brandId(entity.getBrandId())
                .currencyCode(entity.getCurrencyCode())
                .endDate(entity.getEndDate())
                .price(entity.getPrice())
                .productId(entity.getProductId())
                .startDate(entity.getStartDate())
                .finalPrice(finalPrice)
                .build();
    }
    public Rate toEntity(RateDTO dto) {
        return Rate.builder()
                .id(dto.getId())
                .brandId(dto.getBrandId())
                .currencyCode(dto.getCurrencyCode())
                .endDate(dto.getEndDate())
                .price(dto.getPrice())
                .productId(dto.getProductId())
                .startDate(dto.getStartDate())
                .build();
    }
}
