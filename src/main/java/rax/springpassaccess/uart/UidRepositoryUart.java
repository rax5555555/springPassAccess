package rax.springpassaccess.uart;

import rax.springpassaccess.forms.UidForm;
import rax.springpassaccess.models.UidList;

import java.util.List;

public interface UidRepositoryUart {

    List<UidList> findAll();

    void save(Boolean status, String datatime, Long id);

    void deleteUser(Long userId);

    void addUser(UidForm uidForm);
}

