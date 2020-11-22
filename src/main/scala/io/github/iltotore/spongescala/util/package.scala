package io.github.iltotore.spongescala

import scala.concurrent.duration.Duration

package object util {

  implicit class DurationToTick(base: Duration) {

    def toTicks: Long = base.toSeconds * 20
  }

}