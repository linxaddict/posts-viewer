package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.CompanyDto
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