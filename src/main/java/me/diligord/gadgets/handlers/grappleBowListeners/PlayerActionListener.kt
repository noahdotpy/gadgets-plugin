package me.diligord.gadgets.handlers.grappleBowListeners

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import me.diligord.gadgets.Gadgets
import me.diligord.gadgets.utils.GrappleBowUtils
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.spigotmc.event.entity.EntityDismountEvent


class PlayerActionListener(private val plugin: Gadgets): Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    private fun onPlayerMove(e: PlayerMoveEvent) {
        if (
            !plugin.config.getBoolean("grappleBow.stopsPlayerMovement") ||
            e.player.hasPermission("gadgets.grappleBow.evadeMovementRestrictions")
        ) return

        e.to = e.from.setDirection(e.to.direction)
        e.isCancelled = true
    }

    @EventHandler
    private fun onPlayerJump(e: PlayerJumpEvent) {
        if (
            !plugin.config.getBoolean("grappleBow.stopsPlayerMovement") ||
            e.player.hasPermission("gadgets.grappleBow.evadeMovementRestrictions")
        ) return

        e.isCancelled = true
    }

    @EventHandler
    private fun onPlayerDismount(e: EntityDismountEvent) {
        if (e.dismounted.type != EntityType.ARROW || e.entity !is Player) return

        if (
            // If disableDismountWhenUsing: false
            !plugin.config.getBoolean("grappleBow.disableDismountWhenGrappling") ||
            e.entity.hasPermission("gadgets.grappleBow.evadeMovementRestrictions")
        ) {
            e.dismounted.remove()
            return
        }

        e.isCancelled = true
    }

    @EventHandler
    private fun onPlayerJoin(e: PlayerJoinEvent) {
        if (plugin.config.getBoolean("grappleBow.giveOnJoin")) {
            e.player.inventory.addItem(GrappleBowUtils.makeGrappleBow())
            e.player.inventory.addItem(GrappleBowUtils.makeGrappleHook())
        }

        if (plugin.config.getBoolean("glide.giveOnJoin")) {
            e.player.isGliding = true
        }
    }


}