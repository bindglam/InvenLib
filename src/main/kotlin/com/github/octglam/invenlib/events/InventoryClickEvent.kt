package com.github.octglam.invenlib.events

import com.github.octglam.invenlib.InvenLib
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory

class InventoryClickEvent : Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent){
        for(inv: Inventory in InvenLib.inventories){
            if(event.inventory == inv){
                if(InvenLib.inventoryIsNotClick[inv] == true){
                    event.isCancelled = true
                }
            }
        }
    }
}