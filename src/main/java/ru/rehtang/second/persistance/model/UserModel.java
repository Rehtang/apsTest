package ru.rehtang.second.persistance.model;

import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserModel {
  @Id @Column private String username;

  @Column private String password;

  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinTable(
      name = "owned_animals",
      joinColumns = @JoinColumn(name = "username"),
      inverseJoinColumns = @JoinColumn(name = "name"))
  private List<AnimalModel> ownedAnimals;

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
}
