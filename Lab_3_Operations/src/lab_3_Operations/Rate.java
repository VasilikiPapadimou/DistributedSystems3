//icsd14151 Vasiliki Papadimou
package lab_3_Operations;
import java.io.Serializable;
// Μία αξιολόγηση τραγουδιού
public class Rate implements Serializable{
    String user;
    String title;
    int stars;
    public Rate(String user,String title,int stars){
        this.user = user;
        this.title = title;
        this.stars = stars;
    }
    public Rate(){}

    public void setUser(String user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public int getStars() {
        return stars;
    }
    
}
