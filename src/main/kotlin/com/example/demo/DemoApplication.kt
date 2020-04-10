package com.example.demo

import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore
import org.axonframework.eventsourcing.eventstore.EventStorageEngine
import org.axonframework.eventsourcing.eventstore.EventStore
import org.axonframework.eventsourcing.eventstore.inmemory.InMemoryEventStorageEngine
import org.axonframework.spring.config.AxonConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean


@SpringBootApplication
class DemoApplication {
	@Bean
	fun eventStore(
			storageEngine: EventStorageEngine,
			configuration: AxonConfiguration
	): EmbeddedEventStore {
		return EmbeddedEventStore.builder()
				.storageEngine(storageEngine)
				.messageMonitor(configuration.messageMonitor(EventStore::class.java, "eventStore"))
				.build()
	}

	@Bean
	fun storageEngine(): EventStorageEngine {
		return InMemoryEventStorageEngine()
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
