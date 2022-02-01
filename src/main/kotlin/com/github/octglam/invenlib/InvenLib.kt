package com.github.octglam.invenlib

import com.github.octglam.invenlib.events.InventoryClickEvent
import com.github.octglam.invenlib.events.InventoryCloseEvent
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class InvenLib : JavaPlugin(), Listener {
    override fun onEnable() {
        logger.info("InvenLib API 활성화")
        instance = this

        Bukkit.getPluginManager().registerEvents(this, this)
        Bukkit.getPluginManager().registerEvents(InventoryClickEvent(), this)
        Bukkit.getPluginManager().registerEvents(InventoryCloseEvent(), this)
    }

    override fun onDisable() {
        inventories.clear()
        inventoryIsNotClose.clear()
        inventoryIsNotClick.clear()
    }

    companion object{
        lateinit var instance: InvenLib
        var inventories: ArrayList<Inventory> = ArrayList()
        var inventoryIsNotClick: HashMap<Inventory, Boolean> = HashMap()
        var inventoryIsNotClose: HashMap<Inventory, Boolean> = HashMap()

        fun create(name: String, lineNumber: Int, isNotClick: Boolean, isNotClose: Boolean): Inventory {
            val inventory = Bukkit.createInventory(null, 9 * lineNumber, name)
            inventories.add(inventory)
            inventoryIsNotClick[inventory] = isNotClick
            inventoryIsNotClose[inventory] = isNotClose
            return inventory
        }

        fun setInvItem(name: String, mat: Material, lore: List<String>, amount: Int, slot: Int, inventory: Inventory){
            val item = ItemStack(mat, amount)
            val meta = item.itemMeta
            meta.setDisplayName(name)
            meta.lore = lore
            item.itemMeta = meta
            inventory.setItem(slot, item)
        }

        fun setInvItem(itemStack: ItemStack, slot: Int, inventory: Inventory){
            inventory.setItem(slot, itemStack)
        }

        fun addInvItem(name: String, mat: Material, lore: List<String>, amount: Int, inventory: Inventory){
            val item = ItemStack(mat, amount)
            val meta = item.itemMeta
            meta.setDisplayName(name)
            meta.lore = lore
            item.itemMeta = meta
            inventory.addItem(item)
        }

        fun addInvItem(itemStack: ItemStack, inventory: Inventory){
            inventory.addItem(itemStack)
        }

        fun createItem(name: String, mat: Material, lore: List<String>, amount: Int): ItemStack{
            val item = ItemStack(mat, amount)
            val meta = item.itemMeta
            meta.setDisplayName(name)
            meta.lore = lore
            item.itemMeta = meta
            return item
        }

        fun refreshInventory(inventory: Inventory){
            for(player: Player in Bukkit.getOnlinePlayers()){
                if(player.inventory == inventory){
                    player.closeInventory()
                    player.openInventory(inventory)
                }
            }
        }

        fun openInventory(player: Player, inventory: Inventory){
            player.openInventory(inventory)
        }

        fun openInventory(uuid: UUID, inventory: Inventory){
            for(player: Player in Bukkit.getOnlinePlayers()){
                if(player.uniqueId == uuid){
                    player.openInventory(inventory)
                }
            }
        }

        fun openInventory(name: String, inventory: Inventory){
            for(player: Player in Bukkit.getOnlinePlayers()){
                if(player.name == name){
                    player.openInventory(inventory)
                }
            }
        }

        fun openInventoryAllPlayers(inventory: Inventory){
            for(player: Player in Bukkit.getOnlinePlayers()){
                player.openInventory(inventory)
            }
        }

        fun close(inventory: Inventory){
            for(player: Player in Bukkit.getOnlinePlayers()){
                if(player.inventory == inventory){
                    player.closeInventory()
                }
            }
        }
    }
}