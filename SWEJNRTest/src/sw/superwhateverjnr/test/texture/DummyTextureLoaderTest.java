package sw.superwhateverjnr.test.texture;

import java.io.InputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import sw.superwhateverjnr.SWEJNR;
import sw.superwhateverjnr.texture.DummyTextureLoader;
import sw.superwhateverjnr.texture.Texture;
import sw.superwhateverjnr.util.IdAndSubId;
import junit.framework.Assert;
import junit.framework.TestCase;

public class DummyTextureLoaderTest extends TestCase
{
	public void testDummyTextureLoaderWithStone() throws Exception
	{
		IdAndSubId idstone=new IdAndSubId(1, -1);
		
		DummyTextureLoader loader=new DummyTextureLoader();
		Texture tex=loader.loadTexture(idstone); 
		
		InputStream isstone=SWEJNR.getInstance().getResources().getAssets().open("dummy/textures/blocks/stone.png");
		Bitmap bmstone=BitmapFactory.decodeStream(isstone);
		Texture texstone=new Texture(idstone,bmstone.getWidth(),bmstone.getHeight(),bmstone);
		
		Assert.assertEquals(texstone, tex);
	}
	public void testDummyTextureLoaderWithSomethingElse() throws Exception
	{
		IdAndSubId idsomethingelse=new IdAndSubId(255, -1);
		
		DummyTextureLoader loader=new DummyTextureLoader();
		Texture tex=loader.loadTexture(idsomethingelse); 
		
		InputStream iserror=SWEJNR.getInstance().getResources().getAssets().open("dummy/textures/error.png");
		Bitmap bmerror=BitmapFactory.decodeStream(iserror);
		Texture texerror=new Texture(idsomethingelse,bmerror.getWidth(),bmerror.getHeight(),bmerror);
		
		Assert.assertEquals(texerror, tex);
	}
}
