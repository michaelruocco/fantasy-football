package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public abstract class ArrayDocument<T> {

    @NotNull
    @Valid
    private Meta meta;

    @NotNull
    @Valid
    private List<T> data;

    @NotNull
    @Valid
    private Links links;

    public ArrayDocument() {
        // required by spring
    }

    public ArrayDocument(ArrayDocumentBuilder<T> builder) {
        this.data = builder.data;

        this.meta = new Meta();
        this.meta.totalPages = builder.totalPages;
        this.meta.totalItems = builder.totalItems;
        this.meta.pageNumber = builder.pageNumber;
        this.meta.pageSize = builder.pageSize;

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

    public List<T> getData() {
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
    public long getTotalItems() {
        return meta.getTotalItems();
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
    public String getSelfLink() {
        return links.getSelf();
    }

    @JsonIgnore
    public String getFirstLink() {
        return links.getFirst();
    }

    @JsonIgnore
    public String getLastLink() {
        return links.getLast();
    }

    @JsonIgnore
    public String getNextLink() {
        return links.getNext();
    }

    @JsonIgnore
    public String getPreviousLink() {
        return links.getPrevious();
    }

    private static class Meta {

        private long totalPages;
        private long totalItems;
        private int pageNumber;
        private int pageSize;

        public long getTotalPages() {
            return totalPages;
        }

        public long getTotalItems() {
            return totalItems;
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

    public abstract static class ArrayDocumentBuilder<T> {

        private List<T> data;

        private int totalPages;
        private long totalItems;
        private int pageNumber;
        private int pageSize;

        private String selfLink;
        private String firstLink;
        private String lastLink;
        private String nextLink;
        private String previousLink;

        public ArrayDocumentBuilder<T> setData(List<T> data) {
            this.data = data;
            return this;
        }

        public ArrayDocumentBuilder<T> setTotalPages(int totalPages) {
            this.totalPages = totalPages;
            return this;
        }

        public ArrayDocumentBuilder<T> setTotalItems(long totalItems) {
            this.totalItems = totalItems;
            return this;
        }

        public ArrayDocumentBuilder<T> setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
            return this;
        }

        public ArrayDocumentBuilder<T> setPageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public ArrayDocumentBuilder<T> setSelfLink(String selfLink) {
            this.selfLink = selfLink;
            return this;
        }

        public ArrayDocumentBuilder<T> setFirstLink(String firstLink) {
            this.firstLink = firstLink;
            return this;
        }

        public ArrayDocumentBuilder<T> setLastLink(String lastLink) {
            this.lastLink = lastLink;
            return this;
        }

        public ArrayDocumentBuilder<T> setNextLink(String nextLink) {
            this.nextLink = nextLink;
            return this;
        }

        public ArrayDocumentBuilder<T> setPreviousLink(String previousLink) {
            this.previousLink = previousLink;
            return this;
        }

        public abstract ArrayDocument<T> build();

    }

}
