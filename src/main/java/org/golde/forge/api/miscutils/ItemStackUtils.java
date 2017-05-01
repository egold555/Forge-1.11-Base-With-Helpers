package org.golde.forge.api.miscutils;

import java.util.UUID;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameData;
import net.minecraftforge.oredict.OreDictionary;

public final class ItemStackUtils {
    
    /**
     * Sets a stack compound to an ItemStack if it does not already have one.
     * 
     * @param stackItemStack having a tag set on it.
     */
    public static NBTTagCompound prepareDataTag (ItemStack stack) {
        
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        
        return stack.getTagCompound();
    }
    
    /**
     * Sets the lore for an ItemStack. This will override any existing lore on that item.
     * 
     * @param stackAn instance of an ItemStack to write the lore to.
     * @param loreAn array containing the lore to write. Each line is a new entry.
     * @return ItemStackThe same instance of ItemStack that was passed to this method.
     */
    public static ItemStack setLore (ItemStack stack, String[] lore) {
        
        prepareDataTag(stack);
        final NBTTagCompound tag = stack.getTagCompound();
        final NBTTagList loreList = new NBTTagList();
        
        if (!tag.hasKey("display", 10))
            tag.setTag("display", new NBTTagCompound());
        
        for (final String line : lore)
            loreList.appendTag(new NBTTagString(line));
        
        tag.getCompoundTag("display").setTag("Lore", loreList);
        stack.setTagCompound(tag);
        
        return stack;
    }
    
    /**
     * Writes an ItemStack as a String. This method is intended for use in configuration files,
     * and allows for a damage sensitive item to be represented as a String. The format looks
     * like "itemid#damage". This method is not intended for actually saving an ItemStack.
     * 
     * @param stackThe instance of ItemStack to write.
     * @return StringA string which can be used to represent a damage sensitive item.
     */
    public static String writeStackToString (ItemStack stack) {
        
        return stack.getItem().getRegistryName().toString() + "#" + stack.getItemDamage();
    }
    
    /**
     * Reads an ItemStack from a string This method is intended for use in reading information
     * from a configuration file. The correct format is "itemid#damage". This method is
     * intended for use with writeStackToString.
     * 
     * @param stackStringThe string used to construct an ItemStack.
     * @return ItemStackAn ItemStack representation of a damage sensitive item.
     */
    public static ItemStack createStackFromString (String stackString) {
        
        final String[] parts = stackString.split("#");
        final Object contents = getThingByName(parts[0]);
        final int damage = parts.length > 1 ? Integer.parseInt(parts[1]) : 0;
        return contents instanceof Item ? new ItemStack((Item) contents, 1, damage) : new ItemStack((Block) contents, 1, damage);
    }
    
    /**
     * Retrieves the color associated with an ItemStack. This method will check the
     * OreDictionary for all items that match with a dye item. The color of that dye will be
     * returned. This is currently only for dyes.
     * 
     * @param stackThe ItemStack to check for the color.
     * @return intAn Integer based representation of a color. Java's Color can be used to
     *         convert these back into their primary components.
     */
    public static int getDyeColor (ItemStack stack) {
        
        if (ItemStackUtils.isValidStack(stack))
            for (final VanillaColor color : VanillaColor.values())
                for (final ItemStack oreStack : OreDictionary.getOres(color.getDyeName()))
                    if (oreStack.isItemEqual(stack))
                        return color.color.getRGB();
                    
        return -1337;
    }
    
    /**
     * Checks if an ItemStack is valid. A valid ItemStack is one that is not null, and has an
     * Item.
     * 
     * @param stackThe ItemStack to check.
     * @return booleanTrue if the stack is valid, false if it is not.
     */
    public static boolean isValidStack (ItemStack stack) {
        
        return stack != null && stack.getItem() != null;
    }
    
