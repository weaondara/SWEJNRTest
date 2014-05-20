package sw.superwhateverjnr.test.entity;

import sw.superwhateverjnr.entity.Player;
import junit.framework.TestCase;

public class EntityJumpCalcTest extends TestCase
{
	private Player player;
	public EntityJumpCalcTest()
	{
		player=new Player(null);
	}
	public void testJumpMaxWidth()
	{
		assertTrue((""+player.getJumpMaxWidth()).equals("4.050899999999965"));
	}
	public void testJumpMaxHeight()
	{
		assertTrue((""+player.getJumpMaxHeight()).equals("2.0254500000000237"));
	}
}
