package terarriabot;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TerarriaBot implements KeyListener
{
	private static Robot robot_;
	
	public static void main(String[] args)
	{
		// try to make a robot
		try
		{
			robot_ = new Robot();
		}
		catch (AWTException e)
		{
			System.out.println("failed to create robot");
		}
	}

	private static void destroyBlock(Robot robot)
	{
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		robot.delay(100);
	}

	@Override
	public void keyPressed(KeyEvent pressed)
	{
		if(pressed.getKeyCode()  == KeyEvent.VK_NUMPAD0)
		{
			robot_.mouseMove(1380,580);
			destroyBlock(robot_);
			
			robot_.mouseMove(1380,570);
			destroyBlock(robot_);
			
			robot_.mouseMove(1380,560);
			destroyBlock(robot_);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}
