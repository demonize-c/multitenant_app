package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManager;

public interface UpdateFunctionInterface<T> {
    T apply(EntityManager em);
}
