package org.fibi.usos.service.user;

import org.fibi.usos.model.user.UserModel;

public interface UserService {
    UserModel createOrUpdate(UserModel user);
}
