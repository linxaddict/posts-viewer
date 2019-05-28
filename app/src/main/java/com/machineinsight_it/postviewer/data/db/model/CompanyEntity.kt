package com.machineinsight_it.postviewer.data.db.model

import androidx.room.ColumnInfo

data class CompanyEntity(
    @ColumnInfo(name = "companyName")
    var name: String,
    var catchPhrase: String?,
    var bs: String?
)