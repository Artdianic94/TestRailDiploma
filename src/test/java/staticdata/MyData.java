package staticdata;


import database.DataBaseUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MyData {
    private String email = "email";
    private String password = "password";

    public Map getMyData() throws SQLException {
        DataBaseUtils dataBaseUtils = new DataBaseUtils();
        dataBaseUtils.connect();
        ResultSet selectResult = dataBaseUtils.selectFrom("Data");
        Map myData = new HashMap<>();
        myData.put(email, dataBaseUtils.getDataFromDB(selectResult).getEmail());
        myData.put(password, dataBaseUtils.getDataFromDB(selectResult).getPassword());
        dataBaseUtils.close();
        return myData;
    }

    public String getMyNicknameFromDB() throws SQLException {
        DataBaseUtils dataBaseUtils = new DataBaseUtils();
        dataBaseUtils.connect();
        ResultSet selectResult = dataBaseUtils.selectFrom("Data");
        String myNickname = dataBaseUtils.getDataFromDB(selectResult).getMyNickname();
        dataBaseUtils.close();
        return myNickname;
    }
}

