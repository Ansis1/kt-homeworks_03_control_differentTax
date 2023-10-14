import org.junit.Test

import org.junit.Assert.*
import ru.netology.calcTaxTransfer


const val TYPE_MASTERCARD = "MasterCard"
const val TYPE_MAESTRO = "Maestro"
const val TYPE_VISA = "Visa"
const val TYPE_MIR = "Mir"
const val TYPE_VKPAY = "VK Pay"

class MainKtTest {
    //Maestro

    var amount = 15000

    @Test
    fun calcTaxTransferInclTaxMaestroOverLimit() { //превышение лимита (комиссия)

        val sumAllTransfers = 75001

        val result = calcTaxTransfer(TYPE_MAESTRO, sumAllTransfers, amount)

        assertEquals(110.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferExclTaxMaestro() { //без комиссии
        val sumAllTransfers = 0

        val result = calcTaxTransfer(TYPE_MAESTRO, sumAllTransfers, amount)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferMaestroNullAmount() { //нулевая сумма
        amount = 0
        val sumAllTransfers = 3000

        val result = calcTaxTransfer(TYPE_MAESTRO, sumAllTransfers, amount)

        assertEquals(0.0, result, 0.0)
    }


    @Test
    fun calcTaxTransferMaestroNotTotal() { //без указания суммы переводов
        amount = 1000

        val result = calcTaxTransfer(TYPE_MAESTRO, amount = amount)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferMaestroNotTotalAndAmount() { //без указания суммы переводов и суммы тек. перевода

        val result = calcTaxTransfer(TYPE_MAESTRO, amount = 0)

        assertEquals(0.0, result, 0.0)
    }


    //MasterCard
    var sumAllTransfers = 75001

    @Test
    fun calcTaxTransferInclTaxMasterCardOverLimit() { //превышение лимита (комиссия)
        amount = 15000


        var result = calcTaxTransfer(TYPE_MASTERCARD, sumAllTransfers, amount)

        assertEquals(110.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferNoTaxMasterCard() { //без комиссии
        amount = 15000
        sumAllTransfers = 0

        val result = calcTaxTransfer(TYPE_MASTERCARD, sumAllTransfers, amount)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferInclTaxMasterCardNullAmount() { //нулевая сумма
        amount = 0
        sumAllTransfers = 3000
        val result = calcTaxTransfer(TYPE_MASTERCARD, sumAllTransfers, amount)

        assertEquals(0.0, result, 0.0)
    }


    @Test
    fun calcTaxTransferInclTaxMasterCardNotTotal() { //без указания суммы переводов
        amount = 1000
        val result = calcTaxTransfer(TYPE_MASTERCARD, amount = amount)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferInclTaxMasterCardNullTotalNullAmount() { //без указания суммы переводов и суммы тек. перевода

        val result = calcTaxTransfer(TYPE_MASTERCARD, amount = 0)

        assertEquals(0.0, result, 0.0)
    }


    //VK
    @Test
    fun calcTaxTransferNoTaxVkWithAmount() { //без указания типа и накопительной суммы.
        amount = 15000

        val result = calcTaxTransfer(amount = amount)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferNoTaxVkExclType() { //без указания типа и накопительной суммы и нулевой суммой перевода.
        amount = 0

        val result = calcTaxTransfer(amount = amount)

        assertEquals(0.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferNoTaxVkWithType() { //без указания типа и накопительной суммы и нулевой суммой перевода.
        amount = 0

        val result = calcTaxTransfer(TYPE_VKPAY, amount = amount)

        assertEquals(0.0, result, 0.0)
    }


    //VISA

    @Test
    fun calcTaxTransferInclAllTaxVisa() { //комиссия
        amount = 15000
        val result = calcTaxTransfer(TYPE_VISA, amount = amount)

        assertEquals(112.5, result, 0.0)
    }

    @Test
    fun calcTaxTransferInclMinTaxVisa() { //комиссия мин
        amount = 0
        val result = calcTaxTransfer(TYPE_VISA, amount = amount)

        assertEquals(35.0, result, 0.0)
    }

    @Test
    fun calcTaxTransferNoTaxVisaWrong() { //Другой регистр
        amount = 0
        val result = calcTaxTransfer(TYPE_VISA.lowercase(), amount = amount)

        assertEquals(0.0, result, 0.0)
    }


    //Mir
    @Test
    fun calcTaxTransferInclTaxMir() { //комиссия
        amount = 15000

        val result = calcTaxTransfer(TYPE_MIR, amount = amount)

        assertEquals(112.5, result, 0.0)
    }

    @Test
    fun calcTaxTransferMinlTaxMir() { //комиссия мин
        amount = 0

        val result = calcTaxTransfer(TYPE_MIR, amount = amount)

        assertEquals(35.0, result, 0.0)
    }


// Wrong type

    @Test
    fun calcTaxTransferTaxWrongType() {
        amount = 15000
        sumAllTransfers = 75001

        val result = calcTaxTransfer("-18", sumAllTransfers, amount)

        assertEquals(0.0, result, 0.0)
    }


}