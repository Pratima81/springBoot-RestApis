package com.springproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.springproject.model.CloudVendor;
import com.springproject.service.CloudVendorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CloudVendorApiController.class)
class CloudVendorApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CloudVendorService cloudVendorService;

    private CloudVendor cloudVendorOne;
    private CloudVendor cloudVendorTwo;

    List<CloudVendor> cloudVendorList = new ArrayList<>();



    @BeforeEach
    void setUp() {
        cloudVendorOne = new CloudVendor("1", "Amazon", "USA", "xxxx");
        cloudVendorTwo = new CloudVendor("2", "GCP", "UX", "yyyyy");

        cloudVendorList.add(cloudVendorOne);
        cloudVendorList.add(cloudVendorTwo);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testGetCloudVendorDetails() throws Exception {
        when(cloudVendorService.getCloudVendor("1"))
                .thenReturn(cloudVendorOne);

        this.mockMvc.perform(get("/cloudvendor/1"))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    void testGetAllCloudVendorDetails() throws Exception {
        when(cloudVendorService.getAllCloudVendors())
                .thenReturn(cloudVendorList);

        this.mockMvc.perform(get("/cloudvendor"))
                .andDo(print()).andExpect(status().isOk());

    }

    @Test
    void testCreateCloudVendorDetails() throws Exception {

        // Mocking data
        CloudVendor cloudVendorOne = new CloudVendor("1", "Amazon",
                "USA", "xxx");

        // Mocking service method
        when(cloudVendorService.createCloudVendor(any(CloudVendor.class)))
                .thenReturn(cloudVendorOne);

        // Convert CloudVendor to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectWriter cloudVendorOneJson = objectMapper.writer().withDefaultPrettyPrinter();
        String cloudVendorOneJsonString = cloudVendorOneJson.writeValueAsString(cloudVendorOne);


        // Perform the request with the provided data
        this.mockMvc.perform(post("/cloudvendor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cloudVendorOneJsonString))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    void testUpdateCloudVendorDetails() throws Exception {
        when(cloudVendorService.updateCloudVendor(cloudVendorOne))
                .thenReturn("updated successfully");


        ObjectMapper objectMapper = new ObjectMapper();
        String cloudVendorOneJson = objectMapper.writeValueAsString(cloudVendorOne);

        this.mockMvc.perform(put("/cloudvendor/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cloudVendorOne)))
                .andDo(print())
                .andExpect(status().isOk());


    }

    @Test
    void testDeleteCloudVendorDetails() throws Exception {
        when(cloudVendorService.deleteCloudVendor("1"))
                .thenReturn("deleted");

        this.mockMvc.perform(delete("/cloudvendor/1"))
                .andDo(print()).andExpect(status().isOk());
    }
}