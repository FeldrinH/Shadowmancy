package feldrinh.shadowmancy.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RefluxRecipeBasic implements IRefluxRecipe
{
	private final Item baseItem;
	private final ItemStack resultItemStack;
	private final float shadowSpeed;
	/**The speed at which progress is accumulated (per tick)*/
	private final float progressSpeed;
	
	public RefluxRecipeBasic(Item baseItem, ItemStack resultItemStack, float progressSpeed, float shadowSpeed)
	{
		this.baseItem = baseItem;
		this.resultItemStack = resultItemStack;
		this.progressSpeed = progressSpeed;
		this.shadowSpeed = shadowSpeed;
	}
	
	@Override
	public float getShadowSpeed()
	{
		return shadowSpeed;
	}
	
	@Override
	public float getProgressSpeed()
	{
		return progressSpeed;
	}
	
	@Override
	public boolean baseMatches(ItemStack base)
	{
		return (base.getItem() == baseItem);
	}
	
	@Override
	public boolean resultMatches(ItemStack result)
	{
		//More fuzziness?
		return ItemStack.areItemStacksEqual(resultItemStack, result);
	}

	@Override
	public ItemStack getNewResult(int curStackSize)
	{
		ItemStack newResult = resultItemStack.copy();
		newResult.stackSize = curStackSize;
		return newResult;
	}
}
