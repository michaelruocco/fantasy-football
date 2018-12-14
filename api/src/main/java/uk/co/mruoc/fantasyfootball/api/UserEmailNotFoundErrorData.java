package uk.co.mruoc.fantasyfootball.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UserEmailNotFoundErrorData extends ErrorData {

    public UserEmailNotFoundErrorData(final String email, final String detail) {
        super(new ErrorDataBuilder()
                .setCode("USER_NOT_FOUND")
                .setTitle("User not found")
                .setDetail(detail)
                .setMeta(toMeta(email)));
    }

    private static Map<String, Object> toMeta(final String email) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("email", email);
        return Collections.unmodifiableMap(meta);
    }

}
