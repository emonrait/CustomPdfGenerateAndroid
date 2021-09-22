package com.rheyansh.lenden.model

import com.example.createpdfreport.model.Transaction


class PdfGeneratorModel(list: List<Transaction>, header: String, daterange: String) {

    var list = emptyList<Transaction>()
    var header = ""
    var totalCredit = ""
    var totalDebit = ""
    var balance = ""
    var dateRange = ""

    init {
        this.list = list
        this.header = header
        calculateTotal(list)
        this.dateRange = daterange
    }

    private fun calculateTotal(items: List<Transaction>) {
        val totalPlus = items.map {
            it.debitAmount
        }.sum()

        val totalMinus = items.map {
            it.debitAmount
        }.sum()

        val totalBal = items.map {
            it.balance
        }.sum()

        totalDebit = "-$totalMinus"
        totalCredit = totalPlus.toString()
        balance = totalBal.toString()

    }
}