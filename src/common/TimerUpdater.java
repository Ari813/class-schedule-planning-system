package common;

import java.util.Timer;
import java.util.TimerTask;

import Controllers.ManagerController;

public class TimerUpdater
{
	private Timer timer;
	private TimerTask timerTask;
	private ManagerController manager;
	boolean keepRunning;

	public TimerUpdater(ManagerController managerController)
	{

		this.manager = managerController;
		keepRunning = true;
		// create timer task to increment counter
		timerTask = new TimerTask()
		{

			public void run()
			{
				manager.updateRunTimeDisplay();
			}
		};

	}

	public void startTimer()
	{
		// create thread to print counter value
		Thread t = new Thread(new Runnable()
		{

			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						if (!keepRunning)
						{
							timer.cancel();// end the timer
							break;// end this loop
						}
						Thread.sleep(1000);
					} catch (InterruptedException ex)
					{
						ex.printStackTrace();
					}
				}
			}
		});

		timer = new Timer("MyTimer");// create a new timer
		timer.scheduleAtFixedRate(timerTask, 30, 1000);// start timer in 30ms to
														// increment counter

		t.start();// start thread to display counter
	}

	public void StopTimer()
	{
		keepRunning = false;
	}

}
