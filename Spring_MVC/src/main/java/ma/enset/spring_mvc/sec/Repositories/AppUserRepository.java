package ma.enset.spring_mvc.sec.Repositories;

import ma.enset.spring_mvc.sec.service.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
