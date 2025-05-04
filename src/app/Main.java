package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        Product apple = new Product("apple", "fruit", 2.2);
        Product banana = new Product("banana", "fruit", 1.5);
        Product orange = new Product("orange", "fruit", 2.0);
        Product strawberry = new Product("strawberry", "fruit", 3.5);
        Product grape = new Product("grape", "fruit", 4.0);

        Product carrot = new Product("carrot", "vegetable", 1.0);
        Product broccoli = new Product("broccoli", "vegetable", 2.5);
        Product tomato = new Product("tomato", "vegetable", 1.8);
        Product potato = new Product("potato", "vegetable", 0.8);
        Product cucumber = new Product("cucumber", "vegetable", 1.2);

        Product milk = new Product("milk", "dairy", 3.0);
        Product cheese = new Product("cheese", "dairy", 4.0);
        Product yogurt = new Product("yogurt", "dairy", 1.2);
        Product butter = new Product("butter", "dairy", 4.1);
        Product cream = new Product("cream", "dairy", 2.4);

        List<Product> productList = new ArrayList<>();
        productList.add(apple);
        productList.add(banana);
        productList.add(orange);
        productList.add(strawberry);
        productList.add(grape);
        productList.add(carrot);
        productList.add(broccoli);
        productList.add(tomato);
        productList.add(potato);
        productList.add(cucumber);
        productList.add(milk);
        productList.add(cheese);
        productList.add(yogurt);
        productList.add(butter);
        productList.add(cream);

        Function<Product, String> getCategory = product -> product.getCategory();

        Consumer<Product> printProduct = product -> System.out.println(product);

        Map<String, List<Product>> grouped = productList.stream()
                .collect(Collectors.groupingBy(getCategory));

        System.out.println("Групування продуктів за категоріями:");
        grouped.forEach((category, list) -> {
            System.out.println("\n" + category.toUpperCase() + ":");
            list.forEach(printProduct);
        });

        Map<String, Double> averagePrice = productList.stream()
                .collect(Collectors.groupingBy(getCategory,
                        Collectors.averagingDouble(product -> product.getPrice())));

        System.out.println("\nСередня ціна по категоріям:");
        averagePrice.forEach((category, avg) ->
                System.out.printf("%s: %.2f \n", category, avg));

        averagePrice.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry ->
                        System.out.printf("\nКатегорія з найвищою середньою ціною: %s (%.2f)\n",
                                entry.getKey(), entry.getValue()));

    }
}