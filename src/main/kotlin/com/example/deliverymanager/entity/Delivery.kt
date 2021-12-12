package com.example.deliverymanager.entity

/**
 * Delivery data class
 */
data class Delivery(
    val deliveryId: Int,
    val product: String,
    val supplier: String,
    val quantity: Int,
    val expectedDate: String,
    val expectedWarehouse: String,
    var received: Boolean
) {
    constructor(
        deliveryId: Int, product: String, supplier: String,
        quantity: Int, expectedDate: String, expectedWarehouse: String
    )
            : this(deliveryId, product, supplier, quantity, expectedDate, expectedWarehouse, false)
}
