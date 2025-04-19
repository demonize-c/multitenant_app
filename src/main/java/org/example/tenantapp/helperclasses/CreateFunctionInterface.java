package org.example.tenantapp.helperclasses;

import jakarta.persistence.EntityManager;

public interface CreateFunctionInterface {
      void apply(EntityManagerUtil em) throws Exception;
}
