package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManager;

public interface FunctionWithSession{
    void apply(EntityManager em);
}
