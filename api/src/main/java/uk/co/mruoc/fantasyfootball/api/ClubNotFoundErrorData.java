package uk.co.mruoc.fantasyfootball.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ClubNotFoundErrorData extends ErrorData {

    public ClubNotFoundErrorData(final long id, final String detail) {
        super(new ErrorDataBuilder()
                .setCode("CLUB_NOT_FOUND")
                .setTitle("Club not found")
                .setDetail(detail)
                .setMeta(toMeta(id)));
    }

    private static Map<String, Object> toMeta(final long id) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("id", id);
        return Collections.unmodifiableMap(meta);
    }

}
