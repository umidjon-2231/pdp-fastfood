package com.example.adminservice.controller;

import com.example.adminservice.dto.ApiResponse;
import com.example.adminservice.dto.FilialDto;
import com.example.adminservice.entity.Filial;
import com.example.adminservice.mapper.FilialMapper;
import com.example.adminservice.repository.FilialRepository;
import com.example.adminservice.service.FilialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FilialController.class)
public class FilialTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    FilialService filialService;
    @MockBean
    FilialRepository filialRepository;
    @MockBean
    FilialMapper filialMapper;

    private final Filial RECORD_1 = new Filial(1L, "Chorsu", "", "", "", LocalTime.NOON, LocalTime.MIDNIGHT, 1.0f, 2.0f, true);
    private final Filial RECORD_2 = new Filial(2L, "", "", "", "", LocalTime.NOON, LocalTime.MIDNIGHT, 1.0f, 2.0f, true);
    private final Filial RECORD_3 = new Filial(3L, "", "", "", "", LocalTime.NOON, LocalTime.MIDNIGHT, 1.0f, 2.0f, true);

    @Test
    public void getAllRecords() throws Exception {
        List<Filial> list = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));

        //when
        Mockito.when(filialRepository.findByActiveTrue()).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/filial")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].nameUz", is("Chorsu")));
    }

    @Test
    public void getOneRecord() throws Exception {
        Mockito.when(filialRepository.findByIdAndActiveTrue(1L)).thenReturn(Optional.of(RECORD_1));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/filial/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameUz", is("Chorsu")));
    }

}
