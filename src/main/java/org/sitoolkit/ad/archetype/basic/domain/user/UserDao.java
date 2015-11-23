package org.sitoolkit.ad.archetype.basic.domain.user;

import org.sitoolkit.ad.archetype.basic.infra.layer.BaseDao;
import org.sitoolkit.ad.archetype.basic.infra.layer.Dao;

/**
 * このクラスは、ユーザーマスターのDAOです。
 * 
 * @author
 */
@Dao
public class UserDao extends BaseDao<UserEntity, String> {

    @Override
    public Class<UserEntity> getEntityType() {
        return UserEntity.class;
    }

}
