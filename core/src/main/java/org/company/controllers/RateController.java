package org.company.controllers;

import lombok.AllArgsConstructor;
import org.company.services.RateDTO;
import org.company.services.RateSrv;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("v1")
@AllArgsConstructor //lombok
public class RateController {
    private final RateSrv rateSrv;

    @PostMapping(
            path = "rate",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public RateRS create(
            @RequestBody RateRQ rateRQ
    ) {
        RateDTO dto = this.fromRQ(rateRQ);
        RateDTO finalDto = this.rateSrv.create(dto);
        return this.toRS(finalDto);
    }

    @GetMapping(
            path = "rate/{id}"
    )
    public RateRS get(@PathVariable("id") Long id) {
        RateDTO dto = this.rateSrv.get(id);
        return this.toRS(dto);
    }

    @PutMapping(
            path = "rate/{id}/price",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public RateRS updatePrice(
            @PathVariable("id") Long id,
            @RequestParam("price") Long price) {
        RateDTO dto = this.rateSrv.updatePrice(id, price);
        return this.toRS(dto);
    }

    @DeleteMapping(
            path = "rate/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<String> delete(
            @PathVariable("id") Long id
    ) {
        rateSrv.delete(id);
        return ResponseEntity.ok("Rate deleted successfully");
    }

    @GetMapping("/rate")
    public RateRS getByParams(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
            @RequestParam("productId") Long productId,
            @RequestParam("brandId") Long brandId
    ) {
        RateDTO dto = this.rateSrv.getByContent(date, productId, brandId);
        return this.toRS(dto);
    }

    private RateDTO fromRQ(RateRQ rateRQ) {
        return RateDTO.builder()
                .id(rateRQ.getId())
                .brandId(rateRQ.getBrandId())
                .currencyCode(rateRQ.getCurrencyCode())
                .endDate(rateRQ.getEndDate())
                .price(rateRQ.getPrice())
                .productId(rateRQ.getProductId())
                .startDate(rateRQ.getStartDate())
                .build();
    }

    private RateRS toRS(RateDTO dto) {
        return RateRS.builder()
                .id(dto.getId())
                .brandId(dto.getBrandId())
                .currencyCode(dto.getCurrencyCode())
                .endDate(dto.getEndDate())
                .price(dto.getPrice())
                .productId(dto.getProductId())
                .startDate(dto.getStartDate())
                .finalPrice(dto.getFinalPrice())
                .build();
    }
}
