package com.temportalist.racesforminecraft.common.talent;

import com.temportalist.origin.library.common.lib.NameParser;
import com.temportalist.racesforminecraft.api.talent.ISkill;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.HashSet;

/**
 * Tracks what items/blocks the RacePlayer CAN use (whitelist)
 *
 * @author TheTemportalist
 */
public class Skill extends AbstractTalent implements ISkill {
	private final HashSet<String> blackList = new HashSet<String>();
	private final HashSet<String> whiteList = new HashSet<String>();

	public Skill(String name) {
		super(name);
	}

	@Override
	public HashSet<String> getBlacklist() {
		return blackList;
	}

	@Override
	public HashSet<String> getWhitelist() {
		return whiteList;
	}

	@Override
	public boolean hasItem(ItemStack itemStack) {
		return this.hasItem(itemStack, true) || this.hasItem(itemStack, false);
	}

	private boolean hasItem(ItemStack itemStack, boolean checkBlackList) {
		if (checkBlackList) {
			if (!this.blackList.isEmpty() && NameParser.isInCollection(itemStack, this.blackList)) {
				return true;
			}
		}
		else {
			if (!this.whiteList.isEmpty() && NameParser.isInCollection(itemStack, this.whiteList)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canUseItem(EntityPlayer player, ItemStack itemStack) {
		return this.hasItem(itemStack, true);
	}

}
