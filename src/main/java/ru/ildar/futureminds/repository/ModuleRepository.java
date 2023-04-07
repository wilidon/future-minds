package ru.ildar.futureminds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ildar.futureminds.model.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {

}
