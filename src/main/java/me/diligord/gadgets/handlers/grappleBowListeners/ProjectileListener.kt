package me.diligord.gadgets.handlers.grappleBowListeners

import me.diligord.gadgets.Gadgets
import org.bukkit.Bukkit
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.event.entity.ProjectileLaunchEvent

class ProjectileListener(private val plugin: Gadgets): Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    private fun onProjectileLaunch(e: ProjectileLaunchEvent) {
        if (
            e.entity.shooter !is Player ||
            (e.entity.shooter as Player).vehicle?.type == EntityType.ARROW ||
            e.entityType != EntityType.ARROW
        ) return

        val entity = e.entity
        val player = e.entity.shooter as Player
        val velocityMultiplier = plugin.config.getDouble("grappleBow.velocityMultiplier")
        entity.velocity = entity.velocity.multiply(velocityMultiplier)
        entity.addPassenger(player)
    }

    @EventHandler
    private fun onProjectileLand(e: ProjectileHitEvent) {
        if (e.entity.shooter !is Player || e.entityType != EntityType.ARROW) return

        // silence the landing sound of the arrow
        e.entity.isSilent = true

        e.entity.remove()
    }
}