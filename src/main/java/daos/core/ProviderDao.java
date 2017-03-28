package daos.core;

import entities.core.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderDao extends JpaRepository<Provider, Integer> {

    Provider findByMobile(long mobile);
    
    Provider findByCompany(String company);

    Provider findById(int id);

}
