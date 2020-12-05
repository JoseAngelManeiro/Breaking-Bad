package com.mediquo.breakingbad.data.mapper

interface Mapper<DataModel, DomainModel> {
  fun toDomain(dataModel: DataModel): DomainModel
}