    /**
     * Compares all ore dictionary names associated with an ItemStack, with the provided ore
     * dictionary name.
     * 
     * @param stackThe ItemStack to compare against.
     * @param oreNameThe ore dictionary name to compare to.
     * @return booleanTrue if any of the ore dictionary entries for the provided stack match
     *         the provided ore name.
     */
    public static boolean compareStackToOreName (ItemStack stack, String oreName) {
        
        for (final int stackName : OreDictionary.getOreIDs(stack))
            if (OreDictionary.getOreName(stackName).equalsIgnoreCase(oreName))
                return true;
            
        return false;
    }
    
    /**
     * Compares all applicable ore dictionary names for two item stacks, to see if either have
     * a name in common.
     * 
     * @param firstStackThe first ItemStack to compare.
     * @param secondStackThe second ItemStack to compare.
     * @return booleanTrue, if any of the ore dictionary names for either stack are the same.
     */
    public static boolean doStacksShareOreName (ItemStack firstStack, ItemStack secondStack) {
        
        for (final int firstName : OreDictionary.getOreIDs(firstStack))
            for (final int secondName : OreDictionary.getOreIDs(secondStack))
                if (firstName == secondName)
                    return true;
                
        return false;
    }
    
    /**
     * Checks to see if two ItemStacks are similar. A similar stack has the same item, and the
     * same damage.
     * 
     * @param firstStackThe first stack to check.
     * @param secondStackThe second stack to check.
     * @return booleanTrue if stacks are similar, or if both are null.
     */
    public static boolean areStacksSimilar (ItemStack firstStack, ItemStack secondStack) {
        
        return firstStack == null && secondStack == null ? true : isValidStack(firstStack) && isValidStack(secondStack) && firstStack.getItemDamage() == secondStack.getItemDamage() && firstStack.getItem() == secondStack.getItem();
    }
    
    /**
     * Checks to see if two ItemStacks are similar. A similar stack has the same item, and the
     * same damage and same size.
     * 
     * @param firstStackThe first stack to check.
     * @param secondStackThe second stack to check.
     * @return booleanTrue if stacks are similar, or if both are null.
     */
    public static boolean areStacksSimilarWithSize (ItemStack firstStack, ItemStack secondStack) {
        
        return firstStack == null && secondStack == null ? true : isValidStack(firstStack) && isValidStack(secondStack) && firstStack.getItemDamage() == secondStack.getItemDamage() && firstStack.getItem() == secondStack.getItem() && firstStack.getCount() == secondStack.getCount();
    }
    
    public static ItemStack writePotionEffectsToStack (ItemStack stack, PotionEffect[] effects) {
        
        final NBTTagCompound stackTag = prepareDataTag(stack);
        final NBTTagList potionTag = new NBTTagList();
        
        for (final PotionEffect effect : effects)
            potionTag.appendTag(effect.writeCustomPotionEffectToNBT(new NBTTagCompound()));
        
        stackTag.setTag("CustomPotionEffects", potionTag);
        return stack;
    }
    
    /**
     * Writes an ItemStack as a sub NBTTagCompound on a larger NBTTagCompound.
     * 
     * @param stackThe ItemStack to write to the tag.
     * @param tagThe NBTTagCompound to write the stack to.
     * @param tagNameThe name for this new NBTTagCompound entry.
     */
    public static void writeStackToTag (ItemStack stack, NBTTagCompound tag, String tagName) {
        
        final NBTTagCompound stackTag = new NBTTagCompound();
        stack.writeToNBT(stackTag);
        tag.setTag(tagName, stackTag);
    }
    
    /**
     * Safely decreases the amount of items held by an ItemStack.
     * 
     * @param stackThe ItemStack to decrease the size of.
     * @param amountThe amount to decrease the stack size by.
     * @return ItemStackNull, if the stack size is smaller than 1.
     */
    public static ItemStack decreaseStackSize (ItemStack stack, int amount) {
        
        stack.setCount(stack.getCount()-amount);
        return stack.getCount() <= 0 ? null : stack;
    }
    
