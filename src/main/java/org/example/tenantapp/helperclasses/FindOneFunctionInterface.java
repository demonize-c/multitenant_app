package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManager;

public interface FindOneFunctionInterface<T> {
    T apply(EntityManager em);
}
