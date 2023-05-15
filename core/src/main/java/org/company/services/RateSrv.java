package org.company.services;

import lombok.AllArgsConstructor;
import org.company.controllers.errors.ErrorCatalog;
import org.company.models.Rate;
import org.company.repositories.RateRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Transactional
public class RateSrv {
    private final RateRepo rateRepo;
    private final RateMapper rateMapper;

    public RateDTO create(RateDTO dto) {
        Rate rate = this.rateMapper.toEntity(dto);
        rate.setId(null);
        rate = this.rateRepo.save(rate);
        return this.rateMapper.toDTO(rate);
    }

    @Transactional(readOnly = true)
    public RateDTO get(Long id) {
        Rate rate = this.rateRepo.getReferenceById(id);
        return this.rateMapper.toDTO(rate);
    }

    public RateDTO updatePrice(Long id, Long price) {
        Rate rate = this.rateRepo.getReferenceById(id);
        rate.setPrice(price);
        rate = this.rateRepo.save(rate);
        return this.rateMapper.toDTO(rate);
    }

    public void delete(Long id) {
        if (this.rateRepo.existsById(id)) {
            this.rateRepo.deleteById(id);
        } else {
            throw ErrorCatalog.ELEMENT_NOT_FOUND.getException();
        }
    }

    @Transactional(readOnly = true)
    public RateDTO getByContent(LocalDateTime date, Long productId, Long brandId) {
        Rate rate = this.rateRepo.findByBrandIdAndDateAndProductId( date, productId, brandId);
        return this.rateMapper.toDTO(rate);
    }
}
