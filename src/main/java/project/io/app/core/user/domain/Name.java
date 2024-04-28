package project.io.app.core.user.domain;

import javax.persistence.*;
import java.util.*;
import java.util.regex.*;

@Embeddable
public class Name {

    private static final Pattern pattern = Pattern.compile("^[가-힣]{1,5}$");

    @Column(name = "name")
    private String name;

    protected Name() {
    }

    public Name(String name) {
        validate(name);
        this.name = name;
    }

    /**
     * 한글 이름만 고려
     */
    private void validate(String name) {
        if (name == null || name.length() > 5) {
            throw new IllegalArgumentException("올바른 이름을 입력해주세요.");
        }
        if (!match(name).matches()) {
            throw new IllegalArgumentException("올바른 이름을 입력해주세요.");
        }
    }

    private static Matcher match(String name) {
        return pattern.matcher(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name1 = (Name) o;
        return Objects.equals(name, name1.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
