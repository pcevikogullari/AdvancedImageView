package pamir.com.advancedimageview;

/**
 * Created by Pamir on 21.09.2016.
 */

public enum Source {
    GLIDE(0), PICASSO(1);

    int id;

    Source(int id){
        this.id = id;
    }

    public int getValue(){
        return id;
    }

    public static Source getValue(int id){
        for (Source source : Source.values()){
            if(id == source.getValue()){
                return source;
            }
        }
        return GLIDE;
    }
}
