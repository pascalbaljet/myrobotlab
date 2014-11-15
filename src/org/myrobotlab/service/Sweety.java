package org.myrobotlab.service;

import org.myrobotlab.framework.Peers;
import org.myrobotlab.framework.Service;
import org.myrobotlab.framework.Status;
import org.myrobotlab.logging.Level;
import org.myrobotlab.logging.LoggerFactory;
import org.myrobotlab.logging.Logging;
import org.myrobotlab.logging.LoggingFactory;
import org.slf4j.Logger;

public class Sweety extends Service {

	private static final long serialVersionUID = 1L;

	public final static Logger log = LoggerFactory.getLogger(Sweety.class);
	transient Arduino arduino;
	transient Sphinx ear;
	transient Speech mouth;
	
	transient Servo leftForearm;
	transient Servo rightForearm;
	transient Servo rightShoulder;
	transient Servo leftShoulder;
	transient Servo rightArm;
	transient Servo neck;
	transient Servo leftEye;
	transient Servo leftArm;
	transient Servo rightEye;
	transient Servo rightHand;
	transient Servo rightWrist;
	transient Servo leftHand;
	transient Servo leftWrist;
	
	int rightMotorBackwardPin = 2;
	int rightMotorForwardPin = 3;
	int leftMotorForwardPin = 4;
	int leftMotorBackwardPin = 5;

	int backUltrasonicTrig = 22;
	int backUltrasonicEcho = 23;
	int back_leftUltrasonicTrig = 24;
	int back_leftUltrasonicEcho = 25;
	int back_rightUltrasonicTrig = 26;
	int back_rightUltrasonicEcho = 27;
	int front_leftUltrasonicTrig = 28;
	int front_leftUltrasonicEcho = 29;
	int frontUltrasonicTrig = 30;
	int frontUltrasonicEcho = 31;
	int front_rightUltrasonicTrig = 32;
	int front_rightUltrasonicEcho = 33;

	int SHIFT = 47;
	int LATCH = 48;
	int DATA = 49;

	public static Peers getPeers(String name) {
		Peers peers = new Peers(name);

		// put peer definitions in
		peers.put("arduino", "Arduino", "arduino");
		peers.put("mouth", "Speech", "sweetys mouth");
		peers.put("ear", "Sphinx", "ear");
		
		peers.put("leftForearm", "Servo", "servo");
		peers.put("rightForearm", "Servo", "servo");
		peers.put("rightShoulder", "Servo", "servo");
		peers.put("leftShoulder", "Servo", "servo");
		peers.put("rightArm", "Servo", "servo");
		peers.put("neck", "Servo", "servo");
		peers.put("leftEye", "Servo", "servo");
		peers.put("leftArm", "Servo", "servo");
		peers.put("rightEye", "Servo", "servo");
		peers.put("rightHand", "Servo", "servo");
		peers.put("rightWrist", "Servo", "servo");
		peers.put("leftHand", "Servo", "servo");
		peers.put("leftWrist", "Servo", "servo");
		
		return peers;
	}

	public void startService(){
		super.startService();
		
		arduino = (Arduino) startPeer("arduino");
		arduino = (Arduino) startPeer("arduino");
		arduino = (Arduino) startPeer("arduino");
		
		mouth.setLanguage("fr");
		mouth.setBackendType("GOOGLE");
		mouth.setGenderFemale();

		leftForearm = (Servo) startPeer("leftForearm");
		rightForearm = (Servo) startPeer("rightForearm");
		rightShoulder = (Servo) startPeer("rightShoulder");
		rightArm = (Servo) startPeer("rightArm");
		neck = (Servo) startPeer("neck");
		leftEye = (Servo) startPeer("leftEye");
		leftArm = (Servo) startPeer("leftArm");
		rightEye = (Servo) startPeer("rightEye");
		rightHand = (Servo) startPeer("rightHand");
		rightWrist = (Servo) startPeer("rightWrist");
		leftHand = (Servo) startPeer("leftHand");
		
		leftForearm.setMinMax(85,140);
		rightForearm.setMinMax(5,67);
		rightShoulder.setMinMax(0,155);
		leftShoulder.setMinMax(0,145);
		rightArm.setMinMax(25,130);
		neck.setMinMax(55,105);
		leftEye.setMinMax(25,125);
		leftArm.setMinMax(45,117);
		rightEye.setMinMax(80,180);
		rightHand.setMinMax(10,75);
		rightWrist.setMinMax(0,180);
		leftHand.setMinMax(80,150);
		leftWrist.setMinMax(0,180);
	}
	
	
	public void startPosition(){
		leftForearm.moveTo(136);
		rightForearm.moveTo(5);
		rightShoulder.moveTo(4);
		leftShoulder.moveTo(145);
		rightArm.moveTo(30);
		leftArm.moveTo(108);
		neck.moveTo(75);
		leftEye.moveTo(75);
		rightEye.moveTo(127);
		rightHand.moveTo(10);
		rightWrist.moveTo(116);
		leftHand.moveTo(150);
		leftWrist.moveTo(85);
	}
	
	public Sweety publishState(){
		super.publishState();
		arduino.publishState();
		leftForearm.publishState();
		rightForearm.publishState();
		rightShoulder.publishState();
		leftShoulder.publishState();
		rightArm.publishState();
		neck.publishState();
		leftEye.publishState();
		leftArm.publishState();
		rightEye.publishState();
		rightHand.publishState();
		rightWrist.publishState();
		leftHand.publishState();
		leftWrist.publishState();
		return this;
	}
	
	
	public boolean connect(String port){
		return arduino.connect(port);
	}
	
	public void attach(){
		rightForearm.attach(arduino.getName(),34);
		leftForearm.attach(arduino.getName(),35);
		rightShoulder.attach(arduino.getName(),36);
		leftShoulder.attach(arduino.getName(),37);
		rightArm.attach(arduino.getName(),38);
		leftArm.attach(arduino.getName(),41);
		neck.attach(arduino.getName(),39);
		leftEye.attach(arduino.getName(),40);
		rightEye.attach(arduino.getName(),42);
		rightHand.attach(arduino.getName(),46);
		rightWrist.attach(arduino.getName(),44);
		leftHand.attach(arduino.getName(),43);
		leftWrist.attach(arduino.getName(),45);
	}
	
	public void detach(){
		rightForearm.detach();
		leftForearm.detach();
		rightShoulder.detach();
		leftShoulder.detach();
		rightArm.detach();
		leftArm.detach();
		neck.detach();
		leftEye.detach();
		rightEye.detach();
		rightHand.detach();
		rightWrist.detach();
		leftHand.detach();
		leftWrist.detach();
	}
	
	public Sweety(String n) {
		super(n);
	}

	@Override
	public String getDescription() {
		return "Service for the robot Sweety";
	}
	
	public Status test(){
		Status status = super.test();
		Runtime.start("gui", "GUIService");
		
		return status;
	}

	static void main(String[] args) {
		LoggingFactory.getInstance().configure();
		LoggingFactory.getInstance().setLevel(Level.INFO);

		try {

			Sweety sweety = (Sweety) Runtime.start("sweety", "Sweety");
			sweety.test();

		} catch (Exception e) {
			Logging.logException(e);
		}
	}

}