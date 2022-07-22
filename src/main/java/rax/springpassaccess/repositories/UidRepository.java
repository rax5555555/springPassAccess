package rax.springpassaccess.repositories;

import rax.springpassaccess.models.UidList;

import java.util.List;

public interface UidRepository {

    List<UidList> findAll();

    void save(Boolean status, String datatime, Long id);
}

