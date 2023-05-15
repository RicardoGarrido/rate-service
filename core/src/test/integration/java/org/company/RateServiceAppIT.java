package org.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.company.controllers.RateRQ;
import org.company.controllers.RateRS;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = RateServiceApp.class
)
@Transactional
public class RateServiceAppIT {
    private static final RateRQ REQUEST = RateRQ.builder()
            .id(1L)
            .price(200L)
            .brandId(1L)
            .currencyCode("EUR")
            .endDate(LocalDateTime.now().plusDays(5))
            .startDate(LocalDateTime.now())
            .productId(1L)
            .build();

    private static final RateRS EXPECTATION = RateRS.builder()
            .id(1L)
            .price(200L)
            .brandId(1L)
            .currencyCode("EUR")
            .endDate(LocalDateTime.now().plusDays(5))
            .startDate(LocalDateTime.now())
            .productId(1L)
            .build();

    private static final String DELETE_EXPECTATION = "Rate deleted successfully";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void ratePostRQ() throws Exception {
        MvcResult result = this.mockMvc.perform(
                MockMvcRequestBuilders
                        .post("/v1/rate")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.objectMapper.writeValueAsString(REQUEST)))
                        .andExpect(status().isOk())
                        .andReturn();


        RateRS response =  this.objectMapper.readValue(result.getResponse().getContentAsString(), RateRS.class);
        assertNotNull(response.getId());
        assertEquals(response.getProductId(), EXPECTATION.getProductId());

    }

    @Test
    public void testRateGetAfterRatePostRQ() throws Exception {
        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/rate")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(REQUEST)))
                .andExpect(status().isOk())
                .andReturn();


        RateRS response =  this.objectMapper.readValue(result.getResponse().getContentAsString(), RateRS.class);
        assertNotNull(response.getId());
        assertEquals(response.getProductId(), EXPECTATION.getProductId());

        MvcResult getResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/v1/rate/{id}", response.getId())
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        RateRS getResponse =  this.objectMapper.readValue(getResult.getResponse().getContentAsString(), RateRS.class);

        assertEquals(response, getResponse);

    }

    @Test
    public void testRateUpdatePriceAfterRatePostRQ() throws Exception {
        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/rate")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(REQUEST)))
                .andExpect(status().isOk())
                .andReturn();


        RateRS response =  this.objectMapper.readValue(result.getResponse().getContentAsString(), RateRS.class);
        assertNotNull(response.getId());
        assertEquals(response.getProductId(), EXPECTATION.getProductId());

        MvcResult putResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .put("/v1/rate/{id}/price", response.getId())
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("price", String.valueOf(220L)))
                .andExpect(status().isOk())
                .andReturn();

        RateRS putResponse =  this.objectMapper.readValue(putResult.getResponse().getContentAsString(), RateRS.class);

        assertNotEquals(response.getPrice(), putResponse.getPrice());

    }

    @Test
    public void testRateDeleteAfterRatePostRQ() throws Exception {
        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/rate")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(REQUEST)))
                .andExpect(status().isOk())
                .andReturn();


        RateRS response =  this.objectMapper.readValue(result.getResponse().getContentAsString(), RateRS.class);
        assertNotNull(response.getId());
        assertEquals(response.getProductId(), EXPECTATION.getProductId());

        MvcResult deleteResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/v1/rate/{id}", response.getId())
                                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();

        String deleteResponse = deleteResult.getResponse().getContentAsString();

        assertEquals(DELETE_EXPECTATION, deleteResponse);

    }

    @Test
    public void testRateDeleteNotFound() throws Exception {

        this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .delete("/v1/rate/{id}", 22L)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    public void testRateGetByParamAfterRatePostRQ() throws Exception {
        MvcResult result = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/rate")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.objectMapper.writeValueAsString(REQUEST)))
                .andExpect(status().isOk())
                .andReturn();


        RateRS response =  this.objectMapper.readValue(result.getResponse().getContentAsString(), RateRS.class);
        assertNotNull(response.getId());
        assertEquals(response.getProductId(), EXPECTATION.getProductId());

        MvcResult getResult = this.mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/v1/rate")
                                .param("date", LocalDateTime.now().plusDays(2).format(DateTimeFormatter.ISO_DATE_TIME))
                                .param("productId", String.valueOf(1L))
                                .param("brandId", String.valueOf(1L))
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        RateRS getResponse =  this.objectMapper.readValue(getResult.getResponse().getContentAsString(), RateRS.class);

        assertEquals(response, getResponse);

    }
}