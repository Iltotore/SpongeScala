package io.github.iltotore.spongescala

import org.spongepowered.api.data.DataHolder
import org.spongepowered.api.data.key.Key
import org.spongepowered.api.data.value.BaseValue

import scala.jdk.OptionConverters.RichOptional

object data {

  implicit class ScalaDataHolder(base: DataHolder) {

    def apply[T](key: Key[_ <: BaseValue[T]]): Option[T] = base.get(key).toScala
    def update[T](key: Key[_ <: BaseValue[T]], value: T): Unit = base.offer(key, value)
  }

}
