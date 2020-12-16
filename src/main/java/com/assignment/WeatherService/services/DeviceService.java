package com.assignment.WeatherService.services;

import com.assignment.WeatherService.api.model.Device;

import javax.persistence.EntityNotFoundException;

public interface DeviceService {
    Device getDeviceById(String id) throws EntityNotFoundException;
}
