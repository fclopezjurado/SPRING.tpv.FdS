package daos.users;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import entities.users.User;

public interface UserDao extends JpaRepository<User, Integer> {

    @Query("select token.user from Token token where token.value = ?1 AND token.creationDate > ?2")
    public User findByTokenValue(String tokenValue, Date ahoraMenosTiempoExpiracion);

    public User findByMobile(long mobile);

    @Query("select t.user from Ticket t where t.reference = ?1")
    User findByTicketReference(String reference);

}
