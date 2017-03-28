package daos.core;

import org.springframework.data.jpa.repository.JpaRepository;

import entities.core.Product;

public interface ProductDao  extends JpaRepository<Product, Integer>, ProductExtended{

}
