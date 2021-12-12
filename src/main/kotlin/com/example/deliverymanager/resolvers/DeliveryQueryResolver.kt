package com.example.deliverymanager.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.example.deliverymanager.entity.Delivery
import com.example.deliverymanager.enums.DeliveryType
import com.example.deliverymanager.handler.Handler
import org.springframework.stereotype.Component

/**
 * Class for declaring query resolvers
 */
@Component
class DeliveryQueryResolver(val handler: Handler) : GraphQLQueryResolver {

    /**
     * Query for getting all deliveries
     *
     * @return list of all deliveries
     */
    fun deliveries(): List<Delivery> {
        return handler.getDeliveries(DeliveryType.ALL)
    }

    /**
     * Query for getting received deliveries
     *
     * @return list of received deliveries
     */
    fun receivedDeliveries(): List<Delivery> {
        return handler.getDeliveries(DeliveryType.RECEIVED)
    }

    /**
     * Query for getting not yet received deliveries
     *
     * @return list of not yet received deliveries
     */
    fun notYetReceivedDeliveries(): List<Delivery> {
        return handler.getDeliveries(DeliveryType.NOT_RECEIVED)
    }


}