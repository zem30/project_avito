package com.amr.project.dao.abstracts;

import com.amr.project.model.entity.Advert;
import com.amr.project.model.entity.Item;

import java.util.List;


public interface AdvertDao extends ReadWriteDao<Advert, Long>{

    Advert getAdvertName(String nameAdvert);

    List<Advert> getUnmoderatedAdverts();

    List<Advert> getModeratedAdverts();


}
