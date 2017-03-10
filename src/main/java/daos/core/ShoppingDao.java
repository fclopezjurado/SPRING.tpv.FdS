package daos.core;
 
 
 import org.springframework.data.jpa.repository.JpaRepository;
 
 import entities.core.Shopping;
 
 public interface ShoppingDao extends JpaRepository<Shopping, Long>{
     
 }