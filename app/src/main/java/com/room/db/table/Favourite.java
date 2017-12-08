package com.room.db.table;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Query;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * author by Anuj Sharma on 12/6/2017.
 */

@Entity(tableName = "favourite")
public class Favourite implements Parcelable {
    @Dao
    public interface FavouriteDao {

        @Query("SELECT * FROM favourite")
        List<Favourite> getAll();

        @Query("SELECT COUNT(*) FROM favourite")
        int countTotal();

        @Insert
        void insert(Favourite obj);

        @Delete
        void delete(Favourite obj);

        @Query("DELETE FROM favourite WHERE mediaId = :id")
        void delete(String id);

        @Query("SELECT * FROM favourite WHERE media = :mediaUrl LIMIT 1")
        boolean isFavourite(String mediaUrl);
    }


    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "type")
    private String type;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "desc")
    private String desc;
    @ColumnInfo(name = "media")
    private String media;
    @ColumnInfo(name = "media_fullsize")
    private String media_fullsize;
    @ColumnInfo(name = "thumbnail")
    private String thumbnail;
    @ColumnInfo(name = "mediaId")
    private String mediaId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMedia_fullsize() {
        return media_fullsize;
    }

    public void setMedia_fullsize(String media_fullsize) {
        this.media_fullsize = media_fullsize;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mediaId);
        dest.writeString(this.type);
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.media);
        dest.writeString(this.media_fullsize);
        dest.writeString(this.thumbnail);
    }

    public Favourite() {
    }

    protected Favourite(Parcel in) {
        this.mediaId = in.readString();
        this.type = in.readString();
        this.title = in.readString();
        this.desc = in.readString();
        this.media = in.readString();
        this.media_fullsize = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<Favourite> CREATOR = new Parcelable.Creator<Favourite>() {
        @Override
        public Favourite createFromParcel(Parcel source) {
            return new Favourite(source);
        }

        @Override
        public Favourite[] newArray(int size) {
            return new Favourite[size];
        }
    };
}
