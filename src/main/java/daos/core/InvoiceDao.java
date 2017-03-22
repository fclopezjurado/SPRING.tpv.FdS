package daos.core;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Invoice;

public interface InvoiceDao extends JpaRepository<Invoice, Integer> {

    @Query("select i from Invoice i where i.ticket.user.mobile = ?1")
    List<Invoice> findByUserMobile(long userMobile);
    
    @Query("select max(i.id) from Invoice i")
    int findMaxInvoiceID();

}
