package managedbeans;

import utils.DateUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Date;

@ManagedBean
public class Project {
    private String title;
    private String summary;
    private String description;
    private String type;
    private String language;
    private Double cost;
    private Date startDate;
    private Date finishDate;

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
        if (startDate == null) {
            startDate = new Date();
        }
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        if (finishDate == null) {
            finishDate = DateUtils.nextDay(getStartDate());
        }
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }



    public String update(){
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
            return("edit_result");
        }
    }


}
