package publicDataTest.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import publicDataTest.PublicDataAPITest;
import publicDataTest.model.LandPriceVO;

public class LandPriceRegisterManager {
	public static Scanner sc = new Scanner(System.in); 
	
	public void insertManager() throws SQLException {
		LandPriceDAO ldao = new LandPriceDAO();
		ArrayList<LandPriceVO> landPriceList = PublicDataAPITest.apiDataLoad();
		boolean successFlag = false;
		for(LandPriceVO lvo : landPriceList) {
			int count = ldao.landPriceCheckNodeNOSelect(lvo);
			if(count <= 0) {
				successFlag = ldao.landPriceInsert(lvo);
			}else {
				successFlag = ldao.landPriceUpdate(lvo);
			}
		}
		
		if(successFlag == true) {
			System.out.println("데이터를 입력/수정했습니다");
		}else {
			System.out.println("데이터를 입력/수정 실패했습니다.");
		}
		
	}

	public static void selectManager() throws SQLException {
		LandPriceDAO ldao = new LandPriceDAO();
		LandPriceVO lvo = new LandPriceVO();
		ArrayList<LandPriceVO> list = ldao.landPriceSelect();

		
	}

	public static void updateManager() throws SQLException {
		LandPriceDAO ldao = new LandPriceDAO();
		LandPriceVO lvo = new LandPriceVO();
		ArrayList<LandPriceVO> list = new ArrayList<LandPriceVO>();
		
		//학과번호 , 수정할 학과 이름을 입력
		System.out.print("수정할 번호 선택>> ");
		int nodeno = Integer.parseInt(sc.nextLine());
		
		System.out.print("수정할 위도 입력>> ");
		double gpslati = Double.parseDouble(sc.nextLine());
		
		System.out.print("수정할 경도 입력>> ");
		double gpslong = Double.parseDouble(sc.nextLine());
		
		System.out.print("수정할 아이디>> ");
		String nodeid = (sc.nextLine()).trim();
		
		System.out.print("수정할 정류소명>> ");
		String nodenm = (sc.nextLine()).trim();
		lvo = new LandPriceVO(nodeno, gpslati, gpslong, nodeid, nodenm);
		list.add(lvo);
		boolean successFlag = ldao.landPriceUpdate(lvo);
		
		if(successFlag == true) {
			System.out.println("수정처리 성공");
		}else {
			System.out.println("수정처리 실패");
		}
	}

	public void deleteManager() throws SQLException {
		LandPriceDAO ldao = new LandPriceDAO();
		LandPriceVO lvo = new LandPriceVO();
		ArrayList<LandPriceVO> list = ldao.landPriceDelete(lvo);
		

	}

	//전체 학생리스트를 출력진행
	public static void printLandPriceList(ArrayList<LandPriceVO> landPriceList) {
		System.out.println("============================================");
		for( LandPriceVO sv :  landPriceList) {
			System.out.println(sv.toString());
		}
		System.out.println("============================================");
	}
}





