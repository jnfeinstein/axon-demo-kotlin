package com.example.demo

import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Service

@Service
class DemoService {
    @QueryHandler
    fun on(query: DemoQuery): List<DemoDto> {
        return listOf(
                DemoDto("foo")
        )
    }
}