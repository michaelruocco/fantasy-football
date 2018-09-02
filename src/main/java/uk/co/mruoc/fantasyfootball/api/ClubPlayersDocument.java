package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ClubPlayersDocument {

    @NotNull
    @Valid
    private Meta meta;

    @NotNull
    @Valid
    private List<Data> data;

    @NotNull
    @Valid
    private Links links;

    public ClubPlayersDocument() {
        // required by spring
    }

    public ClubPlayersDocument(PlayersDocumentBuilder builder) {
        this.data = builder.data;

        this.meta = new Meta();
        this.meta.totalPages = builder.totalPages;
        this.meta.totalPlayers = builder.totalPlayers;
        this.meta.pageNumber = builder.pageNumber;
        this.meta.pageSize = builder.pageSize;
        this.meta.clubId = builder.clubId;

        this.links = new Links();
        this.links.self = ClubPlayersLinkBuilder.build(builder.clubId, builder.pageNumber, builder.pageSize);
        this.links.first = ClubPlayersLinkBuilder.build(builder.clubId, 0, builder.pageSize);
        this.links.last = ClubPlayersLinkBuilder.build(builder.clubId, builder.getLastPage(), builder.pageSize);

        if (builder.pageNumber < builder.totalPages - 1) {
            this.links.next = ClubPlayersLinkBuilder.build(builder.clubId, builder.pageNumber + 1, builder.pageSize);
        }

        if (builder.pageNumber > 0) {
            this.links.previous = ClubPlayersLinkBuilder.build(builder.clubId, builder.pageNumber - 1, builder.pageSize);
        }
    }

    public Meta getMeta() {
        return meta;
    }

    public List<Data> getData() {
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
        private long clubId;

        public long getTotalPages() {
            return totalPages;
        }

        public long getTotalPlayers() {
            return totalPlayers;
        }

        public int getPageNumber() { return pageNumber; }

        public int getPageSize() { return pageSize; }

        public long getClubId() {
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

    public static class PlayersDocumentBuilder {

        private long clubId;
        private long totalPlayers;
        private int pageNumber;
        private int pageSize;
        private long totalPages;
        private List<Data> data;

        public PlayersDocumentBuilder setClubId(long clubId) {
            this.clubId = clubId;
            return this;
        }

        public PlayersDocumentBuilder setTotalPlayers(long totalPlayers) {
            this.totalPlayers = totalPlayers;
            return this;
        }

        public PlayersDocumentBuilder setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public PlayersDocumentBuilder setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public PlayersDocumentBuilder setData(List<Data> data) {
            this.data = data;
            return this;
        }

        public PlayersDocumentBuilder setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public long getLastPage() {
            if (totalPages < 1) {
                return 0;
            }
            return totalPages - 1;
        }

        public ClubPlayersDocument build() {
            return new ClubPlayersDocument(this);
        }

    }

}
