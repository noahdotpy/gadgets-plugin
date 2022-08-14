package me.diligord.gadgets.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.util.UUID


const val COOLDOWN_TIME = 10000L // In milliseconds


class GiveGolfClubCommand(private val cooldownMap: HashMap<UUID, Long> = HashMap()): CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if ((args == null) || (sender !is Player && args.isEmpty()))  {
            return false
        }

        if (!sender.hasPermission("gadgets.giveGolfClubCommand")) {
            sender.sendMessage("${ChatColor.RED}You do not have permission to execute this command.")
            return true
        }

        val player = sender as Player
        val playerUUID = player.uniqueId


        if (!this.cooldownMap.containsKey(playerUUID)) {
            this.cooldownMap[playerUUID] = System.currentTimeMillis()
        } else {
            val timeElapsed = System.currentTimeMillis() - this.cooldownMap[playerUUID]!!

            if (timeElapsed > COOLDOWN_TIME) {
                this.cooldownMap[playerUUID] = System.currentTimeMillis()
            } else {
                player.sendMessage("${ChatColor.RED}You must wait ${(COOLDOWN_TIME - timeElapsed) / 1000} seconds to execute this command.")
                return true
            }
        }


        // Sender gave player's name as arg, works for any sender type (player, console, command_block)
        if (args.isNotEmpty()) {

            val targetName = args[0]
            val targetPlayer = Bukkit.getServer().getPlayerExact(targetName)

            if (targetPlayer == null) {
                sender.sendMessage("${ChatColor.RED}This player is not online")
                return true
            }

            sender.sendMessage("${ChatColor.GREEN}You have given a golf club to ${targetPlayer.name}.")
            targetPlayer.sendMessage("${ChatColor.GREEN}You have been given a golf club, enjoy.")
            targetPlayer.inventory.addItem(ItemStack(Material.BOW))

        }

        // Sender is a player and didn't give any args
        if (args.isEmpty()) {

            val targetPlayer = sender.player
            if (targetPlayer == null) {
                sender.sendMessage("${ChatColor.RED}This player is not online")
                return true
            }

            sender.sendMessage("${ChatColor.GREEN}You have been given a golf club, enjoy.")
            targetPlayer.inventory.addItem(ItemStack(Material.BOW))
        }

        return true
    }
}