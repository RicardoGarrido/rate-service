package org.company.services;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RateDTO {
    private Long id;
    private Long brandId;
    private Long productId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long price;
    private String currencyCode;
    private String finalPrice;
}
