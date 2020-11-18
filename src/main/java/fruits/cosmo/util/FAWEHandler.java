package fruits.cosmo.util;

import com.sk89q.worldedit.BlockVector;
import com.sk89q.worldedit.BlockVector2D;
import com.sk89q.worldedit.EditSession;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.ClipboardFormats;

import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.math.transform.Transform;
import fruits.cosmo.CosmoFruit;
import org.bukkit.Location;

import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

import java.io.File;
import java.io.IOException;

public class FAWEHandler {
    public static boolean pasteSchematic(String schematicName, Location loc, boolean noAir, BlockFace bf) {
        com.sk89q.worldedit.world.World  weWorld = new BukkitWorld(loc.getWorld());

        File file = new File(CosmoFruit.INSTANCE.getDataFolder() + File.separator + "schematics" + File.separator + schematicName + ".schematic");
        if (file.exists() == false) {
            return false;
        }
     //   Vector vec = new Vector(loc.getBlockX()+converter_BX(bf), loc.getBlockY(), loc.getBlockZ());
       // com.sk89q.worldedit.Vector v = BukkitAdapte
         BlockVector v =  BlockVector.toBlockPoint(loc.getBlockX()+converter_BX(bf), loc.getBlockY(), loc.getBlockZ()+converter_BZ(bf));
    //    BlockVector3 alpha = BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
        Transform tf = new AffineTransform().rotateY(90*converter_A(bf));
        System.out.println(90*converter_A(bf));
        try {
            EditSession editSession = ClipboardFormats.findByFile(file)
                    .load(file)
                    .paste(weWorld,v,false,!noAir,tf);
                //    .paste(weWorld, alpha, false, !noAir, null);

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    private static void rotatePointAround(Location point, int centerX, int centerZ) {
        double angle = -0.5 * Math.PI;

        double rotatedX = Math.cos(angle) * (point.getX() - centerX) - Math.sin(angle) * (point.getZ() - centerZ) + centerX;
        double rotatedZ = Math.sin(angle) * (point.getX() - centerX) + Math.cos(angle) * (point.getZ() - centerZ) + centerZ;

        point.setX(rotatedX);
        point.setZ(rotatedZ);
    }
    public static int converter_A(BlockFace bf){
        switch(bf){
            case NORTH:
                return 1;
            case SOUTH:
                return 3;
            case EAST:
                return 0;
            case WEST:
                return 2;
        }
        return 0;
    }
    public static int converter_BX(BlockFace bf){
        switch(bf){
            case NORTH:
                return 0;
            case SOUTH:
                return 0;
            case EAST:
                return -2;
            case WEST:
                return +2;
        }
        return 0;
    }
    public static int converter_BZ(BlockFace bf){
        switch(bf){
            case NORTH:
                return 2;
            case SOUTH:
                return -2;
            case EAST:
                return 0;
            case WEST:
                return 0;
        }
        return 0;
    }
}
