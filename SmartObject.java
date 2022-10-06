//Name: Semih
//Surname: Bağ


/** Defining the SmartObjecy class*/
public abstract class SmartObject {
	private String alias;
	private String macId;
	private String IP;
	private boolean connectionStatus;
	
	/** Constructure of SmartObject */
	public SmartObject() {	
	}
	
	/** To connect with given IP */
	public boolean connect(String IP) {
		setIP(IP);
		setConnectionStatus(true);
		System.out.println(getAlias()+" connection established");
		return true;
	}
	
	/** To disconnect */
	public boolean disconnect() {
		setConnectionStatus(false);
		setIP(null);
		return true;
	}
	
	/** Printing info about SmartObject object */
	public void SmartObjectToString() {
		System.out.println("This is "+this.getClass().getName()+" device "+this.getAlias()+"\n\tMacId: "+this.getMacId()+"\n\tIP: "+this.getIP());
	}
	
	/** Check if this object connect and return appropriate boolean type */
	public boolean controlConnection() {
		if(this.isConnectionStatus()) {
			return true;
		}
		else {
			String str = this.getClass() + "";
			String[] s = str.split(" ");
			System.out.println("This device is not connected. "+s[1]+" -> "+this.getAlias());
			return false;
		}
	}
	
	/** There is abstract methods
	 * They will be defined 
	 */
	public abstract boolean testObject();
	public abstract boolean shutDownObject();
	
	
	
	// Getter and setter methods
	/** Return alias */	
	public String getAlias() {
		return alias;
	}
	/** Set a new alias */
	public void setAlias(String alias) {
		this.alias = alias;
	}

	
	/** Return MacId */
	public String getMacId() {
		return macId;
	}
	/** Set a new MacId */
	public void setMacId(String macId) {
		this.macId = macId;
	}

	
	/** Return IP */
	public String getIP() {
		return IP;
	}
	/** Set a new IP */
	public void setIP(String IP) {
		this.IP = IP;
	}

	
	/** Return connectionStatus */
	public boolean isConnectionStatus() {
		return connectionStatus;
	}
	/** Set a new connectionStatus*/
	public void setConnectionStatus(boolean connectionStatus) {
		this.connectionStatus = connectionStatus;
	}
}
