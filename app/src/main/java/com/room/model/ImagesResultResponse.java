package com.room.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * author by Anuj Sharma on 12/4/2017.
 */

public class ImagesResultResponse {

    /**
     * result : {"items":[{"title":"Gripparty Pannoniaring - 1000PS Grid Girls Motorrad Fotos ...","type":"image","media":"https://www.motorrad-bilder.at/slideshows/291/012918/gripparty-pannonia-2015-gridgirls-89.jpg","desc":"","thumbnail":"//s1.qwant.com/thumbr/299x200/b/2/18ec7bcced724083daa3f88bcf13d1/b_1_q_0_p_0.jpg?u=https%3A%2F%2Fwww.motorrad-bilder.at%2Fslideshows%2F291%2F012918%2Fgripparty-pannonia-2015-gridgirls-89.jpg&q=0&b=1&p=0&a=0&b_id=OIP.tlkT25bPNwzMc8yQhoT_-AEsDI","thumb_width":299,"thumb_height":200,"width":"2048","height":"1366","size":"180450","url":"http://www.1000ps.at/motorrad-bildergalerie-gripparty-pannoniaring-1000ps-grid-girls-12918","_id":"a26f33e7045f702a5799f16aea094747","b_id":"OIP.tlkT25bPNwzMc8yQhoT_-AEsDI","media_fullsize":"//s1.qwant.com/thumbr/0x0/0/4/88f848bf929777070c84a528b28a15/b_1_q_0_p_0.jpg?u=https%3A%2F%2Fwww.motorrad-bilder.at%2Fslideshows%2F291%2F012918%2Fgripparty-pannonia-2015-gridgirls-89.jpg&q=0&b=1&p=0&a=1","thumb_type":"jpg","count":1}]}
     */

    @SerializedName("items")
    private List<ItemsBean> items;

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean implements Parcelable {
        /**
         * title : Gripparty Pannoniaring - 1000PS Grid Girls Motorrad Fotos ...
         * type : image
         * media : https://www.motorrad-bilder.at/slideshows/291/012918/gripparty-pannonia-2015-gridgirls-89.jpg
         * desc :
         * thumbnail : //s1.qwant.com/thumbr/299x200/b/2/18ec7bcced724083daa3f88bcf13d1/b_1_q_0_p_0.jpg?u=https%3A%2F%2Fwww.motorrad-bilder.at%2Fslideshows%2F291%2F012918%2Fgripparty-pannonia-2015-gridgirls-89.jpg&q=0&b=1&p=0&a=0&b_id=OIP.tlkT25bPNwzMc8yQhoT_-AEsDI
         * thumb_width : 299
         * thumb_height : 200
         * width : 2048
         * height : 1366
         * size : 180450
         * url : http://www.1000ps.at/motorrad-bildergalerie-gripparty-pannoniaring-1000ps-grid-girls-12918
         * _id : a26f33e7045f702a5799f16aea094747
         * b_id : OIP.tlkT25bPNwzMc8yQhoT_-AEsDI
         * media_fullsize : //s1.qwant.com/thumbr/0x0/0/4/88f848bf929777070c84a528b28a15/b_1_q_0_p_0.jpg?u=https%3A%2F%2Fwww.motorrad-bilder.at%2Fslideshows%2F291%2F012918%2Fgripparty-pannonia-2015-gridgirls-89.jpg&q=0&b=1&p=0&a=1
         * thumb_type : jpg
         * count : 1
         */

        @SerializedName("title")
        private String title;
        @SerializedName("type")
        private String type;
        @SerializedName("media")
        private String media;
        @SerializedName("desc")
        private String desc;
        @SerializedName("thumbnail")
        private String thumbnail;
        @SerializedName("thumb_width")
        private int thumbWidth;
        @SerializedName("thumb_height")
        private int thumbHeight;
        @SerializedName("width")
        private String width;
        @SerializedName("height")
        private String height;
        @SerializedName("size")
        private String size;
        @SerializedName("url")
        private String url;
        @SerializedName("_id")
        private String id;
        @SerializedName("b_id")
        private String bId;
        @SerializedName("media_fullsize")
        private String mediaFullsize;
        @SerializedName("thumb_type")
        private String thumbType;
        @SerializedName("count")
        private int count;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMedia() {
            return media;
        }

        public void setMedia(String media) {
            this.media = media;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public int getThumbWidth() {
            return thumbWidth;
        }

        public void setThumbWidth(int thumbWidth) {
            this.thumbWidth = thumbWidth;
        }

        public int getThumbHeight() {
            return thumbHeight;
        }

        public void setThumbHeight(int thumbHeight) {
            this.thumbHeight = thumbHeight;
        }

        public String getWidth() {
            return width;
        }

        public void setWidth(String width) {
            this.width = width;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBId() {
            return bId;
        }

        public void setBId(String bId) {
            this.bId = bId;
        }

        public String getMediaFullsize() {
            return mediaFullsize;
        }

        public void setMediaFullsize(String mediaFullsize) {
            this.mediaFullsize = mediaFullsize;
        }

        public String getThumbType() {
            return thumbType;
        }

        public void setThumbType(String thumbType) {
            this.thumbType = thumbType;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeString(this.media);
            dest.writeString(this.desc);
            dest.writeString(this.thumbnail);
            dest.writeInt(this.thumbWidth);
            dest.writeInt(this.thumbHeight);
            dest.writeString(this.width);
            dest.writeString(this.height);
            dest.writeString(this.size);
            dest.writeString(this.url);
            dest.writeString(this.id);
            dest.writeString(this.bId);
            dest.writeString(this.mediaFullsize);
            dest.writeString(this.thumbType);
            dest.writeInt(this.count);
        }

        public ItemsBean() {
        }

        protected ItemsBean(Parcel in) {
            this.title = in.readString();
            this.type = in.readString();
            this.media = in.readString();
            this.desc = in.readString();
            this.thumbnail = in.readString();
            this.thumbWidth = in.readInt();
            this.thumbHeight = in.readInt();
            this.width = in.readString();
            this.height = in.readString();
            this.size = in.readString();
            this.url = in.readString();
            this.id = in.readString();
            this.bId = in.readString();
            this.mediaFullsize = in.readString();
            this.thumbType = in.readString();
            this.count = in.readInt();
        }

        public static final Parcelable.Creator<ItemsBean> CREATOR = new Parcelable.Creator<ItemsBean>() {
            @Override
            public ItemsBean createFromParcel(Parcel source) {
                return new ItemsBean(source);
            }

            @Override
            public ItemsBean[] newArray(int size) {
                return new ItemsBean[size];
            }
        };
    }

}
