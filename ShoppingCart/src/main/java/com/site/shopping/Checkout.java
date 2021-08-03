package com.site.shopping;

import java.math.BigDecimal;

public class Checkout {

	private static final int goldDiscount = 20;
	private static final int silverDiscount = 10;
	private static final int normalDiscount = 0;

	private ShoppingCart shoppingCart;
	private CrediCards card;
	private BigDecimal totalPrice;

	public Checkout(ShoppingCart shoppingCart, CrediCards card) {
		this.shoppingCart = shoppingCart;
		this.card = card;
	}

	public BigDecimal getTotalPrice() {

		CrediCards cards = this.card;

		int discount = normalDiscount;
		if (cards.equals(CrediCards.GOLD)) {
			discount = goldDiscount;
		}
		if (cards.equals(CrediCards.SILVER)) {
			discount = silverDiscount;
		}
		System.out.println(shoppingCart.totalPrice());
		totalPrice = shoppingCart.totalPrice().subtract(
				shoppingCart.totalPrice().multiply(BigDecimal.valueOf(discount).divide(BigDecimal.valueOf(100))));
		return totalPrice;
	}

}
