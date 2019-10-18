package dashboard;

public class Test {

    public static void main(String[] args) {
        System.out.println("Program started\n");
        try {
            FetchingData fetch = new FetchingData();
            fetch.fetchAvailableBoards();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Program finished\n");
    }
}
