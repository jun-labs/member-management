package project.io.app.core.user.domain;

import javax.persistence.*;
import java.util.*;
import java.util.regex.*;

@Embeddable
public class PhoneNumber {

    private static final Pattern pattern = Pattern.compile("^010\\d{8}$");

    @Column(name = "phone_number")
    private String phoneNumber;

    protected PhoneNumber() {
    }

    public PhoneNumber(String phoneNumber) {
        validate(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    private void validate(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new IllegalArgumentException("올바른 핸드폰 번호를 입력해주세요.");
        }
        if (!match(phoneNumber).matches()) {
            throw new IllegalArgumentException("올바른 핸드폰 번호를 입력해주세요.");
        }
    }

    private static Matcher match(String phoneNumber) {
        return pattern.matcher(phoneNumber);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneNumber);
    }
}
