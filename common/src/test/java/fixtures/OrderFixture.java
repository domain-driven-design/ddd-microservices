package fixtures;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFixture {
    public static Order buildData() {
        Order order = new Order();
        order.setId("123456");
        order.setCustomerName("John Doe");
        order.setDate(new Date());
        for (int i = 0; i < 100; i++) {
            order.getItems().add(new Item("Widget" + i + 1, 2, 9.99));
            order.getItems().add(new Item("Gadget" + i + 1, 1, 19.99));
        }
        order.calculateTotal();
        return order;
    }

    @Data
    @AllArgsConstructor
    public static class Item {
        private String name;
        private int quantity;
        private double price;
    }

    @Data
    public static class Order {
        private String id;
        private String customerName;
        private Date date;
        private List<Item> items = new ArrayList<>();
        private double total;

        public void calculateTotal() {
            total = items.stream().mapToDouble(item -> item.getPrice() * item.getQuantity()).sum();
        }
    }
}
