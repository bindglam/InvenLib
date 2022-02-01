package com.github.octglam.invenlib.events

import com.github.octglam.invenlib.InvenLib
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory

class InventoryCloseEvent : Listener {
    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent){
        for(inv: Inventory in InvenLib.inventories){
            if(event.inventory == inv){
                if(InvenLib.inventoryIsNotClose[inv] == true){
                    Bukkit.getServer().scheduler.runTask(InvenLib.instance, Runnable {
                        event.player.openInventory(inv)
                    })
                }
            }
        }
    }
}