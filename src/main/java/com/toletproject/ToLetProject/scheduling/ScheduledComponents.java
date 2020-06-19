package com.toletproject.ToLetProject.scheduling;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class ScheduledComponents {


    @Scheduled(cron = "0 35-47/3 14 * * ?", zone = "GMT+6:00")
    public void updateDatabase1() {


    }


}
