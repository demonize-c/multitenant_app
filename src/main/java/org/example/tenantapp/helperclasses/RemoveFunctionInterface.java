package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManager;

public interface RemoveFunctionInterface {
      void apply(EntityManager em);
}
