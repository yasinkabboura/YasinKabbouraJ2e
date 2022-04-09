package ma.enset.spring_mvc.sec.Repositories;

import ma.enset.spring_mvc.sec.service.AppRole;
import ma.enset.spring_mvc.sec.service.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppUser findByRoleName(String roleName);
}
