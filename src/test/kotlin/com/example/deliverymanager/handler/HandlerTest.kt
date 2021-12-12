package com.example.deliverymanager.handler

import com.example.deliverymanager.database.DeliveryRepository
import com.example.deliverymanager.entity.Delivery
import com.example.deliverymanager.enums.DeliveryType
import com.example.deliverymanager.handler.HandlerTest.MockitoHelper.capture
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest

@RunWith(MockitoJUnitRunner::class)
class HandlerTest {

    object MockitoHelper {
        // use this in place of captor.capture() if you are trying to capture an argument that is not nullable
        fun <T> capture(argumentCaptor: ArgumentCaptor<T>): T = argumentCaptor.capture()
    }

    @Captor
    private lateinit var argumentCaptor: ArgumentCaptor<Map<Int, Delivery>>

    private var mockDb: HashMap<Int, Delivery> = hashMapOf()

    @Mock
    lateinit var deliveryRepository: DeliveryRepository

    @Before
    fun init() {
        mockDb.clear()
        val delivery1 = Delivery(
            101, "Bananas",
            "JunglesInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon"
        )
        val delivery2 = Delivery(
            102, "Saiyans",
            "Bardock", 9001, "2019-10-10T09:08:11.098Z", "Namek"
        )
        val delivery3 = Delivery(
            103, "Skull, Crystal",
            "Akator", 1, "2008-05-22T00:00:00.001Z", "Headquarters"
        )
        val delivery4 = Delivery(
            104, "Bananas",
            "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
        )
        val delivery5 = Delivery(
            105, "Apples",
            "ApplesToOrangesInc", 50, "2020-05-01T09:00:42.000Z", "Oranges"
        )
        val delivery6 = Delivery(
            106, "Salad",
            "HealthyFoodInc", 600, "2021-11-01T11:20:42.000Z",
            "Headquarters", true
        )
        mockDb[delivery1.deliveryId] = delivery1
        mockDb[delivery2.deliveryId] = delivery2
        mockDb[delivery3.deliveryId] = delivery3
        mockDb[delivery4.deliveryId] = delivery4
        mockDb[delivery5.deliveryId] = delivery5
        mockDb[delivery6.deliveryId] = delivery6
    }

    @Test
    fun gelAllDeliveries() {
        Mockito.`when`(deliveryRepository.readFromJson()).thenReturn(mockDb)
        val handler = Handler(deliveryRepository)
        val deliveries = handler.getDeliveries(DeliveryType.ALL)
        val expectedDeliveries: ArrayList<Delivery> = arrayListOf(
            Delivery(
                101, "Bananas",
                "JunglesInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon"
            ), Delivery(
                102, "Saiyans",
                "Bardock", 9001, "2019-10-10T09:08:11.098Z", "Namek"
            ), Delivery(
                103, "Skull, Crystal",
                "Akator", 1, "2008-05-22T00:00:00.001Z", "Headquarters"
            ), Delivery(
                104, "Bananas",
                "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
            ), Delivery(
                105, "Apples",
                "ApplesToOrangesInc", 50, "2020-05-01T09:00:42.000Z", "Oranges"
            ), Delivery(
                106, "Salad",
                "HealthyFoodInc", 600, "2021-11-01T11:20:42.000Z",
                "Headquarters", true
            )
        )
        Assert.assertTrue(deliveries == expectedDeliveries)
    }

    @Test
    fun gelReceivedDeliveries() {
        Mockito.`when`(deliveryRepository.readFromJson()).thenReturn(mockDb)
        val handler = Handler(deliveryRepository)
        val deliveries = handler.getDeliveries(DeliveryType.RECEIVED)
        val expectedDeliveries: ArrayList<Delivery> = arrayListOf(
            Delivery(
                106, "Salad",
                "HealthyFoodInc", 600, "2021-11-01T11:20:42.000Z",
                "Headquarters", true
            )
        )
        Assert.assertTrue(deliveries == expectedDeliveries)
    }

    @Test
    fun gelNotYetReceivedDeliveries() {
        Mockito.`when`(deliveryRepository.readFromJson()).thenReturn(mockDb)
        val handler = Handler(deliveryRepository)
        val deliveries = handler.getDeliveries(DeliveryType.NOT_RECEIVED)
        val expectedDeliveries: ArrayList<Delivery> = arrayListOf(
            Delivery(
                101, "Bananas",
                "JunglesInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon"
            ), Delivery(
                102, "Saiyans",
                "Bardock", 9001, "2019-10-10T09:08:11.098Z", "Namek"
            ), Delivery(
                103, "Skull, Crystal",
                "Akator", 1, "2008-05-22T00:00:00.001Z", "Headquarters"
            ), Delivery(
                104, "Bananas",
                "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
            ), Delivery(
                105, "Apples",
                "ApplesToOrangesInc", 50, "2020-05-01T09:00:42.000Z", "Oranges"
            )
        )
        Assert.assertTrue(deliveries == expectedDeliveries)
    }

