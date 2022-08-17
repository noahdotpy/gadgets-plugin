package me.diligord.gadgets

import me.diligord.gadgets.commands.GiveGrappleBowCommand
import me.diligord.gadgets.commands.GlideCommand
import me.diligord.gadgets.handlers.glideHandlers.EntityToggleGlideHandler
import me.diligord.gadgets.handlers.grappleBowListeners.PlayerActionListener
import me.diligord.gadgets.handlers.grappleBowListeners.ProjectileListener
import org.bukkit.plugin.java.JavaPlugin


class Gadgets : JavaPlugin() {

    override fun onEnable() {

        // Config
        saveDefaultConfig()
        config.options().copyDefaults()
logger
        // Plugin startup logic
        if (config.getBoolean("glide.enabled")) {
            logger.info(makeModuleEnabledString("glide"))

            // Commands
            getCommand("glide")?.setExecutor(GlideCommand())

            // Handlers
            EntityToggleGlideHandler(this)
        }

        if (config.getBoolean("grappleBow.enabled")) {
            logger.info(makeModuleEnabledString("grappleBow"))

            // Commands
            getCommand("grapplebow")?.setExecutor(GiveGrappleBowCommand())

            // Handlers
            PlayerActionListener(this)
            ProjectileListener(this)
        }
    }

    override fun onDisable() {}

    fun makeModuleEnabledString(module: String): String {
        return "Module $module enabled."
    }

}