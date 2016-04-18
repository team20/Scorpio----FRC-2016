package auto;

import org.usfirst.frc.team20.robot.Scorpio;
import org.usfirst.frc.team20.robot.Team20Libraries.T20Node;
import org.usfirst.frc.team20.robot.Team20Libraries.T20ParallelNode;
import org.usfirst.frc.team20.robot.Team20Libraries.T20SeriesNode;

import autoCommands.*;

public class AutoModes extends Scorpio {

	private T20Node systemCheckTree;

	/**
	 * Puts the robot through a system check<br>
	 * <br>
	 * 
	 * @return a system check node
	 */

	public void createSystemCheck() {
		systemCheckTree = new T20SeriesNode();
		createAutoBotsTransformRollOut();
		createAutoBotsTransformConceal();
		systemCheckTree.addChild(rollOutTree);
		systemCheckTree.addChild(new T20AutoCommandHoodToOuterworksPosition());
		systemCheckTree.addChild(new T20AutoCommandFlywheelToSpeed(1000));
		systemCheckTree.addChild(new T20AutoCommandFlywheelToSpeed(flywheel.FLYSPEED_STOP));
		systemCheckTree.addChild(new T20AutoCommandDriveStraightTime(.4, 3));
		systemCheckTree.addChild(new T20AutoCommandDriveStraightTime(-.4, 3));
		systemCheckTree.addChild(transformConcealTree);

	}

	/**
	 * Puts the robot through a system check<br>
	 * <br>
	 * 
	 * @return a system check node
	 */

	public void executeSystemCheck() {
		systemCheckTree.execute();
	}

	private T20Node rollOutTree;

	/**
	 * Sets up robot to go under the low bar or portcullis<br>
	 * <br>
	 * 
	 * @return a rollOut node
	 */

	public void createAutoBotsTransformRollOut() {
		rollOutTree = new T20SeriesNode();
		rollOutTree.addChild(new T20AutoCommandTomahawksDown());
		rollOutTree.addChild(new T20AutoCommandHomeHood());
		rollOutTree.addChild(new T20AutoCommandToggleLance());
		T20Node secondaryRollOut = new T20ParallelNode();
		secondaryRollOut.addChild(new T20AutoCommandLanceDown());
		secondaryRollOut.addChild(new T20AutoCommandHoodToLowPosition());
		rollOutTree.addChild(secondaryRollOut);
	}

	/**
	 * Sets up robot to go under the low bar or portcullis<br>
	 * <br>
	 * 
	 * @return a rollOut node
	 */

	public void executeAutoBotsTransformRollOut() {
		rollOutTree.execute();
	}

	private T20Node transformConcealTree;

	/**
	 * Sets up robot to go over B and D defenses<br>
	 * <br>
	 * 
	 * @return a conceal node
	 */

	public void createAutoBotsTransformConceal() {
		transformConcealTree = new T20SeriesNode();
		transformConcealTree.addChild(new T20AutoCommandHoodToSafePosition());
		transformConcealTree.addChild(new T20AutoCommandToggleLance());
		T20Node secondaryTranformConceal = new T20ParallelNode();
		secondaryTranformConceal.addChild(new T20AutoCommandLanceUp());
		secondaryTranformConceal.addChild(new T20AutoCommandTomahawksUp());
		transformConcealTree.addChild(secondaryTranformConceal);
	}

	/**
	 * Sets up robot to go over B and D defenses<br>
	 * <br>
	 * 
	 * @return a conceal node
	 */

	public void executeAutoBotsTransformConceal() {
		transformConcealTree.execute();
	}

	private T20Node lowBarTree;

	/**
	 * Drives the robot through the low bar and high goals<br>
	 * <br>
	 * 
	 * @return a low bar high goal auto
	 */

	public void createLowBar() {
		createAutoBotsTransformRollOut();
		lowBarTree = new T20SeriesNode();
		lowBarTree.addChild(rollOutTree);
		lowBarTree.addChild(new T20AutoCommandDriveStraightTime(1, 2));

	}

	/**
	 * Drives the robot through the low bar and high goals<br>
	 * <br>
	 * 
	 * @return a low bar high goal auto
	 */
	public void executeLowBar() {
		lowBarTree.execute();
	}

	private T20Node lowBarHighGoalTree;

	/**
	 * Drives the robot through the low bar and high goals<br>
	 * <br>
	 * 
	 * @return a low bar high goal auto
	 */

	public void createLowBarHighGoal() {
		createAutoBotsTransformRollOut();
		lowBarHighGoalTree = new T20SeriesNode();
		lowBarHighGoalTree.addChild(rollOutTree);
		lowBarHighGoalTree.addChild(new T20AutoCommandDriveStraightTime(1, 1.7));
		lowBarHighGoalTree.addChild(new T20AutoCommandArcTurnToAngle(.5, 7));
		lowBarHighGoalTree.addChild(new T20AutoCommandArcTurnToAngle(0, 33));
		lowBarHighGoalTree.addChild(new T20AutoCommandHoodToOuterworksPosition());
		lowBarHighGoalTree.addChild(new T20AutoAutoTarget());
	}