    /**
     * Checks if two given ItemStack are equal. For them to be equal, both must be null, or
     * both must have a null item, or both must share a damage value. If either stack has a
     * wild card damage value, they will also be considered the same. If the checkNBT parameter
     * is true, they will also need the same item nbt.
     * 
     * @param firstStack The first ItemStack to compare.
     * @param secondStack The second ItemStack to compare.
     * @param checkNBT Should NBT be checked as well?
     * @return boolean Whether or not the items are close enough to be called the same.
     */
   /* public static boolean areStacksEqual (ItemStack firstStack, ItemStack secondStack, boolean checkNBT) {
        
        if (firstStack == null || secondStack == null)
            return firstStack == secondStack;
        
        final Item firstItem = firstStack.getItem();
        final Item secondItem = secondStack.getItem();
        
        if (firstItem == null || secondItem == null)
            return firstItem == secondItem;
        
        if (firstItem == secondItem) {
            
            if (checkNBT && NBTUtils.NBT_COMPARATOR.compare(firstStack.getTagCompound(), secondStack.getTagCompound()) != 0)
                return false;
            
            return firstStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || secondStack.getItemDamage() == OreDictionary.WILDCARD_VALUE || firstStack.getItemDamage() == secondStack.getItemDamage();
        }
        
        return false;
    }*/
    
    /**
     * A check to see if an ItemStack exists within an array of other ItemStack.
     * 
     * @param stackThe ItemStack you are searching for.
     * @param checkNBTShould the stacks need the same NBT for them to be the same?
     * @param stacksThe array of ItemStack to search through.
     * @return booleanWhether or not the array contains the stack you are looking for.
     */
   /* public static boolean isStackInArray (ItemStack stack, boolean checkNBT, ItemStack... stacks) {
        
        for (final ItemStack currentStack : stacks)
            if (areStacksEqual(stack, currentStack, checkNBT))
                return true;
            
        return false;
    }*/
    
    /**
     * Copies an ItemStack with a new size value.
     * 
     * @param stack The ItemStack to copy.
     * @param size The new size to set for the stack.
     * @return ItemStack A new ItemStack with the same item and meta as the original, but with
     *         a new size.
     */
    public static ItemStack copyStackWithSize (ItemStack stack, int size) {
        
        final ItemStack output = stack.copy();
        output.setCount(size) ;
        return output;
    }
    
    /**
     * A blend between the itemRegistry.getObject and bockRegistry.getObject methods. Used for
     * grabbing something from an ID, when you have no clue what it might be.
     *
     * @param name The ID of the thing you're looking for. Domains are often preferred.
     * @return Hopefully the thing you're looking for.
     */
    public static Object getThingByName (String name) {
        
        Object thing = Item.getByNameOrId(name);
        
        if (thing != null)
            return thing;
        
        thing = Block.getBlockFromName(name);
        
        if (thing != null)
            return thing;
        
        return null;
    }
    
    /**
     * Safely gets a block instance from an ItemStack. If the ItemStack is not valid, null will
     * be returned. Null can also be returned if the Item does not have a block form.
     * 
     * @param stack The ItemStack to get a block from.
     * @return The block version of the item contained in the ItemStack.
     */
    public static Block getBlockFromStack (ItemStack stack) {
        
        return isValidStack(stack) ? Block.getBlockFromItem(stack.getItem()) : null;
    }
    
    /**
     * Safely consumes an item from an ItemStack. Respects container items.
     * 
     * @param stack The stack to use.
     * @return The remaining/generated item.
     */
    public static ItemStack consumeStack (ItemStack stack) {
        
        if (stack.getCount() == 1) {
            
            if (stack.getItem().hasContainerItem(stack))
                return stack.getItem().getContainerItem(stack);
            
            else
                return null;
        }
        
        else {
            
            stack.splitStack(1);
            return stack;
        }
    }
    
