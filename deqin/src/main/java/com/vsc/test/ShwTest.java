package com.vsc.test;

import com.vsc.business.gerd.entity.work.ParkingLockEventLog;

public class ShwTest {

	
	public static void main(String[] args) {
		
		
		
		ParkingLockEventLog vm=new ParkingLockEventLog();
		vm.setEventType(49);
		vm.setState(6);
		System.out.println(vm.getStateString());		
		System.out.println(vm.getDianLiang()) ;
		System.out.println(vm.getIsForeverOpenClose()) ;
		System.out.println(vm.getIsCarOn()) ;
		System.out.println(vm.getIsOpen()) ;
		
vm.setState(14);
		
		System.out.println(vm.getDianLiang()) ;
		System.out.println(vm.getIsForeverOpenClose()) ;
		System.out.println(vm.getIsCarOn()) ;
		System.out.println(vm.getIsOpen()) ;
		
		
	}
}

