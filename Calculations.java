
public class Calculations {
	public double URMS						= 230;			//Mains voltage on transformer
	public double UsAC 						= 42;			//Secondary voltage
	public double Iload						= 15;			//Max load for power the power supply
	public double f							= 50;			//Frequency
	public double diode						= 0.7;			//Diodeloss
	
	// Program is made to only calculate for diode brige rectifiers. 

	

	public Calculations(){	
		
		Ratio();
		Nominell();
		WorstCase1();
		WorstCase2();
		
	}
	
	public void Ratio(){
		double Ratio 						= URMS / UsAC;
		Ratio = Math.floor(Ratio*100)/100;
		if(Ratio >1){
			System.out.println("");
			System.out.println("Ratio: "+ "1:"+Ratio);
		}
		if(Ratio <1){
			Ratio = Math.round(UsAC/URMS);
			System.out.println("Windingratio");
			System.out.println("Ratio: "+ Ratio +":1");
		}
	}
	
	
	public void Nominell(){
		double AUsAC						= (UsAC*(Math.sqrt(2)));
		double Umax							= AUsAC-(2*diode);
		double Ur							= Umax-UsAC;
		double C							= Iload/(2*f*Ur);
		
		System.out.println("Nominell");
		System.out.println("Voltage inn: " + UsAC +"V");
		System.out.println("Amplitude voltage: " + AUsAC +"V" );
		System.out.println("Voltage after diodes: " + Umax + "V");
		System.out.println("Frequency: " + f+"Hz");
		System.out.println("Rippel: " + Ur +"V");
		System.out.println("Load: " + Iload + "A");
		System.out.println("Smoothing capacitor: " + C*1000 + "mikrofarad");
	}

	public void WorstCase1() {
		// TODO Auto-generated method stub
		double W1UsAC 						= UsAC*0.9;
		double W1AUsAC						= (W1UsAC*(Math.sqrt(2)));
		double W1Umax						= W1AUsAC-(2*diode);
		double W1Ur							= W1Umax-W1UsAC;
		double W1C							= Iload/(2*f*W1Ur);
		
		System.out.println("Worstcase -10% on inn voltage");
		System.out.println("Voltage inn: " + W1UsAC +"V");
		System.out.println("Amplitude voltage: " + W1AUsAC +"V" );
		System.out.println("Voltage after diodes: " + W1Umax + "V");
		System.out.println("Frequency: " + f+"Hz");
		System.out.println("Rippel: " + W1Ur +"V");
		System.out.println("Load: " + Iload + "A");
		System.out.println("Smoothing capacitor: " + W1C*1000 + "mikrofarad");
		
	}
	public void WorstCase2() {
		// TODO Auto-generated method stub
		double W2UsAC 						= UsAC*1.1;
		double W2AUsAC						= (W2UsAC*(Math.sqrt(2)));
		double W2Umax						= W2AUsAC-(2*0.7);
		double W2Ur							= W2Umax-W2UsAC;
		double W2C							= Iload/(2*f*W2Ur);
		
		System.out.println("Worstcase +10% on inn voltage");
		System.out.println("Voltage inn: " + W2UsAC +"V");
		System.out.println("Amplitude voltage: " + W2AUsAC +"V" );
		System.out.println("Voltage after diodes: " + W2Umax + "V");
		System.out.println("Frequency: " + f+"Hz");
		System.out.println("Rippel: " + W2Ur +"V");
		System.out.println("Load: " + Iload + "A");
		System.out.println("Smoothing capacitor: " + W2C*1000 + "mikrofarad");
	}
}
