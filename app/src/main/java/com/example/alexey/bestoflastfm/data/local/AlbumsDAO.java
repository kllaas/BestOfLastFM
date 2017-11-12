package com.example.alexey.bestoflastfm.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.alexey.bestoflastfm.data.entity.local.Album;

import java.util.List;

/**
 * Created by alexey
 */

@Dao
public interface AlbumsDAO {

    @Query("SELECT * FROM albums WHERE artistName = :artistName")
    List<Album> getByArtistName(String artistName);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Album> items);

}
