package org.lichtspiele.ConfirmService.api;

public class ConfirmStorage<T> {

	private T object;
	
	public ConfirmStorage() {
		
	}
	
	public ConfirmStorage(T t) {
		object = t;
	}
	
	public T get() {
		return object;
	}
	
	public void set(T t) {
		object = t;
	}
	
}
