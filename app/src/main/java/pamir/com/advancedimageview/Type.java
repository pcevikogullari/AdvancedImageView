package pamir.com.advancedimageview;

/**
 * Created by Pamir on 20.09.2016.
 */

public enum Type {
    PORTRAIT(0), SQUARE(1), LANDSCAPE(2);

    int id;

    Type(int id){
        this.id = id;
    }

    public int getValue(){
        return id;
    }

    public static Type getValue(int id){
        for (Type type : Type.values()){
            if(id == type.getValue()){
                return type;
            }
        }
        return PORTRAIT;
    }
}
