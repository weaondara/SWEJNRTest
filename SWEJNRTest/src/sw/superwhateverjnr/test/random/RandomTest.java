package sw.superwhateverjnr.test.random;

import sw.superwhateverjnr.random.RandomMaterialGetter;
import sw.superwhateverjnr.random.RandomMobGetter;
import sw.superwhateverjnr.random.RandomWorldGenerator;

import sw.superwhateverjnr.block.Material;
import sw.superwhateverjnr.entity.EntityType;
import sw.superwhateverjnr.world.World;

import junit.framework.TestCase;

public class RandomTest extends TestCase
{
    private RandomMaterialGetter mg;
    private RandomMobGetter mobg;
    public RandomTest()
    {
        mg = new RandomMaterialGetter(1337);
        mobg = new RandomMobGetter(1337);
    }
    public void testMaterial()
    {
        for(int i = 0; i < 10; i++)
        {
            assertTrue(mg.nextSurface() != null);
            assertTrue(mg.nextFilling() != null);
        }
    }
    public void testEntity()
    {
        for(int i = 0; i < 10; i++)
        {
            assertTrue(mobg.nextMob() != null);
        }
    }
    public void testLittleWorld()
    {
        try
        {
            RandomWorldGenerator rwg = new RandomWorldGenerator(20, 30, 20, 30);
            World w = rwg.newWorld(1337); // Magic!
            assertTrue(w != null);
        }
        catch(Exception e)
        {
            assertTrue(false);
        }
    }
}
