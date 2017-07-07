package org.egov.propertyWorkflow.consumer;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.egov.models.PropertyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class WorkflowProducer {
	@Autowired
	Environment environment;

	@Autowired
	KafkaTemplate<String, PropertyRequest> kafkaTemplate;

	/**
	 * This method will send message to the kafka queue and will process the
	 * success or failure callback
	 */
	public void sendMessage(String topic, PropertyRequest propertyRequest) {
		ListenableFuture<SendResult<String, PropertyRequest>> future = kafkaTemplate().send(topic, propertyRequest);
		future.addCallback(new ListenableFutureCallback<SendResult<String, PropertyRequest>>() {
			@Override
			public void onSuccess(SendResult<String, PropertyRequest> stringTSendResult) {

			}

			@Override
			public void onFailure(Throwable throwable) {

			}
		});
	}

	/**
	 * This method will return map object for producer configuration
	 */
	@Bean
	public Map<String, Object> producerConfig() {
		Map<String, Object> producerProperties = new HashMap<String, Object>();
		producerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				environment.getProperty("bootstrap.server.config"));
		producerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		producerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
		return producerProperties;
	}

	/**
	 * This method will return producer factory bean based on producer
	 * configuration
	 */
	@Bean
	public ProducerFactory<String, PropertyRequest> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfig());
	}

	/**
	 * This method will return kafka template bean based on producer factory
	 * bean
	 */
	@Bean
	public KafkaTemplate<String, PropertyRequest> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	/**
	 * This method will send property request to kakfa producer
	 */
	public void send(String topic, PropertyRequest propertyRequest) {
		kafkaTemplate.send(topic, propertyRequest);
	}
}