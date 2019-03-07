package handler;

import lombok.Getter;

public class PoeTradeBulkItemExchangeSearchData {

    @Getter
    private int minimum = 1;
    @Getter
    private String want;
    @Getter
    private String have;

    private PoeTradeBulkItemExchangeSearchData() {
    }

    public static class Builder {
        private int minimum = 1;
        private String want;
        private String have;

        public Builder addMinimum(int minimum) {
            this.minimum = minimum;
            return this;
        }

        public Builder addWant(String want) {
            this.want = want;
            return this;
        }

        public Builder addHave(String have) {
            if ("Any".equalsIgnoreCase(have)) {
                have = "";
            }
            this.have = have.toLowerCase();
            return this;
        }

        public PoeTradeBulkItemExchangeSearchData build() {
            PoeTradeBulkItemExchangeSearchData result = new PoeTradeBulkItemExchangeSearchData();
            result.minimum = this.minimum;
            result.have = this.have;
            result.want = this.want;
            return result;
        }
    }


}

