package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubData;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ClubsDocument {

    @NotNull
    @Valid
    private ClubMeta meta;

    @NotNull
    @Valid
    private List<ClubData> data;

    @NotNull
    @Valid
    private Links links;

    public ClubsDocument() {
        // required by spring
    }

    public ClubsDocument(ClubsDocumentBuilder builder) {
        this.data = builder.data;

        this.meta = new ClubMeta();
        this.meta.totalPages = builder.totalPages;
        this.meta.totalPlayers = builder.totalPlayers;
        this.meta.pageNumber = builder.pageNumber;
        this.meta.pageSize = builder.pageSize;

        this.links = new Links();
        this.links.self = builder.selfLink;
        this.links.first = builder.firstLink;
        this.links.last = builder.lastLink;
        this.links.next = builder.nextLink;
        this.links.previous = builder.previousLink;
    }

    public ClubMeta getMeta() {
        return meta;
    }

    public List<ClubData> getData() {
        return data;
    }

    public Links getLinks() {
        return links;
    }

    @JsonIgnore
    public long getTotalPages() {
        return meta.getTotalPages();
    }

    @JsonIgnore
    public long getTotalPlayers() {
        return meta.getTotalPlayers();
    }

    @JsonIgnore
    public int getPageNumber() {
        return meta.getPageNumber();
    }

    @JsonIgnore
    public int getPageSize() {
        return meta.getPageSize();
    }

    private static class ClubMeta {

        private long totalPages;
        private long totalPlayers;
        private int pageNumber;
        private int pageSize;

        public long getTotalPages() {
            return totalPages;
        }

        public long getTotalPlayers() {
            return totalPlayers;
        }

        public int getPageNumber() { return pageNumber; }

        public int getPageSize() { return pageSize; }

    }

    private static class Links {

        @NotNull
        private String self;

        @NotNull
        private String first;

        @NotNull
        private String last;

        private String next;
        private String previous;

        public String getSelf() {
            return self;
        }

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        public String getNext() {
            return next;
        }

        public String getPrevious() {
            return previous;
        }

    }

    public static class ClubsDocumentBuilder {

        private List<ClubData> data;

        private int totalPages;
        private long totalPlayers;
        private int pageNumber;
        private int pageSize;

        private String selfLink;
        private String firstLink;
        private String lastLink;
        private String nextLink;
        private String previousLink;

        public ClubsDocumentBuilder setData(List<ClubData> data) {
            this.data = data;
            return this;
        }

        public ClubsDocumentBuilder setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public ClubsDocumentBuilder setTotalPlayers(long totalPlayers) {
            this.totalPlayers = totalPlayers;
            return this;
        }

        public ClubsDocumentBuilder setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public ClubsDocumentBuilder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ClubsDocumentBuilder setSelfLink(String selfLink) {
            this.selfLink = selfLink;
            return this;
        }

        public ClubsDocumentBuilder setFirstLink(String firstLink) {
            this.firstLink = firstLink;
            return this;
        }

        public ClubsDocumentBuilder setLastLink(String lastLink) {
            this.lastLink = lastLink;
            return this;
        }

        public ClubsDocumentBuilder setNextLink(String nextLink) {
            this.nextLink = nextLink;
            return this;
        }

        public ClubsDocumentBuilder setPreviousLink(String previousLink) {
            this.previousLink = previousLink;
            return this;
        }

        public ClubsDocument build() {
            return new ClubsDocument(this);
        }

    }

}
