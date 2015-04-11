package net.lkrnac.book.eiws.chapter04.model;

import java.util.Objects;

@SuppressWarnings("PMD.ShortClassName")
public class User {
  private int identifier;
  private String email;
  private String name;

  public int getIdentifier() {
    return identifier;
  }

  public void setIdentifier(int identifier) {
    this.identifier = identifier;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(this.getIdentifier());
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      return Objects.equals(((User) obj).getIdentifier(), this.getIdentifier());
    }
    return false;
  }

}
