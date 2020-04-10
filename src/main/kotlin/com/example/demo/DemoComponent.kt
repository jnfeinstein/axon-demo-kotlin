package com.example.demo

import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.common.AxonConfigurationException
import org.axonframework.common.AxonException
import org.axonframework.common.AxonNonTransientException
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class DemoComponent(
    val commandGateway: CommandGateway
) : ApplicationListener<ApplicationReadyEvent> {
    override fun onApplicationEvent(event: ApplicationReadyEvent) {
        test(
            ConstructorDemoAggregateCommand(DemoWrappedValueId("FOO"))
        )

        test(
            MethodDemoAggregateCommand(DemoWrappedValueId("BAR"))
        )

        test(
            MethodDemoAggregateCommand(DemoEnumId.BAZ)
        )
    }

    private fun test(command: Any) {
        try {
            commandGateway.send<Any>(
                command
            ).get()
        } catch (ex: Exception) {
            logger.error("$command failed!")
        }
    }

    companion object : KLogging()
}
