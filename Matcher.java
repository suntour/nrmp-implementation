import java.util.ArrayList;
import java.util.HashMap;

public class Matcher {
    ArrayList<Applicant> Applicants = new ArrayList<Applicant>(); //Keeps track of applicants still to be matched
    HashMap<String, Program> Programs = new HashMap<String, Program>(); //For easy translation from Strings to Program
    ArrayList<Applicant> NoMatch = new ArrayList<Applicant>(); //Keeps track of those unable to be matched
    Applicant currentApplicant; //Used as a reference

    Matcher(){
    }

    //After all applicants and programs are added, invoke runMatch to start the algorithm
    public void runMatch(){

        String key;
        boolean uniqueApplicant = true;
        Applicant currentApplicant;
        int numChoices;

        //While there are still applicants to be matched
        while(!Applicants.isEmpty()){

            currentApplicant = getApplicant();
            numChoices = getApplicantChoices().size();

            if(uniqueApplicant){
                System.out.println("The current applicant is: " + getApplicantName());
            }

            if(currentApplicant.getChoices().size() == 0){
                System.out.println(getApplicantName() + " was not matched with any residency. \n");
                NoMatch.add(getApplicant());
                Applicants.remove(0);
            }
            //For each program the applicant has applied to
            for(int i = 0; i < numChoices; i++){

                currentApplicant = getApplicant();
                key = getApplicantChoices().get(i);

                //System.out.println("The current applicant is: " + getApplicantName());
                System.out.println(getApplicantName() + " wants to join " + getApplicantChoices().get(i));

                //Check if the program ranks the applicant at all
                if(getProgram(key).isRanked(currentApplicant)){
                    int currentRank = getProgram(key).getRank(currentApplicant);
                    System.out.println(getProgram(key).getName() + " ranks " + getApplicantName() + " at " + (currentRank + 1));

                    //Is there any room?
                    if(getProgram(key).getPositions() > 0){

                        //There is room, tentatively match applicant with program since they are ranked and there is room
                        System.out.println("There are " + getProgram(key).getPositions() + " positions open, " + getApplicantName() + " has been tentatively matched with " + getApplicantChoices().get(i) + "\n");
                        Programs.get(key).match(currentApplicant, currentRank);

                        //System.out.println("Before remove: " + Applicants);
                        Applicants.remove(getApplicant());

                        //System.out.println("After remove: " + Applicants);
                        uniqueApplicant = true;
                        break;
                    }

                    //There are no positions open. Are they more preferred than the programs currently least preferred applicant?
                    else{
                        int leastPreferred = getProgram(key).getLeastPreferredRank();
                        if(currentRank < leastPreferred){
                            System.out.println("The least preferred matched applicant is ranked " + leastPreferred + ", ("+ getApplicantName(getMatchedApplicant(key, getMatchedList(key).size()-1)) +"). " + getApplicantName() + " is more preferred and will swap with them. \n");

                            //Remove least preferred applicant from program, and add back onto Applicants queue
                            Applicants.add(getProgram(key).unmatch(getMatchedList(key).get(getMatchedList(key).size()-1)));

                            //Match the more preferred applicant
                            getProgram(key).match(currentApplicant, currentRank);
                            Applicants.remove(getApplicant());
                            uniqueApplicant = true;
                            break;
                        }
                        else{
                            //Applicant cannot join the program, they are not preferred over the other applicants, and there is no room
                            getApplicant().removeFirstChoice();
                            System.out.println(getApplicantName() + " is not preferred over any of the matched applicants \n");
                            uniqueApplicant = false;
                            break; //Avoids problems with index i in current loop, remove applicant's top choice and start over
                        }
                    }
                }

                //Program does not rank applicant
                else{
                    getApplicant().removeFirstChoice();
                    System.out.println(getProgram(key).getName() + " does not rank " + getApplicantName() + "\n");
                    uniqueApplicant = false;
                    break;
                }
            }
        }

        for(Program p : Programs.values()){
            int numPositions = p.getPositions();

            if(!p.hasMatches()){
                System.out.println(p.getName() + " has not matched with any applicants.");
            }
            else if(numPositions == 0){
                System.out.println(p.getName() + " has filled all positions. The following applicant(s) were matched: " + p.getFinalNames());
            }
            else{
                System.out.println(p.getName() + " has " + numPositions + " positions(s) unfilled. The following applicant(s) were matched: " + p.getFinalNames());
            }

        }

        if(!NoMatch.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append(NoMatch.get(0).getName());
            for(int i = 1; i < NoMatch.size(); i++){
                sb.append(", " + NoMatch.get(i).getName());
            }

            System.out.println("The following applicant(s) were not matched: " + sb.toString());
        }
    }

    //Returns the ArrayList of matched Applicants, based on Key
    public ArrayList<Applicant> getMatchedList(String key){
        return getProgram(key).getMatched();
    }

    //Returns an Applicant from the matched ArrayList, based on index
    public Applicant getMatchedApplicant(String key, int index){
        return getMatchedList(key).get(index);
    }

    //Returns the Program from the Programs ArrayList, based on Key
    public Program getProgram(String key){
        return Programs.get(key);
    }

    //Returns the Applicant at the front of the queue
    public Applicant getApplicant(){
        return Applicants.get(0); //0 being the front of the Applicants ArrayList
    }

    //Returns the name of the Applicant at the front of the queue
    public String getApplicantName(){
        return getApplicant().getName();
    }

    //Returns the name of the Applicant passed
    public String getApplicantName(Applicant applicant){
        return applicant.getName();
    }

    //Returns the residencies preferred by the Applicant at the front of the queue
    public ArrayList<String> getApplicantChoices(){
        return getApplicant().getChoices();
    }

    //Add applicant to the pool of people waiting to be matched
    public void addApplicant(Applicant newApplicant){
        Applicants.add(newApplicant);
    }

    //Add program to Programs HashMap
    public void addProgram(Program newProgram){
        Programs.put(newProgram.getName(), newProgram);
    }

    //For each
    public void validate(){

    }
}
