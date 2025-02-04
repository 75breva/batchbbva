package com.murex.model.catologo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "numoperfront")
public class NumoperfrontCatalogo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id = 0L;

  @Column(name = "value")
  private long value;

  public NumoperfrontCatalogo() {
  }

  public NumoperfrontCatalogo(long id, long value) {
    this.id = id;
    this.value = value;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getValue() {
    return value;
  }

  public void setValue(long value) {
    this.value = value;
  }
}
