package me.diligord.gadgets.utils

import net.kyori.adventure.text.Component
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

open class GrappleBowUtils {
    companion object Factory {


        @JvmStatic
        fun makeGrappleBow(): ItemStack {
            val grappleBow = ItemStack(Material.BOW)
            val grappleBowMeta = grappleBow.itemMeta

            grappleBowMeta.lore(listOf(
                Component.text("${ChatColor.DARK_GRAY}\"Use this bow to ${ChatColor.RESET}${ChatColor.MAGIC}!${ChatColor.RESET}GRAPPLE${ChatColor.MAGIC}!${ChatColor.RESET}${ChatColor.DARK_GRAY} anywhere!\""),
                Component.text("    ${ChatColor.DARK_GRAY}- ${ChatColor.GOLD}${ChatColor.BOLD}Diligord1")
            ))

            grappleBowMeta.displayName(
                Component.text(
                    "${ChatColor.BOLD}${ChatColor.DARK_GRAY}[ ${ChatColor.BLUE}Grapple ${ChatColor.DARK_PURPLE}Bow${ChatColor.DARK_GRAY}${ChatColor.BOLD}${ChatColor.DARK_GRAY} ]"
                ))

            grappleBowMeta.isUnbreakable = true

            grappleBowMeta.addEnchant(Enchantment.ARROW_INFINITE, 1, false)

            // Save the item meta to the Item
            grappleBow.itemMeta = grappleBowMeta
            return grappleBow
        }


        @JvmStatic
        fun makeGrappleHook(): ItemStack {
            val grappleHook = ItemStack(Material.ARROW)
            val grappleHookMeta = grappleHook.itemMeta

            grappleHookMeta.displayName(Component.text(
                "${ChatColor.DARK_GRAY}[ Grapple Hook ]"
            ))

            // Save the item meta to the Item
            grappleHook.itemMeta = grappleHookMeta
            return grappleHook
        }


    }
}