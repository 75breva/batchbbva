package com.murex.model.catologo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cur_pl")
public class CurlPlCatalogo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "cur_pl")
  private String cur_pl;

  public CurlPlCatalogo() {
  }

  public CurlPlCatalogo(long id, String cur_pl) {
    this.id = id;
    this.cur_pl = cur_pl;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCur_pl() {
    return cur_pl;
  }

  public void setCur_pl(String cur_pl) {
    this.cur_pl = cur_pl;
  }
}
