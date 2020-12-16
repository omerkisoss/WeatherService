package com.assignment.WeatherService.api.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Device implements Serializable {

    @Id
    private String id;
    private long lastHeartbeat;
    private double deviceLongitude;
    private double deviceLatitude;
    private double deviceHeight;
    private boolean connected;
    private boolean enabled;
}
