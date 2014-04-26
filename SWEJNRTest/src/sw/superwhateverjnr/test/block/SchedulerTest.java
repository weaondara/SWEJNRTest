package sw.superwhateverjnr.test.block;

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
		
		Thread.sleep(600);
		
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

		System.out.println(t.getRunnable());
		
		sched.cancelTask(t.getId());

		Assert.assertTrue(t.getState()==State.Cancelled);
		
		System.out.println(t.getRunnable());
		System.out.println(Scheduler.EMPTY);
		
		Assert.assertTrue(t.getRunnable()==Scheduler.EMPTY);
		
		Assert.assertTrue(sched.getPendingTasks().get(t.getId())==null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
