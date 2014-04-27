package sw.superwhateverjnr.test.scheduling;

import java.util.Map;

import sw.superwhateverjnr.scheduling.Scheduler;
import sw.superwhateverjnr.scheduling.Task;
import sw.superwhateverjnr.scheduling.Task.State;
import junit.framework.Assert;
import junit.framework.TestCase;
import lombok.SneakyThrows;

public class SchedulerTest extends TestCase
{
	private Scheduler sched;
	public SchedulerTest()
	{
		sched=new Scheduler();
	}
	
	public void testRegistration()
	{
		Runnable r=new Runnable()
		{
			@Override
			public void run() { }
		};
		sched.registerTask(r, 1000);
		
		Map<Integer, Task> tasks=sched.getPendingTasks();
		
		Assert.assertTrue(tasks.size()==1);
		
		Assert.assertTrue(tasks.entrySet().iterator().next().getKey()==0);
		
		Assert.assertTrue(tasks.entrySet().iterator().next().getValue().getRunnable()==r);
		
		Assert.assertTrue(tasks.entrySet().iterator().next().getValue().getPeriod()==-1);
		
		Assert.assertTrue(tasks.entrySet().iterator().next().getValue().getState()==State.Pending);
	}
	
	@SneakyThrows
	public void testStates()
	{
		Runnable r=new Runnable()
		{
			@SneakyThrows
			@Override
			public void run() { Thread.sleep(2000);}
		};
		Task t=sched.registerTask(r, 500);
		
		Assert.assertTrue(t.getState()==State.Pending);
		
		Thread.sleep(800);
		
		Assert.assertTrue(t.getState()==State.Running);
		
		Thread.sleep(2000);
		
		Assert.assertTrue(t.getState()==State.Executed);
	}
	
	@SneakyThrows
	public void testCancelRunning()
	{
		Runnable r=new Runnable()
		{
			@SneakyThrows
			@Override
			public void run() { Thread.sleep(1000);}
		};
		Task t=sched.registerTask(r, 500);
		
		Thread.sleep(600);
		
		Assert.assertTrue(t.getState()==State.Running);

		sched.cancelTask(t.getId());
		
		Assert.assertTrue(sched.getPendingTasks().get(t.getId())==null);
		
		Assert.assertFalse(t.getRunnable()==Scheduler.EMPTY);

		Assert.assertTrue(t.getState()==State.Cancelled);
	}
	public void testCancelPending()
	{
		Runnable r=new Runnable()
		{
			@SneakyThrows
			@Override
			public void run() { Thread.sleep(1000);}
		};
		Task t=sched.registerTask(r, 500);

		Assert.assertTrue(t.getState()==State.Pending);

		sched.cancelTask(t.getId());

		Assert.assertTrue(t.getState()==State.Cancelled);
		
		Assert.assertTrue(t.getRunnable()==Scheduler.EMPTY);
		
		Assert.assertTrue(sched.getPendingTasks().get(t.getId())==null);
	}
	public void testTaskIds()
	{
		Runnable r=new Runnable()
		{
			@SneakyThrows
			@Override
			public void run() { Thread.sleep(2000);}
		};
		Task t1=sched.registerTask(r, 500);
		Task t2=sched.registerTask(r, 500);
		Task t3=sched.registerTask(r, 500);
		
		Assert.assertTrue(t1.getId()+1 == t2.getId());
		Assert.assertTrue(t1.getId()+2 == t3.getId());
	}
	
	private int iterations=10;
	private int count=0;
	@SneakyThrows
	public void testRepeating()
	{
		Runnable r=new Runnable()
		{
			@Override
			public void run()
			{
				if(count<iterations)
				{
					count++;
				}
			}
		};
		sched.registerRepeatingTask(r, 1, 100);
		
		Thread.sleep(1500);
		
		Assert.assertTrue(iterations==count);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
