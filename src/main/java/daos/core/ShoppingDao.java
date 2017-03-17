package daos.core;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Shopping;

public interface ShoppingDao extends JpaRepository<Shopping, Long> {

    @Query("select s.productId, s.description, sum(s.amount) "
            + "from Ticket t join t.shoppingList s where t.created BETWEEN ?1 AND ?2 GROUP BY s.productId, s.description ORDER BY SUM(s.amount) DESC")
    public List<Object[]> findBestSellersBetweenDates(Calendar inicio, Calendar fin);

    @Query("select t.created, s.productId, s.description, SUM(s.amount) from Ticket t join t.shoppingList s "
            + "where s.productId = ?1 AND t.created BETWEEN ?2 AND ?3  GROUP BY t.created, s.productId, s.description")
    public List<Object[]> findSalesOfProductBetweenDates(long id, Calendar inicio, Calendar fin);

}
