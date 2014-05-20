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
		double jw = player.getJumpWidth(0);
		assertTrue((jw > 4.050) && (jw < 4.051));//"4.050900000000412"

	}
	public void testJumpMaxHeight()
	{
		double jh = player.getJumpMaxHeight();
		assertTrue((jh > 2.025) && (jh < 2.026));//"2.0254500000000237"
	}
}
