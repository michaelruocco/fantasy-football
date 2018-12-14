package uk.co.mruoc.fantasyfootball.api;

import java.util.UUID;

public class InternalServerErrorData extends ErrorData {

    public InternalServerErrorData(final String detail) {
        super(new ErrorDataBuilder()
                .setId(UUID.randomUUID())
                .setCode("INTERNAL_SERVER_ERROR")
                .setTitle("Internal server error")
                .setDetail(detail));
    }

}
