package net.geforcemods.securitycraft.blocks.reinforced;

import java.util.Arrays;
import java.util.List;

import net.geforcemods.securitycraft.SCContent;
import net.minecraft.block.Block;

public interface IReinforcedBlock
{
	public static final List<Block> BLOCKS = Arrays.asList(new Block[] {
			SCContent.reinforcedBrick,
			SCContent.reinforcedCobblestone,
			SCContent.reinforcedCompressedBlocks,
			SCContent.reinforcedConcrete,
			SCContent.reinforcedDirt,
			SCContent.reinforcedEndStoneBricks,
			SCContent.reinforcedGlass,
			SCContent.reinforcedHardenedClay,
			SCContent.unbreakableIronBars,
			SCContent.reinforcedMetals,
			SCContent.reinforcedMossyCobblestone,
			SCContent.reinforcedNetherBrick,
			SCContent.reinforcedNewLogs,
			SCContent.reinforcedOldLogs,
			SCContent.reinforcedPrismarine,
			SCContent.reinforcedPurpur,
			SCContent.reinforcedQuartz,
			SCContent.reinforcedRedNetherBrick,
			SCContent.reinforcedRedSandstone,
			SCContent.reinforcedSandstone,
			SCContent.reinforcedStainedHardenedClay,
			SCContent.reinforcedStone,
			SCContent.reinforcedStoneBrick,
			SCContent.reinforcedWoodPlanks,
			SCContent.reinforcedWool
	});

	public List<Block> getVanillaBlocks();

	public int getAmount();
}
