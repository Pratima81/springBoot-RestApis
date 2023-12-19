package com.springproject.service;


import com.springproject.model.CloudVendor;

import java.util.List;

public interface CloudVendorService {
    public CloudVendor createCloudVendor(CloudVendor cloudVendor);
    public String updateCloudVendor(CloudVendor cloudVendor);
    public String deleteCloudVendor(String cloudVendorId);
    public  CloudVendor getCloudVendor(String cloudVendorId);

    public List<CloudVendor> getAllCloudVendors();

    public List<CloudVendor> getAByVendorName(String name);



}
