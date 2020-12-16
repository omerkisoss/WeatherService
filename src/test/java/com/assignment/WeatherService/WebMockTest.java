package com.assignment.WeatherService;


import static org.mockito.Mockito.when;
import com.assignment.WeatherService.api.model.Device;
import com.assignment.WeatherService.services.DeviceRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
@RunWith(MockitoJUnitRunner.class)
public class WebMockTest {

    @Mock
    DeviceRepository deviceRepository;

    @Test
    public void findDeviceByIdTest() throws Exception {
        Device device = Device.builder().connected(true).enabled(true).lastHeartbeat(1581948022026L).id("USA-027").deviceHeight(3.4).deviceLatitude(-74.005974).deviceLongitude(40.712776).build();
        when(deviceRepository.findById("USA-027")).thenReturn(Optional.of(device));
    }
}