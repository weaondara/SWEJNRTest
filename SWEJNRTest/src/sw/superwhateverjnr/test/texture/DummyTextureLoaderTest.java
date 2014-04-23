package sw.superwhateverjnr.test.texture;

import sw.superwhateverjnr.texture.DummyTextureLoader;
import sw.superwhateverjnr.texture.Texture;
import junit.framework.Assert;
import junit.framework.TestCase;

public class DummyTextureLoaderTest extends TestCase
{
	public void testDummyDummyTextureLoader() throws Exception
	{
		DummyTextureLoader loader=new DummyTextureLoader();
		Texture tex=loader.loadTexture(null); //param doesn't matter/is ignored
		Assert.assertNotNull(tex);
	}
}
