package uk.co.mruoc.fantasyfootball.api;

import java.util.Map;
import java.util.UUID;

public class ErrorData {

    private UUID id;
    private String code;
    private String title;
    private String detail;
    private Map<String, Object> meta;

    protected ErrorData(final ErrorDataBuilder builder) {
        this.id = builder.id;
        this.code = builder.code;
        this.title = builder.title;
        this.detail = builder.detail;
        this.meta = builder.meta;
    }

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public Map<String, Object> getMeta() {
        return meta;
    }

    public static class ErrorDataBuilder {

        private UUID id = UUID.randomUUID();
        private String code;
        private String title;
        private String detail;
        private Map<String, Object> meta;

        public ErrorDataBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public ErrorDataBuilder setCode(String code) {
            this.code = code;
            return this;
        }

        public ErrorDataBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public ErrorDataBuilder setDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public ErrorDataBuilder setMeta(Map<String, Object> meta) {
            this.meta = meta;
            return this;
        }

        public ErrorData build() {
            return new ErrorData(this);
        }

    }

}