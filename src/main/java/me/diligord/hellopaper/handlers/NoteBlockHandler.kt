package me.diligord.hellopaper.handlers

import me.diligord.hellopaper.HelloPaper
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.block.NotePlayEvent

class NoteBlockHandler(private val plugin: HelloPaper) : Listener {
    init {
        Bukkit.getPluginManager().registerEvents(this, plugin)
    }

    @EventHandler
    fun onNoteBlockPlay(event: BlockPlaceEvent) {
        val block = event.block
        plugin.logger.info("block type: $block.type")
        if (block.type != Material.NOTE_BLOCK) return
        plugin.logger.info("Noteblock Placed!!!!")
    }
}