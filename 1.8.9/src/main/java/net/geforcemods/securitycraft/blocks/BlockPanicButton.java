package net.geforcemods.securitycraft.blocks;

import net.geforcemods.securitycraft.tileentity.TileEntityOwnable;
import net.geforcemods.securitycraft.util.BlockUtils;
import net.minecraft.block.BlockButton;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPanicButton extends BlockButton implements ITileEntityProvider {

	public BlockPanicButton() {
		super(false);
	}

	/**
	 * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
	 */
	@Override
	public boolean isNormalCube()
	{
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ){
		if(state.getValue(POWERED).booleanValue()){
			BlockUtils.setBlockProperty(worldIn, pos, POWERED, false, true);
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
			worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.4D, "random.click", 0.3F, 0.5F);
			notifyNeighbors(worldIn, pos, state.getValue(FACING));
			return true;
		}else{
			BlockUtils.setBlockProperty(worldIn, pos, POWERED, true, true);
			worldIn.markBlockRangeForRenderUpdate(pos, pos);
			worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.click", 0.3F, 0.6F);
			notifyNeighbors(worldIn, pos, state.getValue(FACING));
			return true;
		}
	}

	private void notifyNeighbors(World worldIn, BlockPos pos, EnumFacing facing)
	{
		worldIn.notifyNeighborsOfStateChange(pos, this);
		worldIn.notifyNeighborsOfStateChange(pos.offset(facing.getOpposite()), this);
	}

	@Override
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state){
		super.breakBlock(worldIn, pos, state);
		worldIn.removeTileEntity(pos);
	}

	@Override
	public boolean onBlockEventReceived(World worldIn, BlockPos pos, IBlockState state, int eventID, int eventParam){
		super.onBlockEventReceived(worldIn, pos, state, eventID, eventParam);
		TileEntity tileentity = worldIn.getTileEntity(pos);
		return tileentity == null ? false : tileentity.receiveClientEvent(eventID, eventParam);
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
	{
		updateBlockBounds(worldIn.getBlockState(pos));
	}

	private void updateBlockBounds(IBlockState state)
	{
		EnumFacing enumfacing = state.getValue(FACING);
		boolean flag = state.getValue(POWERED).booleanValue();
		float f2 = (flag ? 1 : 2) / 16.0F;

		switch (BlockPanicButton.SwitchEnumFacing.FACING_LOOKUP[enumfacing.ordinal()])
		{
			case 1:
				setBlockBounds(0.0F, 0.30F, 0.18F, f2, 0.70F, 0.82F);
				break;
			case 2:
				setBlockBounds(1.0F - f2, 0.30F, 0.18F, 1.0F, 0.70F, 0.82F);
				break;
			case 3:
				setBlockBounds(0.1800F, 0.300F, 0.0F, 0.8150F, 0.700F, f2);
				break;
			case 4:
				setBlockBounds(0.1800F, 0.300F, 1.0F - f2, 0.8150F, 0.700F, 1.0F);
				break;
			case 5:
				setBlockBounds(0.175F, 0.0F, 0.300F, 0.825F, 0.0F + f2, 0.700F);
				break;
			case 6:
				setBlockBounds(0.175F, 1.0F - f2, 0.300F, 0.8225F, 1.0F, 0.700F);
		}
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityOwnable();
	}

	static final class SwitchEnumFacing
	{
		static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];

		static
		{
			try
			{
				FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 1;
			}
			catch (NoSuchFieldError var6)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 2;
			}
			catch (NoSuchFieldError var5)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 3;
			}
			catch (NoSuchFieldError var4)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 4;
			}
			catch (NoSuchFieldError var3)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.UP.ordinal()] = 5;
			}
			catch (NoSuchFieldError var2)
			{
				;
			}

			try
			{
				FACING_LOOKUP[EnumFacing.DOWN.ordinal()] = 6;
			}
			catch (NoSuchFieldError var1)
			{
				;
			}
		}
	}

}
