package fruits.cosmo.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class OXCreator {
    public static ItemStack WHITE_PANE = new ItemStackBuilder(Material.STAINED_GLASS_PANE).displayname("&f").build();
    public static void openOX(Player player, String title, String o, String x, Runnable r){
        ItemStack RED_PANE = new ItemStackBuilder(Material.STAINED_GLASS_PANE).data((byte)14).displayname(x).build();
        ItemStack GREEN_PANE = new ItemStackBuilder(Material.STAINED_GLASS_PANE).data((byte)5).displayname(o).build();
        Inventory inv = Bukkit.createInventory(null, InventoryType.HOPPER, title);
        inv.setItem(0, WHITE_PANE);
        inv.setItem(1, GREEN_PANE);
        inv.setItem(2, WHITE_PANE);
        inv.setItem(3, RED_PANE);
        inv.setItem(4, WHITE_PANE);
        player.openInventory(inv);
        EventBinder.getBinder(player).bind(InventoryClickEvent.class, EventPriority.HIGHEST, ev -> {
            if(ev.getInventory()!=null&&ev.getInventory().equals(inv) && ((Player)ev.getWhoClicked()).equals(player) && ev.getCurrentItem()!=null) {
                ev.setCancelled(true);
                if(ev.getCurrentItem().isSimilar(GREEN_PANE)){
                    r.run();
                    ev.getWhoClicked().closeInventory();
                    return true;
                }else if(ev.getCurrentItem().isSimilar(RED_PANE)){
                    ev.getWhoClicked().closeInventory();
                    return true;
                }else{
                    return false;
                }
            }
            return false;
        });
    }
}
