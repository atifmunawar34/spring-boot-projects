package io.github.atifmunawar34.catalog;

import java.math.BigDecimal;
import java.util.List;

import io.github.atifmunawar34.catalog.product.Product;
import io.github.atifmunawar34.catalog.product.ProductCategory;
import io.github.atifmunawar34.catalog.product.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

	private final ProductRepository repository;

	public DataSeeder(ProductRepository repository) {
		this.repository = repository;
	}

	@Override
	public void run(String... args) {
		if (repository.count() > 0) {
			return;
		}
		repository.saveAll(List.of(
				p("Wireless Mouse", "Ergonomic 2.4GHz mouse", "24.99", ProductCategory.ELECTRONICS, 150),
				p("Mechanical Keyboard", "RGB hot-swap keyboard", "89.00", ProductCategory.ELECTRONICS, 80),
				p("USB-C Hub", "7-in-1 hub with HDMI", "45.50", ProductCategory.ELECTRONICS, 200),
				p("Noise-Cancel Headphones", "Over-ear ANC headphones", "199.99", ProductCategory.ELECTRONICS, 45),
				p("Webcam 1080p", "Full HD webcam with mic", "59.95", ProductCategory.ELECTRONICS, 120),
				p("Cotton T-Shirt", "Plain crew-neck tee", "14.99", ProductCategory.CLOTHING, 500),
				p("Denim Jeans", "Slim-fit stretch jeans", "49.99", ProductCategory.CLOTHING, 220),
				p("Hoodie", "Fleece pullover hoodie", "39.95", ProductCategory.CLOTHING, 180),
				p("Running Shoes", "Lightweight trainers", "79.99", ProductCategory.CLOTHING, 90),
				p("Winter Jacket", "Waterproof insulated jacket", "129.00", ProductCategory.CLOTHING, 60),
				p("Arabica Coffee Beans", "1kg medium roast", "18.50", ProductCategory.GROCERY, 300),
				p("Green Tea", "100 tea bags", "9.99", ProductCategory.GROCERY, 400),
				p("Olive Oil", "Extra virgin 1L", "12.75", ProductCategory.GROCERY, 250),
				p("Honey", "Raw organic honey 500g", "15.20", ProductCategory.GROCERY, 140),
				p("Almonds", "Roasted almonds 1kg", "22.00", ProductCategory.GROCERY, 110),
				p("Clean Code", "Robert C. Martin", "34.99", ProductCategory.BOOKS, 75),
				p("Effective Java", "Joshua Bloch", "44.99", ProductCategory.BOOKS, 60),
				p("Spring in Action", "Craig Walls", "39.99", ProductCategory.BOOKS, 55),
				p("Design Patterns", "Gang of Four", "49.99", ProductCategory.BOOKS, 40),
				p("The Pragmatic Programmer", "Hunt & Thomas", "37.50", ProductCategory.BOOKS, 65),
				p("Desk Lamp", "LED lamp with dimmer", "29.99", ProductCategory.HOME, 130),
				p("Office Chair", "Mesh ergonomic chair", "149.00", ProductCategory.HOME, 35),
				p("Standing Desk", "Electric height-adjustable", "349.99", ProductCategory.HOME, 20),
				p("Bookshelf", "5-tier wooden shelf", "89.99", ProductCategory.HOME, 50),
				p("Plant Pot Set", "Ceramic pots, set of 3", "19.99", ProductCategory.HOME, 160),
				p("Monitor 27\"", "1440p IPS display", "279.99", ProductCategory.ELECTRONICS, 55),
				p("Laptop Stand", "Aluminum adjustable stand", "34.99", ProductCategory.ELECTRONICS, 190),
				p("Backpack", "Laptop backpack 15.6\"", "54.99", ProductCategory.CLOTHING, 140),
				p("Water Bottle", "Insulated 750ml", "21.99", ProductCategory.HOME, 220),
				p("Notebook Set", "Dotted journals, pack of 4", "16.99", ProductCategory.BOOKS, 300)));
	}

	private Product p(String name, String desc, String price, ProductCategory category, int stock) {
		return new Product(name, desc, new BigDecimal(price), category, stock);
	}
}
