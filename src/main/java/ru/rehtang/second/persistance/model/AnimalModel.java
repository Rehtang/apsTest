package ru.rehtang.second.persistance.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import ru.rehtang.second.dto.AnimalType;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "animal")
public class AnimalModel {
  @Id @Column private String name;

  @Column private LocalDate birthday;

  @Column private String sex;

  @Column private AnimalType type;

  @JsonBackReference
  @ToString.Exclude
  @ManyToMany(fetch = FetchType.EAGER, mappedBy = "owned_animals")
  private List<UserModel> users;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    AnimalModel animal = (AnimalModel) o;
    return name != null && Objects.equals(name, animal.name);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
