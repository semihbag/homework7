//Name: Semih
//Surname: Bağ


import java.util.*;

/** Defining the SmartHome class*/
public class SmartHome {
	private ArrayList<SmartObject> smartObjectList = new ArrayList<>();

	/** Constructure of SmartCamera */
	public SmartHome() {
		
	}
	
	/** Add SmartObject to smartObjectList arraylist */
	public boolean addSmartObject(SmartObject smartObject) {
		int ip = this.getSmartObjectList().size() + 100;
		String IP = "100.0.0." + ip;
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Adding new SmartObject");
		System.out.println("---------------------------------------------------------------------------");
		smartObject.connect(IP);
		System.out.println("Test is starting for"+smartObject.getClass().getName());
		smartObject.testObject();
		System.out.println();
		return this.getSmartObjectList().add(smartObject);
	}

	/** Remove SmartObject to smartObjectList arraylist */
	public boolean removeSmartObject(SmartObject smartObject) {
		return this.getSmartObjectList().remove(smartObject);
	}
	
	/** Control the location */
	public void controlLocation(boolean onCome) {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("LocationControl: OnCome");
		System.out.println("---------------------------------------------------------------------------");

		for (int i = 0 ; i < this.getSmartObjectList().size() ; i++) {
			if (this.getSmartObjectList().get(i) instanceof LocationControl) {
				if (onCome) {
					((SmartLight)(this.getSmartObjectList().get(i))).onCome();
				}
				else {
					((SmartLight)(this.getSmartObjectList().get(i))).onLeave();
				}
			}
		}
	}
	
	/** Control the motion */
	public void controlMotion(boolean hasMotion, boolean isDay) {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("MotionControl: HasMotion, isDay");
		System.out.println("---------------------------------------------------------------------------");
		
		for (int i = 0 ; i < this.getSmartObjectList().size() ; i++) {
			if (this.getSmartObjectList().get(i) instanceof MotionControl) {
				((SmartCamera)(this.getSmartObjectList().get(i))).controlMotion(hasMotion, isDay);
			}
		}
	}
	
	/** Control the program */
	public void controlProgrammable() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Programmable: runProgram");
		System.out.println("---------------------------------------------------------------------------");
		
		for (int i = 0 ; i < this.getSmartObjectList().size() ; i++) {
			if (this.getSmartObjectList().get(i) instanceof Programmable) {
				if (this.getSmartObjectList().get(i) instanceof SmartLight) {
					((SmartLight)(this.getSmartObjectList().get(i))).runProgram();
				}
				if (this.getSmartObjectList().get(i) instanceof SmartPlug) {
					((SmartPlug)(this.getSmartObjectList().get(i))).runProgram();
				}
			}
		}
	}
	
	/** Control the timer */
	public void controlTimer(int seconds) {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Programmable: Timer ="+seconds+" seconds");
		System.out.println("---------------------------------------------------------------------------");
		
		for (int i = 0 ; i < this.getSmartObjectList().size() ; i++) {
			if (this.getSmartObjectList().get(i) instanceof Programmable) {
				if (this.getSmartObjectList().get(i) instanceof SmartLight) {
					if (seconds > 0) {
						((SmartLight)(this.getSmartObjectList().get(i))).setTimer(seconds);
					}
					if (seconds == 0) {
						((SmartLight)(this.getSmartObjectList().get(i))).cancelTimer();;
					}
				}
				if (this.getSmartObjectList().get(i) instanceof SmartPlug) {
					if (seconds > 0) {
						((SmartPlug)(this.getSmartObjectList().get(i))).setTimer(seconds);
					}
					if (seconds == 0) {
						((SmartPlug)(this.getSmartObjectList().get(i))).cancelTimer();;
					}
				}
			}
		}
	}
	
	/** Control the timer randomly */
	public void controlTimerRandomly() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Programmable: Timer = 0, 5 or 10 seconds randomly");
		System.out.println("---------------------------------------------------------------------------");

		for (int i = 0 ; i < this.getSmartObjectList().size() ; i++) {
			if (this.getSmartObjectList().get(i) instanceof Programmable) {
				int randomNumber = (int)(Math.random() * 3);
				
				switch (randomNumber) {
					case 0 :
						if (this.getSmartObjectList().get(i) instanceof SmartLight) {
							((SmartLight)(this.getSmartObjectList().get(i))).cancelTimer();
						}
						if (this.getSmartObjectList().get(i) instanceof SmartPlug) {
							((SmartPlug)(this.getSmartObjectList().get(i))).cancelTimer();
						}
						break;
					case 1 :
						if (this.getSmartObjectList().get(i) instanceof SmartLight) {
							((SmartLight)(this.getSmartObjectList().get(i))).setTimer(5);
						}
						if (this.getSmartObjectList().get(i) instanceof SmartPlug) {
							((SmartPlug)(this.getSmartObjectList().get(i))).setTimer(5);
						}
						break;
					case 2 :
						if (this.getSmartObjectList().get(i) instanceof SmartLight) {
							((SmartLight)(this.getSmartObjectList().get(i))).setTimer(10);
						}
						if (this.getSmartObjectList().get(i) instanceof SmartPlug) {
							((SmartPlug)(this.getSmartObjectList().get(i))).setTimer(10);
						}
						break;
				}
			}
		}
	}
	
	/** Sort cameras */
	public void sortCameras() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Sort Smart Cameras");
		System.out.println("---------------------------------------------------------------------------");
		
		ArrayList<SmartCamera> smartCameras = new ArrayList<>();
		
		for (int i = 0 ; i < this.getSmartObjectList().size() ; i++) {
			if (this.getSmartObjectList().get(i) instanceof SmartCamera) {
				smartCameras.add(((SmartCamera)(this.getSmartObjectList().get(i))));
			}
		}
		
		Object[] smartCamerasArray = smartCameras.toArray();
		Arrays.sort(smartCamerasArray);
		
		for (int i = 0 ; i < smartCamerasArray.length ; i++) {
			System.out.println(smartCamerasArray[i].toString());
		}
	}
	
	
	
	// Getter and setter methods
	/** Return smartObjectList */	
	public ArrayList<SmartObject> getSmartObjectList() {
		return smartObjectList;
	}
	/** Set a new smartObjectList */
	public void setSmartObjectList(ArrayList<SmartObject> smartObjectList) {
		this.smartObjectList = smartObjectList;
	}
}
