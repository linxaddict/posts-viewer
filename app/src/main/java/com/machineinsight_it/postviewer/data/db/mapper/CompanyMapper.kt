package com.machineinsight_it.postviewer.data.db.mapper

import com.machineinsight_it.postviewer.data.db.CompanyEntity
import com.machineinsight_it.postviewer.domain.Company

fun CompanyEntity.toCompany(): Company = Company(
    name = name,
    catchPhrase = catchPhrase,
    bs = bs
)