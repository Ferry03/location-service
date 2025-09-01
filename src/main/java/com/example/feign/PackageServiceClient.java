package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "package-service", url = "${package.service.url:http://localhost:8080}")
public interface PackageServiceClient {
    @GetMapping("/api/package/validate-location/{locationId}")
    boolean validateLocation(@PathVariable String locationId);

    @GetMapping("/api/package/{packageId}/valid-for-location")
    boolean isPackageValidForLocation(@PathVariable Integer packageId);
}