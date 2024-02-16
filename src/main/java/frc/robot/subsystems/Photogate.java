package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Photogate extends SubsystemBase {
    DigitalInput firstSensor;
    DigitalInput secondSensor;
    boolean isRecordingBoth;
    boolean isRecordingFirst;
    boolean isRecordingSecond;
    double DISTANCE = 0.10;
    double NOTE_DIAMETER = 0.3556;
    private Timer timerBoth;
    private Timer timerFirst;
    private Timer timerSecond;

    public Photogate(){
        firstSensor = new DigitalInput(2);
        secondSensor = new DigitalInput(3);
        timerBoth = new Timer();
        timerFirst = new Timer();
        timerSecond = new Timer();
        isRecordingBoth = false; 
        isRecordingFirst = false;
        isRecordingSecond = false;
    }
    public boolean getFirstSensor(){
        return firstSensor.get();
    }
    public boolean getSecondSensor(){
        return secondSensor.get();
    }
     @Override
    public void periodic() {
        showBothVelocity();
        showFirstVelocity();
        showSecondVelocity();
  }
public void showBothVelocity(){
    if(!getFirstSensor() && !isRecordingBoth){
        isRecordingBoth = true;
        timerBoth.start();
    }
    if(!getSecondSensor() && isRecordingBoth){
        isRecordingBoth = false;
        timerBoth.stop();
        System.out.println("Both Velocity: " + NOTE_DIAMETER/timerBoth.get() + " m/s");
        timerBoth.reset();
    }
}
public void showFirstVelocity(){
    if(!getFirstSensor()){
        isRecordingFirst = true;
        timerFirst.start();
    }
    if(getFirstSensor() && isRecordingFirst){
        isRecordingFirst = false;
        timerFirst.stop();
        System.out.println("First Velocity: " + NOTE_DIAMETER/timerFirst.get() + " m/s");
        timerFirst.reset();
    }
}
public void showSecondVelocity(){
    if(!getSecondSensor()){
        isRecordingSecond = true;
        timerSecond.start();
    }
    if(getSecondSensor() && isRecordingSecond){
        isRecordingSecond = false;
        timerSecond.stop();
        System.out.println("Second Velocity: " + DISTANCE/timerSecond.get() + " m/s");
        timerSecond.reset();
    }
}
}
