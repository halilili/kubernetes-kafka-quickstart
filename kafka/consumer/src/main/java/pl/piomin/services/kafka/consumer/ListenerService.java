package pl.piomin.services.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import pl.piomin.services.kafka.common.Info;

@Service
public class ListenerService {

    private static final Logger LOG = LoggerFactory.getLogger(ListenerService.class);

    @KafkaListener(id = "info", topics = "${app.in.topic}")
    public void onMessage(@Payload Info info,
                          @Header(name = KafkaHeaders.RECEIVED_KEY, required = false) Long key,
                          @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) {
        LOG.info("Received(key={}, partition={}): {}", key, partition, info);
    }

}
