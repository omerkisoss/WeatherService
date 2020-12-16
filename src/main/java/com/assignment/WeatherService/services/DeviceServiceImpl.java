package com.assignment.WeatherService.services;

import com.assignment.WeatherService.api.model.Device;
import com.assignment.WeatherService.errorhandler.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    public Device getDeviceById(String id) {
       return deviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Device with ID :"+id+" Not Found!"));
    }
}
