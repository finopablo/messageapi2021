package edu.utn.mailapi.persistence;

import edu.utn.mailapi.domain.Message;

import java.util.List;

public interface Dao<OBJ,ID> {
    OBJ save(OBJ o);
    void remove(OBJ o);
    OBJ get(ID  id);
    List<OBJ> getAll();
}
