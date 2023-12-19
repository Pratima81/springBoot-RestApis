package com.springproject.service.impl;

import com.springproject.model.CloudVendor;
import com.springproject.repository.CloudVendorRepository;
import com.springproject.service.CloudVendorService;
import org.assertj.core.api.ObjectAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//we are not going to use jupiter assertion
//import static org.junit.jupiter.api.Assertions.*;

class CloudVendorServiceImplTest {
    @Mock
    private CloudVendorRepository cloudVendorRepository;
//    @InjectMocks
    private CloudVendorService cloudVendorService;
    AutoCloseable autoCloseable;
    CloudVendor cloudVendor;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        cloudVendorService = new CloudVendorServiceImpl(cloudVendorRepository);
        cloudVendor = new CloudVendor("1", "Amazon", "USA", "xxxx");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.createCloudVendor(cloudVendor)).isEqualTo(cloudVendor);
    }

    @Test
    void testUpdateCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.save(cloudVendor)).thenReturn(cloudVendor);
        assertThat(cloudVendorService.updateCloudVendor(cloudVendor)).isEqualTo("updated successfully");
    }

    @Test
    void testDeleteCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class, Mockito.CALLS_REAL_METHODS);

//        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
//        assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("deleted");

        doAnswer(Answers.CALLS_REAL_METHODS).when(
                cloudVendorRepository
        ).deleteById(any());


        assertThat(cloudVendorService.deleteCloudVendor("1")).isEqualTo("deleted");


    }

    @Test
    void testGetCloudVendor() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findById("1")).thenReturn(Optional.ofNullable(cloudVendor));
        assertThat(cloudVendorService.getCloudVendor("1")).isEqualTo(cloudVendor);

    }

    @Test
    void testGetByVendorName(){
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        when(cloudVendorRepository.findByVendorName("Amazon")).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor))
        );

        assertThat(cloudVendorService.getAByVendorName("Amazon").get(0).getVendorId())
                .isEqualTo(cloudVendor.getVendorId());

    }

    @Test
    void testGetAllCloudVendors() {
        mock(CloudVendor.class);
        mock(CloudVendorRepository.class);

        // Mocking repository behavior
        when(cloudVendorRepository.findAll()).thenReturn(
                new ArrayList<CloudVendor>(Collections.singleton(cloudVendor))
        );


        assertThat(cloudVendorService.getAllCloudVendors()).isNotEmpty();

        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorName())
                .isEqualTo(cloudVendor.getVendorName());

        assertThat(cloudVendorService.getAllCloudVendors().get(0).getVendorId())
                .isEqualTo(cloudVendor.getVendorId());
    }
}