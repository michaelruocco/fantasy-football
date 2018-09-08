package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class PlayersDocument {

    @NotNull
    @Valid
    private Meta meta;

    @NotNull
    @Valid
    private List<PlayerData> data;

    @NotNull
    @Valid
    private Links links;

    public PlayersDocument() {
        // required by spring
    }

    public PlayersDocument(ClubPlayersDocumentBuilder builder) {
        this.data = builder.data;

        this.meta = new Meta();
        this.meta.totalPages = builder.totalPages;
        this.meta.totalPlayers = builder.totalPlayers;
        this.meta.pageNumber = builder.pageNumber;
        this.meta.pageSize = builder.pageSize;
        this.meta.clubId = builder.clubId;

        this.links = new Links();
        this.links.self = builder.selfLink;
        this.links.first = builder.firstLink;
        this.links.last = builder.lastLink;
        this.links.next = builder.nextLink;
        this.links.previous = builder.previousLink;
    }

    public Meta getMeta() {
        return meta;
    }

    public List<PlayerData> getData() {
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

    @JsonIgnore
    public long getClubId() {
        return meta.getClubId();
    }

    private static class Meta {

        private long totalPages;
        private long totalPlayers;
        private int pageNumber;
        private int pageSize;
        private Long clubId;

        public long getTotalPages() {
            return totalPages;
        }

        public long getTotalPlayers() {
            return totalPlayers;
        }

        public int getPageNumber() { return pageNumber; }

        public int getPageSize() { return pageSize; }

        public Long getClubId() {
            return clubId;
        }

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

    public static class ClubPlayersDocumentBuilder {

        private Long clubId;
        private List<PlayerData> data;

        private int totalPages;
        private long totalPlayers;
        private int pageNumber;
        private int pageSize;

        private String selfLink;
        private String firstLink;
        private String lastLink;
        private String nextLink;
        private String previousLink;

        public ClubPlayersDocumentBuilder setClubId(Long clubId) {
            this.clubId = clubId;
            return this;
        }

        public ClubPlayersDocumentBuilder setData(List<PlayerData> data) {
            this.data = data;
            return this;
        }

        public ClubPlayersDocumentBuilder setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public ClubPlayersDocumentBuilder setTotalPlayers(long totalPlayers) {
            this.totalPlayers = totalPlayers;
            return this;
        }

        public ClubPlayersDocumentBuilder setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public ClubPlayersDocumentBuilder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ClubPlayersDocumentBuilder setSelfLink(String selfLink) {
            this.selfLink = selfLink;
            return this;
        }

        public ClubPlayersDocumentBuilder setFirstLink(String firstLink) {
            this.firstLink = firstLink;
            return this;
        }

        public ClubPlayersDocumentBuilder setLastLink(String lastLink) {
            this.lastLink = lastLink;
            return this;
        }

        public ClubPlayersDocumentBuilder setNextLink(String nextLink) {
            this.nextLink = nextLink;
            return this;
        }

        public ClubPlayersDocumentBuilder setPreviousLink(String previousLink) {
            this.previousLink = previousLink;
            return this;
        }

        public PlayersDocument build() {
            return new PlayersDocument(this);
        }

    }

}
