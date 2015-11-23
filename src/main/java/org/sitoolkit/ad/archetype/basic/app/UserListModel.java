package org.sitoolkit.ad.archetype.basic.app;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

import org.sitoolkit.ad.archetype.basic.domain.user.UserEntity;
import org.sitoolkit.ad.archetype.basic.domain.user.UserService;
import org.sitoolkit.ad.archetype.basic.infra.layer.BaseListModel;

@ManagedBean
@RequestScoped
public class UserListModel extends BaseListModel<UserEntity, String, UserService> {

    @Inject
    UserService service;

    @Override
    protected UserService getService() {
        return service;
    }
}
