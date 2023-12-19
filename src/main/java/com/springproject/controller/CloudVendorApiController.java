package com.springproject.controller;

import com.springproject.model.CloudVendor;
import com.springproject.response.ResponseHandler;
import com.springproject.service.CloudVendorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@RequestMapping("/cloudvendor")
@RequiredArgsConstructor
@Tag(name = "VendorController", description = "This is Cloud Vendor")
public class CloudVendorApiController {

    private final CloudVendorService cloudVendorService;
    private static final Logger lofInfo = LoggerFactory.getLogger(CloudVendor.class);

    //Get Specific Cloud Vendor Details

    @GetMapping("{vendorId}")
    public ResponseEntity<Object> getCloudVendorDetails(@PathVariable String vendorId){
        lofInfo.info("Cloud Vendor logging is enabled");
        lofInfo.debug("Could vendor debug is enabled");
        return ResponseHandler.responseBuilder("Request Vendor Details are given here", HttpStatus.OK, this.cloudVendorService.getCloudVendor(vendorId));

    }

    //Get ALL Cloud Vendor Details
    @GetMapping
    public ResponseEntity<Object> getAllCloudVendorDetails() {
        List<CloudVendor> cloudVendors = cloudVendorService.getAllCloudVendors();
        return ResponseHandler.responseBuilder("Find All Cloud Vendors", HttpStatus.OK, cloudVendors);
    }

    @PostMapping
    public ResponseEntity<Object> createCloudVendorDetails(@RequestBody CloudVendor cloudVendor){
        return ResponseHandler.responseBuilder("Created Successfully", HttpStatus.CREATED, this.cloudVendorService.createCloudVendor(cloudVendor));
    }

    @PutMapping("{vendorId}")
    public String updateCloudVendorDetails(@RequestBody CloudVendor cloudVendor, @PathVariable String vendorId){
        this.cloudVendorService.updateCloudVendor(cloudVendor);
        return "updated successfully";
    }

    @DeleteMapping("{vendorId}")
    public String deleteCloudVendorDetails(@PathVariable String vendorId){
        this.cloudVendorService.deleteCloudVendor(vendorId);
        return "deleted";
    }
}
