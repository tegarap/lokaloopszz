package com.tegarap.lokaloops.models

data class ListHistory(
    val name: String,
    val price: String,
    val foto: String,
    val barcode: String,
    val quantity: String
)

data class ListHistoryResponse(
    val result: ArrayList<ListHistory>
)
