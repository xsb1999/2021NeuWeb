package managedbeans;

import db_utils.DBUtil_project;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;

@ManagedBean
public class Projects {

    private int id;
    private String title;
    private String summary;
    private String description;
    private String type;
    private String language;
    private Double cost;
    private Date startDate;
    private Date finishDate;
    private int user_id;

    @ManagedProperty("#{user}")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public Projects(int id, String title, String summary, String description, String type, String language, Double cost, Date startDate, Date finishDate) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.type = type;
        this.language = language;
        this.cost = cost;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public Projects() {
    }


    @Override
    public String toString() {
        return "Projectjdbc{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", language='" + language + '\'' +
                ", cost=" + cost +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }





// functions related to database

    public String addProjects() throws SQLException, ParseException {

        String messageText = "";
        Date today = new Date();
        if (startDate.after(today)) {
            messageText += "The start date can not be in the future";
        }
        if (finishDate.after(today)) {
            messageText += ",    "+"the finish date can not be in the future";
        }
        if (!startDate.before(finishDate)) {
            messageText += ",    "+"start date must be before finish date";
        }
        if (messageText != "") {
            startDate = null;
            finishDate = null;
            FacesMessage errorMessage = new FacesMessage(messageText);
            errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null,
                    errorMessage);
            return null;
        } else {

            this.user_id = user.getUser_id();
            Connection con = DBUtil_project.getConnection();

            int success = DBUtil_project.addProject(this, con);

            if (success == 1){
                System.out.println("success!");
            }else {
                System.out.println("fail!");
            }
            con.close();
            return "add_result";

        }

    }


    public String updateProjects() throws SQLException, ParseException {

        String messageText = "";
        Date today = new Date();
        if (startDate.after(today)) {
            messageText += "The start date can not be in the future";
        }
        if (finishDate.after(today)) {
            messageText += ",    "+"the finish date can not be in the future";
        }
        if (!startDate.before(finishDate)) {
            messageText += ",    "+"start date must be before finish date";
        }
        if (messageText != "") {
            startDate = null;
            finishDate = null;
            FacesMessage errorMessage = new FacesMessage(messageText);
            errorMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            FacesContext.getCurrentInstance().addMessage(null,
                    errorMessage);
            return null;
        } else {

            Connection con = DBUtil_project.getConnection();

            int success = DBUtil_project.UpdateProject(this, con);

            if (success == 1){
                System.out.println("success!");
            }else {
                System.out.println("fail!");
            }
            con.close();
            return ("edit_result");

        }

    }

}
