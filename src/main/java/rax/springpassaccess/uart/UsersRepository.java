package rax.springpassaccess.uart;

import rax.springpassaccess.models.UidList;
import rax.springpassaccess.models.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UsersRepository {

    List<UidList> findAll();

    void save(Boolean status, String datatime, Long id);
}

