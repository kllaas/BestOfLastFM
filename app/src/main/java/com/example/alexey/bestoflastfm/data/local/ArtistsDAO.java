package com.example.alexey.bestoflastfm.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.alexey.bestoflastfm.data.entity.local.Artist;

import java.util.List;

/**
 * Created by alexey
 */

@Dao
public interface ArtistsDAO {

    @Query("SELECT * FROM artists WHERE country = :country")
    List<Artist> getByCountry(String country);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Artist> items);

}
