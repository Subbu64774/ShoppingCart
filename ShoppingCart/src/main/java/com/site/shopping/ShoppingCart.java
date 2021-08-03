package com.site.shopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ShoppingCart {

	private Map<String, LineItem> cartItems = new LinkedHashMap<>();
	private final Inventory inventory;

	public ShoppingCart(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Map<String, LineItem> getCartItems() {
		return cartItems;
	}

	public void addItem(LineItem lineItem) {

		if (cartItems.containsKey(lineItem.getItem())) {
			LineItem lineItemInCart = cartItems.get(lineItem.getItem());
			lineItemInCart.increaseQuantityBy(lineItem.getQuantity());
		} else {
			Item item = inventory.get(lineItem.getItem());
			lineItem.setName(item.getName());
			lineItem.setPrice(item.getPrice());
			cartItems.put(lineItem.getItem(), lineItem);
		}
	}

	public int totalNumberOfCounts() {
		return cartItems.values().stream().mapToInt(o -> o.getQuantity()).sum();
	}

	public void remove(LineItem lineItemToRemove) {
		boolean deleteLineItem = false;
		for (LineItem lineItem : cartItems.values()) {
			if (lineItem.getItem().equals(lineItemToRemove.getItem())) {
				if (lineItem.getQuantity() == lineItemToRemove.getQuantity()) {
					deleteLineItem = true;
				} else {
					lineItem.reduceQuantityBy(lineItemToRemove.getQuantity());
				}

			}
		}
		if (deleteLineItem) {
			cartItems.remove(lineItemToRemove.getItem());
		}

	}

	public List<LineItem> lineItems() {

		return Collections.unmodifiableList(new ArrayList<>(cartItems.values()));
	}

	public BigDecimal totalPrice() {
		BigDecimal totalPrice = BigDecimal.valueOf(0);
		for (LineItem lineItem : cartItems.values()) {
			totalPrice = totalPrice.add(lineItem.totalPrice());
		}
		return totalPrice;
	}

}
