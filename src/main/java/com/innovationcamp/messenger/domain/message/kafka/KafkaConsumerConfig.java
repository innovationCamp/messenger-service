package com.innovationcamp.messenger.domain.message.kafka;

import com.innovationcamp.messenger.domain.message.dto.MessageRequestDto;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    /**
     * listener(consumer)는 Kafka로부터 메시지를 받는 곳
     * GROUP_ID_CONFIG는 consumer group id를 설정
     * KEY_DESERIALIZER_CLASS_CONFIG와 VALUE_DESERIALIZER_CLASS_CONFIG는 Kafka에서 받은 데이터의 키와 값을 역직렬화함
     * AUTO_OFFSET_RESET_CONFIG에는 latest(가장 최근에 생성된 메시지를 offset reset), earliest(가장 오래된 메시지를), none의 값을 입력할 수 있음
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageRequestDto> sendKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(sendConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MessageRequestDto> saveKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MessageRequestDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(saveConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, MessageRequestDto> sendConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(sendConsumerConfigurations(), new StringDeserializer(), new JsonDeserializer<>(MessageRequestDto.class));
    }

    @Bean
    public ConsumerFactory<String, MessageRequestDto> saveConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(saveConsumerConfigurations(), new StringDeserializer(), new JsonDeserializer<>(MessageRequestDto.class));
    }

    @Bean
    public Map<String, Object> sendConsumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.SEND_GROUP_ID);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return configurations;
    }

    @Bean
    public Map<String, Object> saveConsumerConfigurations() {
        Map<String, Object> configurations = new HashMap<>();
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.SAVE_GROUP_ID);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configurations.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return configurations;
    }
}
