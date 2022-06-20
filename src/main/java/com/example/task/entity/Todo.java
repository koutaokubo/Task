package com.example.task.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="task")
public class Todo {
  
  @Override
  public String toString() {
    return "Todo [detail=" + detail + ", id=" + id + ", title=" + title + "boolean=" + done + "]";
  }

  public Todo() {}

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Size(min=1, max = 20, message = "input 1 to 20")
  private String title;
  @Size(min = 1, max = 100, message = "input 1 to 100")
  private String detail;
  private boolean done;
  
  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDetail() {
    return detail;
  }
  public void setDetail(String detail) {
    this.detail = detail;
  }

}
