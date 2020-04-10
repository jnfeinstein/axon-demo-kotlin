package com.example.demo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.modelling.command.TargetAggregateIdentifier
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class DemoAggregate() {
    @AggregateIdentifier
    lateinit var id: DemoId

    @CommandHandler
    constructor(command: ConstructorDemoAggregateCommand) : this() {
        apply(
            DemoAggregateCreatedEvent(command.id)
        )
    }

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: MethodDemoAggregateCommand) {
        apply(
            DemoAggregateCreatedEvent(command.id)
        )
    }

    @EventSourcingHandler
    fun on(event: DemoAggregateCreatedEvent) {
        id = event.id
    }
}

interface DemoId

data class DemoWrappedValueId(val value: String) : DemoId {
    companion object {
        @JvmStatic
        @JsonCreator
        fun of(value: String) = DemoWrappedValueId(value)
    }

    @JsonValue
    override fun toString() = value

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is String -> value == other
            else -> super.equals(other)
        }
    }

    override fun hashCode() = value.hashCode()
}

enum class DemoEnumId : DemoId {
    BAZ;
}

data class ConstructorDemoAggregateCommand(
    @TargetAggregateIdentifier
    val id: DemoId
)

data class MethodDemoAggregateCommand(
        @TargetAggregateIdentifier
        val id: DemoId
)

data class DemoAggregateCreatedEvent(
    val id: DemoId
)