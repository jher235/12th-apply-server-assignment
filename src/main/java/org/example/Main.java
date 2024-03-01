package org.example;

import java.util.*;

public class Main {
//    static Scanner sc = new Scanner(System.in);

//    문의내용 저장을 위한 맵
    public static Map<String, ArrayList<String>> listMap = new HashMap<>();

    public static Room A = new Room();
    public static Room B = new Room();
    public static Room C = new Room();

//    스터디 룸 객체
    public static class Room{
        String [] time = new String[13];

        //기본 생성자 오버라이드
        public Room() {
            for(int i=0;i<13;i++){
                this.time[i]="X";
            }
        }

        //예약 확인 메소드. 시작 시간, 종료 시간을 받아와서 기존 예약시간과 겹치는지 확인 후 boolean으로 전달
        public boolean checkReserve(int s, int e){
            for (int i=s;i<=e;i++){
                if(this.time[i].equals("O")) {
                    return false;
                }
            }
            return true;
        }

        //예약 메소드
        public void reserve(int s, int e){
            for (int i=s;i<=e;i++){
                this.time[i]="O";
            }
        }


    }

//    문의하기
    private static void inquire(){
        String id;
        String content;
        ArrayList<String> arrayList = null;

        Scanner sc = new Scanner(System.in);

        System.out.println("----- 문의 남기기 -----");
        System.out.print("문의 아이디: ");
        id = sc.nextLine();
        System.out.print("문의내용: ");
        content = sc.nextLine();


//        id, 문의내용 유효한지 확인
        if((id!=null&&id.length()>0) && (content!=null&&content.length()>0)) {

//            이미 존재하던 id라면 문의내용을 기존 것에 추가하도록 함
            if (listMap.containsKey(id)) {
                arrayList = listMap.get(id);    //기존 ArrayList를 불러 온 후 내용을 추가해서 저장
                arrayList.add(content);
                listMap.put(id, arrayList);
            } else {
                arrayList = new ArrayList<>();  //ArrayList를 새로 만든 후 내용을 추가해서 저장
                arrayList.add(content);
                listMap.put(id,arrayList);
            }

            System.out.println();
            System.out.println("문의가 저장되었습니다!");
        }else{
            System.out.println("유효하지 않은 id값 혹은 문의내용입니다.");
        }

    }


//  문의 리스트 보기
    private static void getInquire(){
        System.out.println("----- 문의 리스트 보기 -----");
        for(String id : listMap.keySet()){  //id 값들을 가져옴
            System.out.println("문의 아이디: "+id);
            listMap.get(id).stream().forEach(content->System.out.println("문의 내용: "+content));   //해당 id의 문의 내용을 전부 출력해줌
            System.out.println();
        }
    }



//    예약
    private static void reserve(){
        //방을 저장할 변수
        String r;
        //시간대를 저장할 변수
        int start, end;
        Scanner sc = new Scanner(System.in);

        System.out.println("----- 스터디룸 예약 -----");
        System.out.print("예약할 스터디룸: ");
        r = sc.nextLine();

        //배열 인덱스값(0부터 시작)과 시간 값(10부터 시작)을 맞추기 위해 -10 적용
        System.out.print("사용 시작 시간: ");
        start = Integer.valueOf(sc.nextLine()) -10;

        System.out.print("사용 종료 시간: ");
        end = Integer.valueOf(sc.nextLine()) -10;

//        유효한 시간대인지 확인
        if(start<=end && (0<=start && end<=12)) {
            //스터디 룸 객체마다 예약 가능한지 확인 후 가능하다면 예약
            if (r.equals("A")) {
                if (!A.checkReserve(start, end)) {
                    System.out.println("스터디룸 " + r + "는 해당 시간에 이미 예약되어 있습니다.");
                    System.out.println("예약에 실패했습니다.");
                    return;
                }
                A.reserve(start, end);

            } else if (r.equals("B")) {
                if (!B.checkReserve(start, end)) {
                    System.out.println("스터디룸 " + r + "는 해당 시간에 이미 예약되어 있습니다.");
                    System.out.println("예약에 실패했습니다.");
                    return;
                }
                B.reserve(start, end);

            } else if (r.equals("C")) {
                if (!C.checkReserve(start, end)) {
                    System.out.println("스터디룸 " + r + "는 해당 시간에 이미 예약되어 있습니다.");
                    System.out.println("예약에 실패했습니다.");
                    return;
                }
                C.reserve(start, end);
            } else {
                System.out.println("스터디룸 " + r + "는(은) 존재하지 않습니다.");
                System.out.println("예약에 실패했습니다.");
                return;
            }
        }else {
            System.out.println("유효하지 않은 시간대입니다.");
            System.out.println("예약에 실패했습니다.");
        }

    }

//   방 조회
    private static void checkRoom(){

        System.out.println("----- 예약 현황 -----");
        System.out.println("        | A | B | C |");

        for (int i =0;i<13;i++){
            if(i<3){
                System.out.println("오전 "+"1"+i+"시| "+A.time[i]+" | "+B.time[i]+" | "+C.time[i]+" |");
            } else{
                System.out.println("오후 "+(i<12?"0"+(i-2):i-2)+"시| "+A.time[i]+" | "+B.time[i]+" | "+C.time[i]+" |");
            }
        }
    }

//    작업 리스트
    private static void workList(){
        System.out.println();
        System.out.println("----- 작업 -----");
        System.out.println();
        System.out.println("1. 스터디룸 예약");
        System.out.println("2. 예약 현황 조회");
        System.out.println("3. 문의 남기기");
        System.out.println("4. 문의 리스트 보기");
        System.out.println("5. 프로그램 종료");
        System.out.println();
        System.out.print("작업을 선택하세요:");
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        while (true){
            workList();
            int num = sc.nextInt();
            System.out.println();
            if (num==1){
                reserve();
            }else if (num==2){
                checkRoom();
            } else if (num==3) {
                inquire();
            } else if (num==4) {
                getInquire();
            } else if (num==5) {
                break;
            }

        }

        System.out.println("프로그램을 종료합니다...");

    }
}
