package com.site.shopping;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
	
	private Map<String, Item> inventoryMap = new HashMap<String, Item>();
	
	public void add(Item item) {
		this.inventoryMap.put(item.getId(), item);
	}

	public Item get(String id) {
		return inventoryMap.get(id);
	}
}
