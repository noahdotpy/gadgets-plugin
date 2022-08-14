package me.diligord.gadgets.handlers.grappleBowListeners

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import me.diligord.gadgets.Gadgets
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.spigotmc.event.entity.EntityDismountEvent


class PlayerActionListener(plugin: Gadgets): Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    private fun onPlayerMove(e: PlayerMoveEvent) {
        if (e.player.hasPermission("gadgets.grappleBow.evadeRestrictions")) return

        e.isCancelled = true
        e.to = e.from.setDirection(e.to.direction)
    }

    @EventHandler
    private fun onPlayerJump(e: PlayerJumpEvent) {
        if (e.player.hasPermission("gadgets.grappleBow.evadeRestrictions")) return

        e.isCancelled = true
    }

    @EventHandler
    private fun onPlayerDismount(e: EntityDismountEvent) {
        if (e.dismounted.type != EntityType.ARROW || e.entity !is Player) return

        if (e.entity.hasPermission("gadgets.grappleBow.evadeRestrictions")) {
            e.dismounted.remove()
            return
        }

        e.isCancelled = true
    }
}