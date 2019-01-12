import java.util.ArrayList;

public class TestMatcher {

    public static void main(String[] args){

        Matcher newMatcher = new Matcher();

        /* Test example from http://www.nrmp.org/wp-content/uploads/2014/05/Run-A-Match.pdf

        Applicant app1 = new Applicant("Anderson", "City");
        Applicant app2 = new Applicant("Chen", "City, Mercy");
        Applicant app3 = new Applicant("Ford", "City, General, Mercy");
        Applicant app4 = new Applicant("Davis", "Mercy, City, General");
        Applicant app5 = new Applicant("Eastman", "City, Mercy, General");
        Applicant app7 = new Applicant("NoMatchGuy", "City");

        newMatcher.addApplicant(app1);
        newMatcher.addApplicant(app2);
        newMatcher.addApplicant(app3);
        newMatcher.addApplicant(app4);
        newMatcher.addApplicant(app5);
        newMatcher.addApplicant(app7);

        Program prog1 = new Program("Mercy", 2, "Chen, Ford");
        Program prog2 = new Program("City", 2, "Eastman, Anderson, Chen, Davis, Ford, NoMatchGuy");
        Program prog3 = new Program("General", 2,"Eastman, Anderson, Ford, Davis");

        newMatcher.addProgram(prog1);
        newMatcher.addProgram(prog2);
        newMatcher.addProgram(prog3);
        */

        ArrayList<Applicant> processApplicants = new ArrayList<Applicant>();

        processApplicants.add(new Applicant("Alex", "American, Mercy, County, Mission, General, Fairview, Saint Mark, City, Deaconess, Park"));
        processApplicants.add(new Applicant("Brian", "County, Deaconess, American, Fairview, Mercy, Saint Mark, City, General, Mission, Park"));
        processApplicants.add(new Applicant("Cassie", "Deaconess, Mercy, American, Fairview, City, Saint Mark, Mission, Park, County, General"));
        processApplicants.add(new Applicant("Dana", "Mission, Saint Mark, Fairview, Park, Deaconess, Mercy, General, City, County, American"));
        processApplicants.add(new Applicant("Edward", "General, Fairview, City, County, Saint Mark, Mercy, American, Mission, Deaconess, Park"));
        processApplicants.add(new Applicant("Faith", "City, American, Fairview, Park, Mercy, Mission, County, General, Deaconess, Saint Mark"));
        processApplicants.add(new Applicant("George", "Park, Mercy, Mission, City, County, American, Fairview, Deaconess, General, Saint Mark"));
        processApplicants.add(new Applicant("Hannah", "American, Mercy, Deaconess, Saint Mark, Mission, County, General, City, Park, Fairview"));
        processApplicants.add(new Applicant("Ian", "Park, Mercy, City"));
        processApplicants.add(new Applicant("Jessica", "American, Saint Mark, General, Park, Mercy, City, Fairview, County, Mission, Deaconess"));

        for(Applicant a : processApplicants){
            newMatcher.addApplicant(a);
        }

        ArrayList<Program> processPrograms = new ArrayList<Program>();

        processPrograms.add(new Program("American", 1,"Brian, Faith, Jessica, George, Ian, Alex, Dana, Edward, Cassie, Hannah"));
        processPrograms.add(new Program("City", 1, "Brian, Alex, Cassie, Faith, George, Dana, Ian, Edward, Jessica, Hannah"));
        processPrograms.add(new Program("County", 1, "Faith, Brian, Edward, George, Hannah, Cassie, Ian, Alex, Dana, Jessica"));
        processPrograms.add(new Program("Fairview", 1,"Faith, Jessica, Cassie, Alex, Ian, Hannah, George, Dana, Brian, Edward"));
        processPrograms.add(new Program("Mercy", 2,"Jessica, Hannah, Faith, Dana, Alex, George, Cassie, Edward, Ian, Brian"));
        processPrograms.add(new Program("Saint Mark", 2,"Brian, Alex, Edward, Ian, Jessica, Dana, Faith, George, Cassie, Hannah"));
        processPrograms.add(new Program("Park", 1,"Jessica, George, Hannah, Faith, Brian, Alex, Cassie, Edward, Dana, Ian"));
        processPrograms.add(new Program("Deaconess", 2,"George, Jessica, Brian, Alex, Ian, Dana, Hannah, Edward, Cassie, Faith"));
        processPrograms.add(new Program("Mission", 1,"Ian, Cassie, Hannah, George, Faith, Brian, Alex, Edward, Jessica, Dana"));
        processPrograms.add(new Program("General", 9,"Edward, Hannah, George, Alex, Brian, Jessica, Cassie, Ian, Faith, Dana"));

        for(Program p : processPrograms){
            newMatcher.addProgram(p);
        }

        newMatcher.runMatch();

    }

}
