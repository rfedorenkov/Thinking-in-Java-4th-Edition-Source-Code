package exercises.chapter21;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 38
 * Using the approach in CarBuilder.java, model the
 * house-building story that was given in this chapter.
 */
class House {
    private final int id;
    private boolean foundation = false, walling = false, roofing = false,
            facadeDecoration = false, communications = false, interiorDecoration = false;

    public House(int idn) {
        id = idn;
    }

    public House() {
        id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addFoundation() {
        foundation = true;
    }

    public synchronized void addWalling() {
        walling = true;
    }

    public synchronized void addRoofing() {
        roofing = true;
    }

    public synchronized void addFacadeDecoration() {
        facadeDecoration = true;
    }

    public synchronized void addCommunications() {
        communications = true;
    }

    public synchronized void addInteriorDecoration() {
        interiorDecoration = true;
    }

    @Override
    public synchronized String toString() {
        return "House " + id + " [" + " foundation: " + foundation
                + " walling: " + walling
                + " roofing: " + roofing
                + " facadeDecoration: " + facadeDecoration
                + " communications: " + communications
                + " interiorDecoration: " + facadeDecoration + " ]";
    }
}

class HouseQueue extends LinkedBlockingQueue<House> {
}

class Digger implements Runnable {
    private HouseQueue houseQueue;
    private int counter = 0;

    public Digger(HouseQueue hq) {
        houseQueue = hq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                House h = new House(counter++);
                print("Digger created " + h);
                houseQueue.put(h);
            }
        } catch (InterruptedException e) {
            print("Interrupted: Digger");
        }
        print("Digger off");
    }
}

class HouseBuilder implements Runnable {
    private HouseQueue baseQueue, finishingQueue;
    private House house;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private TeamPool teamPool;

    public HouseBuilder(HouseQueue bq, HouseQueue fq, TeamPool tp) {
        baseQueue = bq;
        finishingQueue = fq;
        teamPool = tp;
    }

    public House house() {
        return house;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                house = baseQueue.take();
                teamPool.hire(FoundationTeam.class, this);
                teamPool.hire(WallingTeam.class, this);
                teamPool.hire(RoofingTeam.class, this);
                barrier.await();
                teamPool.hire(FacadeDecorationTeam.class, this);
                teamPool.hire(CommunicationsTeam.class, this);
                teamPool.hire(InteriorDecorationTeam.class, this);
                barrier.await();
                finishingQueue.put(house);
            }
        } catch (InterruptedException e) {
            print("Exiting HouseBuilder via interrupt");
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        print("HouseBuilder off");
    }
}

class Reporter2 implements Runnable {
    private HouseQueue houseQueue;

    public Reporter2(HouseQueue hq) {
        houseQueue = hq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(houseQueue.take());
            }
        } catch (InterruptedException e) {
            print("Exiting Reporter via interrupt");
        }
        print("Reporter off");
    }
}

abstract class Team implements Runnable {
    private TeamPool pool;

    public Team(TeamPool p) {
        pool = p;
    }

    protected HouseBuilder houseBuilder;

    public Team assignHouseBuilder(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
        return this;
    }

    private boolean engage = false;

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    // The part of run() that's different for each team:
    abstract protected void performService();

