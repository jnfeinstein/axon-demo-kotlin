package com.example.demo

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext

@Aggregate
class DemoAggregate() {
    @AggregateIdentifier
    lateinit var id: String

    @CommandHandler
    constructor(
        command: CreateDemoAggregateCommand,
        @Autowired applicationContext: ApplicationContext
    ) : this() {
        apply(
            DemoAggregateCreatedEvent(command.id)
        )
    }

    @EventSourcingHandler
    fun on(event: DemoAggregateCreatedEvent) {
        id = event.id
    }
}


data class CreateDemoAggregateCommand(
    @TargetAggregateIdentifier
    val id: String
)

data class DemoAggregateCreatedEvent(
    val id: String
)