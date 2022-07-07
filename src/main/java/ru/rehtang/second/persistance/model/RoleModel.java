package ru.rehtang.second.persistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "roles")
public class RoleModel {

  @Id private UUID id;

  @Enumerated(EnumType.STRING)
  @Column(name = "name", nullable = false)
  private ERole name;

  @JsonBackReference
  @ToString.Exclude
  @ManyToMany(
      mappedBy = "roles",
      cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
  private List<UserModel> users;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    RoleModel that = (RoleModel) o;
    return id != null && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public enum ERole {
    ROLE_USER,
    ROLE_ADMIN
  }
}
