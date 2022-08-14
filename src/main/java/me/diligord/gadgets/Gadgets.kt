package me.diligord.gadgets

import me.diligord.gadgets.commands.GiveGrappleBowCommand
import me.diligord.gadgets.commands.GlideCommand
import me.diligord.gadgets.handlers.glideHandlers.EntityToggleGlideHandler
import me.diligord.gadgets.handlers.grappleBowListeners.*
import org.bukkit.plugin.java.JavaPlugin


class Gadgets : JavaPlugin() {

    override fun onEnable() {

        config.options().copyDefaults()
        saveDefaultConfig()

        if (config.getBoolean("Glide")) {
            // Commands
            getCommand("glide")?.setExecutor(GlideCommand())

            // Handlers
            EntityToggleGlideHandler(this)
        }

        if (config.getBoolean("GrappleBow")) {
            // Commands
            getCommand("grapplebow")?.setExecutor(GiveGrappleBowCommand())

            // Handlers
            PlayerActionListener(this)
            ProjectileListener(this)
        }
    }

    override fun onDisable() {}

}