    @Test
    fun putNewDelivery() {
        Mockito.`when`(deliveryRepository.readFromJson()).thenReturn(mockDb)
        val handler = Handler(deliveryRepository)
        val delivery = Delivery(
            107, "Bananas",
            "JunglesInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon"
        )
        handler.putNewDelivery(delivery)
        val expectedDeliveries: HashMap<Int, Delivery> = hashMapOf()
        expectedDeliveries[101] = Delivery(
            101, "Bananas",
            "JunglesInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon"
        )
        expectedDeliveries[102] = Delivery(
            102, "Saiyans",
            "Bardock", 9001, "2019-10-10T09:08:11.098Z", "Namek"
        )
        expectedDeliveries[103] = Delivery(
            103, "Skull, Crystal",
            "Akator", 1, "2008-05-22T00:00:00.001Z", "Headquarters"
        )
        expectedDeliveries[104] = Delivery(
            104, "Bananas",
            "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
        )
        expectedDeliveries[104] = Delivery(
            104, "Bananas",
            "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
        )
        expectedDeliveries[105] = Delivery(
            105, "Apples",
            "ApplesToOrangesInc", 50, "2020-05-01T09:00:42.000Z", "Oranges"
        )
        expectedDeliveries[106] = Delivery(
            106, "Salad",
            "HealthyFoodInc", 600, "2021-11-01T11:20:42.000Z",
            "Headquarters", true
        )
        expectedDeliveries[107] = delivery
        Mockito.verify(deliveryRepository).persist(capture(argumentCaptor))
        Assert.assertTrue(argumentCaptor.value == expectedDeliveries)
    }

    @Test
    fun deleteDelivery() {
        Mockito.`when`(deliveryRepository.readFromJson()).thenReturn(mockDb)
        val handler = Handler(deliveryRepository)
        handler.deleteDelivery(106)
        val expectedDeliveries: HashMap<Int, Delivery> = hashMapOf()
        expectedDeliveries[101] = Delivery(
            101, "Bananas",
            "JunglesInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon"
        )
        expectedDeliveries[102] = Delivery(
            102, "Saiyans",
            "Bardock", 9001, "2019-10-10T09:08:11.098Z", "Namek"
        )
        expectedDeliveries[103] = Delivery(
            103, "Skull, Crystal",
            "Akator", 1, "2008-05-22T00:00:00.001Z", "Headquarters"
        )
        expectedDeliveries[104] = Delivery(
            104, "Bananas",
            "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
        )
        expectedDeliveries[104] = Delivery(
            104, "Bananas",
            "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
        )
        expectedDeliveries[105] = Delivery(
            105, "Apples",
            "ApplesToOrangesInc", 50, "2020-05-01T09:00:42.000Z", "Oranges"
        )
        Mockito.verify(deliveryRepository).persist(capture(argumentCaptor))
        Assert.assertTrue(argumentCaptor.value == expectedDeliveries)
    }

    @Test
    fun updateDelivery() {
        Mockito.`when`(deliveryRepository.readFromJson()).thenReturn(mockDb)
        val handler = Handler(deliveryRepository)
        handler.updateDelivery(105)
        val expectedDeliveries: HashMap<Int, Delivery> = hashMapOf()
        expectedDeliveries[101] = Delivery(
            101, "Bananas",
            "JunglesInc", 1000000, "2027-01-08T07:17:48.237Z", "TheMoon"
        )
        expectedDeliveries[102] = Delivery(
            102, "Saiyans",
            "Bardock", 9001, "2019-10-10T09:08:11.098Z", "Namek"
        )
        expectedDeliveries[103] = Delivery(
            103, "Skull, Crystal",
            "Akator", 1, "2008-05-22T00:00:00.001Z", "Headquarters"
        )
        expectedDeliveries[104] = Delivery(
            104, "Bananas",
            "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
        )
        expectedDeliveries[104] = Delivery(
            104, "Bananas",
            "JungleInc", 1, "2020-10-08T07:18:42.937Z", "BerlinZoo"
        )
        expectedDeliveries[105] = Delivery(
            105, "Apples",
            "ApplesToOrangesInc", 50, "2020-05-01T09:00:42.000Z",
            "Oranges", true
        )
        expectedDeliveries[106] = Delivery(
            106, "Salad",
            "HealthyFoodInc", 600, "2021-11-01T11:20:42.000Z",
            "Headquarters", true
        )
        Mockito.verify(deliveryRepository).persist(capture(argumentCaptor))
        Assert.assertTrue(argumentCaptor.value == expectedDeliveries)
    }
}
