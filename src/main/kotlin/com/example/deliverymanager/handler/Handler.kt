package com.example.deliverymanager.handler

import com.example.deliverymanager.database.DeliveryRepository
import com.example.deliverymanager.entity.Delivery
import com.example.deliverymanager.enums.DeliveryType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.streams.toList

/**
 * Class for handling mutation and query operations
 */
@Component
class Handler @Autowired constructor(
    private val deliveryRepository : DeliveryRepository
){
    /**
     * Query operations for getting deliveries from database
     *
     * @param deliveryType type of deliveries should be returned
     *
     * @return Deliveries in List format
     */
    fun getDeliveries(deliveryType : DeliveryType): List<Delivery> {
        val deliveryMap : HashMap<Int, Delivery> = deliveryRepository.readFromJson()
        return when (deliveryType) {
            DeliveryType.ALL -> deliveryMap.toList().map {it.second}
            DeliveryType.RECEIVED -> ArrayList(deliveryMap.values).stream().filter{it.received}.toList()
            DeliveryType.NOT_RECEIVED -> ArrayList(deliveryMap.values).stream().filter{!it.received}.toList()
        }
    }

    /**
     * Mutation operation for adding new delivery to database
     *
     * @param delivery New delivery to be added to database
     *
     * @return Added delivery
     */
    fun putNewDelivery(delivery: Delivery): Delivery {
        val deliveryMap : HashMap<Int, Delivery> = deliveryRepository.readFromJson()
        deliveryMap[delivery.deliveryId] = delivery
        deliveryRepository.persist(deliveryMap)
        return delivery
    }

    /**
     * Mutation operation for deleting existing delivery
     *
     * @param deliveryId Id of delivery
     *
     * @return true if successful deletion
     */
    fun deleteDelivery(deliveryId: Int): Boolean {
        val deliveryMap : HashMap<Int, Delivery> = deliveryRepository.readFromJson()
        return if (deliveryMap.containsKey(deliveryId)) {
            deliveryMap.remove(deliveryId)
            deliveryRepository.persist(deliveryMap)
            true
        } else {
            false
        }
    }

    /**
     *  Mutation operation for updating received field of deliveries
     *
     *  @param deliveryId Id of delivery
     *
     *  @return Updated delivery if delivery exists
     */
    fun updateDelivery(deliveryId: Int): Delivery? {
        val deliveryMap : HashMap<Int, Delivery> = deliveryRepository.readFromJson()
        val delivery = deliveryMap[deliveryId]
        if (delivery != null) {
            delivery.received = true
            deliveryRepository.persist(deliveryMap)
        }
        return delivery
    }
}