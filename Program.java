import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.TreeMap;

public class Program {
    private String name;
    private int positions;
    private int leastConfirmedRank = 0;
    private TreeMap<String, Integer> rankedList = new TreeMap<String, Integer>();
    private ArrayList<Applicant> matched = new ArrayList<Applicant>();
    private ArrayList<Applicant> confirmed = new ArrayList<Applicant>();

    Program(String programName, int numPositions, String stringRankedList){
        name = programName;
        positions = numPositions;

        String[] s = stringRankedList.split(", ");
        for(int i = 0; i < s.length; i++){
            rankedList.put(s[i], i);
        }
    }

    public String getName(){
        return name;
    }

    public int getPositions(){
        return positions;
    }

    public ArrayList<Applicant> getMatched(){
        return matched;
    }

    public boolean hasMatches(){
        int matches = matched.size() + confirmed.size();
        if(matches == 0){
            return false;
        }
        return true;
    }

    public void match(Applicant incApplicant, int newRank) {
        Applicant newApp = incApplicant;
        newApp.changeRank(newRank);
        positions--;

        if(getRank(newApp) == leastConfirmedRank){
            confirmed.add(newApp);
            rankedList.remove(newApp.getName());
            Collections.sort(confirmed, new ApplicantComp());
        }
        else{
            matched.add(newApp);
            Collections.sort(matched, new ApplicantComp());
        }
    }

    public Applicant unmatch(Applicant removedApplicant){
        Applicant exitApp = removedApplicant;
        exitApp.changeRank(-1);
        exitApp.removeFirstChoice();
        positions++;
        matched.remove(removedApplicant);
        return exitApp;
    }

    public int getRank(Applicant newApplicant){
        return rankedList.get(newApplicant.getName());
    }

    public int getLeastPreferredRank(){
        return getRank(matched.get(matched.size()-1));
    }

    public boolean isRanked(Applicant newApplicant){
        String tempName = newApplicant.getName();

        //Attempt to find newApplicant in rankedList
        if(rankedList.containsKey(tempName)){
            return true;
        }
        return false;
    }

    public String getFinalNames(){
        StringBuilder sb = new StringBuilder();

        if(confirmed.isEmpty()){
            sb.append(matched.get(0).getName());
            for(int i = 1; i < matched.size(); i++){
                sb.append(", " + matched.get(i).getName());
            }
            return sb.toString();
        }
        else{
            sb.append(confirmed.get(0).getName());
            for(int i = 1; i < confirmed.size(); i++){
                sb.append(confirmed.get(i).getName());
            }
            for(Applicant a : matched){
                sb.append(", " + a.getName());
            }
            return sb.toString();
        }
    }

    //Anonymous comparator
    class ApplicantComp implements Comparator<Applicant>{
        @Override
        public int compare(Applicant a1, Applicant a2) {
            if(a1.getRank() > a2.getRank()){
                return 1;
            } else {
                return -1;
            }
        }
    }
}
