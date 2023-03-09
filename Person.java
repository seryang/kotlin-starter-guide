import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public class Person implements Comparable<Person> {
	int age;

	public Person(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int compareTo(@NotNull Person person) {
		return Integer.compare(this.age, person.age);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Person person = (Person)o;
		return age == person.age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(age);
	}
}
