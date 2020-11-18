package fruits.cosmo.classes;

import java.util.ArrayList;
import java.util.List;

public class TreeTypes {
    int atime;
    int btime;
    String aType;
    ArrayList<String> bType;
    public TreeTypes(int atime, int btime,String aType, List<String> bType){
        this.atime = atime;
        this.btime = btime;
        this.aType = aType;
        this.bType = new ArrayList<String>(bType);
    }
    public String getaType(){
        return aType;
    }
}
