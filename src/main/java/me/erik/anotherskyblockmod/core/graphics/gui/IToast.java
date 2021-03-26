package me.erik.anotherskyblockmod.core.graphics.gui;

public interface IToast {
    
    Object NO_TOKEN = new Object();
    
    IToast.Visibility draw(GuiToast toastGui, long delta);
    
    default Object getType() { return NO_TOKEN; }
    
    enum Visibility {
        SHOW,
        HIDE
    }
    
}
