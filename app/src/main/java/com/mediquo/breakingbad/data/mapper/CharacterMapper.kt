package com.mediquo.breakingbad.data.mapper

import com.mediquo.breakingbad.data.entity.CharacterEntity
import com.mediquo.breakingbad.domain.model.Character
import java.text.SimpleDateFormat
import java.util.*

class CharacterMapper: Mapper<CharacterEntity, Character> {

  override fun toDomain(dataModel: CharacterEntity): Character {
    return Character(
      charId = dataModel.charId,
      name = dataModel.name,
      birthday = parseBirthday(dataModel.birthday),
      occupation = dataModel.occupation,
      img = dataModel.img,
      status = dataModel.status,
      nickname = dataModel.nickname,
      appearance = dataModel.appearance,
      portrayed = dataModel.portrayed,
      category = dataModel.category.split(","),
      betterCallSaulAppearance = dataModel.betterCallSaulAppearance
    )
  }

  private fun parseBirthday(birthday: String): Date? {
    if (birthday == "Unknown") return null
    else return SimpleDateFormat("MM-dd-yyyy", Locale.US).parse(birthday)
  }
}