    @Override
    public void run() {
        try {
            rest();
            while (!Thread.interrupted()) {
                performService();
                houseBuilder.barrier().await();
                rest();
            }
        } catch (InterruptedException e) {
            print("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        print(this + " off");
    }

    private synchronized void rest() throws InterruptedException {
        engage = false;
        houseBuilder = null;
        pool.release(this);
        while (engage == false)
            wait();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class FoundationTeam extends Team {
    public FoundationTeam(TeamPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        print(this + " installing foundation");
        houseBuilder.house().addFoundation();
    }
}

class WallingTeam extends Team {
    public WallingTeam(TeamPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        print(this + " installing walling");
        houseBuilder.house().addWalling();
    }
}

class RoofingTeam extends Team {
    public RoofingTeam(TeamPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        print(this + " installing roofing");
        houseBuilder.house().addRoofing();
    }
}

class FacadeDecorationTeam extends Team {
    public FacadeDecorationTeam(TeamPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        print(this + " installing facade decoration");
        houseBuilder.house().addFacadeDecoration();
    }
}

class CommunicationsTeam extends Team {
    public CommunicationsTeam(TeamPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        print(this + " installing facade communications");
        houseBuilder.house().addCommunications();
    }
}

class InteriorDecorationTeam extends Team {
    public InteriorDecorationTeam(TeamPool pool) {
        super(pool);
    }

    @Override
    protected void performService() {
        print(this + " installing interior decoration");
        houseBuilder.house().addInteriorDecoration();
    }
}

class TeamPool {
    private Set<Team> pool = new HashSet<>();

    public synchronized void add(Team r) {
        pool.add(r);
        notifyAll();
    }

    public synchronized void hire(Class<? extends Team> teamType, HouseBuilder d) throws InterruptedException {
        for (Team t : pool)
            if (t.getClass().equals(teamType)) {
                pool.remove(t);
                t.assignHouseBuilder(d);
                t.engage();
                return;
            }
        wait();
        hire(teamType, d);
    }

    public synchronized void release(Team r) {
        add(r);
    }
}

public class E38_HouseBuilder {
    public static void main(String[] args) throws Exception {
        HouseQueue baseQueue = new HouseQueue(),
                finishingQueue = new HouseQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        TeamPool teamPool = new TeamPool();
        exec.execute(new FoundationTeam(teamPool));
        exec.execute(new WallingTeam(teamPool));
        exec.execute(new RoofingTeam(teamPool));
        exec.execute(new FacadeDecorationTeam(teamPool));
        exec.execute(new CommunicationsTeam(teamPool));
        exec.execute(new InteriorDecorationTeam(teamPool));
        exec.execute(new HouseBuilder(baseQueue, finishingQueue, teamPool));
        exec.execute(new Reporter2(finishingQueue));
        exec.execute(new Digger(baseQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
}
/* Output:
Digger created House 0 [ foundation: false walling: false roofing: false facadeDecoration: false communications: false interiorDecoration: false ]
RoofingTeam installing roofing
WallingTeam installing walling
FoundationTeam installing foundation
InteriorDecorationTeam installing interior decoration
CommunicationsTeam installing facade communications
FacadeDecorationTeam installing facade decoration
House 0 [ foundation: true walling: true roofing: true facadeDecoration: true communications: true interiorDecoration: true ]
Digger created House 1 [ foundation: false walling: false roofing: false facadeDecoration: false communications: false interiorDecoration: false ]
FoundationTeam installing foundation
WallingTeam installing walling
RoofingTeam installing roofing
FacadeDecorationTeam installing facade decoration
CommunicationsTeam installing facade communications
InteriorDecorationTeam installing interior decoration
House 1 [ foundation: true walling: true roofing: true facadeDecoration: true communications: true interiorDecoration: true ]
Digger created House 2 [ foundation: false walling: false roofing: false facadeDecoration: false communications: false interiorDecoration: false ]
FoundationTeam installing foundation
WallingTeam installing walling
RoofingTeam installing roofing
FacadeDecorationTeam installing facade decoration
CommunicationsTeam installing facade communications
InteriorDecorationTeam installing interior decoration
House 2 [ foundation: true walling: true roofing: true facadeDecoration: true communications: true interiorDecoration: true ]
Digger created House 3 [ foundation: false walling: false roofing: false facadeDecoration: false communications: false interiorDecoration: false ]
FoundationTeam installing foundation
WallingTeam installing walling
RoofingTeam installing roofing
FacadeDecorationTeam installing facade decoration
InteriorDecorationTeam installing interior decoration
CommunicationsTeam installing facade communications
House 3 [ foundation: true walling: true roofing: true facadeDecoration: true communications: true interiorDecoration: true ]
...
Digger created House 12 [ foundation: false walling: false roofing: false facadeDecoration: false communications: false interiorDecoration: false ]
FoundationTeam installing foundation
WallingTeam installing walling
RoofingTeam installing roofing
FacadeDecorationTeam installing facade decoration
CommunicationsTeam installing facade communications
InteriorDecorationTeam installing interior decoration
House 12 [ foundation: true walling: true roofing: true facadeDecoration: true communications: true interiorDecoration: true ]
Exiting Reporter via interrupt
Reporter off
Interrupted: Digger
Digger off
Exiting HouseBuilder via interrupt
HouseBuilder off
Exiting FacadeDecorationTeam via interrupt
FacadeDecorationTeam off
Exiting FoundationTeam via interrupt
FoundationTeam off
Exiting CommunicationsTeam via interrupt
CommunicationsTeam off
Exiting WallingTeam via interrupt
WallingTeam off
Exiting InteriorDecorationTeam via interrupt
InteriorDecorationTeam off
Exiting RoofingTeam via interrupt
RoofingTeam off
 */