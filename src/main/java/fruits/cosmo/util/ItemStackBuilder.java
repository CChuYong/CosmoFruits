package fruits.cosmo.util;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ItemStackBuilder {
    private Material material;
    private int amount = 1;
    private byte bte = 0;
    private Short durability = null;
    private String displayName = null;
    private boolean unbreakable = false;
    private ArrayList<String> lore = new ArrayList<String>();
    private ArrayList<ItemFlag> flags = new ArrayList<ItemFlag>();
    public ItemStackBuilder(Material mat)
    {
        this.material = mat;
    }
    public ItemStack build(){
        ItemStack ret = new ItemStack(material, amount, bte);
        ItemMeta im = ret.getItemMeta();
        if(displayName!=null)
            im.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
        if(durability!=null)
            ret.setDurability(durability);
        if(!lore.isEmpty())
            im.setLore(lore);
        im.setUnbreakable(unbreakable);
        for(ItemFlag flg : flags)
            im.addItemFlags(flg);
        ret.setItemMeta(im);

        return ret;
    }
    public ItemStackBuilder amount(int amount){
        this.amount = amount;
        return this;
    }
    public ItemStackBuilder displayname(String displayname){
        this.displayName = displayname;
        return this;
    }
    public ItemStackBuilder durability(short durability){
        this.durability = durability;
        return this;
    }
    public ItemStackBuilder data(byte durability){
        this.bte = durability;
        return this;
    }
    public ItemStackBuilder unbreakable(boolean unbreak){
        this.unbreakable = unbreak;
        return this;
    }
    public ItemStackBuilder addLore(String lore){
        this.lore.add(ChatColor.translateAlternateColorCodes('&', lore));
        return this;
    }
    public ItemStackBuilder addFlag(ItemFlag flag){
        this.flags.add(flag);
        return this;
    }
}
