package org.example.zooshop.config;
import org.example.zooshop.entity.Product;
import org.example.zooshop.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    public CommandLineRunner initData(ProductRepository productRepository) {
        return args -> {
            // Создаем список товаров
            List<Product> products = new ArrayList<>();

            // для собак
            products.add(
                    new Product(
                            "Корм для собак",
                            "dog",
                            500.0
                    )
            );
            products.add(
                    new Product(
                            "Ошейник для собак",
                            "dog",
                            1500.0

                    )
            );
            products.add(
                    new Product(
                            "Игрушка косточка для собак",
                            "dog",
                            1500.0

                    )
            );
            products.add(
                    new Product(
                            "Веревка с мячиком для собак",
                            "dog",
                            1500.0

                    )
            );
            products.add(
                    new Product(
                            "Игрушечный мячик для собак",
                            "dog",
                            1500.0

                    )
            );

            // для рыбок
            products.add(
                    new Product(
                            "Корм для рыбок",
                            "fish",
                            500.0

                    )
            );
            products.add(
                    new Product(
                            "Аквариум для рыбок",
                            "fish",
                            2000.0

                    )
            );
            products.add(
                    new Product(
                            "Декорация для аквариума",
                            "fish",
                            1500.0

                    )
            );

            // для кошек
            products.add(
                    new Product(
                            "Корм для кошек",
                            "cat",
                            700.0

                    )
            );
            products.add(
                    new Product(
                            "Мятные шарики для кошек",
                            "cat",
                            1300.0

                    )
            );
            products.add(
                    new Product(
                            "Игрушка для кошек",
                            "cat",
                            880.0
                    )
            );
            // Сохраняем товары в базу данных
            productRepository.saveAll(products);
        };
    }
}

