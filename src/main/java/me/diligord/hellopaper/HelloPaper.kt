package me.diligord.hellopaper

import me.diligord.hellopaper.handlers.NoteBlockHandler
import org.bukkit.plugin.java.JavaPlugin


class HelloPaper : JavaPlugin() {
    override fun onEnable() {
        logger.info("Hello, Paper!")
        NoteBlockHandler(this)
    }

    override fun onDisable() {}
}