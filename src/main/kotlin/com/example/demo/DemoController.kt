package com.example.demo

import kotlinx.coroutines.future.await
import kotlinx.coroutines.reactor.mono
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController(
        val queryGateway: QueryGateway
) {
    @GetMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    fun index() = mono {
        queryGateway.query(
                DemoQuery(),
                ResponseTypes.multipleInstancesOf(DemoDto::class.java)
        ).await()
    }
}
