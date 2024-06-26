package net.javaguides.giftbackend.repository;

import net.javaguides.giftbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
