package io.github.iltotore.spongescala

import org.spongepowered.api.plugin.PluginContainer

/**
 * A simple trait allowing implicit passing with plugin's main class
 */
trait SpongePlugin {

  def container: PluginContainer
}
