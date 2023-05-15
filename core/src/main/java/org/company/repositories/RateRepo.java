package org.company.repositories;

import org.company.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface RateRepo extends JpaRepository<Rate, Long> {

    @Query("SELECT r FROM Rate r WHERE r.brandId = :brandId AND :date BETWEEN r.startDate AND r.endDate AND r.productId = :productId")
    Rate findByBrandIdAndDateAndProductId(
            @Param("date") LocalDateTime date,
            @Param("productId") Long productId,
            @Param("brandId") Long brandId
    );

}
