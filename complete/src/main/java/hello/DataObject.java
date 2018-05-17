package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public class DataObject {

        private Long id;
        private String name;
        private String symbol;
        private String website_slug;
        private Long rank;
        private Float circulating_supply;
        private Float total_supply;
        private Float max_supply;
        private CMCQuotes quotes;
        private String last_updated;

        public DataObject() {
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public String getWebsite_slug() {
            return website_slug;
        }

        public void setWebsite_slug(String website_slug) {
            this.website_slug = website_slug;
        }

        public Long getRank() {
            return rank;
        }

        public void setRank(Long rank) {
            this.rank = rank;
        }

        public float getCirculating_supply() {
            return circulating_supply;
        }

        public void setCirculating_supply(float circulating_supply) {
            this.circulating_supply = circulating_supply;
        }

        public float getTotal_supply() {
            return total_supply;
        }

        public void setTotal_supply(float total_supply) {
            this.total_supply = total_supply;
        }

        public float getMax_supply() {
            return max_supply;
        }

        public void setMax_supply(float max_supply) {
            this.max_supply = max_supply;
        }

        public CMCQuotes getQuotes() {
            return quotes;
        }

        public void setQuotes(CMCQuotes quotes) {
            this.quotes = quotes;
        }

        public String getLast_updated() {
            return last_updated;
        }

        public void setLast_updated(String last_updated) {
            this.last_updated = last_updated;
        }

        @Override
        public String toString() {
            return  "id=" + id +
                    ", name='" + name + '\'' +
                    ", symbol='" + symbol + '\'' +
                    ", website_slug='" + website_slug + '\'' +
                    ", rank=" + rank +
                    ", circulating_supply=" + circulating_supply +
                    ", total_supply=" + total_supply +
                    ", max_supply=" + max_supply +
                    ", " + quotes +
                    ", last_updated=" + last_updated ;
        }

}
