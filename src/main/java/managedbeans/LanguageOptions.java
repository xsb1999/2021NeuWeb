package managedbeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class LanguageOptions {
    private String[] language =
            {"Java","C","C++","Python","Javascript","Vue","php","C#","Pascal","Basic","SQL","Others"};

    public String[] getLanguage() {
        return language;
    }

    public void setLanguage(String[] language) {
        this.language = language;
    }
}

