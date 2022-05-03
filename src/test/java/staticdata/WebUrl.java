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
    public static final String TRCREATETESTCASE = "/index.php?/api/v2/add_case/{section_id}";
    public static final String TRCREATETESTRUN = "/index.php?/api/v2/add_run/{project_id}";
    public static final String TRCREATEMILESTONE ="/index.php?/api/v2/add_milestone/{project_id}";
    public static final String TRCREATESECTION = "/index.php?/api/v2/add_section/{project_id}";
    public static final String TRUPDATEPROJECT ="/index.php?/api/v2/update_project/{project_id}";
    public static final String TRUPDATETESTCASE="/index.php?/api/v2/update_case/{case_id}";
    public static final String TRUPDATETESTRUN ="/index.php?/api/v2/update_run/{run_id}";
    public static final String TRUPDATEMILESTONE="/index.php?/api/v2/update_milestone/{milestone_id}";
    public static final String TRADDSUITE = "/index.php?/api/v2/add_suite/{project_id}";
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
        String TRDashboardURL = getTRNickname().concat(TRDASHBOARD);
        return TRDashboardURL;
    }
    public String getTRUpdateProjectUrl() throws SQLException {
        String TRUpdateProjectURL = getTRNickname().concat(TRUPDATEPROJECT);
        return TRUpdateProjectURL;
    }
    public String getTRUpdateTestCaseUrl() throws SQLException {
        String TRUpdateTestCaseURL = getTRNickname().concat(TRUPDATETESTCASE);
        return TRUpdateTestCaseURL;
    }
    public String getTRUpdateTestRunUrl() throws SQLException {
        String TRUpdateTestRunURL = getTRNickname().concat(TRUPDATETESTRUN);
        return TRUpdateTestRunURL;
    }
    public String getTRUpdateMilestoneUrl() throws SQLException {
        String TRUpdateMilestoneURL = getTRNickname().concat(TRUPDATEMILESTONE);
        return TRUpdateMilestoneURL;
    }
    public String getTRCreateTestCaseUrl() throws SQLException {
        String TRCreateTestCaseURL = getTRNickname().concat(TRCREATETESTCASE);
        return TRCreateTestCaseURL;
    }
    public String getTRCreateSectionUrl() throws SQLException {
        String TRCreateSectionURL = getTRNickname().concat(TRCREATESECTION);
        return TRCreateSectionURL;
    }
    public String getTRAddSuiteUrl() throws SQLException {
        String TRAddSuiteURL = getTRNickname().concat(TRADDSUITE);
        return TRAddSuiteURL;
    }
    public String getTRCreateTestRunUrl() throws SQLException {
        String TRCreateTestRunURL = getTRNickname().concat(TRCREATETESTRUN);
        return TRCreateTestRunURL;
    }
    public String getTRCreateMilestoneUrl() throws SQLException {
        String TRCreateMilestoneURL = getTRNickname().concat(TRCREATEMILESTONE);
        return TRCreateMilestoneURL;
    }
}
