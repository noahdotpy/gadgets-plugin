package me.diligord.gadgets.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GlideCommand: CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if ((args == null) || (sender !is Player && args.isEmpty()))  {
            return false
        }

        if (!sender.hasPermission("gadgets.giveGolfClubCommand")) {
            sender.sendMessage("${ChatColor.RED}You do not have permission to execute this command.")
            return true
        }

        if (args.isNotEmpty()) {

            val targetName = args[0]
            val targetPlayer = Bukkit.getServer().getPlayerExact(targetName)

            if (targetPlayer == null) {
                sender.sendMessage("${ChatColor.RED}This player is not online")
                return true
            }

            sender.sendMessage("${ChatColor.GREEN}Enabled gliding mode for ${targetPlayer.name}.")
            targetPlayer.sendMessage("${ChatColor.GREEN}Enabled gliding mode, enjoy.")
            targetPlayer.isGliding = !targetPlayer.isGliding

        }

        if (sender is Player && args.isEmpty()) {

            val targetPlayer = sender.player
            if (targetPlayer == null) {
                sender.sendMessage("${ChatColor.RED}This player is not online")
                return true
            }

            sender.sendMessage("${ChatColor.GREEN}Enabled gliding mode, enjoy.")
            targetPlayer.isGliding = !targetPlayer.isGliding
        }

        return true
    }
}