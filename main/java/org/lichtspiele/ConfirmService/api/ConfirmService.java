package org.lichtspiele.ConfirmService.api;

import java.util.Date;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;
import org.lichtspiele.ConfirmService.ConfirmServicePlugin;
import org.lichtspiele.ConfirmService.exception.TimeoutException;

public abstract class ConfirmService implements ConfirmInterface {

	private int timeout;
	
	private long created							= (long) new Date().getTime();
	
	protected BukkitTask task;
	
	@SuppressWarnings("rawtypes")
	private HashMap<String, ConfirmStorage> storage	= new HashMap<String,ConfirmStorage>();
	
	/*
	 * constructors
	 */
	public ConfirmService() {
		this(ConfirmServicePlugin.getInstance().getConfig().getInt("timeout"));
	}
	
	public ConfirmService(int timeout) { 
		this.setTimeout(timeout);
		
		final ConfirmService csi = this;
		// create task with timeout
		// this task needs to be canceled when call() is executed via task.cancel();
		task = Bukkit.getServer().getScheduler().runTaskLater(ConfirmServicePlugin.getInstance(), new Runnable() {

			@Override
			public void run() {
				csi.abort();
			}
			
		}, (long) this.getTimeout() * 20L);	
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
		if (new Date().getTime() > this.created + ( this.timeout * 1000)) {
			this.abort();
			throw new TimeoutException();
		}
	}
	
	
		
}