/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveTrain;

public class TriggerDrive extends CommandBase {
  private final DriveTrain driveTrain;
  private Timer timer;
  /**
   * Creates a new TriggerDrive.
   */
  public TriggerDrive(DriveTrain driveTrain) {
    this.driveTrain = driveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer = new Timer();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double triggerVal = RobotContainer.xbox.getRawAxis(Constants.RIGHT_TRIGGER)
        - RobotContainer.xbox.getRawAxis(Constants.LEFT_TRIGGER);
    double stick = RobotContainer.xbox.getRawAxis(Constants.LEFT_STICK_X);
    driveTrain.setLeftMotors(triggerVal - stick);
    driveTrain.setRightMotors(triggerVal + stick);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.setLeftMotors(0);
    driveTrain.setRightMotors(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
