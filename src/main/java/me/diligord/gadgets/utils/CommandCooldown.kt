package me.diligord.gadgets.utils

import org.bukkit.command.CommandSender
import java.util.UUID

/**
 * Returns the time left to wait in milliseconds.
 * 0 means that they should be able to execute the command.
 */
fun commandCooldown(sender: CommandSender, senderUUID: UUID, cooldownMap: HashMap<UUID, Long>, cooldownTime: Long): Long {
    if (!sender.hasPermission("gadgets.evadeCommandCooldown")) {
        if (!cooldownMap.containsKey(senderUUID)) {
            cooldownMap[senderUUID] = System.currentTimeMillis()
        } else {
            val timeElapsed = System.currentTimeMillis() - cooldownMap[senderUUID]!!

            if (timeElapsed > cooldownTime) {
                cooldownMap[senderUUID] = System.currentTimeMillis()
            } else {
                return System.currentTimeMillis() - timeElapsed
            }
        }
    }

    return 0
}