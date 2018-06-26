package poecontroller;

import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;

import javafx.scene.shape.Circle;

import java.lang.Math;

public class PoeController
{
	public static void main(String[] args)
	{
		Robot robot = null;

		// try to make a robot
		try
		{
			robot = new Robot();

		}
		catch (AWTException e)
		{
			System.out.println("failed to create robot");
		}

		// if a robot was made
		if (robot != null)
		{
			// initialize a controller
			ControllerManager controllers = new ControllerManager();
			controllers.initSDLGamepad();

			// find the center of the screen
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			double radius = screenSize.getHeight() / 2;
			double centerX = screenSize.getWidth() / 2;
			double centerY = screenSize.getHeight() / 2;
			double currentX = centerX;
			double currentY = centerY;

			// click stuff
			float currentLeftTriggerState = 0;
			float currentRightTriggerState = 0;

			// controller controlls enabled
			boolean controllerOn = true;

			// this stores the state of the controller
			ControllerState currState;

			// program loop
			while (true)
			{
				// poll for the new state
				currState = controllers.getState(0);
				
				//turns controller on or off
				if(currState.guideJustPressed)
				{
					controllerOn = controllerOn ? false : true;
				}
				
				if (controllerOn)
				{
					// move the mouse to the current location
					robot.mouseMove((int) currentX, (int) currentY);

					// end program if controller is not connected
					// this is for debugging incase mouse is stuck i
					// have a way to exit
					if (!currState.isConnected)
					{
						break;
					}

					// these handle potions
					if (currState.dpadUpJustPressed)
					{
						robot.keyPress('1');
					}
					if (currState.dpadDownJustPressed)
					{
						robot.keyPress('2');
					}
					if (currState.dpadLeftJustPressed)
					{
						robot.keyPress('3');
					}
					if (currState.dpadRightJustPressed)
					{
						robot.keyPress('4');
					}
					if (currState.leftStickJustClicked)
					{
						robot.keyPress('5');
					}

					// these handle skills
					if (currState.aJustPressed)
					{
						robot.keyPress('q');
					}
					if (currState.bJustPressed)
					{
						robot.keyPress('w');
					}
					if (currState.xJustPressed)
					{
						robot.keyPress('e');
					}
					if (currState.yJustPressed)
					{
						robot.keyPress('r');
					}
					if (currState.startJustPressed)
					{
						robot.keyPress('t');
					}

					// update triggers
					currentLeftTriggerState = updateTrigger(robot, currentLeftTriggerState, currState.leftTrigger,
							InputEvent.BUTTON1_DOWN_MASK);
					currentRightTriggerState = updateTrigger(robot, currentRightTriggerState, currState.rightTrigger,
							InputEvent.BUTTON3_DOWN_MASK);

					// update the location of the mouse
					currentY = centerY - Math.sin(Math.toRadians(currState.rightStickAngle)) * radius
							* currState.rightStickMagnitude;
					currentX = centerX + Math.cos(Math.toRadians(currState.rightStickAngle)) * radius
							* currState.rightStickMagnitude;
				}
			}
		}
	}

	private static float updateTrigger(Robot robot, float currentState, float triggerState, int button)
	{
		// if the left trigger has changed
		if (currentState != triggerState)
		{
			// update what is stored
			currentState = triggerState;

			// if it is pressed set the left mouse button to clicked
			if (currentState > 0)
			{
				robot.mousePress(button);
			}
			// otehr wise release the click
			else
			{
				robot.mouseRelease(button);
			}
		}
		return currentState;
	}
}
