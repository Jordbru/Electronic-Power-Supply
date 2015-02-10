/**
 * Created by droydi on 07/02/15.
 */
public class Inputs {
    public double URMS;			//Main voltage on transformer
    public double UsAC;			//Secondary voltage
    public double Umin;			//Lowest design voltage added 10.02 Jordbru
    public double Iload;		//Max load for power the power supply
    public double f	= 50;		//Frequency
    public double diode	= 0.7;	//Diodeloss


    public double getURMS() {
        return URMS;
    }

    public void setURMS(double URMS) {
        this.URMS = URMS;
    }

    public double getUsAC() {
        return UsAC;
    }

    public void setUsAC(double usAC) {
        UsAC = usAC;
    }

    public double getUmin() {
		return Umin;
	}

	public void setUmin(double umin) {
		Umin = umin;
	}

	public double getIload() {
        return Iload;
    }

    public void setIload(double iload) {
        Iload = iload;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public double getDiode() {
        return diode;
    }

    public void setDiode(double diode) {
        this.diode = diode;
    }

    @Override
    public String toString() {
        return  "Mains voltage: " + URMS +
                ", Secondary voltage: " + UsAC +
                ", Max load (Amps): " + Iload;
    }
}
