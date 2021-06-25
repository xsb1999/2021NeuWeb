package managedbeans;

import db_utils.DBUtil_project;
import db_utils.DBUtil_user;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@SessionScoped
public class User {
    private int user_id;
    private String username;
    private String password;
    private String password_confirm;
    private String basic_intro;
    private String interests;
    private String experiences;
    private String skills;
    private String goals;
    private List<Projects> projectsList;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    public String getBasic_intro() {
        return basic_intro;
    }

    public void setBasic_intro(String basic_intro) {
        this.basic_intro = basic_intro;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getExperiences() {
        return experiences;
    }

    public void setExperiences(String experiences) {
        this.experiences = experiences;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }

    // login
    public String login() throws SQLException {

        List<User> userList = DBUtil_user.userLogin(this, DBUtil_user.getConnection());
        // fail to login
        if (userList.size() == 0){
            FacesMessage errorMessage = new FacesMessage("Username or password wrong!");
            errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null,
                    errorMessage);
            return null;
            // login successfully
        }else {
            this.user_id = DBUtil_user.getId(this, DBUtil_user.getConnection());
            // get the information details of users
            User u = DBUtil_user.getUserById(this.user_id, DBUtil_user.getConnection());
            this.setGoals(u.getGoals());
            this.setSkills(u.getSkills());
            this.setInterests(u.getInterests());
            this.setExperiences(u.getExperiences());
            this.setBasic_intro(u.getBasic_intro());
            // find all projects of the user
            this.projectsList = DBUtil_project.getProjectsByUserId(user_id, DBUtil_project.getConnection());

            System.out.println(userList.get(0).getUsername()+" Login Success!");
            return "home1";
        }

    }


    // register
    public String register() throws SQLException {

        String messageText = "";
        if (this.getPassword().equals(this.getPassword_confirm())){
            Connection connection = DBUtil_user.getConnection();
            int success = DBUtil_user.registerUser(this, connection);
            System.out.println(success);
            return "login";
        }else {
            messageText += "The two passwords are not equals, please input again!";
            password = null;
            password_confirm = null;
            FacesMessage errorMessage = new FacesMessage(messageText);
            errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null,
                    errorMessage);
            return null;
        }

    }



    // add or update detail information
    public String add_update_Info() throws SQLException {

        DBUtil_user.add_update_Info(this, DBUtil_user.getConnection());

        // update user info from the database
        User u = DBUtil_user.getUserById(this.user_id, DBUtil_user.getConnection());
        this.setGoals(u.getGoals());
        this.setSkills(u.getSkills());
        this.setInterests(u.getInterests());
        this.setExperiences(u.getExperiences());
        this.setBasic_intro(u.getBasic_intro());

        return "add_update_info_result";
    }


    // update the projects list of the user
    public String updateProjectsList() throws SQLException {

        this.projectsList = DBUtil_project.getProjectsByUserId(user_id, DBUtil_project.getConnection());

        return "projects1";
    }



}
