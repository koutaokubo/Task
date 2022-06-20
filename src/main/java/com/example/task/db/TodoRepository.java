package com.example.task.db;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.task.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{
  @Transactional
  @Modifying
  @Query(value = "delete from task where id = :pid", nativeQuery = true)
  void deleteById(@Param("pid") Integer pid);

  @Transactional
  @Modifying
  @Query(value = "UPDATE task AS t SET t.done = :pdone WHERE t.id = :pid", nativeQuery = true)
  void checkTask(@Param("pdone") boolean pdone, @Param("pid") Integer pid);

}