package me.erik.anotherskyblockmod.core;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.mutable.MutableFloat;

public class FloatPair {
    
    private static final ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    
    private final MutableFloat x;
    private final MutableFloat y;
    
    public FloatPair(float x, float y) {
        this.x = new MutableFloat(x);
        this.y = new MutableFloat(y);
    }
    
    public FloatPair(int x, int y) {
        this(x / (float) sr.getScaledHeight(), y / (float) sr.getScaledHeight());
    }
    
    public float getX() { return x.getValue(); }
    
    public void setX(float x) { this.x.setValue(x); }
    
    public float getY() { return y.getValue(); }
    
    public void setY(float y) { this.y.setValue(y); }
    
    @Override
    public boolean equals(Object other) {
        
        if (other == null) return false;
        if (other == this) return true;
        if (other.getClass() != getClass()) return false;
        
        FloatPair otherFloatPair = (FloatPair) other;
        return new EqualsBuilder().append(getX(), otherFloatPair.getX()).append(getY(), otherFloatPair.getY()).isEquals();
        
    }
    
    @Override
    public int hashCode() { return new HashCodeBuilder(83, 11).append(getX()).append(getY()).toHashCode(); }
    
    @Override
    public String toString() { return getX() + "|" + getY(); }
    
    public FloatPair cloneCoords() { return new FloatPair(getX(), getY()); }
    
}
