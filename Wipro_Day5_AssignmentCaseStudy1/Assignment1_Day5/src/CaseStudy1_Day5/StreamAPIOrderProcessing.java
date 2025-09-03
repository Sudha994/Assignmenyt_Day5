package CaseStudy1_Day5;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Order {
	private String customer;
	private String productCategory;
	private double value;

	public Order(String customer, String productCategory, double value) {
		this.customer = customer;
		this.productCategory = productCategory;
		this.value = value;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String toString() {
		return customer + " -->  "+ productCategory +"  : ( $ "+value + " )";
	}
}

public class StreamAPIOrderProcessing {

	public static void main(String[] args) {
		List<Order> orders = Arrays.asList(
				new Order("Sanjith", "Electronics", 1200.0),
				new Order("Sanjith", "Electronics", 100.0),
				new Order("Vinodh", "Books", 135.0),
				new Order("Kumar", "Books", 225.0),
				new Order("Shivu", "Electronics", 800.0),
				new Order("Bob", "Electronics", 600.0)
				);
		
		// Now we need to filter the Orders above 500 rs  price or value
		double minValue = 500.0;
		List<Order> highValueOrders = orders.stream()
				.filter(or -> or.getValue()  > minValue)
				.collect(Collectors.toList());
		System.out.println("Orders above $"+ minValue+" : ");
		highValueOrders.forEach(System.out::println);
		
		
		//  Count total orders per customer
		Map<String, Long> ordersPerCustomer = orders.stream()
				.collect(Collectors.groupingBy(Order::getCustomer, Collectors.counting()));
		System.out.println("\nOrders per Customer: ");
		ordersPerCustomer.forEach((customer, count) ->
		System.out.println(customer+ " : "+count));
		
		
		// Sort and group orders by product category
		Map<String, List<Order>> ordersByCategory = orders.stream()
				.sorted(Comparator.comparing(Order::getProductCategory))
				.collect(Collectors.groupingBy(Order::getProductCategory));
		System.out.println("\nOrders by category: ");
		ordersByCategory.forEach((category, orderList) -> {
			System.out.println("\n"+ category);
			orderList.forEach(System.out::println);
		});
	}

}
