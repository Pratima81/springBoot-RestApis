package com.springproject.repository;

import com.springproject.model.CloudVendor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest

public class CloudVendorRepositoryTest {
    @Autowired
    private  CloudVendorRepository cloudVendorRepository;
    private CloudVendor cloudVendor;

    //given - when - then

    @BeforeEach
    void setUp(){
        //lets mock the data/populate the cloud vendor
         cloudVendor= new CloudVendor("1", "Amazon",
                "USA", "xxx");
        cloudVendorRepository.save(cloudVendor); //will be saved in h2
        System.out.println(cloudVendor.getVendorName());
    }

    @AfterEach
    void tearDown(){
        cloudVendor = null;
        cloudVendorRepository.deleteAll();
    }

    //Test case SUCCESS
    @Test
    void testFindByVendorNameFound(){
        // Retrieve the CloudVendor from the repository
        List<CloudVendor> cloudVendors = cloudVendorRepository.findByVendorName("Amazon");

        // Check if the list is not empty before accessing its elements
        assertThat(cloudVendors).isNotEmpty();

        // Check if the first vendor in the list has the expected vendor name
        assertThat(cloudVendors.get(0).getVendorName()).isEqualTo("Amazon");
    }


    //Test case Failure
    @Test
    void testFindByVendorName_NotFound(){
        // Retrieve the CloudVendor from the repository
        List<CloudVendor> cloudVendors = cloudVendorRepository.findByVendorName("GCP");

        // Check if the list is not empty before accessing its elements
        assertThat(cloudVendors.isEmpty()).isTrue();

        //this list should not be empty
//        assertThat(cloudVendors.isEmpty()).isFalse();


    }



}
