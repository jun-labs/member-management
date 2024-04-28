package project.io.app.core.user.domain;

import javax.persistence.*;
import java.security.*;
import java.util.*;

@Embeddable
public class Password {

    private static final MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Column(name = "password")
    private String password;

    protected Password() {
    }

    public Password(String password) {
        this.password = hashPassword(password);
    }

    private String hashPassword(String password) {
        digest.reset();
        byte[] encodedHash = digest.digest(password.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte bt : encodedHash) {
            String hex = Integer.toHexString(0xff & bt);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password);
    }
}
