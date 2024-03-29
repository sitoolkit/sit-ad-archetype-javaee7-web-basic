package org.sitoolkit.ad.archetype.basic.domain.user;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.sitoolkit.ad.archetype.basic.infra.layer.BaseDao;
import org.sitoolkit.ad.archetype.basic.infra.layer.BaseService;

/**
 * このクラスは、ユーザーマスターのサービスです。
 * 
 * @author
 **/
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class UserService extends BaseService<UserEntity, String> {

    @Inject
    UserDao dao;

    @Override
    public BaseDao<UserEntity, String> getDao() {
        return dao;
    }

}
