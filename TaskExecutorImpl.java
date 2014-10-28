package edu.utdallas.taskExecutorImpl;

import edu.utdallas.taskExecutor.Task;
import edu.utdallas.taskExecutor.TaskExecutor;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TaskExecutorImpl implements TaskExecutor {

	BlockingQueue<Task> fifoQueue;
	ArrayList<TaskRunner> threadPool;
	public TaskExecutorImpl(int poolSize) 
	{
		//Initialize the blocking queue to store task
		fifoQueue = new ArrayBlockingQueue<Task>(poolSize);
		
		//Initialize the thread pool, and add threads to it. 
		threadPool = new ArrayList<TaskRunner>();
		for (int i = 0; i < poolSize; i++)
		{
			TaskRunner aTaskRunner = new TaskRunner();	
			threadPool.add(aTaskRunner);
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

}
