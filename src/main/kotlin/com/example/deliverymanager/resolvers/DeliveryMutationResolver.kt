package com.example.deliverymanager.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.example.deliverymanager.entity.Delivery
import com.example.deliverymanager.handler.Handler
import org.springframework.stereotype.Component

/**
 * Class for declaring mutation resolvers
 */
@Component
class DeliveryMutationResolver(private val handler: Handler) : GraphQLMutationResolver {

    /**
     * Mutation for new delivery
     *
     * @param deliveryId Id of delivery
     * @param product Product name
     * @param supplier Supplier name
     * @param quantity Quantity of product
     * @param expectedDate Expected date of delivery
     * @param expectedWarehouse Expected warehouse name
     *
     * @return Added delivery
     */
    fun newDelivery(
        deliveryId: Int, product: String, supplier: String,
        quantity: Int, expectedDate: String, expectedWarehouse: String
    ): Delivery {
        val delivery = Delivery(deliveryId, product, supplier, quantity, expectedDate, expectedWarehouse)
        return handler.putNewDelivery(delivery)
    }

    /**
     * Mutation for deleting delivery
     *
     * @param deliveryId Id of delivery
     *
     * @return true if successful deletion
     */
    fun deleteDelivery(deliveryId: Int): Boolean {
        return handler.deleteDelivery(deliveryId)
    }

    /**
     * Mutation for updating dleivery
     *
     * @param deliveryId Id of delivery
     *
     * @return Updated delivery if delivery exists
     */
    fun updateDelivery(deliveryId: Int): Delivery? {
        return handler.updateDelivery(deliveryId)
    }
}