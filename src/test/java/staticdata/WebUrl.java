package staticdata;


import java.sql.SQLException;


public class WebUrl {
    static MyData myData = new MyData();
    public static String TESTRAIL = "https://%s.testrail.io";
    public static final String TRLOGIN = "/index.php?/auth/login";
    public static final String TRGETPROJECTS = "/index.php?/api/v2/get_projects";
    public static final String TRADDPROJECT = "/index.php?/api/v2/add_project";
    public static final String TRDELETEPROJECT = "/index.php?/api/v2/delete_project/{project_id}";
    public static final String TRDASHBOARD = "/index.php?/dashboard";


    public String getTRNickname() throws SQLException {
        String TestRailBaseURL = String.format(TESTRAIL, myData.getMyNicknameFromDB());
        return TestRailBaseURL;
    }

    public String getTRLoginUrl() throws SQLException {
        String TRLoginURL = getTRNickname().concat(TRLOGIN);
        return TRLoginURL;
    }

    public String getTRGetProjectsUrl() throws SQLException {
        String TRGetProjectsURL = getTRNickname().concat(TRGETPROJECTS);
        return TRGetProjectsURL;
    }

    public String getTRAddProjectUrl() throws SQLException {
        String TRAddProjectURL = getTRNickname().concat(TRADDPROJECT);
        return TRAddProjectURL;
    }

    public String getTRDeleteProjectUrl() throws SQLException {
        String TRDeleteProjectURL = getTRNickname().concat(TRDELETEPROJECT);
        return TRDeleteProjectURL;
    }

    public String getTRDashboardUrl() throws SQLException {
        String TRDeleteProjectURL = getTRNickname().concat(TRDASHBOARD);
        return TRDeleteProjectURL;
    }
}