    /**
     * Safely drops an ItemStack intot he world. Used for mob drops.
     * 
     * @param world The world to drop the item in.
     * @param pos The base pos to drop the item.
     * @param stack The stack to drop.
     */
    public static void dropStackInWorld (World world, BlockPos pos, ItemStack stack) {
        
        if (!world.isRemote) {
            
            final float offset = 0.7F;
            final double offX = world.rand.nextFloat() * offset + (1.0F - offset) * 0.5D;
            final double offY = world.rand.nextFloat() * offset + (1.0F - offset) * 0.5D;
            final double offZ = world.rand.nextFloat() * offset + (1.0F - offset) * 0.5D;
            final EntityItem entityitem = new EntityItem(world, pos.getX() + offX, pos.getY() + offY, pos.getZ() + offZ, stack);
            entityitem.setPickupDelay(10);
            world.spawnEntity(entityitem);
        }
    }
    
    /**
     * Creates an ItemStack which represents a TileEntity, and has all of the TileEntities
     * properties stored.
     * 
     * @param tile The TileEntity to turn into an ItemStack.
     * @return The resulting ItemStack.
     */
    public static ItemStack createStackFromTileEntity (TileEntity tile) {
        
        final ItemStack stack = new ItemStack(tile.getBlockType(), 1, tile.getBlockMetadata());
        prepareDataTag(stack);
        final NBTTagCompound tileTag = tile.writeToNBT(new NBTTagCompound());
        stack.getTagCompound().setTag("TileData", tileTag);
        return stack;
    }
    
    /**
     * Reads tile entity data from an ItemStack. Meant to be an inverse of
     * {@link ItemStackUtils#createStackFromTileEntity(TileEntity)}.
     * 
     * @param tile The tile to apply the changes to.
     * @param stack The stack to read from.
     */
    public static void readTileEntityFromStack (TileEntity tile, ItemStack stack) {
        
        tile.readFromNBT(stack.getTagCompound().getCompoundTag("TileData"));
    }
    
    /**
     * Creates an ItemStack representation of an IBlockState.
     * 
     * @param state The state to use.
     * @param size The stack size to create.
     * @return An ItemStack which represents the passed state.
     */
    public static ItemStack getStackFromState (IBlockState state, int size) {
        
        return new ItemStack(state.getBlock(), size, state.getBlock().getMetaFromState(state));
    }
    
    public static boolean isItemAArrow(Item item){
    	if(item.equals(Items.ARROW) || item.equals(Items.TIPPED_ARROW) || item.equals(Items.SPECTRAL_ARROW)){
    		return true;
    	}
    	return false;
    }
    
    /**
     * Create a skull from an instance of EntityPlayer.
     *
     * @param player The EntityPlayer to use the skin from.
     * @return ItemStack An ItemStack containing a skull that represents the passed player.
     */
    public static ItemStack createSkull (EntityPlayer player) {
        
        return createSkull(player.getDisplayNameString(), player.getUniqueID());
    }
    
    /**
     * Creates a skull using a players UUID.
     * 
     * @param uuid The UUID of the player to base the skull on.
     * @return ItemStack An ItemStack containing a skull which represents the owner of the
     *         passed UUID.
     */
    public static ItemStack createSkull (String name, UUID uuid) {
        
        final ItemStack stack = new ItemStack(Items.SKULL, 1, 3);
        ItemStackUtils.prepareDataTag(stack);
        final NBTTagCompound ownerTag = new NBTTagCompound();
        ownerTag.setString("Name", name);
        ownerTag.setString("Id", uuid.toString());
        stack.getTagCompound().setTag("SkullOwner", ownerTag);
        return stack;
    }
    
    /**
     * Creates a skull from the list of publicly provided MHF accounts.
     * 
     * @param account The MHFAccount to create the skull from.
     * @return ItemStack An ItemStack containing a skull which represents the MHF account.
     */
    public static ItemStack createSkull (MHFAccount account) {
        
        return createSkull(account.getMHFName());
    }
    
