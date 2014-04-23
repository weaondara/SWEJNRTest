package sw.superwhateverjnr.test.block;

import java.lang.reflect.Method;

import sw.superwhateverjnr.block.Block;
import sw.superwhateverjnr.block.BlockFactory;
import sw.superwhateverjnr.block.Material;
import sw.superwhateverjnr.world.DummyWorldLoader;
import sw.superwhateverjnr.world.Location;
import sw.superwhateverjnr.world.World;
import sw.superwhateverjnr.world.WorldLoader;
import junit.framework.Assert;
import junit.framework.TestCase;

public class BlockFactoryTest extends TestCase
{
	private BlockFactory bf;
	private World w;
	public BlockFactoryTest() throws Exception
	{
		bf=BlockFactory.getInstance();
		
		Method m=WorldLoader.class.getDeclaredMethod("createWorld", String.class, int.class, int.class, Location.class, Block[][].class);
		m.setAccessible(true);
		w=(World) m.invoke(new DummyWorldLoader(), "dummy", 10, 5, new Location(1, 1), new Block[10][5]);
	}
	public void testIDNegativ() throws Exception
	{
		try
		{
			bf.create(-1, 0, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	public void testIDOutOFBounds() throws Exception
	{
		try
		{
			bf.create(256, 0, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	public void testIDNeverSupported() throws Exception
	{
		try
		{
			bf.create(255, 0, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof NullPointerException);
		}
		
	}
	public void testXOutOFBounds() throws Exception
	{
		try
		{
			bf.create(0, 10, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}
	public void testYOutOFBounds() throws Exception
	{
		try
		{
			bf.create(0, 0, 5, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
	}
	public void testYOutOFBoundsClose() throws Exception
	{
		//invalid y=5
		try
		{
			bf.create(0, 0, 5, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	public void testAir() throws Exception
	{
		Block b=bf.create(0, 0, 0, w);
		assertTrue(b.getType()==Material.AIR);
	}
	public void testStone() throws Exception
	{
		Block c=bf.create(1, 0, 0, w);
		assertTrue(c.getType()==Material.STONE);
	}
}
