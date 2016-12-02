package models;

public class Document implements Comparable<Object>{
    private String comment;
    private String id;
    /*ORDINE LABEL : LOVE, JOY,SURPRISE,ANGER,SADNESS,FEAR*/
    private String[] sentiments;
    private String finaLabel;
    private enum label{positive,negative,neutral,mixed};
    public String getId(){
        return id;
    }
    public String getComment(){
        return comment;
    }
    public String getFinaLabel(){
        return finaLabel;
    }


    public String get_Love(){
        return sentiments[0];
    }
    public String get_Joy(){
        return sentiments[1];
    }
    public String get_Anger(){
        return sentiments[3];
    }
    public String get_Sadness(){
        return sentiments[4];
    }
    public String get_Fear(){
        return sentiments[5];
    }
    public String get_Surprise(){
        return sentiments[2];
    }
   public void setComment(String comment) {
        this.comment = comment;
    }

    public void setId(String id) {
        this.id=id;
    }

    public void setSentiments(String[] sentiments) {
        this.sentiments = sentiments;
        if(sentiments!=null)
             setFinaLabel();
    }
// la position serve per l'ordine dinamico
    private boolean get_love_joy(){
        return (sentiments[0].equals("x") || sentiments[1].equals("x"));
    }
    private boolean get_anger__sadness_fear(){
        return (sentiments[3].equals("x") || sentiments[4].equals("x") || sentiments[5].equals("x"));
    }
    /*questo metodo setta la lb. finale
    - se (almeno una label tra sadness, anger, fear) e (nessuna label tra love and joy) = negative
    - se (almeno una label tra love and joy) e (nessuna label tra sadness, anger, fear) = positive
    - se sia una label positiva che una negativa -> mixed
    - se nessuna label -> neutral
    */
    private void setFinaLabel() {
        if(get_anger__sadness_fear() && !get_love_joy()){
            finaLabel=label.negative.toString();
        }
        else
            if(get_love_joy() && !get_anger__sadness_fear()){
                finaLabel=label.positive.toString();
            }
        else
            if(get_love_joy() && get_anger__sadness_fear()){
                finaLabel=label.mixed.toString();
            }
        else
            if(!(get_love_joy() && get_anger__sadness_fear())){
                finaLabel=label.neutral.toString();
            }
    }

    public void setFinaLabel(String l){
        this.finaLabel=l;
    }



    @Override
    public int compareTo(Object o) {
        if (o instanceof  Document){
            Document d= (Document) o;
            int id1= Integer.parseInt(id);
            int id2= Integer.parseInt(d.getId());
            if(id1>=id2){
                return 1;
            }
            if(id1<id2) {
                return -1;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return id;
    }
}
