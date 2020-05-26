package com.example.demo

import mu.KLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.common.AxonConfigurationException
import org.axonframework.common.AxonException
import org.axonframework.common.AxonNonTransientException
import org.axonframework.modelling.command.Repository
import org.axonframework.queryhandling.QueryGateway
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component

@Component
class DemoComponent(
    val queryGateway: QueryGateway, /* Comment me out to break everything! */
    val repository: Repository<DemoAggregate>
)
