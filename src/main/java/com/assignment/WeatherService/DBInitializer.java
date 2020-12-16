package com.assignment.WeatherService;

import com.assignment.WeatherService.api.model.Device;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Slf4j
public class DBInitializer {

    @PersistenceContext
    EntityManager em;

    @EventListener(ApplicationStartedEvent.class)
    @Transactional
    public void init(SpringApplicationEvent event) {
        log.info("Initializing db: {}", event);
            em.persist(Device.builder().connected(true).enabled(true).lastHeartbeat(1581948022026L).id("USA-027").deviceHeight(3.4).deviceLatitude(-74.005974).deviceLongitude(40.712776).build());
            em.persist(Device.builder().connected(true).enabled(true).lastHeartbeat(1581948022027L).id("USA-028").deviceHeight(3.4).deviceLatitude(33.441792).deviceLongitude(-94.037689).build());


    }
}
