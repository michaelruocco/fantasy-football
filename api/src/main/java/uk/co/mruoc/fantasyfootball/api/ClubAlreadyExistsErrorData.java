package uk.co.mruoc.fantasyfootball.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ClubAlreadyExistsErrorData extends ErrorData {

    public ClubAlreadyExistsErrorData(final long id, final String detail) {
        super(new ErrorDataBuilder()
                .setCode("CLUB_ALREADY_EXISTS")
                .setTitle("Club already exists")
                .setDetail(detail)
                .setMeta(toMeta(id)));
    }

    private static Map<String, Object> toMeta(final long id) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("id", id);
        return Collections.unmodifiableMap(meta);
    }

}
