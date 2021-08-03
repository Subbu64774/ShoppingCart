package com.site.shopping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {
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
	public void add_item_to_cart() {
		cart.addItem(new LineItem("item-1"));

		int totalItemCount = cart.totalNumberOfCounts();

		Assertions.assertThat(totalItemCount).isEqualTo(1);
	}
	

	@Test
	public void add_multiple_items_to_cart() {
		cart.addItem(new LineItem("item-1",1));
		cart.addItem(new LineItem("item-2",3));

		int totalItemCount = cart.totalNumberOfCounts();

		Assertions.assertThat(totalItemCount).isEqualTo(4);
	}

	@Test
	public void add_multiple_items_of_the_same_items_to_cart() {
		cart.addItem(new LineItem("item-1",2));

		int totalItemCount = cart.totalNumberOfCounts();

		Assertions.assertThat(totalItemCount).isEqualTo(2);
	}
	
	@Test
	public void remove_item_from_cart() {
		cart.addItem(new LineItem("item-1",1));
		cart.addItem(new LineItem("item-2",3));

		cart.remove(new LineItem("item-1",1));
		int totalItemCount = cart.totalNumberOfCounts();
		Assertions.assertThat(totalItemCount).isEqualTo(3);
	}
	
	@Test
	public void remove_specific_quantity_from_cart() {
		cart.addItem(new LineItem("item-1",2));
		cart.addItem(new LineItem("item-2",3));

		cart.remove(new LineItem("item-1",1));
		cart.remove(new LineItem("item-2",1));
		
		int totalItemCount = cart.totalNumberOfCounts();
		Assertions.assertThat(totalItemCount).isEqualTo(3);
	}
	
	@Test
	public void toal_price_in_cart() {
		cart.addItem(new LineItem("item-1",4));
		cart.addItem(new LineItem("item-2",3));
		
		List<LineItem> lineItems = cart.lineItems();
		
		Assertions.assertThat(lineItems.get(0).totalPrice()).isEqualTo(BigDecimal.valueOf(4000));
		Assertions.assertThat(lineItems.get(1).totalPrice()).isEqualTo(BigDecimal.valueOf(9000));
		
	}
	
	@Test
	public void increase_quantity_for_same_item() {
		cart.addItem(new LineItem("item-1",4));
		cart.addItem(new LineItem("item-1",3));
		
		int totalItemCount = cart.totalNumberOfCounts();
		Assertions.assertThat(totalItemCount).isEqualTo(7);
	}
	@Test
	public void cart_total_price() {
		cart.addItem(new LineItem("item-1",4));
		cart.addItem(new LineItem("item-2",3));
		
		BigDecimal totalPrice = cart.totalPrice();
		
		Assertions.assertThat(totalPrice).isEqualTo(BigDecimal.valueOf(13000));
	}

}
