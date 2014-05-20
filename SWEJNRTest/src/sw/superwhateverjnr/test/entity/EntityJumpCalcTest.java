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
	public void testJumpWidth()
	{
		System.out.println(player.getJumpWidth(0));
		assertTrue((""+player.getJumpWidth(0)).equals("4.050900000000412"));

	}
	public void testJumpMaxHeight()
	{
		assertTrue((""+player.getJumpMaxHeight()).equals("2.0254500000000237"));
	}
}
