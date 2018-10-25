package net.unesc.sextou.login;

import android.content.Context;

import net.unesc.sextou.database.SqlAbstractDao;

public class UserDao extends SqlAbstractDao<User> {
    public UserDao(Context context) {
        super(context);
    }

    @Override
    protected Class<User> getClazz() {
        return User.class;
    }
}
