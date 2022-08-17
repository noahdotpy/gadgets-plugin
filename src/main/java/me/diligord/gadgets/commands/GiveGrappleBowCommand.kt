package me.diligord.gadgets.commands

import me.diligord.gadgets.utils.GrappleBowUtils
import me.diligord.gadgets.utils.commandCooldown
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*


class GiveGrappleBowCommand(private val cooldownMap: HashMap<UUID, Long> = HashMap()): CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if ((args == null) || (sender !is Player && args.isEmpty())) {
            return false
        }

        if (sender !is Player) return true

        if (!sender.hasPermission("gadgets.giveGrappleBowCommand")) {
            sender.sendMessage(Bukkit.permissionMessage())
            return true
        }

        val cooldownTimeLeft = commandCooldown(sender, sender.uniqueId, this.cooldownMap, 10_000L)
        if (cooldownTimeLeft > 0) sender.sendMessage("${ChatColor.RED}You must wait ${(cooldownTimeLeft) / 1_000} seconds to execute this command.")


        // Sender gave player's name as arg, works for any sender type (player, console, command_block)
        if (args.isNotEmpty()) {

            val targetName = args[0]
            val targetPlayer = Bukkit.getServer().getPlayerExact(targetName)

            if (targetPlayer == null) {
                sender.sendMessage("${ChatColor.RED}This player is not online")
                return true
            }

            sender.sendMessage("${ChatColor.GREEN}You have given a golf club to ${targetPlayer.name}.")

            targetPlayer.sendMessage("${ChatColor.GREEN}You have been given a grapple bow, enjoy.")
            targetPlayer.inventory.addItem(GrappleBowUtils.makeGrappleBow())
            targetPlayer.inventory.addItem(GrappleBowUtils.makeGrappleHook())

        } else if (args.isEmpty()) {

            val targetPlayer = sender.player

            if (targetPlayer == null) {
                sender.sendMessage("${ChatColor.RED}This player is not online")
                return true
            }

            targetPlayer.sendMessage("${ChatColor.GREEN}You have been given a grapple bow, enjoy.")
            targetPlayer.inventory.addItem(GrappleBowUtils.makeGrappleBow())
            targetPlayer.inventory.addItem(GrappleBowUtils.makeGrappleHook())

        }

        return true
    }
}