//Name: Semih
//Surname: Bağ


import java.util.*;

/** Defining the SmartLight class*/
public class SmartLight extends SmartObject implements LocationControl,Programmable {
	private boolean hasLightTurned;
	private Calendar programTime;
	private boolean programAction;
	
	/** Constructure of SmartLight */
	public SmartLight(String alias, String macId) {
		setAlias(alias);
		setMacId(macId);
	}
	
	/** To turn on the lights */
	public void turnOnLight() {
		if(this.controlConnection()) {
			if (this.isHasLightTurned()) {
				System.out.println("Smart Light - "+this.getAlias()+" has been already turned on");
			}
			else {	
				setHasLightTurned(true);
				System.out.println("Smart Light - "+this.getAlias()+" is turned on now (Current time:"+(new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds()+")"));
			}
		}
	}
	
	/** To turn off the lights */
	public void turnOffLight() {
		if(this.controlConnection()) {
			if (!(this.isHasLightTurned())) {
				System.out.println("Smart Light - "+this.getAlias()+" has been already turned off");
			}
			else {	
				setHasLightTurned(false);
				System.out.println("Smart Light - "+this.getAlias()+" is turned off now (Current time:"+(new Date().getHours()+":"+new Date().getMinutes()+":"+new Date().getSeconds()+")"));
			}
		}
	}
	
	/** Test the object if it works and return appropriate boolean type */
	public boolean testObject() {
		if (this.controlConnection()) {
			this.SmartObjectToString();
			this.turnOnLight();
			this.turnOffLight();
			System.out.println("Test completed for SmartLight");
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Turn off devices */
	public boolean shutDownObject() {
		if (this.controlConnection()) {
			if (isHasLightTurned()) {
				this.SmartObjectToString();
				this.turnOffLight();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	public void onLeave() {
		if (this.controlConnection()) {
			System.out.println("On Leave -> Smart Light - "+this.getAlias());
			this.turnOffLight();
		}
	}
	
	public void onCome() {
		if (this.controlConnection()) {
			System.out.println("On Come -> Smart Light - "+this.getAlias());
			this.turnOnLight();
		}
	}
	
	/** Set the time for next action */
	public void setTimer(int seconds) {
		if (this.controlConnection()) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, seconds);
			setProgramTime(cal);
			setProgramAction(true);
			
			if (this.isHasLightTurned()) {
				System.out.print("Smart light - "+this.getAlias()+" will be turned off "+seconds+" seconds later! ");
				System.out.println("(Current time: "+cal.getTime().getHours()+":"+cal.getTime().getMinutes()+":"+cal.getTime().getSeconds()+")");
			}
			else {
				System.out.print("Smart light - "+this.getAlias()+" will be turned on "+seconds+" seconds later !");
				System.out.println("(Current time: "+cal.getTime().getHours()+":"+cal.getTime().getMinutes()+":"+cal.getTime().getSeconds()+")");
			}
		}
	}
	
	/** Cancel the set time */
	public void cancelTimer() {
		if (this.controlConnection()) {
			setProgramTime(null);
			setProgramAction(false);
		}
	}
	
	/** Perform the action according to the set time */
	public void runProgram() {
		if (controlConnection()) {
			Calendar cal = Calendar.getInstance();
			if (this.isProgramAction() && (this.getProgramTime().getTime().getHours() == cal.getTime().getHours()) && (this.getProgramTime().getTime().getMinutes() == cal.getTime().getMinutes()) && (this.getProgramTime().getTime().getSeconds() == cal.getTime().getSeconds())) {
				System.out.println("RunProgram -> Smart Light - "+this.getAlias());
				if(this.isHasLightTurned()) {
					this.turnOffLight();
				}
				else if (!this.isHasLightTurned()) {
					this.turnOnLight();
				}
				this.setProgramTime(null);
				setProgramAction(false);
			}
		}
	}
	
	
	
	// Getter and setter methods
	/** Return hasLightTurned */	
	public boolean isHasLightTurned() {
		return hasLightTurned;
	}
	/** Set a new hasLightTurned */
	public void setHasLightTurned(boolean hasLightTurned) {
		this.hasLightTurned = hasLightTurned;
	}

	
	/** Return programTime */	
	public Calendar getProgramTime() {
		return programTime;
	}
	/** Set a new programTime */
	public void setProgramTime(Calendar programTime) {
		this.programTime = programTime;
	}

	
	/** Return programAction */	
	public boolean isProgramAction() {
		return programAction;
	}
	/** Set a new programAction */
	public void setProgramAction(boolean programAction) {
		this.programAction = programAction;
	}
}
