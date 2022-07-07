package ru.rehtang.second.persistance.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class UserModel {
  @Id
  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "owned_animals",
      joinColumns = @JoinColumn(name = "username"),
      inverseJoinColumns = @JoinColumn(name = "name"))
  private List<AnimalModel> ownedAnimals;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false, columnDefinition = "ACTIVE")
  private Status status;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_role",
      joinColumns = @JoinColumn(name = "user_name", referencedColumnName = "username"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
  private Set<RoleModel> roles = new HashSet<>();

  public UserModel addToOwned(AnimalModel animal) {
    if (ownedAnimals == null) {
      ownedAnimals = new ArrayList<>();
    }
    ownedAnimals.add(animal);
    return this;
  }

  public UserModel removeFromOwned(AnimalModel animal) {
    if (CollectionUtils.isEmpty(ownedAnimals)) {
      return this;
    }
    ownedAnimals.remove(animal);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    UserModel user = (UserModel) o;
    return username != null && Objects.equals(username, user.username);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  public enum Status {
    ACTIVE,
    BANNED,
    DELETED
  }
}
