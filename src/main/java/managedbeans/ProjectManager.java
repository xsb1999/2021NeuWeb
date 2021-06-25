package managedbeans;

import db_utils.DBUtil_project;

import javax.faces.bean.ManagedBean;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
public class ProjectManager {

    private List<Projects> projectList ;

    public ProjectManager() throws SQLException {
        this.projectList = DBUtil_project.getAllProjects(DBUtil_project.getConnection());
    }

    public List<Projects> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Projects> projectList) {
        this.projectList = projectList;
    }
}
