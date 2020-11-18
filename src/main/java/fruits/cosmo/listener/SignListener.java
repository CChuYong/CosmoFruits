package fruits.cosmo.listener;

import fruits.cosmo.CosmoFruit;
import fruits.cosmo.cache.TreeCache;
import fruits.cosmo.cache.TreeCaches;
import fruits.cosmo.util.FAWEHandler;
import fruits.cosmo.util.OXCreator;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Sign;

import java.util.Arrays;

public class SignListener implements Listener {
    @EventHandler
    public void onSignReplace(SignChangeEvent event){
        if(CosmoFruit.t.containsKey(event.getLine(0))){
          String name =  CosmoFruit.t.get(event.getLine(0)).getaType();
            Sign s = (Sign) event.getBlock().getState().getData();
            if(event.getBlock().getType()== Material.WALL_SIGN){
                if(isRightType(event.getBlock().getLocation().clone(), s.getFacing())){
                    if(!TreeCaches.map.containsKey(event.getBlock().getLocation())){
                        TreeCaches.map.put(event.getBlock().getLocation(), new TreeCache());
                        FAWEHandler.pasteSchematic(name, event.getBlock().getLocation().clone(), false, s.getFacing());
                        event.getPlayer().sendMessage("§f§l[§a§lSYSTEM§f§l] "+event.getLine(0)+" 를 생성했습니다!");
                    }else{
                        event.getPlayer().sendMessage("§f§l[§a§lSYSTEM§f§l] 이미 이 위치에 나무가 존재합니다!");
                    }
                }
                else{
                    event.getPlayer().sendMessage("§f§l[§a§lSYSTEM§f§l] 구조물을 테두리로 두른 후 이용해주세요.");
                }
            }
            else {
                event.getPlayer().sendMessage("§f§l[§a§lSYSTEM§f§l] 구조물을 갖추고 난 후에 이용해주세요.");
            }
        }
    }
    @EventHandler
    public void onSignRightClick(PlayerInteractEvent event){
        if(event.getClickedBlock()!=null&&event.getClickedBlock().getType()==Material.WALL_SIGN){
            if(TreeCaches.map.containsKey(event.getClickedBlock().getLocation())){
                event.setCancelled(true);
                OXCreator.openOX(event.getPlayer(), "나무를 지우시겠습니까?", "§a§l나무를 삭제합니다.","§c§l나무를 삭제하지 않습니다.",()->{
                    Sign s = (Sign)event.getClickedBlock().getState().getData();
                    FAWEHandler.pasteSchematic("empty", event.getClickedBlock().getLocation().clone(), false, s.getFacing());
                    TreeCaches.map.remove(event.getClickedBlock().getLocation());
                    event.getPlayer().sendMessage("§f§l[§a§lSYSTEM§f§l] 나무를 삭제했습니다!");
                });
            }
        }
    }
    public boolean isRightType(Location loc, BlockFace bf){
        switch(bf){
            case NORTH:
               if(isBlockTypeCorrect(loc.clone().add(0,0, +1))
               && isBlockTypeCorrect(loc.clone().add(1,0, +1))
               && isBlockTypeCorrect(loc.clone().add(-1,0, +1))
                       && isBlockTypeCorrect(loc.clone().add(2,0, +2))
                       && isBlockTypeCorrect(loc.clone().add(2,0, +3))
                       && isBlockTypeCorrect(loc.clone().add(2,0, +4))
                            && isBlockTypeCorrect(loc.clone().add(-2,0, +2))
                            && isBlockTypeCorrect(loc.clone().add(-2,0, +3))
                            && isBlockTypeCorrect(loc.clone().add(-2,0, +4))
                        && isBlockTypeCorrect(loc.clone().add(0,0, +5))
                        && isBlockTypeCorrect(loc.clone().add(1,0, +5))
                        && isBlockTypeCorrect(loc.clone().add(-1,0, +5))){
                    return true;
               }
               return false;
            case SOUTH:
                if(isBlockTypeCorrect(loc.clone().add(0,0, -1))
                        && isBlockTypeCorrect(loc.clone().add(1,0, -1))
                        && isBlockTypeCorrect(loc.clone().add(-1,0, -1))
                        && isBlockTypeCorrect(loc.clone().add(2,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(2,0, -3))
                        && isBlockTypeCorrect(loc.clone().add(2,0, -4))
                        && isBlockTypeCorrect(loc.clone().add(-2,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(-2,0, -3))
                        && isBlockTypeCorrect(loc.clone().add(-2,0, -4))
                        && isBlockTypeCorrect(loc.clone().add(0,0, -5))
                        && isBlockTypeCorrect(loc.clone().add(1,0, -5))
                        && isBlockTypeCorrect(loc.clone().add(-1,0, -5))){
                    return true;
                }
                return false;
            case WEST:
                if(isBlockTypeCorrect(loc.clone().add(1,0, 0))
                        && isBlockTypeCorrect(loc.clone().add(1,0, +1))
                        && isBlockTypeCorrect(loc.clone().add(1,0, -1))
                        && isBlockTypeCorrect(loc.clone().add(2,0, +2))
                        && isBlockTypeCorrect(loc.clone().add(3,0, +2))
                        && isBlockTypeCorrect(loc.clone().add(4,0, +2))
                        && isBlockTypeCorrect(loc.clone().add(2,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(3,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(4,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(5,0, 0))
                        && isBlockTypeCorrect(loc.clone().add(5,0, +1))
                        && isBlockTypeCorrect(loc.clone().add(5,0, -1))){
                    return true;
                }
                return false;
            case EAST:
                if(isBlockTypeCorrect(loc.clone().add(-1,0, 0))
                        && isBlockTypeCorrect(loc.clone().add(-1,0, +1))
                        && isBlockTypeCorrect(loc.clone().add(-1,0, -1))
                        && isBlockTypeCorrect(loc.clone().add(-2,0, +2))
                        && isBlockTypeCorrect(loc.clone().add(-3,0, +2))
                        && isBlockTypeCorrect(loc.clone().add(-4,0, +2))
                        && isBlockTypeCorrect(loc.clone().add(-2,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(-3,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(-4,0, -2))
                        && isBlockTypeCorrect(loc.clone().add(-5,0, 0))
                        && isBlockTypeCorrect(loc.clone().add(-5,0, +1))
                        && isBlockTypeCorrect(loc.clone().add(-5,0, -1))){
                    return true;
                }
                return false;
        }
        return false;
    }
    private boolean isBlockTypeCorrect(Location loc){
        if(loc.getBlock().getType()==Material.STEP){
            return true;
        }
        return false;
    }
}
