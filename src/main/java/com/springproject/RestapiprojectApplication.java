package com.springproject;

import com.springproject.model.StoreInformation;
import com.springproject.repository.StoreInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class RestapiprojectApplication implements CommandLineRunner {

    private final StoreInformationRepository storeInformationRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestapiprojectApplication.class, args);
    }

    @Override
    public void run(String ...run){
        StoreInformation s1 = new StoreInformation(1, "Amazon", "description", "11111");
        StoreInformation s2 = new StoreInformation(2, "Amazon1", "description", "11111");
        StoreInformation s3 = new StoreInformation(3, "Amazon2", "description", "11111");
        StoreInformation s4 = new StoreInformation(4, "Amazon3", "description", "11111");
        storeInformationRepository.save(s1);
        storeInformationRepository.save(s2);
        storeInformationRepository.save(s3);
        storeInformationRepository.save(s4);

        storeInformationRepository.findByStoreName("Amazon").forEach(
                val -> System.out.println(val)
        );

        System.out.println(storeInformationRepository.count());
    }



}
