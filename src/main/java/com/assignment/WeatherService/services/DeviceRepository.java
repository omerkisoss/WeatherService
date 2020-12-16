package com.assignment.WeatherService.services;


import com.assignment.WeatherService.api.model.Device;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DeviceRepository extends CrudRepository<Device, String> {
    Optional<Device> findById(String id);
}
