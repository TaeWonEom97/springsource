package ch3;

public class SamsungTv implements TV{ 
	@Override
	public void turnOn() {
		// TODO Auto-generated method stub
		System.out.println("SamsungTv - 전원 On");
		
	}
	@Override
	public void turnOff() {
		// TODO Auto-generated method stub
		System.out.println("SamsungTv - 전원 Off");
		
	}
	@Override
	public void soundUp() {
		// TODO Auto-generated method stub
		System.out.println("SamsungTv - 볼륨 Up");
		
	}
	@Override
	public void soundDown() {
		// TODO Auto-generated method stub
		System.out.println("SamsungTv - 볼륨 Down");
		
	}
	@Override
	public void setSpeaker(AppleSpeaker appleSpeaker) {
		// TODO Auto-generated method stub
		
	}

}
