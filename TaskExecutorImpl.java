package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class TaskExecutorImpl implements TaskExecutor 
{
	BlockingQueue<Task> fifoQueue;
	ArrayList<Thread> threadPool;
	public TaskExecutorImpl(int poolSize) 
	{
		//Initialize the blocking queue to store task
		fifoQueue = new ArrayBlockingQueue<Task>(poolSize);
		
		//Initialize the thread pool, and add threads to it. 
		threadPool = new ArrayList<Thread>();
		for (int i = 0; i < poolSize; i++)
		{
			TaskRunner aTaskRunner = new TaskRunner();	
			Thread aThread = new Thread(aTaskRunner);
			threadPool.add(aThread);
		}
		
		//Start all the threads in the thread pool
		for (int i = 0; i < poolSize; i++)
		{
			threadPool.get(i).start();
		}
	}

	@Override
	public void addTask(Task task) 
	{
		try 
		{
			fifoQueue.put(task);
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	
	class TaskRunner implements Runnable
	{
		@Override
		public void run() 
		{
			while(true)
			{
				try
				{
					Task task = fifoQueue.take();
					task.execute();
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
			}
			
		}
		
	}
}


