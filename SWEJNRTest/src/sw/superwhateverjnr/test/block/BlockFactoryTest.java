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
	public void testBlockFactory() throws Exception
	{
		BlockFactory bf=BlockFactory.getInstance();
		
		Method m=WorldLoader.class.getDeclaredMethod("createWorld", String.class, int.class, int.class, Location.class, Block[][].class);
		m.setAccessible(true);
		World w=(World) m.invoke(new DummyWorldLoader(), "dummy", 10, 5, new Location(1, 1), new Block[10][5]);
		
		//invalid id -1
		try
		{
			bf.create(-1, 0, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		//invalid id 256
		try
		{
			bf.create(256, 0, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		//never supported id 255
		try
		{
			bf.create(255, 0, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof NullPointerException);
		}
		
		//invalid x=10
		try
		{
			bf.create(0, 10, 0, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		//invalid y=5
		try
		{
			bf.create(0, 0, 5, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		//invalid y=5
		try
		{
			bf.create(0, 0, 5, w);
		}
		catch(Exception e)
		{
			Assert.assertTrue(e instanceof IndexOutOfBoundsException);
		}
		
		//valid 
		Block b=bf.create(0, 0, 0, w);
		assertTrue(b.getType()==Material.AIR);
		
		Block c=bf.create(1, 0, 0, w);
		assertTrue(c.getType()==Material.STONE);
	}
}
