package daos.core;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.core.Voucher;

public interface VoucherDao extends JpaRepository<Voucher, Integer> {

    @Query(value = "SELECT coalesce(sum(value),0) FROM Voucher WHERE dateOfUse is NULL and (expiration is NULL or expiration > now() )", nativeQuery = true)
    BigDecimal findTotalActiveVouchers();

    Voucher findFirstByReference(String reference);
}
