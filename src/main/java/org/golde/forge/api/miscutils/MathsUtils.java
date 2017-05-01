package org.golde.forge.api.miscutils;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

public final class MathsUtils {
    static Random RANDOM = new Random();
    /**
     * Checks if a double is within range of two other doubles.
     * 
     * @param min: The smallest valid value.
     * @param max: The largest valid value.
     * @param value: The value to check.
     * @return boolean: Whether or not the value is within the provided scope.
     */
    public static boolean isInRange (double min, double max, double value) {
        
        return value <= max && value >= min;
    }
    
    /**
     * Calculates the distance between two Vec3 positions.
     * 
     * @param firstPos: The first position to work with.
     * @param secondPos: The second position to work with.
     * @return double: The distance between the two provided locations.
     */
	public static double getDistanceBetweenPoints (Vec3d firstPos, Vec3d secondPos) {
        
        final double distanceX = firstPos.xCoord - secondPos.xCoord;
        final double distanceY = firstPos.yCoord - secondPos.yCoord;
        final double distanceZ = firstPos.zCoord - secondPos.zCoord;
        
        return Math.sqrt(distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ);
    }
    
    /**
     * This method can be used to round a double to a certain amount of places.
     * 
     * @param value: The double being round.
     * @param places: The amount of places to round the double to.
     * @return double: The double entered however being rounded to the amount of places
     *         specified.
     */
    public static double round (double value, int places) {
        
        return value >= 0 && places > 0 ? new BigDecimal(value).setScale(places, RoundingMode.HALF_UP).doubleValue() : value;
    }
    
    /**
     * Used to retrieve a random integer between the two provided integers. The integers
     * provided are also possible outcomes.
     * 
     * @param min: The minimum value which can be returned by this method.
     * @param max: The maximum value which can be returned by this method.
     */
    public static int nextIntInclusive (int min, int max) {
        
        return RANDOM.nextInt(max - min + 1) + min;
    }
    
    /**
     * Creates a MovingObjectPosition based on where a player is looking.
     * 
     * @param player: The player to get the looking position of.
     * @param length: The distance to go outwards from the player, the maximum "reach". Default
     *        reach is 4.5D.
     * @return MovingObjectPosition: A MovingObjectPosition containing the exact location where
     *         the player is looking.
     */
    public static RayTraceResult rayTrace (EntityPlayer player, double length) {
        
        final Vec3d vec1 = new Vec3d(player.posX, player.posY + player.getEyeHeight(), player.posZ);
        final Vec3d vec2 = player.getLookVec();
        final Vec3d vec3 = vec1.addVector(vec2.xCoord * length, vec2.yCoord * length, vec2.zCoord * length);
        return player.world.rayTraceBlocks(vec1, vec3);
    }
    
    /**
     * A method which handles the calculating of percentages. While this isn't a particularly
     * difficult piece of code, it has been added for the sake of simplicity.
     * 
     * @param percent: The percent chance that this method should return true. 1.00 = 100%
     * @return boolean: Returns are randomly true or false, based on the suplied percentage.
     */
    public static boolean tryPercentage (double percent) {
        
        return Math.random() < percent;
    }
    
    /**
     * Generates a random color as an integer, from Color and three random floats.
     * 
     * @return int: An integer based representation of a Color.
     */
    public static int getRandomColor () {
        
        return new Color(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat()).getRGB();
    }
    
    /**
     * Gets the middle integer between two other integers. The order is not important.
     * 
     * @param first: The first integer.
     * @param second: The second integer.
     * @return int: The integer that is between the two provided integers.
     */
    public static int getAverage (int first, int second) {
        
        return Math.round((first + second) / 2.0F);
    }
    
    /**
     * Converts time in ticks to a human readable string.
     * 
     * @param ticks: The amount of ticks to convert.
     * @return String: A human readable version of the time.
     */
    public static String ticksToTime (int ticks) {
        
        final int seconds = ticks / 20;
        final int minutes = seconds / 60;
        return minutes + ":" + seconds;
    }
    
    /** linearly interpolate for y between [x1, y1] to [x2, y2] using x
     *  y = y1 + (y2 - y1) * (x - x1) / (x2 - x1)
     *  For example:  if [x1, y1] is [0, 100], and [x2,y2] is [1, 200], then as x increases from 0 to 1, this function
     *    will increase from 100 to 200
     * @param x  the x value to linearly interpolate on
     * @param x1
     * @param x2
     * @param y1
     * @param y2
     * @return linearly interpolated value.  If x is outside the range, clip it to the nearest end
     */
    public static double interpolate(double x, double x1, double x2, double y1, double y2)
    {
      if (x1 > x2) {
        double temp = x1; x1 = x2; x2 = temp;
        temp = y1; y1 = y2; y2 = temp;
      }

      if (x <= x1) return y1;
      if (x >= x2) return y2;
      double xFraction = (x - x1) / (x2 - x1);
      return y1 + xFraction * (y2 - y1);
    }

    public static Vec3d scalarMultiply(Vec3d source, double multiplier)
    {
      return new Vec3d(source.xCoord * multiplier, source.yCoord * multiplier, source.zCoord * multiplier);
    }

}
