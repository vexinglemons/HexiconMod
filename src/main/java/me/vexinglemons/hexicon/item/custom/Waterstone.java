package me.vexinglemons.hexicon.item.custom;

import me.vexinglemons.hexicon.capabilities.PlayerData.IPlayerData;
import me.vexinglemons.hexicon.capabilities.PlayerData.PlayerDataProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;
import java.util.List;

public class Waterstone extends Item {

    public Waterstone(Properties properties) {
        super(properties);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.hexicon.waterstone_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.hexicon.shift"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.getHeldItem(handIn).damageItem(1, playerIn, player -> playerIn.sendBreakAnimation(handIn));
        playerIn.getCooldownTracker().setCooldown(this, 100);
        LazyOptional<IPlayerData> waterstoneActive = playerIn.getCapability(PlayerDataProvider.capability, null);
        waterstoneActive.ifPresent(player -> {player.setInOrb(true);});
        //IPlayerTicks ticks = playerIn.getCapability(TicksProvider.TICKS_CAPABILITY, null);
       // IBubble bubble = playerIn.getCapability(BubbleProvider.BUBBLE_CAPABILITY,` null);
       // ticks.setTicks(0);
        //bubble.setBubble(true);
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

}

