package org.pet_shelter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    String name;
    String breed;
    Number age;

    @Override
    public int hashCode() {
        return Objects.hash(name, breed, age);
    }
}