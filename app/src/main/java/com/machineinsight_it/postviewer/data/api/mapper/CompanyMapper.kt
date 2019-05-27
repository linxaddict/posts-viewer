package com.machineinsight_it.postviewer.data.api.mapper

import com.machineinsight_it.postviewer.data.api.model.CompanyDto
import com.machineinsight_it.postviewer.domain.Company

fun CompanyDto.toCompany(): Company? {
    if (name == null) {
        return null
    }

    return Company(
        name = name,
        catchPhrase = catchPhrase,
        bs = bs
    )
}