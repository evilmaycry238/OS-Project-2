package edu.utdallas.taskExecutorImpl;

import java.util.concurrent.BlockingQueue;

import edu.utdallas.taskExecutor.Task;

public class TaskRunner implements Runnable 
{
	public void runLoop(BlockingQueue<Task> queue) 
	{
		while(true)
		{
			try
			{
				Task task = queue.take();
				task.execute();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
