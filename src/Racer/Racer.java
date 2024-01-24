//Raziel houri 305427874 Eyasu belay 346440969

package Racer;

public class Racer implements Runnable{
    static int GlobalId = 1;
    protected int id;
    protected final int length =100;
    protected int speed;
    protected Track track;
    public Racer(int speed, Track track) {
        this.track = track;
        this.id = GlobalId++;
        if (speed >= 1 && speed <= 10) {
            this.speed = speed;
        } else {
            System.out.println("Valid input: " + speed);
            throw new IllegalArgumentException("Invalid input. Number must be between 1 and 10.");

        }

    }
    @Override
    public void run() {
        try {
            go();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void go() throws InterruptedException {
        for (int i=1; i<=length; i++){
            System.out.println("runner " + id + " ran " + i + " meters");
            Thread.sleep(speed);
            if(i==length){
                track.FinsheRacer.add(id);
                System.out.println("runner " + id + " finished " + printPosition());}

        }
    }


    private String printPosition(){

        switch (track.FinsheRacer.size()){
            case 1:
                return track.FinsheRacer.size()+"st";
            case 2:
                return track.FinsheRacer.size()+"nd";
            case 3:
                return track.FinsheRacer.size()+"rd";
            default:
                return track.FinsheRacer.size()+"th";

        }
    }

    ;
}
