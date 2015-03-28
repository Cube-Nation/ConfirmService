package org.lichtspiele.ConfirmService.api;

import java.util.Date;
import java.util.HashMap;

import org.lichtspiele.ConfirmService.exception.TimeoutException;

public abstract class ConfirmService implements ConfirmInterface {

	private int timeout;
	
	private long created							= (long) new Date().getTime();
	
	@SuppressWarnings("rawtypes")
	private HashMap<String, ConfirmStorage> storage	= new HashMap<String,ConfirmStorage>();
	
	/*
	 * constructors
	 */
	public ConfirmService() {
		this(15);
	}
	
	public ConfirmService(int timeout) { 
		this.setTimeout(timeout);
	}
	
	/*
	 * storage stuff
	 */
	public void store(String key, ConfirmStorage<?> cs) {
		this.storage.put(key, cs);
	}
	
	public ConfirmStorage<?> get(String key) {
		return this.storage.get(key);
	}
	
	/*
	 * timeout stuff
	 */
	private void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	public int getTimeout() {
		return this.timeout;
	}
	
	public void checkExceeded() throws TimeoutException {
		if (this.created + this.timeout > new Date().getTime()) {
			this.abort();
			throw new TimeoutException();
		}
	}
	
	
		
}