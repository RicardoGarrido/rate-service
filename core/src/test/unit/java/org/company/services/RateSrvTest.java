package org.company.services;

import org.company.models.Rate;
import org.company.repositories.RateRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
public class RateSrvTest {

    private static final LocalDateTime DATE = LocalDateTime.now();
    private static final RateDTO RATE_DTO = RateDTO.builder().id(32L).brandId(32L).build();

    private static final RateDTO EXPECTATION = RateDTO.builder().id(34L).brandId(32L).price(1000L).build();

    private static final RateDTO GET_BY_PARAM_EXPECTATION = RateDTO.builder().id(35L).brandId(32L).build();

    private static final Rate UPDATE_EXPECTATION = Rate.builder().id(34L).brandId(32L).price(1000L).build();
    private static final Rate RATE = Rate.builder().id(35L).brandId(32L).build();

    private static final Rate RATE_TO_SAVE = Rate.builder().brandId(32L).build();

    private static final Rate NEW_RATE = Rate.builder().id(34L).brandId(32L).price(1000L).build();

    private static final Rate NEW_RATE_PRICE_UPDATED = Rate.builder().id(34L).brandId(32L).price(1000L).build();
    @Mock
    private RateRepo rateRepo;
    @Spy
    private RateMapper rateMapper;

    @InjectMocks
    private RateSrv rateSrv;


    @Test
    public void validateCorrectRateIsReturnedOnGet() {
            Mockito.when(rateRepo.getReferenceById(32L)).thenReturn(NEW_RATE);
            RateDTO result = rateSrv.get(32L);
            Mockito.verify(rateRepo, Mockito.times(1)).getReferenceById(32L);
            Assertions.assertEquals(EXPECTATION, result);
    }

    @Test
    public void validateRatePriceIsUpdated() {
        Mockito.when(rateRepo.getReferenceById(32L)).thenReturn(NEW_RATE);
        Mockito.when(rateRepo.save(UPDATE_EXPECTATION)).thenReturn(NEW_RATE_PRICE_UPDATED);
        RateDTO result = rateSrv.updatePrice(32L, 1000L);
        Mockito.verify(rateRepo, Mockito.times(1)).getReferenceById(32L);
        Mockito.verify(rateRepo, Mockito.times(1)).save(UPDATE_EXPECTATION);
        Assertions.assertEquals(EXPECTATION, result);
    }

    @Test
    public void validateRatIsDeleted() {
        Mockito.when(rateRepo.existsById(32L)).thenReturn(true);
        doNothing().when(rateRepo).deleteById(32L);
        rateSrv.delete(32L);
        Mockito.verify(rateRepo, Mockito.times(1)).deleteById(32L);
    }

    @Test
    public void validateCorrectRateIsReturnedOnGetByParams() {
        Mockito.when(rateRepo.findByBrandIdAndDateAndProductId(DATE, 32L, 32L)).thenReturn(RATE);
        RateDTO result = rateSrv.getByContent(DATE, 32L, 32L);
        Mockito.verify(rateRepo, Mockito.times(1)).findByBrandIdAndDateAndProductId(DATE, 32L, 32L);
        Assertions.assertEquals(GET_BY_PARAM_EXPECTATION, result);
    }
}