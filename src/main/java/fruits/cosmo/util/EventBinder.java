package fruits.cosmo.util;


import fruits.cosmo.CosmoFruit;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.*;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class EventBinder {
    private static EventBinderDummyPlugin dummyPlugin = new EventBinderDummyPlugin();
    private static EventBinderListener dummyListener = new EventBinderListener();
    private HashMap<DataPair<Class<? extends Event>, EventPriority>, List<Predicate<Event>>> events = new HashMap<>();
    private static HashSet<DataPair<Class<? extends Event>, EventPriority>> reged = new HashSet<>();


    public static EventBinder getBinder(Player p) {
        EventBinder bind;
        if (p.hasMetadata("[EvBinder] Binder")) {
            bind = (EventBinder) p.getMetadata("[EvBinder] Binder").get(0).value();
        } else {
            p.setMetadata("[EvBinder] Binder", new FixedMetadataValue(CosmoFruit.INSTANCE, bind = new EventBinder()));
        }
        return bind;
    }

    public void accept(Event ex, EventPriority pr) {
        DataPair<Class<? extends Event>, EventPriority> prx = new DataPair<>(ex.getClass(), pr);
        if (events.containsKey(prx)) {
            events.get(prx).removeIf(eventPredicate -> eventPredicate.test(ex));
        }
    }

    public <T extends Event> void bind(Class<T> cl, EventPriority pr, Predicate<T> ev) {
        DataPair<Class<? extends Event>, EventPriority> prx = new DataPair<>(cl, pr);
        if (!reged.contains(prx)) {
            try {
                Method mtd = cl.getMethod("getHandlerList");
                HandlerList handlerList = (HandlerList) mtd.invoke(null);
                handlerList.register(new RegisteredListener(dummyListener, new EventExecutor() {
                    @Override
                    public void execute(Listener listener, Event event) throws EventException {
                        if (event instanceof InventoryClickEvent) {
                            getBinder((Player)((InventoryClickEvent) event).getWhoClicked()).accept(event, pr);
                        }
                    }
                }, pr, dummyPlugin, true));
//                handlerList.bake();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
//            System.out.println("Not contains");
            reged.add(prx);
        }
        events.computeIfAbsent(prx, a -> new ArrayList<>()).add((Predicate<Event>) ev);
    }

    private static class EventBinderListener implements Listener {

    }

    private static class EventBinderDummyPlugin implements Plugin {

        @Override
        public File getDataFolder() {
            return new File("dummy");
        }

        @Override
        public PluginDescriptionFile getDescription() {
            return new PluginDescriptionFile("EventBinderDummy", "1.0-SNAPSHOT", "");
        }

        @Override
        public FileConfiguration getConfig() {
            return null;
        }

        @Override
        public InputStream getResource(String s) {
            return null;
        }

        @Override
        public void saveConfig() {

        }

        @Override
        public void saveDefaultConfig() {

        }

        @Override
        public void saveResource(String s, boolean b) {

        }

        @Override
        public void reloadConfig() {

        }

        @Override
        public PluginLoader getPluginLoader() {
            return null;
        }

        @Override
        public Server getServer() {
            return Bukkit.getServer();
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        @Override
        public void onDisable() {

        }

        @Override
        public void onLoad() {

        }

        @Override
        public void onEnable() {

        }

        @Override
        public boolean isNaggable() {
            return false;
        }

        @Override
        public void setNaggable(boolean b) {

        }

        @Override
        public ChunkGenerator getDefaultWorldGenerator(String s, String s1) {
            return null;
        }

        @Override
        public Logger getLogger() {
            return null;
        }

        @Override
        public String getName() {
            return null;
        }

        @Override
        public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
            return false;
        }

        @Override
        public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
            return null;
        }
    }
}
