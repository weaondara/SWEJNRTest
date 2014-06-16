package sw.superwhateverjnr.test.util;

import junit.framework.Assert;
import junit.framework.TestCase;
import sw.superwhateverjnr.util.Rectangle;

public class RectangleTest extends TestCase
{
	public void testIntersect1()
	{
		Rectangle r1 = new Rectangle(0, 0, 5, 5);
		Rectangle r2 = new Rectangle(1, 1, 6, 6);
		Rectangle rres = new Rectangle(1, 1, 5, 5);
		
		Assert.assertEquals(r1.intersect(r2), rres);
		Assert.assertEquals(r2.intersect(r1), rres);
	}
	public void testIntersect2()
	{
		Rectangle r1 = new Rectangle(0, 0, 5, 5);
		Rectangle r2 = new Rectangle(1, -1, 6, 4);
		Rectangle rres = new Rectangle(1, 0, 5, 4);
		
		Assert.assertEquals(r1.intersect(r2), rres);
		Assert.assertEquals(r2.intersect(r1), rres);
	}
	
	public void testIntersect3()
	{
		Rectangle r1 = new Rectangle(0, 0, 5, 5);
		Rectangle r2 = new Rectangle(-1, -1, 6, 6);
		Rectangle rres = new Rectangle(0, 0, 5, 5);
		
		Assert.assertEquals(r1.intersect(r2), rres);
		Assert.assertEquals(r2.intersect(r1), rres);
	}
	
	public void testIntersect4()
	{
		Rectangle r1 = new Rectangle(0, 0, 5, 5);
		Rectangle r2 = new Rectangle(1, 1, 6, 6);
		Rectangle rres = new Rectangle(1, 1, 5, 5);
		
		Assert.assertEquals(r1.intersect(r2), rres);
		Assert.assertEquals(r2.intersect(r1), rres);
	}
}
