package com.assignment.WeatherService.services;

import com.assignment.WeatherService.api.model.Device;
import com.assignment.WeatherService.errorhandler.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    public Device getDeviceById(String id) {
        log.info("checking in DB the device");
       return deviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Device with ID :"+id+" Not Found!"));
    }
}
