package com.example.deliverymanager.database

import com.example.deliverymanager.entity.Delivery
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import java.io.File

/**
 * Class for handling connection between in-memory database
 */
@Repository
class DeliveryRepository {

    @Value("\${database.path}")
    private val jsonFilePath = ""

    /**
     * Read from in-memory database
     *
     * @return Deliveries in HashMap format
     */
    fun readFromJson(): HashMap<Int, Delivery> {
        val mapper = jacksonObjectMapper()
        mapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true)
        val jsonString: String = File(jsonFilePath).readText(Charsets.UTF_8)
        val deliveries : List<Delivery> = mapper.readValue(jsonString, object : TypeReference<List<Delivery>>() {})
        return deliveries.associateBy { it.deliveryId } as HashMap<Int, Delivery>
    }

    /**
     * Persist new data to in-memory database
     *
     * @param deliveries updated deliveries
     */
    fun persist(deliveries: Map<Int, Delivery>) {
        val mapper = jacksonObjectMapper()
        mapper.writeValue(File(jsonFilePath), deliveries.toList().map {it.second})
    }
}