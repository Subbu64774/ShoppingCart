package com.site.shopping;

import java.math.BigDecimal;
import java.util.function.IntPredicate;

public class LineItem {

	private String itemId;
	private int quantity;
	private String name;
	private BigDecimal price;

	public LineItem(String itemId, int quantity) {
		this.itemId = itemId;
		this.quantity = quantity;
	}

	public LineItem(String item) {
		this(item, 1);
	}

	public String getItem() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineItem other = (LineItem) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	public void reduceQuantityBy(int quantityToReduce) {

		this.quantity -= quantityToReduce;

	}

	public BigDecimal totalPrice() {
		return price.multiply(BigDecimal.valueOf(quantity));
	}

	public void increaseQuantityBy(int quantity2) {
		this.quantity += quantity2;
	}

}
