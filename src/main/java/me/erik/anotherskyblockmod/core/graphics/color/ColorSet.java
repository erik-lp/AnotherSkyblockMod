package me.erik.anotherskyblockmod.core.graphics.color;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ColorSet<T extends CustomColor> {
    
    private final T[] colors;
    private final String[] names;
    private final Map<String, T> nameMap;
    
    public ColorSet(T[] colors, String[] names) {
        
        this.colors = colors;
        this.names = new String[names.length];
        
        assert this.colors.length == this.names.length;
        
        nameMap = new HashMap<>(colors.length);
        for (int i = 0; i < colors.length; ++i) {
            String name = names[i].trim().replace(' ', '_').toUpperCase(Locale.ROOT);
            nameMap.put(name.replace("_", ""), this.colors[i]);
            this.names[i] = name;
        }
        
    }
    
    public T fromCode(int code) {
        if (code < 0 || colors.length <= code) {
            return null;
        }
        return colors[code];
    }
    
    public int getCode(CustomColor c) {
        if (c == null) return -1;
        
        for (int i = 0; i < colors.length; ++i) {
            if (colors[i].equals(c)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getCode(String name) { return getCode(fromName(name)); }
    
    public String getName(CustomColor c) {
        int code = getCode(c);
        if (code == -1) return null;
        return names[code];
    }
    
    public String getName(int code) {
        if (code < 0 || names.length <= code) return null;
        return names[code];
    }
    
    public T fromName(String name) {
        if (name == null) return null;
        
        name = name.trim().replace(' ', '_').replace("_", "").toUpperCase(Locale.ROOT);
        return nameMap.getOrDefault(name, null);
    }
    
    public String canonicalize(String name) { return getName(fromName(name)); }
    
    public boolean has(String name) { return fromName(name) != null; }
    
    public boolean has(int code) { return 0 <= code && code < colors.length; }
    
    public boolean has(CustomColor c) { return getCode(c) != -1; }
    
    public T valueOf(String name) { return fromName(name); }
    
    public T valueOf(int code) { return fromCode(code); }
    
    public T valueOf(CustomColor c) { return fromCode(getCode(c)); }
    
    public int size() { return colors.length; }
    
    public CustomColor[] copySet() {
        CustomColor[] colors = new CustomColor[this.colors.length];
        for (int i = 0; i < colors.length; ++i) {
            colors[i] = new CustomColor(this.colors[i]);
        }
        return colors;
    }
    
    public int[] asInts() {
        int[] colors = new int[this.colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = this.colors[i].toInt();
        }
        return colors;
    }
    
}
