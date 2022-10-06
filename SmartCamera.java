//Name: Semih
//Surname: Bağ


/** Defining the SmartCamera class*/
public class SmartCamera extends SmartObject implements MotionControl, Comparable<SmartCamera> {
	private boolean status;
	private int batteryLife;
	private boolean nightVision;
	
	/** Constructure of SmartCamera */
	public SmartCamera(String alias, String macId, boolean nightVision, int batteryLife) {
		setAlias(alias);
		setMacId(macId);
		setNightVision(nightVision);
		setBatteryLife(batteryLife);
	}
	
	/** Start recording */
	public void recordOn(boolean isDay) {
		if (this.controlConnection()) {
			if (isDay) {
				if (this.isStatus()) {
					System.out.println("Smart Camera - "+this.getAlias()+" has been already turned on");
				}
				else if (!this.isStatus()) {
					this.setStatus(true);
					System.out.println("Smart Camera - "+this.getAlias()+" is turned on now");
				}
			}
			else if (!isDay) {
				if (this.isNightVision()) {
					this.setStatus(true);
					System.out.println("Smart Camera - "+this.getAlias()+" is turned on now");
				}
				else if (!this.isNightVision()) {
					this.setStatus(false);
					System.out.println("Sorry! Smart Camera - "+this.getAlias()+" does not have night vision feature.");
				}
			}
		}
	}
	
	/** Stop recording */
	public void recordOff() {
		if (this.controlConnection()) {
			if (this.isStatus()) {
				this.setStatus(false);
				System.out.println("Smart Camera - "+this.getAlias()+" Cam is turned off now");
			}
			else if (!this.isStatus()){
				System.out.println("Smart Camera - "+this.getAlias()+" Cam has been already turned off");
			}
		}
	}
	
	/** Test the object if it works and return appropriate boolean type */
	public boolean testObject() {
		if (this.controlConnection()) {
			this.SmartObjectToString();
			System.out.println("Test is starting for SmartCamera day time");
			this.recordOn(true);
			this.recordOff();
			System.out.println("Test is starting for SmartCamera night time");
			this.recordOn(false);
			this.recordOff();
			System.out.println("Test completed for SmartCamera");
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Turn off devices */
	public boolean shutDownObject() {
		if (this.isConnectionStatus()) {
			this.SmartObjectToString();
			if (isStatus()) {
				this.recordOff();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	/**  Control the motioan and record*/
	public boolean controlMotion(boolean hasMotion, boolean isDay) {
		if (hasMotion) {
			System.out.println("Motion detected!");
			this.recordOn(isDay);
			return true;
		}
		else {
			System.out.println("Motion not detected!");
			return false;
		}
	}
	
	/** Compare to cameras */
	public int compareTo(SmartCamera smartCamera) {
		if (this.getBatteryLife() > smartCamera.getBatteryLife()) {
			return 1;
		}
		else if (this.getBatteryLife() == smartCamera.getBatteryLife()) {
			return 0;
		}
		else {
			return -1;
		}
	}
	
	/** Printing info about SmartCamera object */
	public String toString() {
		if(this.isStatus()) {
			return "SmartCamera -> "+this.getAlias()+"'s battery life is "+this.getBatteryLife()+" status is recording";
		}
		else {
			return "SmartCamera -> "+this.getAlias()+"'s battery life is "+this.getBatteryLife()+" status is not recording";
		}
	}
	
	
	
	// Getter and setter methods
	/** Return status */	
	public boolean isStatus() {
		return status;
	}
	/** Set a new status */
	public void setStatus(boolean status) {
		this.status = status;
	}

	
	/** Return BatteryLife */	
	public int getBatteryLife() {
		return batteryLife;
	}
	/** Set a new BatteryLife */
	public void setBatteryLife(int batteryLife) {
		this.batteryLife = batteryLife;
	}

	
	/** Return NightVision */	
	public boolean isNightVision() {
		return nightVision;
	}
	/** Set a new NightVision */
	public void setNightVision(boolean nightVision) {
		this.nightVision = nightVision;
	}
}
