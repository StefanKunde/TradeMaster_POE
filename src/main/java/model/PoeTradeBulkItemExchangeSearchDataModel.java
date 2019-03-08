package model;

import lombok.Getter;

public class PoeTradeBulkItemExchangeSearchDataModel {

    @Getter
    private int minimum = 1;
    @Getter
    private String want = "";
    @Getter
    private String have = "";

    private PoeTradeBulkItemExchangeSearchDataModel() {
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

        public PoeTradeBulkItemExchangeSearchDataModel build() {
            PoeTradeBulkItemExchangeSearchDataModel result = new PoeTradeBulkItemExchangeSearchDataModel();
            result.minimum = this.minimum;
            result.have = this.have;
            result.want = this.want;
            return result;
        }
    }


}