    /**
     * Creates a skull that represents a player. This method can use plain text usernames, or
     * player UUID. It is recommended to use the UUID over the username, unless you are 100%
     * certain that the username will never change.
     * 
     * @param owner The owner of the skull being created. Can be a username of a UUID.
     * @return ItemStack An ItemStack containing a skull which represents the passed owner
     *         name.
     */
    public static ItemStack createSkull (String owner) {
        
        final ItemStack stack = new ItemStack(Items.SKULL, 1, 3);
        ItemStackUtils.prepareDataTag(stack);
        stack.getTagCompound().setString("SkullOwner", owner);
        return stack;
    }
    
    /**
     * Creates a vanilla Wither Skeleton Skull.
     * 
     * @return ItemStack An ItemStack containing a vanilla wither skeleton skull.
     */
    public static ItemStack getWitherSkeletonSkull () {
        
        return new ItemStack(Items.SKULL, 1, 1);
    }
    
    /**
     * Creates a vanilla Zombie Skull.
     * 
     * @return ItemStack An ItemStack containing a vanilla zombie skull.
     */
    public static ItemStack getZombieSkull () {
        
        return new ItemStack(Items.SKULL, 1, 2);
    }
    
    /**
     * Creates a vanilla Creeper Skull.
     * 
     * @return ItemStack An ItemStack containing a vanilla creeper skull.
     */
    public static ItemStack getCreeperSkull () {
        
        return new ItemStack(Items.SKULL, 1, 4);
    }
    
    /**
     * Creates a vanilla Steve Skull.
     * 
     * @return ItemStack An ItemStack containing a vanilla steve skull.
     */
    public static ItemStack getSteveSkull () {
        
        return new ItemStack(Items.SKULL, 1, 3);
    }
    
    /**
     * Creates a vanilla Skeleton Skull.
     * 
     * @return ItemStack An ItemStack containing a vanilla skeleton skull.
     */
    public static ItemStack getSkeletonSkull () {
        
        return new ItemStack(Items.SKULL, 1, 0);
    }
    
    /**
     * Creates an array of ItemStacks containing MHF Skulls.
     * 
     * @return ItemStack[] An array of ItemStack containing every skull from the MHFAccount
     *         enum.
     */
    public static ItemStack[] getMHFSkulls () {
        
        int counter = 0;
        final ItemStack[] MHFSkulls = new ItemStack[MHFAccount.values().length];
        
        for (final MHFAccount account : MHFAccount.values()) {
            
            MHFSkulls[counter] = createSkull(account);
            counter++;
        }
        
        return MHFSkulls;
    }
    
    public static enum MHFAccount {
        
