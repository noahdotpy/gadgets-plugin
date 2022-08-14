package me.diligord.gadgets

import me.diligord.gadgets.handlers.glideHandlers.EntityToggleGlideHandler
import me.diligord.gadgets.handlers.grappleBowListeners.*
import me.diligord.gadgets.commands.GlideCommand
import me.diligord.gadgets.commands.GiveGolfClubCommand
import org.bukkit.plugin.java.JavaPlugin


class Gadgets : JavaPlugin() {

    override fun onEnable() {

        getConfig().options().copyDefaults()
        saveDefaultConfig()

        if (config.getBoolean("Glide") == true) {
            // Commands
            getCommand("glide")?.setExecutor(GlideCommand())

            // Handlers
            EntityToggleGlideHandler(this)
        }

        if (config.getBoolean("GolfClub") == true) {
            // Commands
            getCommand("grapplebow")?.setExecutor(GiveGolfClubCommand())

            // Handlers
            PlayerActionListener(this)
            ProjectileListener(this)
        }
    }

    override fun onDisable() {}

}