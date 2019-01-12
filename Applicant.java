import java.util.ArrayList;
import java.util.Arrays;

public class Applicant {

    int testrank;

    private String name;
    private ArrayList<String> choices;

    Applicant(String newName, String stringChoices){
        choices = new ArrayList<String>(Arrays.asList(stringChoices.split(", ")));
        name = newName;
    }

    public String getName(){
        return name;
    }

    public void changeRank(int newRank){
        testrank = newRank;
    }
    public int getRank(){
        return testrank;
    }

    public ArrayList<String> getChoices(){
        return choices;
    }

    public void removeFirstChoice(){
        choices.remove(0);
    }

    public void setChoices(ArrayList<String> NewChoices){
        choices = NewChoices;
    }

    public String valueOf(){
        return getName();
    }
}
