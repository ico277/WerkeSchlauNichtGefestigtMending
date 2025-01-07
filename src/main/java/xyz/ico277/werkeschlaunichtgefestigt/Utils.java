package xyz.ico277.werkeschlaunichtgefestigt;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class Utils {
    public static boolean checkMending(NBTTagCompound nbt) {
        try {
            NBTTagList enchantments;
            if (nbt.hasKey("StoredEnchantments")) {
                enchantments = nbt.getTagList("StoredEnchantments", 10);
            } else {
                enchantments = nbt.getTagList("ench", 10);
            }
            for (int j = 0; j < enchantments.tagCount(); j++) {
                NBTTagCompound enchantment = enchantments.getCompoundTagAt(j);
                if (enchantment.getShort("id") == 70) { // 70 is the id for Mending
                    return true;
                }
            }
        } catch (Exception ignored) {
            return false;
        }
        return false;
    }
}
