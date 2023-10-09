package ru.netology

import java.text.DecimalFormat

fun main() {

    val amount = 50_000
    var sumAllTransfers = 590_000
    val type = "MasterCard"
    val limitLevel = checkLimits(type, sumAllTransfers, amount)

    if (limitLevel == 0) {

        val numberViewTax = DecimalFormat("#.##").format(calcTaxTransfer(type, sumAllTransfers, amount));
        sumAllTransfers += amount
        println(
            """Комиссия за перевод: $amount р. с карты $type составит: $numberViewTax р.
                |Сумма всех Ваших переводов (на текущий месяц): $sumAllTransfers р.
            """.trimMargin()
        )

    } else {

        when (limitLevel) {

            1 -> println("Превышена максимально допустимая сумма перевода.")
            2 -> println("Превышен месячный лимит переводов.")
            3 -> println("Сумма перевода больше доступного остатка по лимиту переводов в месяц.")
        }

    }

}


fun checkLimits(type: String = "VK Pay", sumAllTransfers: Int, amount: Int): Int = when (type) {

    "VK Pay" -> when {
        amount > 15_000 -> 1
        sumAllTransfers > 40_000 -> 2
        sumAllTransfers + amount > 40_000 -> 3
        else -> 0
    }

    else -> when {
        amount > 150_000 -> 1
        sumAllTransfers > 600_000 -> 2
        sumAllTransfers + amount > 600_000 -> 3
        else -> 0
    }

}

fun calcTaxTransfer(type: String = "VK Pay", sumAllTransfers: Int = 0, amount: Int): Double {

    val masterCardMaestroTaxRate = 0.006
    val masterCardMaestroTaxPlus = 20
    val masterCardMaestroMaxUnTaxLimit = 75_000
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