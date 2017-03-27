package daos.core;

import entities.core.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    @Query("select i from Invoice i where i.ticket.user.mobile = ?1")
    List<Invoice> findByUserMobile(long userMobile);

    @Query("select max(i.id) from Invoice i")
    int findMaxInvoiceID();

}
