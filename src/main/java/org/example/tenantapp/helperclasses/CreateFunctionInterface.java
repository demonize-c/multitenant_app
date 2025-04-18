package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManager;

public interface CreateFunctionInterface {
      void apply(EntityManager em);
}
