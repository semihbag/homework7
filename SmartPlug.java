//Name: Semih
//Surname: Bað
//Student ID:150120070


import java.util.*;

/** Defining the SmartPlug class*/
public class SmartPlug extends SmartObject implements Programmable {
	private boolean status;
	private Calendar programTime;
	private boolean programAction;
	
	/** Constructure of SmartPlug */
	public SmartPlug(String alias, String macId) {
		setAlias(alias);
		setMacId(macId);
	}
	
	/** To turn on the plugs */
	public void turnOn() {
		if(this.controlConnection()) {
			if (this.isStatus()) {
				System.out.println("Smart Plug - "+this.getAlias()+" has been already turned on");
			}
			else {	
				setStatus(true);
				System.out.println("Smart Plug - "+this.getAlias()+" is turned on now (Current time:"+(new Date().getHours()+":"+new Date().getHours()+":"+new Date().getSeconds()+")"));
			}
		}
	}
	
	/** To turn off the plugs */
	public void turnOff() {
		if(this.controlConnection()) {
			if (!(this.isStatus())) {
				System.out.println("Smart Plug - "+this.getAlias()+" has been already turned off");
			}
			else {	
				setStatus(false);
				System.out.println("Smart Plug - "+this.getAlias()+" is turned off now (Current time:"+(new Date().getHours()+":"+new Date().getHours()+":"+new Date().getSeconds()+")"));
			}
		}
	}
	
	/** Test the object if it works and return appropriate boolean type */
	public boolean testObject() {
		if (this.controlConnection()) {
			this.SmartObjectToString();
			this.turnOn();
			this.turnOff();
			System.out.println("Test completed for SmartPlug");
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Turn off devices */
	public boolean shutDownObject() {
		if (this.controlConnection()) {
			this.SmartObjectToString();
			if (isStatus()) {
				this.turnOff();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	/** Set the time for next action */
	public void setTimer(int seconds) {
		if (this.controlConnection()) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.SECOND, seconds);
			setProgramTime(cal);
			setProgramAction(true);
			
			if (this.isStatus()) {
				System.out.print("Smart plug - "+this.getAlias()+" will be turned off "+seconds+" seconds later! ");
				System.out.println("(Current time: "+cal.getTime().getHours()+":"+cal.getTime().getMinutes()+":"+cal.getTime().getSeconds()+")");
			}
			else {
				System.out.print("Smart plug - "+this.getAlias()+" will be turned on "+seconds+" seconds later! ");
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
				System.out.println("RunProgram -> Smart Plug - "+this.getAlias());
				if(this.isStatus()) {
					this.turnOff();
				}
				else if (!this.isStatus()) {
					this.turnOn();
				}
				this.setProgramTime(null);
				setProgramAction(false);
			}
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
