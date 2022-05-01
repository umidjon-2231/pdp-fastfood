package com.example.adminservice.listener;

import com.example.adminservice.entity.Category;
import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;
import org.springframework.stereotype.Component;

@Component
public class HibernateEventListener implements PreUpdateEventListener {
    @Override
    public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
        Object entity = preUpdateEvent.getEntity();
        if (entity instanceof Category category) {
            //category update listener
        }
        return false;
    }
}