package com.xyz.xyz_backend_app.seeder;

import com.github.javafaker.Faker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import com.xyz.xyz_backend_app.entity.Product;
import com.xyz.xyz_backend_app.repository.ProductRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Only seed data if the database is empty
        if (productRepository.count() == 0) {
            Faker faker = new Faker();
            List<String> brands = Arrays.asList("Toyota", "Daihatsu", "Honda", "Mitsubishi", "Suzuki", "Nissan");
            List<String> types = Arrays.asList("SUV", "Sedan", "Hatchback", "MPV", "Crossover");

            for (int i = 0; i < 20; i++) { 
                Product product = new Product();
                product.setBrand(brands.get(faker.random().nextInt(brands.size())));
                product.setType(types.get(faker.random().nextInt(types.size())));
                product.setStock(faker.number().numberBetween(1, 50));
                product.setPrice((long) faker.number().numberBetween(150, 801) * 1_000_000);
                product.setDescription(faker.lorem().sentence());

                productRepository.save(product);
            }
            System.out.println("Database seeded with 20 products.");
        } else {
            System.out.println("Database already contains data. Seeding not required.");
        }
    }
}
