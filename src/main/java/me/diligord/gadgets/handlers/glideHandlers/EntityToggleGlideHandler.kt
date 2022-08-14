package me.diligord.gadgets.handlers.glideHandlers

import me.diligord.gadgets.Gadgets
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityToggleGlideEvent

class EntityToggleGlideHandler(plugin: Gadgets): Listener {
    init{
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    private fun onToggleGlide(e: EntityToggleGlideEvent) {
        if (
            e.entity !is Player
            || (e.entity as Player).inventory.chestplate?.type == Material.ELYTRA
        ) return

        e.isCancelled = true
    }

}