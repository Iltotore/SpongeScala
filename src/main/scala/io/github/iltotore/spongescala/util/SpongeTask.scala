package io.github.iltotore.spongescala.util

import io.github.iltotore.spongescala.SpongePlugin
import org.spongepowered.api.scheduler.Task

import scala.concurrent.duration.FiniteDuration

class SpongeTask(builder: Task.Builder) {

  def each(time: Long): SpongeTask = {
    builder.intervalTicks(time)
    this
  }

  def each(time: FiniteDuration): SpongeTask = each(time.toTicks)

  def after(time: Long): SpongeTask = {
    builder.delayTicks(time)
    this
  }

  def after(time: FiniteDuration): SpongeTask = after(time.toTicks)

  def named(name: String): SpongeTask = {
    builder.name(name)
    this
  }

  def async(value: Boolean): SpongeTask = if(value) {
    builder.async()
    this
  } else throw new IllegalArgumentException("Can't re-sync an asynchronous task")

  def submit(plugin: Any): Task = builder.submit(plugin)
}

object SpongeTask {

  def task(statement: Task => Unit): SpongeTask = new SpongeTask(Task.builder().execute(task => statement(task)))
  def runnableTask(statement: => Unit): SpongeTask = task(_ => statement)
  def synchronize(statement: => Unit)(implicit plugin: SpongePlugin): Task =
    runnableTask(statement) after 0 submit plugin

  def asynchronize(statement: => Unit)(implicit plugin: SpongePlugin): Task =
    runnableTask(statement) after 0 async true submit plugin
}