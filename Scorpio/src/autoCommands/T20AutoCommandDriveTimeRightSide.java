package autoCommands;

import org.usfirst.frc.team20.robot.Scorpio;
import org.usfirst.frc.team20.robot.Team20Libraries.T20Command;

import edu.wpi.first.wpilibj.Timer;

public class T20AutoCommandDriveTimeRightSide extends Scorpio implements T20Command {
	private boolean isFinished, isStarted;
	private double speed, time, heading;
	private Timer driveTimer = new Timer();

	public T20AutoCommandDriveTimeRightSide(double speed, double time) {
		this.isFinished = false;
		this.isStarted = false;
		this.time = time;
		this.speed = -speed;
	}

	@Override
	public void execute() {
		if (isFinished) {
			return;
		}

		if (!isStarted) {
			System.out.println("<Drive Straight At Speed: " + this.speed + " For Time: " + this.time + ">");
			drivetrain.setRobotCentric();
			isStarted = !isStarted;
			driveTimer.start();
		}
		if (driveTimer.get() < this.time) {
			drivetrain.driveRightSideOnly(this.speed);
		} else if (driveTimer.get() > this.time) {
			drivetrain.drive(0, 0);
			System.out.println("</Drive Straight At Speed: " + this.speed + " For Time: " + this.time + ">");
			this.isFinished = true;
		}
	}

	@Override
	public boolean isFinished() {
		return isFinished;
	}

	@Override
	public T20Command copy() {
		return new T20AutoCommandDriveTimeRightSide(this.speed, this.time);
	}

}
