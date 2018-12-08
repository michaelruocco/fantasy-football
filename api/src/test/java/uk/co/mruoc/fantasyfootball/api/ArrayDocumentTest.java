package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayDocumentTest {

    private static final String LINK = "/testData?pageNumber=1&pageSize=5";

    @Test
    public void shouldReturnSelfLink() {
        final ArrayDocument<TestData> document = new TestDataDocumentBuilder()
                .setSelfLink(LINK)
                .build();

        assertThat(document.getSelfLink()).isEqualTo(LINK);
    }

    @Test
    public void shouldReturnFirstLink() {
        final ArrayDocument<TestData> document = new TestDataDocumentBuilder()
                .setFirstLink(LINK)
                .build();

        assertThat(document.getFirstLink()).isEqualTo(LINK);
    }

    @Test
    public void shouldReturnLastLink() {
        final ArrayDocument<TestData> document = new TestDataDocumentBuilder()
                .setLastLink(LINK)
                .build();

        assertThat(document.getLastLink()).isEqualTo(LINK);
    }

    @Test
    public void shouldReturnNextLink() {
        final ArrayDocument<TestData> document = new TestDataDocumentBuilder()
                .setNextLink(LINK)
                .build();

        assertThat(document.getNextLink()).isEqualTo(LINK);
    }

    @Test
    public void shouldReturnPreviousLink() {
        final ArrayDocument<TestData> document = new TestDataDocumentBuilder()
                .setPreviousLink(LINK)
                .build();

        assertThat(document.getPreviousLink()).isEqualTo(LINK);
    }

    private static class TestData {

        // intentionally blank

    }

    private static class TestDataDocument extends ArrayDocument<TestData> {

        public TestDataDocument() {
            // required by jackson
        }

        public TestDataDocument(TestDataDocumentBuilder builder) {
            super(builder);
        }

    }

    private static class TestDataDocumentBuilder extends ArrayDocument.ArrayDocumentBuilder<TestData> {

        public ArrayDocument<TestData> build() {
            return new TestDataDocument(this);
        }

    }

}
