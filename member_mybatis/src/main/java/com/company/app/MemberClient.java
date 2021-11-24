package com.company.app;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.company.domain.ChangeDTO;
import com.company.domain.MemberDTO;
import com.company.service.MemberService;

public class MemberClient {

	public static void main(String[] args) {
		// 스프링 컨테이너 로드
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");

		// 서비스 메소드 호출
		MemberService service = (MemberService) ctx.getBean("service");

		Scanner sc = new Scanner(System.in);
		boolean flag = true;

		while (flag) {
			System.out.println("====================================================");
			System.out.println("1. 전체 멤버 조회");
			System.out.println("2. 특정 멤버 조회");
			System.out.println("3. 특정 멤버 수정");
			System.out.println("4. 특정 멤버 삭제");
			System.out.println("5. 특정 멤버 추가");
			System.out.println("6. 프로그램 종료");
			System.out.println("====================================================");

			System.out.println("메뉴 >> ");
			int no = Integer.parseInt(sc.nextLine());

			switch (no) {
			case 1:
				System.out.println();
				
				List<MemberDTO> list = service.getList();
				System.out.println("아이디\t 성명\t 성별\t 이메일");
				System.out.println("-----------------------------------------------");
				for(MemberDTO dto:list) {
					System.out.print(dto.getUserid()+"\t");
					System.out.print(dto.getName()+"\t");
					System.out.print(dto.getGender()+"\t");
					System.out.println(dto.getEmail());
				}
				break;

			case 2:
				System.out.println("아이디를 입력하시오");
				String userid=sc.nextLine();
				System.out.println("비밀번호를 입력하시오");
				String password=sc.nextLine();
				MemberDTO dto = service.getRow(userid, password);
				System.out.print("userid = "+dto.getUserid()+"\t");
				System.out.print("name = "+dto.getName()+"\t");
				System.out.print("gender = "+dto.getGender()+"\t");
				System.out.println("email = "+dto.getEmail());
				break;
			case 3:
				
				System.out.println("아이디를 입력하시오");
				String userid1=sc.nextLine();
				System.out.println("비밀번호를 입력하시오");
				String password1=sc.nextLine();
				System.out.println("수정할 비밀번호를 입력하시오");
				String confirm_password=sc.nextLine();
				ChangeDTO changeDto = new ChangeDTO();
				changeDto.setUserid(userid1);
				changeDto.setPassword(password1);
				changeDto.setConfirm_password(confirm_password);
				System.out.println("수정결과는 다음과 같습니다.");
				System.out.println(service.updateDto(changeDto)?"수정성공":"수정실패");
				break;
			case 4:
				System.out.println("삭제할 아이디를 입력하시오");
				String userid2=sc.nextLine();
				System.out.println(service.deleteDto(userid2)?"삭제성공":"삭제실패"); 
				break;
			case 5:
				System.out.println("아이디를 입력하시오. : ");
				String userid3=sc.nextLine();
				System.out.println("비밀번호를 입력하시오. : ");
				String password3=sc.nextLine();
				System.out.println("이름을 입력하시오 : ");
				String name=sc.nextLine();
				System.out.println("성별을 입력하시오. : ");
				String gender=sc.nextLine();
				System.out.println("이메일을 입력하시오. : ");
				String email=sc.nextLine();
				MemberDTO dto2 = new MemberDTO(userid3, password3, name, gender, email);
				System.out.println(service.insertDto(dto2)?"입력성공":"입력실패");
				break;

			case 6:
				flag = false;
				System.out.println("프로그램을 종료합니다.");
				break;
			}

		}
	}

}
