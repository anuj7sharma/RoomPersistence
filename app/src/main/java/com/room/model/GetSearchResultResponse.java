package com.room.model;

import com.google.gson.annotations.SerializedName;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class GetSearchResultResponse {

    /**
     * status : success
     * data : {"query":{"locale":"fr_fr","query":"nature","offset":2},"cache":{"key":"de6c5291a95270d564d8056fdd912c6b","created":1512366348,"expiration":86400,"status":"miss","age":0}}
     */

    @SerializedName("status")
    private String status;
    @SerializedName("data")
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * query : {"locale":"fr_fr","query":"nature","offset":2}
         * cache : {"key":"de6c5291a95270d564d8056fdd912c6b","created":1512366348,"expiration":86400,"status":"miss","age":0}
         */

        @SerializedName("query")
        private QueryBean query;
        @SerializedName("cache")
        private CacheBean cache;

        @SerializedName("result")
        private ImagesResultResponse result;

        public ImagesResultResponse getResult() {
            return result;
        }

        public void setResult(ImagesResultResponse result) {
            this.result = result;
        }

        public QueryBean getQuery() {
            return query;
        }

        public void setQuery(QueryBean query) {
            this.query = query;
        }

        public CacheBean getCache() {
            return cache;
        }

        public void setCache(CacheBean cache) {
            this.cache = cache;
        }

        public static class QueryBean {
            /**
             * locale : fr_fr
             * query : nature
             * offset : 2
             */

            @SerializedName("locale")
            private String locale;
            @SerializedName("query")
            private String query;
            @SerializedName("offset")
            private int offset;

            public String getLocale() {
                return locale;
            }

            public void setLocale(String locale) {
                this.locale = locale;
            }

            public String getQuery() {
                return query;
            }

            public void setQuery(String query) {
                this.query = query;
            }

            public int getOffset() {
                return offset;
            }

            public void setOffset(int offset) {
                this.offset = offset;
            }
        }

        public static class CacheBean {
            /**
             * key : de6c5291a95270d564d8056fdd912c6b
             * created : 1512366348
             * expiration : 86400
             * status : miss
             * age : 0
             */

            @SerializedName("key")
            private String key;
            @SerializedName("created")
            private int created;
            @SerializedName("expiration")
            private int expiration;
            @SerializedName("status")
            private String status;
            @SerializedName("age")
            private int age;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public int getCreated() {
                return created;
            }

            public void setCreated(int created) {
                this.created = created;
            }

            public int getExpiration() {
                return expiration;
            }

            public void setExpiration(int expiration) {
                this.expiration = expiration;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }
    }
}
