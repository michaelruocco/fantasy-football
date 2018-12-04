package uk.co.mruoc.fantasyfootball.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PlayerNotFoundErrorData extends ErrorData {

    public PlayerNotFoundErrorData(final long id, final String detail) {
        super(new ErrorData.ErrorDataBuilder()
                .setCode("PLAYER_NOT_FOUND")
                .setTitle("Player not found")
                .setDetail(detail)
                .setMeta(toMeta(id)));
    }

    private static Map<String, Object> toMeta(final long id) {
        Map<String, Object> meta = new HashMap<>();
        meta.put("id", id);
        return Collections.unmodifiableMap(meta);
    }

}