	/**
	 * Drives the robot through the low bar and high goals<br>
	 * <br>
	 * 
	 * @return a low bar high goal auto
	 */

	public void executeLowBarHighGoal() {
		lowBarHighGoalTree.execute();
	}

	private T20Node finishWithAHighGoalTree;

	/**
	 * Drives the robot through a defense and high goals<br>
	 * <br>
	 * 
	 * @return a defense high goal auto
	 */

	public void createfinishWithAHighGoal(boolean hasRolledOut, double turnAngle) {
		createAutoBotsTransformRollOut();
		finishWithAHighGoalTree = new T20SeriesNode();
		T20Node turnRollOut = new T20ParallelNode();
		if (!hasRolledOut)
			turnRollOut.addChild(rollOutTree);
		turnRollOut.addChild(new T20AutoCommandTurnToAngle(turnAngle));
		finishWithAHighGoalTree.addChild(turnRollOut);
		finishWithAHighGoalTree.addChild(new T20AutoCommandHoodToOuterworksPosition());
		finishWithAHighGoalTree.addChild(new T20AutoAutoTarget());
	}

	/**
	 * Drives the robot through a defense and high goals<br>
	 * <br>
	 * 
	 * @return a defense high goal auto
	 */

	public void executefinishWithAHighGoal() {
		finishWithAHighGoalTree.execute();
	}

	private T20Node testTree;

	/**
	 * Drives the robot through the low bar and high goals<br>
	 * <br>
	 * 
	 * @return a low bar high goal auto
	 */

	public void createTestTree() {
		testTree = new T20SeriesNode();
		testTree.addChild(new T20AutoCommandDriveStraightFeetLeft(.3, 1));

	}

	/**
	 * Drives the robot through the low bar and high goals<br>
	 * <br>
	 * 
	 * @return a low bar high goal auto
	 */

	public void executeTestTree() {
		testTree.execute();
	}

	private T20Node crossBAndD;

	/**
	 * Drives the robot over a B or D defense <br>
	 * <br>
	 * 
	 * @return a cross B and D node
	 */

	public void createCrossBAndD(double speed, double time, double angle) {
		crossBAndD = new T20SeriesNode();
		crossBAndD.addChild(new T20AutoCommandLanceWatchDog());
		crossBAndD.addChild(new T20AutoCommandDriveStraightTime(speed, time));
	}

	/**
	 * Drives the robot over a B or D defense <br>
	 * <br>
	 * 
	 * @return a cross B and D node
	 */

	public void executeCrossBAndD() {
		crossBAndD.execute();
	}

	private T20Node mainAutoNode;

	public void createMainAutoMode(int pos, boolean doExtend, boolean shouldHighGoal) {
		boolean rollOutSender = false;
		createAutoBotsTransformConceal();
		createAutoBotsTransformRollOut();
		createCrossBAndD(1, 2.5, ahrs.ahrs.getAngle());
		createLowBar();
		createLowBarHighGoal();
		createSystemCheck();
		createTestTree();
		mainAutoNode = new T20SeriesNode();
		switch (pos) {
		case 1:
			if (shouldHighGoal) {
				mainAutoNode.addChild(lowBarHighGoalTree);
			} else {
				mainAutoNode.addChild(lowBarTree);
			}
			break;
		case 2:
			if (doExtend) {
				mainAutoNode.addChild(lowBarTree);
				rollOutSender = true;
			} else {
				mainAutoNode.addChild(crossBAndD);

			}
			if (shouldHighGoal) {
				createfinishWithAHighGoal(rollOutSender, 30);
				mainAutoNode.addChild(finishWithAHighGoalTree);
			}
			break;
		case 3:
			if (doExtend) {
				mainAutoNode.addChild(lowBarTree);
				rollOutSender = true;
			} else {
				mainAutoNode.addChild(crossBAndD);

			}
			if (shouldHighGoal) {
				createfinishWithAHighGoal(rollOutSender, 5);
				mainAutoNode.addChild(finishWithAHighGoalTree);
			}
			break;
		case 4:
			if (doExtend) {
				mainAutoNode.addChild(lowBarTree);
				rollOutSender = true;
			} else {
				mainAutoNode.addChild(crossBAndD);

			}
			if (shouldHighGoal) {
				createfinishWithAHighGoal(rollOutSender, 0);
				mainAutoNode.addChild(finishWithAHighGoalTree);
			}
			break;
		case 5:
			if (doExtend) {
				mainAutoNode.addChild(lowBarTree);
				rollOutSender = true;
			} else {
				mainAutoNode.addChild(crossBAndD);

			}
			if (shouldHighGoal) {
				createfinishWithAHighGoal(rollOutSender, -40);
				mainAutoNode.addChild(finishWithAHighGoalTree);
			}
			break;
		case 20:
			mainAutoNode.addChild(systemCheckTree);
			break;
		default:
			mainAutoNode.addChild(new T20AutoCommandDoNothing());
			break;
		}
	}

	public void executeMainAutoMode() {
		mainAutoNode.execute();
	}

}
