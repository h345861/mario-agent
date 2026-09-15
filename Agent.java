
import java.util.Random;

import game.mario.Direction;
import game.mario.MarioGame;
import game.mario.MarioPlayer;
import game.mario.utils.MarioState;

public class Agent extends MarioPlayer {
    /**
     * Mariorol egy masolatot keszitek, hogy tudjak vele kiserletezni,
     * es igy le tudjam szimulalni mario lehetseges lepeseit. A cel,
     * hogy mario mindig a legjovedelmezobb (legjobb pontot es eredmenyt hozo lepest)
     * lepest valassza a lehetosegek kozul.
     * Minden lepesben kiprobalom az iranyokat, es szimulalom mi tortenne az
     * adott irany valasztasa utan. Aztan a tesztben veletlen iranyokba meg tovabb lep.
     * A futasok vegen megnezem, hogy mennyi volt az elmozdulas vizszintesen.
     * Ezutan azokat osszeadom es abbol kapja a pontot az adott irany.
     * Ha eleri a palya veget nagy jutalom, hogy bemenjen a celba.
     * Ha hibat dob akkor a szimulacio megall.
     * Mindig azt az irany fogom valasztani ami a sok szimulacio utan a legjobb pontot adta.
     */

    public Agent(int color, Random random, MarioState state) {
        super(color, random, state);
    }

    @Override
    public Direction getDirection(long remainingTime) {

        Direction[] iranyok = new Direction []{
                new Direction(MarioGame.UP),
                new Direction(MarioGame.RIGHT),
                new Direction(MarioGame.LEFT)
        };
        double max = -11;
        Direction legjobb = new Direction(MarioGame.RIGHT);
        for (Direction i : iranyok) {
            double osszeg = 0;
            int lepesselEloreNezek = 50;
            for(int j = 0; j < lepesselEloreNezek; j++) {
                MarioState marioMasolat = new MarioState(state);
                double marioKezdetiPont = marioMasolat.score;
                double marioKezdetiOszlop = marioMasolat.mario.j;

                try{marioMasolat.apply(i);}catch (Exception e){continue;}
                for(int k = 0; k < 100; k++) {try{marioMasolat.apply(getRandomDirection());}catch (Exception e){break;}}

                if (marioMasolat.mario.j >= MarioGame.W - 1 && marioMasolat.mario.i < MarioGame.H - 1) {
                    osszeg += 100000; //ne alljon meg a vege elott
                }

                double marioLepesUtanPont = marioMasolat.score;
                double nyertPont = marioLepesUtanPont - marioKezdetiPont;
                double tavolsag = Math.abs(marioKezdetiOszlop - marioMasolat.mario.j);
                osszeg += nyertPont + tavolsag*5;
            }
            double ertek = osszeg;

            if (ertek > max) {
                max = osszeg;
                legjobb = i;
            }
        }
        state.apply(legjobb);
        return legjobb;
    }

    // kell egy random iranysorsolo

    private Direction getRandomDirection() {
        Direction [] irany = new Direction[]{
                new Direction(MarioGame.UP),
                new Direction(MarioGame.RIGHT),
                new Direction(MarioGame.LEFT),
                null
        };
        return irany[random.nextInt(irany.length)];
    }
}
