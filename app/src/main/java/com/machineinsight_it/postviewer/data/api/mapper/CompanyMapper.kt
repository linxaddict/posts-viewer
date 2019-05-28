package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.CompanyDto
import com.machineinsight_it.postviewer.data.db.model.CompanyEntity
import com.machineinsight_it.postviewer.domain.Company

fun CompanyDto.canBeCastToCompany(): Boolean = name != null

fun CompanyDto.toCompany(): Company {
    checkNotNull(name)

    return Company(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}

fun CompanyDto.toEntity(): CompanyEntity {
    checkNotNull(name)

    return CompanyEntity(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}