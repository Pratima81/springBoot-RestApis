package com.springproject.service.impl;

import com.springproject.model.CloudVendor;
import com.springproject.repository.CloudVendorRepository;
import com.springproject.service.CloudVendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CloudVendorServiceImpl implements CloudVendorService {

    private final CloudVendorRepository cloudVendorRepository;
    @Override
    public CloudVendor createCloudVendor(CloudVendor cloudVendor) {
        return this.cloudVendorRepository.save(cloudVendor);

    }

    @Override
    public String updateCloudVendor(CloudVendor cloudVendor) {
        cloudVendorRepository.save(cloudVendor);
        return "updated successfully";
    }

    @Override
    public String deleteCloudVendor(String cloudVendorId) {
        cloudVendorRepository.deleteById(cloudVendorId);
        return "deleted";
    }

    @Override
    public CloudVendor getCloudVendor(String cloudVendorId) {
        CloudVendor cloudVendor = cloudVendorRepository.findById(cloudVendorId).get();
        return cloudVendor;
    }

    @Override
    public List<CloudVendor> getAllCloudVendors() {
        return this.cloudVendorRepository.findAll();
    }

    @Override
    public List<CloudVendor> getAByVendorName(String name) {
        return cloudVendorRepository.findByVendorName(name);
    }
}
