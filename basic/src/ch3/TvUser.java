package ch3;

public class TvUser {

	public static void main(String[] args) {
		TV tv = new LgTv();
		tv.setSpeaker(new AppleSpeaker());
		tv.turnOn();
		tv.soundDown();
		tv.soundUp();
		tv.turnOff();
		

	}

}