        ALEX("Alex", "6ab43178-89fd-4905-97f6-0f67d9d76fd9"),
        BLAZE("Blaze", "4c38ed11-596a-4fd4-ab1d-26f386c1cbac"),
        CAVE_SPIDER("CaveSpider", "cab28771-f0cd-4fe7-b129-02c69eba79a5"),
        CHICKEN("Chicken", "92deafa9-4307-42d9-b003-88601598d6c0"),
        COW("Cow", "f159b274-c22e-4340-b7c1-52abde147713"),
        ENDERMAN("Enderman", "40ffb372-12f6-4678-b3f2-2176bf56dd4b"),
        GHAST("Ghast", "063085a6-797f-4785-be1a-21cd7580f752"),
        GOLEM("Golem", "757f90b2-2344-4b8d-8dac-824232e2cece"),
        HEROBRINE("Herobrine", "9586e5ab-157a-4658-ad80-b07552a9ca63"),
        LAVASLIME("LavaSlime", "0972bdd1-4b86-49fb-9ecc-a353f8491a51"),
        MOOSHROOM("MushroomCow", "a46817d6-73c5-4f3f-b712-af6b3ff47b96"),
        OCELOT("Ocelot", "1bee9df5-4f71-42a2-bf52-d97970d3fea3"),
        PIG("Pig", "8b57078b-f1bd-45df-83c4-d88d16768fbe"),
        PIG_ZOMBIE("PigZombie", "18a2bb50-334a-4084-9184-2c380251a24b"),
        SHEEP("Sheep", "dfaad551-4e7e-45a1-a6f7-c6fc5ec823ac"),
        SLIME("Slime", "870aba93-40e8-48b3-89c5-32ece00d6630"),
        SPIDER("Spider", "5ad55f34-41b6-4bd2-9c32-18983c635936"),
        SQUID("Squid", "72e64683-e313-4c36-a408-c66b64e94af5"),
        STEVE("Steve", "c06f8906-4c8a-4911-9c29-ea1dbd1aab82"),
        VILLAGER("Villager", "bd482739-767c-45dc-a1f8-c33c40530952"),
        CACTUS("Cactus", "1d9048db-e836-4b9a-a108-55014922f1ae"),
        CAKE("Cake", "afb489c4-9fc8-48a4-98f2-dd7bea414c9a"),
        CHEST("Chest", "73d4e068-3a6d-4c8b-8f85-3323546955c4"),
        COCONUT_BROWN("CoconutB", "62efa973-f626-4092-aede-57ffbe84ff2b"),
        COCONUT_GREEN("CoconutG", "74556fea-28ed-4458-8db2-9a8220da0c12"),
        MELON("Melon", "1c7d9784-47ea-4bf3-bc23-acf260b436e6"),
        LOG("OakLog", "e224e5ec-e299-4005-ae22-3b0f77a57714"),
        PUMPKIN("Pumpkin", "f44d355b-b6ae-4ba8-8e62-ae6441854785"),
        TNT1("TNT", "d43af93c-c330-4a3d-bab8-ee74234a011a"),
        TNT2("TNT2", "55e73380-a973-4a52-9bb5-1efa5256125c"),
        ARROW_UP("ArrowUp", "fef039ef-e6cd-4987-9c84-26a3e6134277"),
        ARROW_DOWN("ArrowDown", "68f59b9b-5b0b-4b05-a9f2-e1d1405aa348"),
        ARROW_LEFT("ArrowLeft", "a68f0b64-8d14-4000-a95f-4b9ba14f8df9"),
        ARROW_RIGHT("ArrowRight", "50c8510b-5ea0-4d60-be9a-7d542d6cd156"),
        EXCLAMATION("Exclamation", "d3c47f6f-ae3a-45c1-ad7c-e2c762b03ae6"),
        QUESTION("Question", "606e2ff0-ed77-4842-9d6c-e1d3321c7838"),
        PRESENT_GREEN("Present1", "156b251b-12e0-4829-a130-a61b53ba7720"),
        PRESENT_RED("Present2", "f1eb7cad-e2c0-4e9e-8aad-1eae21d5fd95");
        
        /**
         * The base of the username, without the MHF prefix.
         */
        private final String username;
        
        /**
         * The UUID tied to the account.
         */
        public String UUID;
        
        /**
         * An enumeration of all accounts provided by Mojang under the MHF format.
         * 
         * @param username The username tied to the account.
         * @param uuid The uuid tied to the account.
         */
        MHFAccount(String username, String uuid) {
            
            this.username = username;
            this.UUID = uuid;
        }
        
        /**
         * Provides the base name for this skull. This is the base name, and not the full
         * username. Use getMHFName to get an actual username that can be used.
         * 
         * @return String The skull name, without the MHF_ prefix.
         */
        public String getBaseName () {
            
            return this.username;
        }
        
        /**
         * Provides the username in the MHF format. The MHF format is a format used by Mojang
         * to provide a series of additional player names which can reliably be used for things
         * like skulls.
         * 
         * @return String The basic username, with the MHF_ prefix.
         */
        public String getMHFName () {
            
            return "MHF_" + this.username;
        }
    }
    
}
