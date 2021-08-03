package com.site.shopping;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class CheckoutTest {

	private Inventory inventory;
	private ShoppingCart cart;

	@Before
	public void setup() {
		Item item1 = new Item("item-1", "ProducName_1", ItemType.PRODUCT1, BigDecimal.valueOf(1000));
		Item item2 = new Item("item-2", "ProducName_2", ItemType.PRODUCT1, BigDecimal.valueOf(3000));
		inventory = new Inventory();
		inventory.add(item1);
		inventory.add(item2);

		cart = new ShoppingCart(inventory);
	}

	@Test
	public void cart_total_price_gold_discount() {
		cart.addItem(new LineItem("item-1", 4));
		cart.addItem(new LineItem("item-2", 3));

		Checkout checkout = new Checkout(cart, CrediCards.GOLD);

		Assertions.assertThat(checkout.getTotalPrice()).isEqualTo(BigDecimal.valueOf(10400.0));
	}
	
	@Test
	public void cart_total_price_silver_discount() {
		cart.addItem(new LineItem("item-1", 4));
		cart.addItem(new LineItem("item-2", 3));

		Checkout checkout = new Checkout(cart, CrediCards.SILVER);

		Assertions.assertThat(checkout.getTotalPrice()).isEqualTo(BigDecimal.valueOf(11700.0));
	}
	
	@Test
	public void cart_total_price_normal_discount() {
		cart.addItem(new LineItem("item-1", 4));
		cart.addItem(new LineItem("item-2", 3));

		Checkout checkout = new Checkout(cart, CrediCards.NORMAL);

		Assertions.assertThat(checkout.getTotalPrice()).isEqualTo(BigDecimal.valueOf(13000));
	}

}
