package com.customerservice.login.Utility;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "SmartSociety";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_CONTACT = "contacct";
    private static final String KEY_TYPE = "type";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setLogin(boolean isLoggedIn)
    {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
    }
    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }


    //id

    public void setId(String id)
    {
        editor.putString(KEY_ID,id);
        editor.commit();
    }
    public String getId()
    {
        return pref.getString(KEY_ID,"0");
    }

    //name
    public void setName(String name)
    {
        editor.putString(KEY_NAME,name);
        editor.commit();
    }
    public String getName()
    {
        return pref.getString(KEY_NAME,"");
    }
    //contact
    public void setContact(String contact)
    {
        editor.putString(KEY_CONTACT,contact);
        editor.commit();
    }
    public String getContact()
    {
        return pref.getString(KEY_CONTACT,"");
    }
    //type
    public void setType(String type)
    {
        editor.putString(KEY_TYPE,type);
        editor.commit();
    }
    public String getType()
    {
        return pref.getString(KEY_TYPE,"");
    }

}
