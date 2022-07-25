package com.example.db

import io.micronaut.data.annotation.TypeDef
import io.micronaut.data.model.DataType
import java.util.*

@TypeDef(type = DataType.BYTE_ARRAY, converter = OptimizedUUIDAttributeConverter::class)
data class OptimizedUUID(val uuid: UUID)
