//Vasiliki Papadimou

package operations;

import java.io.Serializable;

// Μια εγγραφή-τραγούδι με 3 κατασκευαστές ανάλογα ποιος δημιουργεί την εγγραφή
public class Record implements Serializable{
    private String title;
    private String eidos;
    private String singer;
    private int timesec;
    private float avg;
    
    public Record(String title,String eidos,String singer,int timesec){
        this.title = title;
        this.eidos = eidos;
        this.singer = singer;
        this.timesec = timesec;
    }
    public Record(String title,String eidos,String singer,int timesec,float avg){
        this.title = title;
        this.eidos = eidos;
        this.singer = singer;
        this.timesec = timesec;
        this.avg = avg;
    }
    public Record(){}

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEidos(String eidos) {
        this.eidos = eidos;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setTimesec(int timesec) {
        this.timesec = timesec;
    }

    public String getTitle() {
        return title;
    }

    public String getEidos() {
        return eidos;
    }

    public String getSinger() {
        return singer;
    }

    public int getTimesec() {
        return timesec;
    }
    
    @Override
    public String toString(){
        String disp = "Τραγούδι:"+title +"\n";
        disp += "Είδους:"+eidos +"\n";
        disp+= "Βασικός τραγουδιστής:"+singer+"\n";
        disp+=" Χρόνος σε δευτερόλεπτα:"+timesec/60+":"+(timesec%60<10?"0"+timesec%60:timesec%60);
        // Αν πρόκειτε για τραγούδι μετά από αξιολόγηση εμφανίζει και αυτήν
        if (avg!=0){
            disp+="\n Μέση βαθμολογία:"+avg;
        }
        return disp;
    }
}
