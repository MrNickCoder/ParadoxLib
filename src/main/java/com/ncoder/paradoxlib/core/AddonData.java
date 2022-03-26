package com.ncoder.paradoxlib.core;

import io.github.thebusybiscuit.slimefun4.libraries.dough.common.DoughLogger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddonData {

    private final File file;
    private Logger logger;
    private String header;
    protected FileConfiguration fileConfig;
    
    public AddonData(String path) {
        ParadoxAddon addon = ParadoxAddon.instance();
        this.logger = new DoughLogger(addon.getName() + " Data");
        this.file = new File(new File("data-storage/" + addon.getName()), path);
        this.fileConfig = YamlConfiguration.loadConfiguration(this.file);
        this.fileConfig.options().copyDefaults(true);
    }

    @Nonnull
    public File getFile() {
        return this.file;
    }

    @Nullable
    public String getHeader() {
        return this.header;
    }

    public void setHeader(@Nullable String header) {
        this.header = header;
    }

    @Nonnull
    public FileConfiguration getConfiguration() {
        return this.fileConfig;
    }

    public void clear() {
        Iterator var1 = this.getKeys().iterator();

        while(var1.hasNext()) {
            String key = (String)var1.next();
            this.setValue(key, (Object)null);
        }

    }

    protected void store(@Nonnull String path, Object value) {
        this.fileConfig.set(path, value);
    }

    /**
     * Getters Methods
     */

    @Nullable
    public Object getValue(@Nonnull String path) {
        return this.fileConfig.get(path);
    }

    @Nonnull
    public <T> Optional<T> getValueAs(@Nonnull Class<T> c, @Nonnull String path) {
        Object obj = this.getValue(path);
        return c.isInstance(obj) ? Optional.of(c.cast(obj)) : Optional.empty();
    }

    @Nullable
    public ItemStack getItem(@Nonnull String path) {
        return this.fileConfig.getItemStack(path);
    }

    @Nullable
    public String getString(@Nonnull String path) {
        return this.fileConfig.getString(path);
    }

    public int getInt(@Nonnull String path) {
        return this.fileConfig.getInt(path);
    }

    public boolean getBoolean(@Nonnull String path) {
        return this.fileConfig.getBoolean(path);
    }

    @Nonnull
    public List<String> getStringList(@Nonnull String path) {
        return this.fileConfig.getStringList(path);
    }

    @Nonnull
    public List<Integer> getIntList(@Nonnull String path) {
        return this.fileConfig.getIntegerList(path);
    }

    public float getFloat(@Nonnull String path) {
        return Float.valueOf(String.valueOf(this.getValue(path)));
    }

    public long getLong(@Nonnull String path) {
        return Long.valueOf(String.valueOf(this.getValue(path)));
    }

    public Date getDate(@Nonnull String path) {
        return new Date(this.getLong(path));
    }

    public Chunk getChunk(@Nonnull String path) {
        return Bukkit.getWorld(this.getString(path + ".world")).getChunkAt(this.getInt(path + ".x"), this.getInt(path + ".z"));
    }

    public UUID getUUID(@Nonnull String path) {
        String value = this.getString(path);
        return value != null ? UUID.fromString(value) : null;
    }

    public World getWorld(@Nonnull String path) {
        return Bukkit.getWorld(this.getString(path));
    }

    public double getDouble(@Nonnull String path) {
        return this.fileConfig.getDouble(path);
    }

    @Nonnull
    public Location getLocation(@Nonnull String path) {
        return new Location(
                Bukkit.getWorld(this.getString(path + ".world")),
                this.getDouble(path + ".x"),
                this.getDouble(path + ".y"),
                this.getDouble(path + ".z"),
                this.getFloat(path + ".yaw"),
                this.getFloat(path + ".pitch"));
    }

    @Nonnull
    public Inventory getInventory(@Nonnull String path, int size, @Nonnull String title) {
        Inventory inventory = Bukkit.createInventory((InventoryHolder)null, size, ChatColor.translateAlternateColorCodes('&', title));

        for(int i = 0; i < size; ++i) {
            inventory.setItem(i, this.getItem(path + "." + i));
        }

        return inventory;
    }

    @Nonnull
    public Inventory getInventory(@Nonnull String path, @Nonnull String title) {
        int size = this.getInt(path + ".size");
        Inventory inventory = Bukkit.createInventory((InventoryHolder)null, size, ChatColor.translateAlternateColorCodes('&', title));

        for(int i = 0; i < size; ++i) {
            inventory.setItem(i, this.getItem(path + "." + i));
        }

        return inventory;
    }

    @Nonnull
    public Note getNote(@Nonnull String path) {
        return new Note(
                this.fileConfig.getInt(path + ".octave"),
                Note.Tone.valueOf(this.fileConfig.getString(path + ".tone")),
                Boolean.valueOf(this.fileConfig.getString(path + ".sharped"))
        );
    }

    /**
     * Setter Methods
     */

    public void setValue(@Nonnull String path, Object value) {
        if (value == null) { this.setNull(path); }
        else if (value instanceof Optional) { this.setOptional(path, (Optional) value); }
        else if (value instanceof Inventory) { this.setInventory(path, (Inventory) value); }
        else if (value instanceof Date) { this.setDate(path, (Date) value); }
        else if (value instanceof Long) { this.setLong(path, (Long) value); }
        else if (value instanceof UUID) { this.setUUID(path, (UUID) value); }
        else if (value instanceof Sound) { this.setSound(path, (Sound) value); }
        else if (value instanceof Location) { this.setLocation(path, (Location) value); }
        else if (value instanceof Chunk) { this.setChunk(path, (Chunk) value); }
        else if (value instanceof World) { this.setWorld(path, (World) value); }
        else if (value instanceof Note) { this.setNote(path, (Note) value); }
        else { this.store(path, value); }

    }

    public void setNull(@Nonnull String path) {
        this.store(path, (Object) null);
    }

    public void setOptional(@Nonnull String path, Optional value) {
        this.store(path, value.orElse((Object)null));
    }

    public void setInventory(@Nonnull String path, Inventory value) {
        this.store(path + ".size", value.getSize());

        for(int i = 0; i < ((Inventory)value).getSize(); ++i) {
            this.store(path + "." + i, value.getItem(i));
        }
    }

    public void setDate(@Nonnull String path, Date value) {
        this.store(path, String.valueOf(value.getTime()));
    }

    public void setLong(@Nonnull String path, Long value) {
        this.store(path, String.valueOf(value));
    }

    public void setUUID(@Nonnull String path, UUID value) {
        this.store(path, value.toString());
    }

    public void setSound(@Nonnull String path, Sound value) {
        this.store(path, String.valueOf(value));
    }

    public void setLocation(@Nonnull String path, Location value) {
        this.store(path + ".x", value.getX());
        this.store(path + ".y", value.getY());
        this.store(path + ".z", value.getZ());
        this.store(path + ".pitch", value.getPitch());
        this.store(path + ".yaw", value.getYaw());
        this.store(path + ".world", value.getWorld().getName());
    }

    public void setChunk(@Nonnull String path, Chunk value) {
        this.store(path + ".x", value.getX());
        this.store(path + ".z", value.getZ());
        this.store(path + ".world", value.getWorld().getName());
    }

    public void setWorld(@Nonnull String path, World value) {
        this.store(path, value.getName());
    }

    public void setNote(@Nonnull String path, Note value) {
        this.store(path + ".octave", String.valueOf(value.getOctave()));
        this.store(path + ".tone", value.getTone().toString());
        this.store(path + ".sharped", String.valueOf(value.isSharped()));
    }

    /**
     * Other Methods
     */

    public void save() {
        this.save(this.file);
    }

    public void save(@Nonnull File file) {
        try {
            if (this.header != null) {
                this.fileConfig.options().copyHeader(true);
                this.fileConfig.options().header(this.header);
            } else {
                this.fileConfig.options().copyHeader(false);
            }

            this.fileConfig.save(file);
        } catch (IOException var3) {
            this.logger.log(Level.SEVERE, "Exception while saving a Data file", var3);
        }

    }

    public void setDefaultValue(@Nonnull String path, @Nullable Object value) {
        if (!this.contains(path)) {
            this.setValue(path, value);
        }

    }

    public boolean contains(@Nonnull String path) {
        return this.fileConfig.contains(path);
    }

    public boolean createFile() {
        try {
            return this.file.createNewFile();
        } catch (IOException var2) {
            this.logger.log(Level.SEVERE, "Exception while creating a Config file", var2);
            return false;
        }
    }

    @Nonnull
    public Set<String> getKeys() {
        return this.fileConfig.getKeys(false);
    }

    @Nonnull
    public Set<String> getKeys(@Nonnull String path) {
        ConfigurationSection section = this.fileConfig.getConfigurationSection(path);
        return (Set)(section == null ? new HashSet() : section.getKeys(false));
    }

    public void reload() {
        this.fileConfig = YamlConfiguration.loadConfiguration(this.file);
    }

}
