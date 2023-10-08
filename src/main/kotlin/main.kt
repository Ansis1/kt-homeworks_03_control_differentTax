package ru.netology

import java.text.DecimalFormat

fun main() {

    val amount = 15000
    var sumAllTransfers = 75
    val type = "Mir"
    val numberViewTax = DecimalFormat("#.##").format(calcTaxTransfer(type, sumAllTransfers, amount));
    sumAllTransfers += amount
    println(
        """Комиссия за перевод: $amount р. с карты $type составит: $numberViewTax р.
        |Сумма всех Ваших переводов (на текущий момент): $sumAllTransfers р.
    """.trimMargin()
    )
}

fun calcTaxTransfer(type: String = "VK Pay", sumAllTransfers: Int = 0, amount: Int): Double {

    val masterCardMaestroTaxRate = 0.006
    val masterCardMaestroTaxPlus = 20
    val masterCardMaestroMaxUnTaxLimit = 75000
    val visaMirTaxRate = 0.0075
    val visaMirTaxMin = 35

    return when (type) {
        "MasterCard", "Maestro" -> if (sumAllTransfers > masterCardMaestroMaxUnTaxLimit)
            (amount * masterCardMaestroTaxRate) + masterCardMaestroTaxPlus else 0.0

        "Visa", "Mir" -> if ((amount * visaMirTaxRate) > visaMirTaxMin)
            amount * visaMirTaxRate else visaMirTaxMin.toDouble()

        else -> 0.0
    }

}